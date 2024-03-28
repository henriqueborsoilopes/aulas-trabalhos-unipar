package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PessoaRepository {
    
    private static final String TABELA = "pessoa";
    private static final String[] COLUNAS = {"id", "nome", "email", "telefone", "ativo", "id_endereco"};
    
    public Pessoa inserir(Connection connection, Pessoa pessoa) throws SQLException {
        String query = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[1] + ", " + COLUNAS[2] + ", " + COLUNAS[3] + ", " + COLUNAS[4] + ", " + COLUNAS[5] + ") "
            + "VALUES (?, ?, ?, ?, ?);";
        
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setString(3, pessoa.getTelefone());
            ps.setBoolean(4, pessoa.isAtivo());
            ps.setLong(5, pessoa.getEndereco().getId());
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pessoa.setId(rs.getLong(COLUNAS[0]));
                }
                return pessoa;
            }
        }
    }
    
    public void atualizar(Connection connection, Pessoa pessoa) throws SQLException {
        String query = ""
            + "UPDATE " + TABELA + " "
            + "SET " + COLUNAS[1] + " = ?, " + COLUNAS[3] + " = ? "
            + "WHERE " + COLUNAS[0] + " = ?;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getTelefone());
            ps.setLong(3, pessoa.getId());
            ps.execute();
        }
    }
    
    public void inativar(Connection connection, Long id) throws SQLException {
        String query = ""
            + "UPDATE " + TABELA + " "
            + "SET " + COLUNAS[4] + " = ? "
            + "WHERE " + COLUNAS[0] + " = ?;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, false);
            ps.setLong(2, id);
            ps.execute();
        }
    }
}
