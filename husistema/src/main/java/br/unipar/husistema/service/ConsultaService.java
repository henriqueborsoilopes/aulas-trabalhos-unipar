package br.unipar.husistema.service;

import br.unipar.husistema.infraestructor.ConnectionFactory;
import br.unipar.husistema.model.Consulta;
import br.unipar.husistema.repository.ConsultaRepository;
import java.sql.Connection;

public class ConsultaService {
    
    private final ConsultaRepository consultaRepository;

    public ConsultaService() {
        this.consultaRepository = new ConsultaRepository();
    }
    
    public Consulta inserir(Consulta consulta) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            consulta = consultaRepository.inserir(connection, consulta);
            connection.commit();
            return consulta;
        } catch (Exception e) {
            connection.rollback();
            throw new Exception(e);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void atualizar(Long id, Consulta consulta) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        try {
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
