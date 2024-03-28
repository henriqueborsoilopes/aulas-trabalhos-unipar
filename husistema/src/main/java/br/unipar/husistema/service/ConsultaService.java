package br.unipar.husistema.service;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.ConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.factory.ConnectionFactory;
import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.mapper.ConsultaMapper;
import br.unipar.husistema.repository.ConsultaRepository;
import br.unipar.husistema.service.exception.BancoDadosException;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import br.unipar.husistema.service.validation.ValidacaoService;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConsultaService {
    
    private final ConsultaRepository consultaRepository;
    private final ConsultaMapper consultaMapper;

    public ConsultaService() {
        this.consultaRepository = new ConsultaRepository();
        this.consultaMapper = new ConsultaMapper();
    }
    
    public ConsultaDTO inserir(InserirConsultaDTO dto) throws BancoDadosException, ValidacaoExcecao {
        ValidacaoService.validarConsulta(dto);
        Consulta consulta = consultaMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            consulta = consultaRepository.inserir(connection, consulta);
            connection.commit();
            return consultaMapper.getDTO(consulta);
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
    
    public boolean cansultarAgendamentoPaciente(LocalDate data, Long id_paciente) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return consultaRepository.cansultarAgendamentoPaciente(connection, data, id_paciente);
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
    
    public void cancelar(Long id, CancelarConsultaDTO dto) throws BancoDadosException, ValidacaoExcecao {
        ValidacaoService.validarCancelamentoConsulta(dto);
        Consulta consulta = consultaMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            consultaRepository.cancelar(connection, consulta);
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
