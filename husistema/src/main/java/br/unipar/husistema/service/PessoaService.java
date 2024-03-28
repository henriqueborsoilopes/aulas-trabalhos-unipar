package br.unipar.husistema.service;

import br.unipar.husistema.factory.ConnectionFactory;
import br.unipar.husistema.repository.PessoaRepository;
import br.unipar.husistema.service.exception.BancoDadosException;
import java.sql.Connection;
import java.sql.SQLException;

public class PessoaService {
    
    private final PessoaRepository pessoaRepository;

    public PessoaService() {
        this.pessoaRepository = new PessoaRepository();
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
