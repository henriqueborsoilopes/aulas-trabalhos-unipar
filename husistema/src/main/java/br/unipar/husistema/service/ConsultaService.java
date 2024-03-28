package br.unipar.husistema.service;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.ConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.infraestructor.ConnectionFactory;
import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.mapper.ConsultaMapper;
import br.unipar.husistema.repository.ConsultaRepository;
import br.unipar.husistema.repository.MedicoRepository;
import br.unipar.husistema.service.validation.ValidacaoService;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConsultaService {
    
    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService() {
        this.consultaRepository = new ConsultaRepository();
        this.medicoRepository = new MedicoRepository();
    }
    
    public ConsultaDTO inserir(InserirConsultaDTO consultaDTO) throws Exception {
        ValidacaoService.validarConsulta(consultaDTO);
        Consulta consulta = new Consulta();
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            consulta = consultaRepository.inserir(connection, consulta);
            connection.commit();
            return ConsultaMapper.getDTO(consulta);
        } catch (Exception e) {
            connection.rollback();
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public boolean cansultarAgendamentoPaciente(LocalDate data, Long id) {
        Paciente paciente = new Paciente();
        paciente.setId(id);
        Connection connection = ConnectionFactory.getConnection();
        try {
            return consultaRepository.cansultarAgendamentoPaciente(connection, data, paciente);
        } catch (Exception e) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public boolean cansultarAgendamentoMedico(LocalDateTime data, Long id) {
        Medico medico = new Medico();
        medico.setId(id);
        Connection connection = ConnectionFactory.getConnection();
        try {
            return consultaRepository.cansultarAgendamentoMedico(connection, data, medico);
        } catch (Exception e) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void cancelar(Long id, CancelarConsultaDTO dto) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Consulta consulta = consultaRepository.acharPorId(connection, id);
            consulta.setId(id);
            connection.setAutoCommit(false);
            consultaRepository.atualizar(connection, consulta);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
}
