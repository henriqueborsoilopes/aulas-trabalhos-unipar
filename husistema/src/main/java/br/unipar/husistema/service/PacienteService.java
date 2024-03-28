package br.unipar.husistema.service;

import br.unipar.husistema.dto.PacienteDTO;
import br.unipar.husistema.factory.ConnectionFactory;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.mapper.PacienteMapper;
import br.unipar.husistema.repository.EnderecoRepository;
import br.unipar.husistema.repository.PacienteRepository;
import br.unipar.husistema.repository.PessoaRepository;
import br.unipar.husistema.service.exception.BancoDadosException;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import br.unipar.husistema.service.validation.ValidacaoService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PacienteService {
    
    private final PacienteRepository pacienteRepository;
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteService() {
        this.pacienteRepository = new PacienteRepository();
        this.pessoaRepository = new PessoaRepository();
        this.enderecoRepository = new EnderecoRepository();
        this.pacienteMapper = new PacienteMapper();
    }
    
    public PacienteDTO inserir(PacienteDTO dto) throws BancoDadosException, ValidacaoExcecao {
        ValidacaoService.validarPaciente(dto);
        Paciente paciente = pacienteMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            paciente.setEndereco(enderecoRepository.inserir(connection, paciente.getEndereco()));
            paciente.setId(pessoaRepository.inserir(connection, paciente).getId());
            pacienteRepository.inserir(connection, paciente);
            connection.commit();
            return pacienteMapper.getDTO(paciente);
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
    
    public PacienteDTO acharPorId(Long id) throws BancoDadosException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return pacienteMapper.getDTO(pacienteRepository.acharPorId(connection, id));
        } catch (SQLException e) {
            throw new BancoDadosException("Falha na conexão");
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public List<PacienteDTO> acharTodos() throws BancoDadosException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return pacienteMapper.getLitDTO(pacienteRepository.acharTodos(connection));
        } catch (SQLException e) {
            throw new BancoDadosException("Falha na conexão");
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void atualizar(Long id, PacienteDTO dto) throws BancoDadosException, ValidacaoExcecao {
        ValidacaoService.validarPaciente(dto);
        Paciente paciente = pacienteMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            paciente.setId(id);
            connection.setAutoCommit(false);
            enderecoRepository.atualizar(connection, paciente.getEndereco());
            pessoaRepository.atualizar(connection, paciente);
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
