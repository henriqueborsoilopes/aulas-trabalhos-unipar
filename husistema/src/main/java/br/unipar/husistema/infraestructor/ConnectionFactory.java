package br.unipar.husistema.infraestructor;

import br.unipar.husistema.service.exception.ConexaoException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
    
    private static final String RESOURCE_NAME = "postgresResource";
    
     private static DataSource getDataSource() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup(RESOURCE_NAME);
    }
    
    public static Connection getConnection() throws ConexaoException {
        try {
            return getDataSource().getConnection();
        } catch (SQLException | NamingException ex) {
            throw new ConexaoException("Falha na conexão!");
        }
    }
}
