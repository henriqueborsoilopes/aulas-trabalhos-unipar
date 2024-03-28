package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.entity.Endereco;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.entity.Pessoa;
import br.unipar.husistema.service.ConsultaService;
import br.unipar.husistema.service.MedicoService;
import br.unipar.husistema.service.PacienteService;
import br.unipar.husistema.service.exception.Campo;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidacaoService {

    private static List<Campo> campos;
    private static ConsultaService consultaService;
    private static MedicoService medicoService;
    private static PacienteService pacienteService;

    public static void validarMedico(Medico medico) throws ValidacaoExcecao {
        campos = new ArrayList<>();
        
        if (medico == null) {
            campos.add(new Campo("Todos", "Devem ser preenchido!"));
            throw new ValidacaoExcecao(campos);
        }

        if (medico.getCrm().isEmpty() || medico.getCrm().isBlank()) {
            campos.add(new Campo("CRM", "Deve ser preenchido!"));
        }

        if (medico.getTipoEspecialidade() == null) {
            campos.add(new Campo("Especialidade", "Deve ser preenchido!"));
        }

        validarPessoa(medico);

        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }

    public static void validarPaciente(Paciente paciente) throws ValidacaoExcecao {
        campos = new ArrayList<>();
        
        if (paciente == null) {
            campos.add(new Campo("Todos", "Devem ser preenchido!"));
            throw new ValidacaoExcecao(campos);
        }

        if (paciente.getNome().isEmpty() || paciente.getNome().isBlank()) {
            campos.add(new Campo("Nome", "Deve ser preenchido!"));
        }

        if (paciente.getEmail().isEmpty() || paciente.getEmail().isBlank()) {
            campos.add(new Campo("E-mail", "Deve ser preenchido!"));
        }

        validarPessoa(paciente);

        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }
    
    public static void validarConsulta(InserirConsultaDTO dto) throws ValidacaoExcecao {
        consultaService = new ConsultaService();
        medicoService = new MedicoService();
        pacienteService = new PacienteService();
        campos = new ArrayList<>();
        
        if (dto == null) {
            campos.add(new Campo("Todos", "Devem ser preenchido!"));
            throw new ValidacaoExcecao(campos);
        }
        
        if (dto.getDataConsuta().getMinute() < LocalDateTime.now().getMinute() + 30) {
            campos.add(new Campo("Horário da Consulta", "O agendamento deve ser feito 30 minutos de antecedência!"));
        }
        
        if (dto.getDataConsuta().getHour() < 7 || dto.getDataConsuta().getHour() > 18) {
            campos.add(new Campo("Horário da Consulta", "O horário deve ser estar entre às 7 e 18 horas!"));
        }
        
        try {
            if (!pacienteService.acharPorId(dto.getPacienteId()).isAtivo()) {
                campos.add(new Campo("Paciente", "Paciente inativo!"));
            }
        } catch (Exception e) {
            campos.add(new Campo("Paciente", "Paciente não contrado!"));
        }
        
        if (consultaService.cansultarAgendamentoPaciente(dto.getDataConsuta().toLocalDate(), dto.getPacienteId())) {
            campos.add(new Campo("Paciente", "Paciente já possuí agendamento na data informada!"));
        }
        
        if (dto.getMedicoId()!= null) {
            if (consultaService.cansultarAgendamentoMedico(dto.getDataConsuta(), dto.getMedicoId())) {
            campos.add(new Campo("Médico", "Médico já possuí agendamento na data informada!"));
            }
            
            try {
                if (!medicoService.acharPorId(dto.getMedicoId()).isAtivo()) {
                    campos.add(new Campo("Médico", "Médico inativo!"));
                }
            } catch (Exception e) {
                campos.add(new Campo("Médico", "Médico não contrado!"));
            }
        } else {
            Long id = medicoService.acharMedicoDisponivel();
            if (id == null) {
                campos.add(new Campo("Médico", "Não tem médico disponível!"));
            } else {
                dto.setMedicoId(id);
            }
        }
    }
    
    public void validarCancelamentoConsulta(Consulta consulta) throws ValidacaoExcecao {
        campos = new ArrayList<>();
        
        if (consulta == null) {
            campos.add(new Campo("Todos", "Devem ser preenchido!"));
            throw new ValidacaoExcecao(campos);
        }
        
        if (consulta.getDataCancelamento().getHour() < 23) {
            campos.add(new Campo("Data Cancelamento", "O cancelamento deve ocorrer com antecedência mínima de 24 horas!"));
        }
        
        if (consulta.getDescriCancelamento().isBlank() || consulta.getDescriCancelamento().isEmpty()) {
            campos.add(new Campo("Descrição do Cancelamento", "É obrigatório o preenchimento da descrição do cancelamento!"));
        }
    }

    private static void validarPessoa(Pessoa pessoa) {
        if (pessoa.getNome().isEmpty() || pessoa.getNome().isBlank()) {
            campos.add(new Campo("Nome", "Deve ser preenchido!"));
        }
        
        if (pessoa.getEmail().isEmpty() || pessoa.getEmail().isBlank()) {
            campos.add(new Campo("E-mail", "Deve ser preenchido!"));
        }
        
        if (pessoa.getTelefone().isEmpty() || pessoa.getTelefone().isBlank()) {
            campos.add(new Campo("Telefone", "Deve ser preenchido!"));
        }
        
        validarEndereco(pessoa.getEndereco());
    }
    
    private static void validarEndereco(Endereco endereco) {
        if (endereco.getLogradouro().isEmpty() || endereco.getLogradouro().isBlank()) {
            campos.add(new Campo("Logradouro", "Deve ser preenchido!"));
        }
        
        if (endereco.getBairro().isEmpty() || endereco.getBairro().isBlank()) {
            campos.add(new Campo("Bairro", "Deve ser preenchido!"));
        }
        
        if (endereco.getCidade().isEmpty() || endereco.getCidade().isBlank()) {
            campos.add(new Campo("Cidade", "Deve ser preenchido!"));
        }
        
        if (endereco.getUf().isEmpty() || endereco.getUf().isBlank()) {
            campos.add(new Campo("UF", "Deve ser preenchido!"));
        }
        
        if (endereco.getCep().isEmpty() || endereco.getCep().isBlank()) {
            campos.add(new Campo("CEP", "Deve ser preenchido!"));
        }
    }
}
