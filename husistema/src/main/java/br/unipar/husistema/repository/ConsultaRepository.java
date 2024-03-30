package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class ConsultaRepository {
    
    private static final String TABELA = "consulta";
    private static final String[] COLUNAS = {"id", "data_consulta", "data_cancelamento", 
        "descri_cancelamento", "cancelado", "id_medico", "id_paciente"};
    
    public Consulta inserir(Connection connection, Consulta consulta) throws SQLException {
        String query = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[1] + ", " + COLUNAS[4] + ", "  + COLUNAS[5] + ", " + COLUNAS[6] + ") "
            + "VALUES (?, ?, ?, ?);";
        
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, Timestamp.valueOf(Util.getAnoMesDiaHora(consulta.getDataConsulta())));
            ps.setBoolean(2, false);
            ps.setLong(3, consulta.getMedico().getId());
            ps.setLong(4, consulta.getPaciente().getId());
            ps.execute();        
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    consulta.setId(rs.getLong(COLUNAS[0]));
                }
                return consulta;
            }
        }
    }
    
    public boolean cansultarAgendamentoPaciente(Connection connection, Long id_paciente, Date date) throws SQLException {
        String query = ""
            + "SELECT c." + COLUNAS[1] + " "
            + "FROM " + TABELA + " c "
            + "WHERE c." + COLUNAS[6] + " = ? "
            + "AND DATE(c." + COLUNAS[1] + ") = DATE(?) "
            + "AND c." + COLUNAS[4] + " = false;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id_paciente);
            ps.setTimestamp(2, Timestamp.valueOf(Util.getAnoMesDiaHora(date)));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
        
    public boolean cansultarAgendamentoMedico(Connection connection, Date data, Long id_medico) throws SQLException {
        Calendar dataMais1H = Calendar.getInstance();
        dataMais1H.setTime(data);
        dataMais1H.add(Calendar.HOUR_OF_DAY, 1);
                
        String query = ""
            + "SELECT * "
            + "FROM " + TABELA + " "
            + "WHERE " + COLUNAS[5] + " = ? "
            + "AND DATE(" + COLUNAS[1] + ") = DATE(?) "
            + "AND ? BETWEEN " + COLUNAS[1] + " AND " + COLUNAS[1] + " + INTERVAL '1 hour' "
            + "OR ? BETWEEN " + COLUNAS[1] + " AND " + COLUNAS[1] + " + INTERVAL '1 hour' "
            + "AND " + COLUNAS[4] + " = false;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id_medico);
            ps.setTimestamp(2, Timestamp.valueOf(Util.getAnoMesDiaHora(data)));
            ps.setTimestamp(3, Timestamp.valueOf(Util.getAnoMesDiaHora(data)));
            ps.setTimestamp(4, Timestamp.valueOf(Util.getAnoMesDiaHora(dataMais1H.getTime())));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    public Date cansultarDataConsulta(Connection connection, Long id_consulta) throws SQLException {
        Date data = null;
        String query = ""
            + "SELECT c." + COLUNAS[1] + " "
            + "FROM " + TABELA + " c "
            + "WHERE c." + COLUNAS[0] + " = ?;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id_consulta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = new Date(Util.getAnoMesDiaHora(rs.getDate(COLUNAS[1])));
                }
                return data;
            }
        }
    }
    
    public void cancelar(Connection connection, Long id, Consulta consulta) throws SQLException {
        String query = ""
            + "UPDATE " + TABELA + " "
            + "SET " + COLUNAS[2] + " = CURRENT_TIMESTAMP, " + COLUNAS[3] + " = ?, " + COLUNAS[4] + " = ? "
            + "WHERE " + COLUNAS[0] + " = ?;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, consulta.getDescriCancelamento());
            ps.setBoolean(2, true);            
            ps.setLong(3, id);
            ps.execute();
        }
    }
}
