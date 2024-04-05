package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.EnderecoDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.entity.enums.EspecialidadeEnum;
import br.unipar.husistema.factory.IServiceFactory;
import br.unipar.husistema.factory.imple.ServiceFactoryImple;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidarExcecao;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Validar {
    
    private static ValidarExcecao erro;
    private static IServiceFactory service;

    public static void consulta(InserirConsultaDTO dto) throws ValidarExcecao, BancoDadosExcecao {
        erro = new ValidarExcecao("Erro de validação");
        service = new ServiceFactoryImple();
        
        //Verificar se a data é do passado
        if (dto.getDataHorario().isBefore(LocalDateTime.now())) {
            erro.addErro("Data da Consulta", "Por favor, insira uma Data válida");
        //Verificar se o dia é domingo
        } else if (dto.getDataHorario().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            erro.addErro("Data da Consulta", "Por favor, insira uma Data válida");
        //Verificar se a data da consulta foi marcada com antecedência
        } else if (dto.getDataHorario().isAfter(LocalDateTime.now())
                && dto.getDataHorario().isBefore(LocalDateTime.now().plusMinutes(30))) {
            erro.addErro("Horário da Consulta", "Certifique-se de que o Horário da Consulta deve ser feito 30 minutos de antecedência!");
        //Verificar se o horário está entre 7 e 18 horas
        } else if (dto.getDataHorario().getHour() < 7 || (dto.getDataHorario().getHour() == 17 && 
                dto.getDataHorario().getMinute() > 0)) {
            erro.addErro("Horário da Consulta", "Certifique-se de que Horário da Consulta seja entre às 7 e 17 horas!");
        }
        
        Paciente paciente = service.getPacienteService().acharPorId(dto.getPacienteId());
        
        if (paciente == null) {
            erro.addErro("Paciente", "Paciente não contrado!");
        } else if (!paciente.isAtivo()) {
            erro.addErro("Paciente", "Paciente inativo!");
        } 

        if (service.getConsultaService().consultarAgendamentoPaciente(dto.getPacienteId(), dto.getDataHorario())) {
            erro.addErro("Paciente", "Paciente já possuí agendamento na Data informada!");
        }

        if (dto.getMedicoId()!= null) {
            Medico medico = service.getMedicoService().acharPorId(dto.getMedicoId());
            if (medico == null) {
                erro.addErro("Médico", "Médico não contrado!");
            } else if (!medico.isAtivo()) {
                erro.addErro("Médico", "Médico inativo!");
            }

            if (service.getConsultaService().consultarAgendamentoMedico(dto.getDataHorario(), dto.getMedicoId())) {
                erro.addErro("Médico", "Médico já possuí agendamento na Data informada!");
            }
        } else {
            Long id = service.getMedicoService().acharMedicoDisponivel(dto.getDataHorario());
            if (id == null) {
                erro.addErro("Médico", "Não tem médico disponível!");
            } else {
                dto.setMedicoId(id);
            }
        }
        
        if (!erro.getErros().isEmpty()) {
            throw erro;
        }
    }

    public static void cancelamentoConsulta(Long id_consulta, CancelarConsultaDTO dto) throws ValidarExcecao, BancoDadosExcecao {
        erro = new ValidarExcecao("Erro de validação");
        service = new ServiceFactoryImple();
        
        if (LocalDateTime.now().plusDays(1).isAfter(service.getConsultaService().consultarDataConsulta(id_consulta))) {
            erro.addErro("Data Cancelamento", "Certifique-se de que o a Data do Cancelamento seja feita com antecedência mínima de 24 horas!");
        }
        
        if (dto.getDescriCancelamento().isBlank() || dto.getDescriCancelamento().isEmpty()) {
            erro.addErro("Descrição do Cancelamento", "Por favor, insira uma Descrição.");
        }
        
        if (!erro.getErros().isEmpty()) {
            throw erro;
        }
    }
    
    public static void insercaoMedico(InserirMedicoDTO dto) throws ValidarExcecao {
        erro = new ValidarExcecao("Erro de validação");
        
        if (dto.getCrm().isEmpty() || dto.getCrm().isBlank()) {
            erro.addErro("CRM", "Por favor, insira um CRM.");
        }

        if (dto.getTipoEspecialidade() == null) {
            erro.addErro("Especialidade", "Por favor, insira uma Especialidade.");
        }
        
        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            erro.addErro("Nome", "Por favor, insira um Nome.");
        }

        if (dto.getEmail().isEmpty() || dto.getEmail().isBlank()) {
            erro.addErro("E-mail", "Por favor, insira um E-mail.");
        }
        
        if (dto.getTelefone().isEmpty() || dto.getTelefone().isBlank()) {
            erro.addErro("Telefone", "Por favor, insira um Telefone.");
        }
        
        if (EspecialidadeEnum.paraEnum(dto.getTipoEspecialidade()) == null) {
            erro.addErro("Especialidade", "Código inválido.");
        }
        
        validarEndereco(dto.getEndereco());

        if (!erro.getErros().isEmpty()) {
            throw erro;
        }
    }
    
    public static void insercaoPaciente(InserirPacienteDTO dto) throws ValidarExcecao {
        erro = new ValidarExcecao("Erro de validação");
        
        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            erro.addErro("Nome", "Por favor, insira um Nome.");
        }

        if (dto.getEmail().isEmpty() || dto.getEmail().isBlank()) {
            erro.addErro("E-mail", "Por favor, insira um E-mail.");
        }
        
        if (dto.getTelefone().isEmpty() || dto.getTelefone().isBlank()) {
            erro.addErro("Telefone", "Por favor, insira um Telefone.");
        }
        
        if (dto.getCpf().isEmpty() || dto.getCpf().isBlank()) {
            erro.addErro("CPF", "Por favor, insira um CPF.");
        }
        
        validarEndereco(dto.getEndereco());

        if (!erro.getErros().isEmpty()) {
            throw erro;
        }
    }

    public static void atualizacaoPaciente(Long id, AtualizarPessoaDTO dto) throws ValidarExcecao {
        erro = new ValidarExcecao("Erro de validação");
        
        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            erro.addErro("Nome", "Por favor, insira um Nome.");
        }
        
        if (dto.getTelefone().isEmpty() || dto.getTelefone().isBlank()) {
            erro.addErro("Telefone", "Por favor, insira um Telefone.");
        }
        
        validarEndereco(dto.getEndereco());

        if (!erro.getErros().isEmpty()) {
            throw erro;
        }
    }
    
    public static void inativacao(Long id_pessoa) throws ValidarExcecao, BancoDadosExcecao {
        erro = new ValidarExcecao("Erro de validação");
        service = new ServiceFactoryImple();
        
        if (service.getConsultaService().temConsultaAgendada(id_pessoa)) {
            erro.addErro("Inativar", "Por favor, cancelar a(s) consulta(s) em aberto.");
            throw erro;
        }
    }
    
    private static void validarEndereco(EnderecoDTO dto) {
        if (dto.getLogradouro().isEmpty() || dto.getLogradouro().isBlank()) {
            erro.addErro("Logradouro", "Por favor, insira um Logradouro.");
        }
        
        if (dto.getBairro().isEmpty() || dto.getBairro().isBlank()) {
            erro.addErro("Bairro", "Por favor, insira um Bairro.");
        }
        
        if (dto.getCidade().isEmpty() || dto.getCidade().isBlank()) {
            erro.addErro("Cidade", "Por favor, insira uma Cidade");
        }
        
        if (dto.getUf().isEmpty() || dto.getUf().isBlank()) {
            erro.addErro("UF", "Por favor, insira um UF.");
        }
        
        if (dto.getCep().isEmpty() || dto.getCep().isBlank()) {
            erro.addErro("CEP", "Por favor, insira um CEP.");
        }
    }
}
