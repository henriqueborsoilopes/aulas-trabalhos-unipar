package br.unipar.husistema.service;

import br.unipar.husistema.dto.MedicoDTO;
import br.unipar.husistema.factory.ConnectionFactory;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.mapper.MedicoMapper;
import br.unipar.husistema.repository.EnderecoRepository;
import br.unipar.husistema.repository.MedicoRepository;
import br.unipar.husistema.repository.PessoaRepository;
import br.unipar.husistema.service.exception.BancoDadosException;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import br.unipar.husistema.service.validation.ValidacaoService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MedicoService {
    
    private final MedicoRepository medicoRepository;
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final MedicoMapper medicoMapper;

    public MedicoService() {
        this.medicoRepository = new MedicoRepository();
        this.pessoaRepository = new PessoaRepository();
        this.enderecoRepository = new EnderecoRepository();
        this.medicoMapper = new MedicoMapper();
    }
    
    public MedicoDTO inserir(MedicoDTO dto) throws BancoDadosException, ValidacaoExcecao {
        ValidacaoService.validarMedico(dto);
        Medico entity = medicoMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            entity.setEndereco(enderecoRepository.inserir(connection, entity.getEndereco()));
            entity.setId(pessoaRepository.inserir(connection, entity).getId());
            medicoRepository.inserir(connection, entity);
            connection.commit();
            return medicoMapper.getDTO(entity);
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
    
    public Long acharMedicoDisponivel() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return medicoRepository.acharMedicoDisponivel(connection);
        } catch (SQLException e) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public Medico acharPorId(Long id) throws BancoDadosException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return medicoRepository.acharPorId(connection, id);
        } catch (SQLException e) {
            throw new BancoDadosException("Falha na conexão");
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public List<MedicoDTO> acharTodos() throws BancoDadosException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return medicoMapper.getLitDTO(medicoRepository.acharTodos(connection));
        } catch (SQLException e) {
            throw new BancoDadosException("Falha na conexão");
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void atualizar(Long id, MedicoDTO dto) throws BancoDadosException, ValidacaoExcecao {
        ValidacaoService.validarMedico(dto);
        Medico entity = medicoMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            entity.setId(id);
            connection.setAutoCommit(false);
            enderecoRepository.atualizar(connection, entity.getEndereco());
            pessoaRepository.atualizar(connection, entity);
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
    
    public void inativar(Long id) throws BancoDadosException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            pessoaRepository.inativar(connection, id);
        } catch (SQLException e) {
            throw new BancoDadosException("Falha na conexão");
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
}
