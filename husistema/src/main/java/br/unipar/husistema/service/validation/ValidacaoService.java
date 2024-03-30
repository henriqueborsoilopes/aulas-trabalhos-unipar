package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.EnderecoDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.service.ConsultaService;
import br.unipar.husistema.service.MedicoService;
import br.unipar.husistema.service.PacienteService;
import br.unipar.husistema.service.exception.BancoDadosException;
import br.unipar.husistema.service.exception.Campo;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import br.unipar.husistema.util.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ValidacaoService {

    private static List<Campo> campos;
    private static ConsultaService consultaService;
    private static MedicoService medicoService;
    private static PacienteService pacienteService;

    public static void validarInsercaoMedico(InserirMedicoDTO dto) throws ValidacaoExcecao {
        campos = new ArrayList<>();
        
        if (dto.getCrm().isEmpty() || dto.getCrm().isBlank()) {
            campos.add(new Campo("CRM", "Deve ser preenchido!"));
        }

        if (dto.getTipoEspecialidade() == null) {
            campos.add(new Campo("Especialidade", "Deve ser preenchido!"));
        }
        
        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            campos.add(new Campo("Nome", "Deve ser preenchido!"));
        }

        if (dto.getEmail().isEmpty() || dto.getEmail().isBlank()) {
            campos.add(new Campo("E-mail", "Deve ser preenchido!"));
        }
        
        if (dto.getTelefone().isEmpty() || dto.getTelefone().isBlank()) {
            campos.add(new Campo("Telefone", "Deve ser preenchido!"));
        }
        
        validarEndereco(dto.getEndereco());

        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }

    public static void validarInsercaoPaciente(InserirPacienteDTO dto) throws ValidacaoExcecao {
        campos = new ArrayList<>();
        
        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            campos.add(new Campo("Nome", "Deve ser preenchido!"));
        }

        if (dto.getEmail().isEmpty() || dto.getEmail().isBlank()) {
            campos.add(new Campo("E-mail", "Deve ser preenchido!"));
        }
        
        if (dto.getTelefone().isEmpty() || dto.getTelefone().isBlank()) {
            campos.add(new Campo("Telefone", "Deve ser preenchido!"));
        }
        
        if (dto.getCpf().isEmpty() || dto.getCpf().isBlank()) {
            campos.add(new Campo("Telefone", "Deve ser preenchido!"));
        }
        
        validarEndereco(dto.getEndereco());

        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }
    
    public static void validarAtualizacaoPaciente(Long id, AtualizarPessoaDTO dto) throws ValidacaoExcecao {
        campos = new ArrayList<>();
        
        if (id == null) {
            campos.add(new Campo("Id", "Devem ser informado!"));
            throw new ValidacaoExcecao(campos);
        }

        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            campos.add(new Campo("Nome", "Deve ser preenchido!"));
        }
        
        if (dto.getTelefone().isEmpty() || dto.getTelefone().isBlank()) {
            campos.add(new Campo("Telefone", "Deve ser preenchido!"));
        }
        
        validarEndereco(dto.getEndereco());

        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }
    
    public static void validarConsulta(InserirConsultaDTO dto) throws ValidacaoExcecao, BancoDadosException {
        consultaService = new ConsultaService();
        medicoService = new MedicoService();
        pacienteService = new PacienteService();
        campos = new ArrayList<>();
        
        Calendar dataAgora = Calendar.getInstance();
        dataAgora.setTime(new Date());
        dataAgora.add(Calendar.MINUTE, 30);
        
        //Verificar se a data é do passado
        if (dto.dataConsulta().compareTo(new Date()) < 0) {
            campos.add(new Campo("Data da Consulta", "Data inválida!"));
        //Verificar se o dia é domingo
        } else if (dto.dataConsulta().getDay() == 0) {
            campos.add(new Campo("Data da Consulta", "Data inválida!"));
        //Verificar se a data da consulta foi marcada com antecedência
        } else if (dto.dataConsulta().compareTo(dataAgora.getTime()) < 0) {
            campos.add(new Campo("Horário da Consulta", "O agendamento deve ser feito 30 minutos de antecedência!"));
        //Verificar se o horário está entre 7 e 18 horas
        } else if (dto.dataConsulta().getHours() < 7 || (dto.dataConsulta().getHours() == 17 && dto.dataConsulta().getMinutes() > 0)) {
            campos.add(new Campo("Horário da Consulta", "O horário deve ser estar entre às 7 e 17 horas!"));
        }
        
        try {
            Paciente paciente = pacienteService.acharPorId(dto.getPacienteId());
            if (paciente == null) {
                campos.add(new Campo("Paciente", "Paciente não contrado!"));
            } else if (!paciente.isAtivo()) {
                campos.add(new Campo("Paciente", "Paciente inativo!"));
            } 
            
            if (consultaService.cansultarAgendamentoPaciente(dto.getPacienteId(), dto.dataConsulta())) {
                campos.add(new Campo("Paciente", "Paciente já possuí agendamento na data informada!"));
            }
            
            if (dto.getMedicoId()!= null) {
                Medico medico = medicoService.acharPorId(dto.getMedicoId());
                if (medico == null) {
                    campos.add(new Campo("Médico", "Médico não contrado!"));
                } else if (!medico.isAtivo()) {
                    campos.add(new Campo("Médico", "Médico inativo!"));
                }
                
                if (consultaService.cansultarAgendamentoMedico(dto.dataConsulta(), dto.getMedicoId())) {
                    campos.add(new Campo("Médico", "Médico já possuí agendamento na data informada!"));
                }
            } else {
                Long id = medicoService.acharMedicoDisponivel();
                if (id == null) {
                    campos.add(new Campo("Médico", "Não tem médico disponível!"));
                } else {
                    dto.setMedicoId(id);
                }
            }
        } catch (BancoDadosException e) {
            throw new BancoDadosException("Falha na conexão");
        }
        
        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }
    
    public static void validarCancelamentoConsulta(Long id_consulta, CancelarConsultaDTO dto) throws ValidacaoExcecao {
        campos = new ArrayList<>();
        
        if (dto == null) {
            campos.add(new Campo("Todos", "Devem ser preenchido!"));
            throw new ValidacaoExcecao(campos);
        }
        
        if (consultaService.cansultarDataConsulta(id_consulta).compareTo(new Date()) < 0) {
            campos.add(new Campo("Data Cancelamento", "O cancelamento deve ocorrer com antecedência mínima de 24 horas!"));
        }
        
        if (dto.getDescriCancelamento().isBlank() || dto.getDescriCancelamento().isEmpty()) {
            campos.add(new Campo("Descrição do Cancelamento", "É obrigatório o preenchimento da descrição do cancelamento!"));
        }
        
        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }
    
    private static void validarEndereco(EnderecoDTO dto) {
        if (dto.getLogradouro().isEmpty() || dto.getLogradouro().isBlank()) {
            campos.add(new Campo("Logradouro", "Deve ser preenchido!"));
        }
        
        if (dto.getBairro().isEmpty() || dto.getBairro().isBlank()) {
            campos.add(new Campo("Bairro", "Deve ser preenchido!"));
        }
        
        if (dto.getCidade().isEmpty() || dto.getCidade().isBlank()) {
            campos.add(new Campo("Cidade", "Deve ser preenchido!"));
        }
        
        if (dto.getUf().isEmpty() || dto.getUf().isBlank()) {
            campos.add(new Campo("UF", "Deve ser preenchido!"));
        }
        
        if (dto.getCep().isEmpty() || dto.getCep().isBlank()) {
            campos.add(new Campo("CEP", "Deve ser preenchido!"));
        }
    }
}
