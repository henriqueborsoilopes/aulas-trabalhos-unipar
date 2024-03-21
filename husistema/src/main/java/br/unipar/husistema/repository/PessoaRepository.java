package br.unipar.husistema.repository;

import br.unipar.husistema.model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PessoaRepository {
    
    private static final String[] COLUNAS = {"id", "nome", "email", "telefone", "id_endereco"};
        
    private static final String QUERY_INSERIR = ""
            + "INSERT INTO pessoa (nome, email, telefone, id_endereco) "
            + "VALUES (?, ?, ?, ?)";
    
    private static final String QUERY_ACHAR_POR_ID = ""
            + "SELECT * "
            + "FROM pessoa"
            + "WHERE id = ?";
    
    private static final String QUERY_ATUALIZAR = ""
            + "UPDATE pessoa "
            + "SET nome = ?, telefone = ? "
            + "WHERE id = ? ";
    
    private static final String QUERY_DELETAR = ""
            + "UPDATE pessoa "
            + "SET ativo = ? "
            + "WHERE id = ? ";
    
    private final EnderecoRepository endRepository = new EnderecoRepository();
    
    public Pessoa inserir(Connection connection, PreparedStatement ps, ResultSet rs, Pessoa pessoa) throws SQLException {
        pessoa.setEndereco(endRepository.inserir(connection, ps, rs, pessoa.getEndereco()));
        ps = connection.prepareStatement(QUERY_INSERIR, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, pessoa.getNome());
        ps.setString(2, pessoa.getEmail());
        ps.setString(3, pessoa.getTelefone());
        ps.setLong(4, pessoa.getEndereco().getId());
        rs = ps.getGeneratedKeys();
        pessoa.setId(rs.getLong(COLUNAS[0]));
        
        return pessoa;
    }
    
    public Pessoa acharPorId(Connection connection, PreparedStatement ps, ResultSet rs, Pessoa pessoa) throws SQLException {
        ps = connection.prepareStatement(QUERY_ACHAR_POR_ID);
        rs = ps.getResultSet();
        
        if (rs.next()) {
            pessoa.setId(rs.getLong(COLUNAS[0]));
            pessoa.setNome(rs.getString(COLUNAS[1]));
            pessoa.setEmail(rs.getString(COLUNAS[2]));
            pessoa.setTelefone(rs.getString(COLUNAS[3]));
            pessoa.setEndereco(endRepository.acharPorId(connection, ps, rs, rs.getLong(COLUNAS[4])));
        }
         return pessoa;
    }
    
    public void atualizar(Connection connection, PreparedStatement ps, Pessoa pessoa) throws SQLException {
        ps = connection.prepareStatement(QUERY_ATUALIZAR);
        ps.setString(1, pessoa.getNome());
        ps.setString(2, pessoa.getTelefone());
        ps.setLong(3, pessoa.getEndereco().getId());
        endRepository.atualizar(connection, ps, pessoa.getEndereco());
    }
    
    public void excluir(Connection connection, PreparedStatement ps, ResultSet rs, Long id, boolean ativo) throws SQLException {
        ps = connection.prepareStatement(QUERY_DELETAR);
        ps.setBoolean(1, ativo);
        ps.setLong(2, id);
    }
}
