package br.unipar.husistema.repository;

import br.unipar.husistema.model.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {
    
    private static final String TABELA = "medico";
    private static final String[] COLUNAS = {"id_pessoa", "crm", "cod_especialidade"};
        
    public void inserir(Connection connection, Medico medico) throws SQLException {
        String QUERY_INSERIR = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[0] + ", " + COLUNAS[1] + ", " + COLUNAS[2] + ") "
            + "VALUES (?, ?, ?);";
         
        try (PreparedStatement ps = connection.prepareStatement(QUERY_INSERIR)) {
            ps.setLong(1, medico.getId());
            ps.setString(2, medico.getCrm());
            ps.setInt(3, medico.getEspecialidade());
            ps.execute();
        }
    }
    
    public List<Medico> acharTodos(Connection connection) throws SQLException {
        List<Medico> medicos = new ArrayList<>();
        
        String query = ""
            + "SELECT * "
            + "FROM " + TABELA + ";";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.getResultSet()) {
                while(rs.next()) {
                    Medico medico = new Medico();
                    medico.setId(rs.getLong(COLUNAS[0]));
                    medico.setCrm(rs.getString(COLUNAS[5]));
                    medico.setEspecialidade(rs.getInt(COLUNAS[6]));
                    medicos.add(medico);
                }
                return medicos;
            }
        }
    }
}
