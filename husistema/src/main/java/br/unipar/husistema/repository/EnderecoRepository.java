package br.unipar.husistema.repository;

import br.unipar.husistema.model.Endereco;
import br.unipar.husistema.service.exception.ConexaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnderecoRepository {
    
    private static final String[] COLUNAS = {"id", "logradouro", "numero", "complemento", "bairro", "cidade", "uf", "cep"};
        
    private static final String QUERY_INSERIR = ""
            + "INSERT INTO endereco (logradouro, numero, complemento, bairro, cidade, uf, cep) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String QUERY_ACHAR_POR_ID = ""
            + "SELECT * "
            + "FROM endereco"
            + "WHERE id = ?";
    
    private static final String QUERY_ATUALIZAR = ""
            + "UPDATE endereco "
            + "SET logradouro = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, cep = ? "
            + "WHERE id = ? ";
    
    public Endereco inserir(Connection connection, Endereco endereco) throws ConexaoException {
        try (PreparedStatement ps = connection.prepareStatement(QUERY_INSERIR, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getNumero());
            ps.setString(3, endereco.getComplemento());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getUf());
            ps.setString(7, endereco.getCep());
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    endereco.setId(rs.getLong("id"));
                }
            }
        } catch (SQLException e) {
            throw new ConexaoException("Conexão falhou!");
        }
        return endereco;
    }
    
    public Endereco acharPorId(Connection connection, PreparedStatement ps, ResultSet rs, Long id) throws SQLException {
        Endereco endereco = null;
        ps = connection.prepareStatement(QUERY_ACHAR_POR_ID);
        rs = ps.getResultSet();
        
        if (rs.next()) {
            endereco  = new Endereco(
                rs.getLong(COLUNAS[0]),
                rs.getString(COLUNAS[1]),
                rs.getString(COLUNAS[2]),
                rs.getString(COLUNAS[3]),
                rs.getString(COLUNAS[4]),
                rs.getString(COLUNAS[5]),
                rs.getString(COLUNAS[6]),
                rs.getString(COLUNAS[7])
            );
        }
         return endereco;
    }
    
    public void atualizar(Connection connection, PreparedStatement ps, Endereco endereco) throws SQLException {
        ps = connection.prepareStatement(QUERY_ATUALIZAR);
        ps.setString(1, endereco.getLogradouro());
        ps.setString(2, endereco.getNumero());
        ps.setString(3, endereco.getComplemento());
        ps.setString(4, endereco.getBairro());
        ps.setString(5, endereco.getCidade());
        ps.setString(6, endereco.getUf());
        ps.setString(7, endereco.getCep());
        ps.setLong(8, endereco.getId());
    }
}
