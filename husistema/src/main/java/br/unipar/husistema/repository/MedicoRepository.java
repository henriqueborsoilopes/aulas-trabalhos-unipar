package br.unipar.husistema.repository;

import br.unipar.husistema.infraestructor.ConnectionFactory;
import br.unipar.husistema.model.Medico;
import br.unipar.husistema.service.exception.ConexaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {
    
    private static final String[] COLUNAS = {"id_pessoa", "crm", "cod_especialidade"};
        
    private static final String QUERY_INSERIR = ""
            + "INSERT INTO medico (id_pessoa, crm, cod_especialidade) "
            + "VALUES (?, ?, ?)";
    
    private static final String QUERY_ACHAR_POR_ID = ""
            + "SELECT * "
            + "FROM medico "
            + "WHERE id_pessoa = ?";
    
    private static final String QUERY_ACHARTODOS = ""
            + "SELECT * "
            + "FROM medico ";
    
    private final PessoaRepository pessoaRepository = new PessoaRepository();
    
    public Medico inserir(Medico medico) throws ConexaoException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            try {
                connection.setAutoCommit(true);
                medico.setId(pessoaRepository.inserir(connection, medico).getId());
                try (PreparedStatement ps = connection.prepareStatement(QUERY_INSERIR)) {
                    ps.setLong(1, medico.getId());
                    ps.setString(2, medico.getCrm());
                    ps.setInt(3, medico.getEspecialidade());
                    ps.execute();
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new ConexaoException("Conexão falhou!");
            }
        } catch (SQLException e) {
           throw new ConexaoException("Conexão falhou!");
        }
        return medico;
    }
    
    public Medico acharPorId(Connection connection, PreparedStatement ps, ResultSet rs, Long id) throws SQLException {
        Medico medico = new Medico();
        medico.setId(id);
        medico = (Medico) pessoaRepository.acharPorId(connection, ps, rs, medico);
        ps = connection.prepareStatement(QUERY_ACHAR_POR_ID);
        ps.setLong(1, medico.getId());
        rs = ps.getResultSet();
        
        if (rs.next()) {
            medico.setCrm(rs.getString(COLUNAS[1]));
            medico.setEspecialidade(rs.getInt(COLUNAS[2]));
        }
         return medico;
    }
    
    public List<Medico> acharTodos(Connection connection, PreparedStatement ps, ResultSet rs) throws SQLException {
        List<Medico> medicos = new ArrayList<>();
        ps = connection.prepareStatement(QUERY_ACHARTODOS);
        rs = ps.getResultSet();
        while(rs.next()) {
            Medico medico = new Medico();
            medico.setId(rs.getLong(COLUNAS[0]));
            medico = (Medico) pessoaRepository.acharPorId(connection, ps, rs, medico);
            medico.setCrm(rs.getString(COLUNAS[5]));
            medico.setEspecialidade(rs.getInt(COLUNAS[6]));
            medicos.add(medico);
        }
        return medicos;        
    }
    
    public void atualizar(Connection connection, PreparedStatement ps, Medico medico) throws SQLException {
        pessoaRepository.atualizar(connection, ps, medico);
    }
        
    public void excluir(Connection connection, PreparedStatement ps, ResultSet rs, Long id, boolean ativo) throws SQLException {
        pessoaRepository.excluir(connection, ps, rs, id, ativo);
    }
}
