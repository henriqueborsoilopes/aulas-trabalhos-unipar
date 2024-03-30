package br.unipar.husistema.service;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.factory.ConnectionFactory;
import br.unipar.husistema.mapper.ConsultaMapper;
import br.unipar.husistema.repository.ConsultaRepository;
import br.unipar.husistema.service.exception.BancoDadosException;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import br.unipar.husistema.service.validation.ValidacaoService;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ConsultaService {
    
    private final ConsultaRepository consultaRepository;
    private final ConsultaMapper consultaMapper;

    public ConsultaService() {
        this.consultaRepository = new ConsultaRepository();
        this.consultaMapper = new ConsultaMapper();
    }
    
    public Consulta inserir(InserirConsultaDTO dto) throws BancoDadosException, ValidacaoExcecao {
        ValidacaoService.validarConsulta(dto);
        Consulta consulta = consultaMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            consulta = consultaRepository.inserir(connection, consulta);
            connection.commit();
            return consulta;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                throw new BancoDadosException("Falha na conexão");
            } catch (SQLException ex) {
                throw new BancoDadosException("Falha na conexão");
            }
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public boolean cansultarAgendamentoPaciente(Long id_paciente, LocalDateTime data) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return consultaRepository.cansultarAgendamentoPaciente(connection, id_paciente, data);
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public boolean cansultarAgendamentoMedico(LocalDateTime data, Long id_medico) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return consultaRepository.cansultarAgendamentoMedico(connection, data, id_medico);
        } catch ( SQLException e) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public LocalDateTime cansultarDataConsulta(Long id_consulta) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return consultaRepository.cansultarDataConsulta(connection, id_consulta);
        } catch ( SQLException e) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void cancelar(Long id, CancelarConsultaDTO dto) throws BancoDadosException, ValidacaoExcecao {
        ValidacaoService.validarCancelamentoConsulta(id, dto);
        Consulta consulta = consultaMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            consultaRepository.cancelar(connection, id, consulta);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new BancoDadosException("Falha na conexão");
            } catch (SQLException ex) {
                throw new BancoDadosException("Falha na conexão");
            }
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
}
