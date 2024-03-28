package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnderecoRepository {
    
    private static final String TABELA = "endereco";
    private static final String[] COLUNAS = {"id", "logradouro", "numero", "complemento", "bairro", "cidade", "uf", "cep"};
    
    public Endereco inserir(Connection connection, Endereco endereco) throws SQLException {
        String query = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[1] + ", " + COLUNAS[2] + ", " + COLUNAS[3] + ", " + COLUNAS[4] + ", " + COLUNAS[5] + ", " + COLUNAS[6] + ", " + COLUNAS[7] + ") "
            + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getNumero());
            ps.setString(3, endereco.getComplemento());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getUf());
            ps.setString(7, endereco.getCep());
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) {
                    endereco.setId(rs.getLong("id"));
                }
                return endereco;
            }
        }
    }
    
    public void atualizar(Connection connection, Endereco endereco) throws SQLException {
        String query = ""
            + "UPDATE " + TABELA + " "
            + "SET " + COLUNAS[1] + " = ?, " + COLUNAS[2] + " = ?, " + COLUNAS[3] + " = ?, " + COLUNAS[4] + " = ?, " + COLUNAS[5] + " = ?, " + COLUNAS[6] + " = ?, " + COLUNAS[7] + " = ? "
            + "WHERE id = ?;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getNumero());
            ps.setString(3, endereco.getComplemento());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getUf());
            ps.setString(7, endereco.getCep());
            ps.setLong(8, endereco.getId());
            ps.execute();
        }
    }
}
