package br.unipar.husistema.service;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.ConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.factory.ConnectionFactory;
import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.mapper.ConsultaMapper;
import br.unipar.husistema.repository.ConsultaRepository;
import br.unipar.husistema.service.validation.ValidacaoService;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConsultaService {
    
    private final ConsultaRepository consultaRepository;
    private final ConsultaMapper consultaMapper;

    public ConsultaService() {
        this.consultaRepository = new ConsultaRepository();
        this.consultaMapper = new ConsultaMapper();
    }
    
    public ConsultaDTO inserir(InserirConsultaDTO dto) throws Exception {
        ValidacaoService.validarConsulta(dto);
        Consulta consulta = consultaMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            consulta = consultaRepository.inserir(connection, consulta);
            connection.commit();
            return consultaMapper.getDTO(consulta);
        } catch (Exception e) {
            connection.rollback();
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public boolean cansultarAgendamentoPaciente(LocalDate data, Long id_paciente) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return consultaRepository.cansultarAgendamentoPaciente(connection, data, id_paciente);
        } catch (Exception e) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public boolean cansultarAgendamentoMedico(LocalDateTime data, Long id_medico) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return consultaRepository.cansultarAgendamentoMedico(connection, data, id_medico);
        } catch (Exception e) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void cancelar(Long id, CancelarConsultaDTO dto) throws Exception {
        Consulta consulta = consultaMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            consultaRepository.cancelar(connection, consulta);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
}
