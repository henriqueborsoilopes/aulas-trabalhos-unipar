package br.unipar.husistema.repository;

import br.unipar.husistema.model.Consulta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class ConsultaRepository {
    
    private static final String TABELA = "consulta";
    private static final String[] COLUNAS = {"id", "data_hora", "descri_cancelamento", "cancelado", "id_medico", "id_paciente"};
    
    public Consulta inserir(Connection connection, Consulta consulta) throws SQLException {
        String query = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[1] + ", "  + COLUNAS[4] + ", " + COLUNAS[5] + ") "
            + "VALUES (?, ?, ?);";
        
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, new Date(Timestamp.valueOf(consulta.getDataHora()).getTime()));
            ps.setLong(2, consulta.getMedico().getId());
            ps.setLong(3, consulta.getPaciente().getId());
            ps.execute();        
            try (ResultSet rs = ps.getGeneratedKeys()) {
                consulta.setId(rs.getLong(COLUNAS[0]));
                return consulta;
            }
        }
    }

    public void atualizar(Connection connection, Consulta consulta) throws SQLException {
        String query = ""
            + "UPDATE " + TABELA + " "
            + "SET " + COLUNAS[2] + " = ?, " + COLUNAS[3] + " = ? "
            + "WHERE id = ?;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, consulta.getDescriCancelamento());
            ps.setBoolean(2, true);            
            ps.setLong(3, consulta.getPaciente().getId());
            ps.execute();
        }
    }
}
