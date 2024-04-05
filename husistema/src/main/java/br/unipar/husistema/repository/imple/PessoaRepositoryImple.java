package br.unipar.husistema.repository.imple;

import br.unipar.husistema.entity.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.unipar.husistema.repository.IPessoaRepository;
import br.unipar.husistema.service.connection.ConexaoBD;
import br.unipar.husistema.service.exception.BancoDadosExcecao;

public class PessoaRepositoryImple implements IPessoaRepository {
    
    private static final String TABELA = "pessoa";
    private static final String[] COLUNAS = {"id", "nome", "email", "telefone", "ativo", "id_endereco"};
    
    @Override
    public Pessoa inserir(Pessoa pessoa) {
        String query = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[1] + ", " + COLUNAS[2] + ", " + COLUNAS[3] + ", " + COLUNAS[4] + ", " + COLUNAS[5] + ") "
            + "VALUES (?, ?, ?, ?, ?);";
        
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setString(3, pessoa.getTelefone());
            ps.setBoolean(4, pessoa.isAtivo());
            ps.setLong(5, pessoa.getIdEndereco());
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pessoa.setId(rs.getLong(COLUNAS[0]));
                }
                return pessoa;
            } catch (SQLException e) {
                throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
            }
        } catch (SQLException e) {
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
    
    @Override
    public void atualizar(Pessoa pessoa) {
        String query = ""
            + "UPDATE " + TABELA + " "
            + "SET " + COLUNAS[1] + " = ?, " + COLUNAS[3] + " = ? "
            + "WHERE " + COLUNAS[0] + " = ?;";
        
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(query)) {
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getTelefone());
            ps.setLong(3, pessoa.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
    
    @Override
    public void inativar(Long id) {
        String query = ""
            + "UPDATE " + TABELA + " "
            + "SET " + COLUNAS[4] + " = ? "
            + "WHERE " + COLUNAS[0] + " = ?;";
        
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(query)) {
            ps.setBoolean(1, false);
            ps.setLong(2, id);
            ps.execute();
        } catch (SQLException e) {
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
}
