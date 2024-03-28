package br.unipar.husistema.service;

import br.unipar.husistema.dto.MedicoDTO;
import br.unipar.husistema.factory.ConnectionFactory;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.mapper.MedicoMapper;
import br.unipar.husistema.repository.EnderecoRepository;
import br.unipar.husistema.repository.MedicoRepository;
import br.unipar.husistema.repository.PessoaRepository;
import br.unipar.husistema.service.validation.ValidacaoService;
import java.sql.Connection;
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
    
    public MedicoDTO inserir(MedicoDTO dto) throws Exception {
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
        } catch (Exception e) {
            connection.rollback();
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public Long acharMedicoDisponivel() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return medicoRepository.acharMedicoDisponivel(connection);
        } catch (Exception e) {
            return null;
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public Medico acharPorId(Long id) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return medicoRepository.acharPorId(connection, id);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public List<MedicoDTO> acharTodos() throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return medicoMapper.getLitDTO(medicoRepository.acharTodos(connection));
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void atualizar(Long id, MedicoDTO dto) throws Exception {
        ValidacaoService.validarMedico(dto);
        Medico entity = medicoMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            entity.setId(id);
            connection.setAutoCommit(false);
            enderecoRepository.atualizar(connection, entity.getEndereco());
            pessoaRepository.atualizar(connection, entity);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void inativar(Long id) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        try {
            pessoaRepository.inativar(connection, id);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
}
