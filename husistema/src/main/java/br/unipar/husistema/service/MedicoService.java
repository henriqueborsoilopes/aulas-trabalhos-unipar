package br.unipar.husistema.service;

import br.unipar.husistema.infraestructor.ConnectionFactory;
import br.unipar.husistema.model.Medico;
import br.unipar.husistema.repository.EnderecoRepository;
import br.unipar.husistema.repository.MedicoRepository;
import br.unipar.husistema.repository.PessoaRepository;
import br.unipar.husistema.service.validation.ValidacaoService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MedicoService {
    
    private final MedicoRepository medicoRepository;
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;

    public MedicoService() {
        this.medicoRepository = new MedicoRepository();
        this.pessoaRepository = new PessoaRepository();
        this.enderecoRepository = new EnderecoRepository();
    }
    
    public Medico inserir(Medico medico) throws Exception {
        ValidacaoService.validarMedico(medico);
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            medico.setEndereco(enderecoRepository.inserir(connection, medico.getEndereco()));
            medico.setId(pessoaRepository.inserir(connection, medico).getId());
            medicoRepository.inserir(connection, medico);
            connection.commit();
            return medico;
        } catch (Exception e) {
            connection.rollback();
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public List<Medico> acharTodos() throws Exception {
        List<Medico> medicos = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            return medicoRepository.acharTodos(connection);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void atualizar(Long id, Medico medico) throws Exception {
        ValidacaoService.validarMedico(medico);
        Connection connection = ConnectionFactory.getConnection();
        try {
            medico.setId(id);
            connection.setAutoCommit(false);
            enderecoRepository.atualizar(connection, medico.getEndereco());
            pessoaRepository.atualizar(connection, medico);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void excluir(Long id) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        try {
            pessoaRepository.excluir(connection, id);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
}
