package br.unipar.husistema.service;

import br.unipar.husistema.infraestructor.ConnectionFactory;
import br.unipar.husistema.repository.PessoaRepository;
import java.sql.Connection;

public class PessoaService {
    
    private final PessoaRepository pessoaRepository;

    public PessoaService() {
        this.pessoaRepository = new PessoaRepository();
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
