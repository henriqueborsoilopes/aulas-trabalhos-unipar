package br.unipar.husistema.factory;

import br.unipar.husistema.service.exception.BancoDadosExcecao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
    
    private static final String RESOURCE_NAME = "postgresResource";
    private static Connection connection = null;
    private static boolean manterConexao;
    private static boolean autoCommit;
    
    public static void abrirConexao() throws BancoDadosExcecao {
        try {
            connection = getDataSource().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
    
    public static Connection getConexao() throws SQLException {
        connection.setAutoCommit(autoCommit);
        return connection;
    }
    
    public static void manterConexaoAberta(boolean manter) {
        manterConexao = manter;
    }
    
    public static void autoCommit(boolean escolha) {
        autoCommit = escolha;
    }
    
    public static void commit() throws BancoDadosExcecao {
        try {
            connection.commit();
        } catch (SQLException ex) {
             try {
                connection.rollback();
            } catch (SQLException e) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
                throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
            }
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
    
    public static void fecharConexao() throws BancoDadosExcecao {
        try {
            if (connection != null && !manterConexao) {
                connection.close();
            }  
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
    
    private static DataSource getDataSource() throws BancoDadosExcecao {
        try {
            Context c = new InitialContext();
            return (DataSource) c.lookup(RESOURCE_NAME);
        } catch (NamingException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
}
