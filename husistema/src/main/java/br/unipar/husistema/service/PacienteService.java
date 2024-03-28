package br.unipar.husistema.service;

import br.unipar.husistema.infraestructor.ConnectionFactory;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.repository.EnderecoRepository;
import br.unipar.husistema.repository.PacienteRepository;
import br.unipar.husistema.repository.PessoaRepository;
import br.unipar.husistema.service.validation.ValidacaoService;
import java.sql.Connection;
import java.util.List;

public class PacienteService {
    
    private final PacienteRepository pacienteRepository;
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;

    public PacienteService() {
        this.pacienteRepository = new PacienteRepository();
        this.pessoaRepository = new PessoaRepository();
        this.enderecoRepository = new EnderecoRepository();
    }
    
    public Paciente inserir(Paciente paciente) throws Exception {
        ValidacaoService.validarPaciente(paciente);
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            paciente.setEndereco(enderecoRepository.inserir(connection, paciente.getEndereco()));
            paciente.setId(pessoaRepository.inserir(connection, paciente).getId());
            pacienteRepository.inserir(connection, paciente);
            connection.commit();
            return paciente;
        } catch (Exception e) {
            connection.rollback();
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public Paciente acharPorId(Long id) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return pacienteRepository.acharPorId(connection, id);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public List<Paciente> acharTodos() throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        try {
            return pacienteRepository.acharTodos(connection);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void atualizar(Long id, Paciente paciente) throws Exception {
        ValidacaoService.validarPaciente(paciente);
        Connection connection = ConnectionFactory.getConnection();
        try {
            paciente.setId(id);
            connection.setAutoCommit(false);
            enderecoRepository.atualizar(connection, paciente.getEndereco());
            pessoaRepository.atualizar(connection, paciente);
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
