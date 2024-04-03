package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.factory.imple.ServiceFactoryImple;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.Campo;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import br.unipar.husistema.factory.IServiceFactory;

public class ConsultaValidacao {

    private static IServiceFactory service;
    
    private static List<Campo> campos;
    
    public static void validarConsulta(InserirConsultaDTO dto) throws ValidacaoExcecao, BancoDadosExcecao {
        service = new ServiceFactoryImple();
        campos = new ArrayList<>();
        
        //Verificar se a data é do passado
        if (dto.getDataHorario().isBefore(LocalDateTime.now())) {
            campos.add(new Campo("Data da Consulta", "Por favor, insira uma Data válida"));
        //Verificar se o dia é domingo
        } else if (dto.getDataHorario().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            campos.add(new Campo("Data da Consulta", "Por favor, insira uma Data válida"));
        //Verificar se a data da consulta foi marcada com antecedência
        } else if (dto.getDataHorario().isAfter(LocalDateTime.now())
                && dto.getDataHorario().isBefore(LocalDateTime.now().plusMinutes(30))) {
            campos.add(new Campo("Horário da Consulta", "Certifique-se de que o Horário da Consulta deve ser feito 30 minutos de antecedência!"));
        //Verificar se o horário está entre 7 e 18 horas
        } else if (dto.getDataHorario().getHour() < 7 || (dto.getDataHorario().getHour() == 17 && 
                dto.getDataHorario().getMinute() > 0)) {
            campos.add(new Campo("Horário da Consulta", "Certifique-se de que Horário da Consulta seja entre às 7 e 17 horas!"));
        }
        
        Paciente paciente = service.getPacienteService().acharPorId(dto.getPacienteId());
        
        if (paciente == null) {
            campos.add(new Campo("Paciente", "Paciente não contrado!"));
        } else if (!paciente.isAtivo()) {
            campos.add(new Campo("Paciente", "Paciente inativo!"));
        } 

        if (service.getConsultaService().cansultarAgendamentoPaciente(dto.getPacienteId(), dto.getDataHorario())) {
            campos.add(new Campo("Paciente", "Paciente já possuí agendamento na Data informada!"));
        }

        if (dto.getMedicoId()!= null) {
            Medico medico = service.getMedicoService().acharPorId(dto.getMedicoId());
            if (medico == null) {
                campos.add(new Campo("Médico", "Médico não contrado!"));
            } else if (!medico.isAtivo()) {
                campos.add(new Campo("Médico", "Médico inativo!"));
            }

            if (service.getConsultaService().cansultarAgendamentoMedico(dto.getDataHorario(), dto.getMedicoId())) {
                campos.add(new Campo("Médico", "Médico já possuí agendamento na Data informada!"));
            }
        } else {
            Long id = service.getMedicoService().acharMedicoDisponivel(dto.getDataHorario());
            if (id == null) {
                campos.add(new Campo("Médico", "Não tem médico disponível!"));
            } else {
                dto.setMedicoId(id);
            }
        }
        
        if (!campos.isEmpty()) {
            System.out.println("br.unipar.husistema.service.validation.ConsultaValidacao.validarConsulta() "+ campos.get(0));
            throw new ValidacaoExcecao(campos);
        }
    }
    
    public static void validarCancelamentoConsulta(Long id_consulta, CancelarConsultaDTO dto) throws ValidacaoExcecao, BancoDadosExcecao {
        service = new ServiceFactoryImple();
        campos = new ArrayList<>();
        
        if (LocalDateTime.now().plusDays(1).isAfter(service.getConsultaService().cansultarDataConsulta(id_consulta))) {
            campos.add(new Campo("Data Cancelamento", "Certifique-se de que o a Data do Cancelamento seja feita com antecedência mínima de 24 horas!"));
        }
        
        if (dto.getDescriCancelamento().isBlank() || dto.getDescriCancelamento().isEmpty()) {
            campos.add(new Campo("Descrição do Cancelamento", "Por favor, insira uma Descrição."));
        }
        
        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }
}
