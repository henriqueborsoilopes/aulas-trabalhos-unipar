package br.unipar.husistema.service.connection;

import br.unipar.husistema.service.exception.BancoDadosExcecao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexaoBD {
    
    private static final String RESOURCE_NAME = "postgresResource";
    private static Connection conexao = null;
    private static boolean manterConexao = false;
    private static boolean autoCommit;
    
    public static void abrirConexao() throws BancoDadosExcecao {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = getDataSource().getConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
    
    public static Connection getConexao() throws SQLException {
        conexao.setAutoCommit(autoCommit);
        return conexao;
    }
    
    public static void manterConexaoAberta(boolean manter) {
        manterConexao = manter;
    }
    
    public static void autoCommit(boolean escolha) {
        autoCommit = escolha;
    }
    
    public static void commit() throws BancoDadosExcecao {
        try {
            conexao.commit();
        } catch (SQLException ex) {
             try {
                conexao.rollback();
            } catch (SQLException e) {
                Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, e);
                throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
            }
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
    
    public static void fecharConexao() throws BancoDadosExcecao {
        try {
            if (conexao != null && !manterConexao) {
                conexao.close();
            }  
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
    
    private static DataSource getDataSource() throws BancoDadosExcecao {
        try {
            Context c = new InitialContext();
            return (DataSource) c.lookup(RESOURCE_NAME);
        } catch (NamingException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
}
