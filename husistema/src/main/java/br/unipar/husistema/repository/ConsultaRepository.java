package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConsultaRepository {
    
    private static final String TABELA = "consulta";
    private static final String[] TABELAS_EXTERNA = {"pessoa"};
    private static final String[] COLUNAS_EXTERNA = {"nome"};
    private static final String[] COLUNAS = {"id", "data_consulta", "data_cancelamento", 
        "descri_cancelamento", "cancelado", "id_medico", "id_paciente"};
    
    public Consulta inserir(Connection connection, Consulta consulta) throws SQLException {
        String query = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[1] + ", " + COLUNAS[4] + ", "  + COLUNAS[5] + ", " + COLUNAS[6] + ") "
            + "VALUES (?, ?, ?, ?);";
        
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, new Date(Timestamp.valueOf(consulta.getDataConsulta()).getTime()));
            ps.setBoolean(2, consulta.isCancelado());
            ps.setLong(3, consulta.getMedico().getId());
            ps.setLong(4, consulta.getPaciente().getId());
            ps.execute();        
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    consulta = acharPorId(connection, rs.getLong(COLUNAS[0]));
                }
                return consulta;
            }
        }
    }
    
    public Consulta acharPorId(Connection connection, Long id) throws SQLException {
        String query = ""
            + "SELECT c.*, m." + COLUNAS_EXTERNA[0] + " as nome_medico, p." + COLUNAS_EXTERNA[0] + " as nome_paciente "
            + "FROM " + TABELA + " c "
            + "INNER JOIN " + TABELAS_EXTERNA[0] + " m ON c." + COLUNAS[5] + " = m.id "
            + "INNER JOIN " + TABELAS_EXTERNA[0] + " p ON c." + COLUNAS[6] + " = p.id "
            + "WHERE c." + COLUNAS[0] + " = ?;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.execute();
        
            try (ResultSet rs = ps.getResultSet()) {
                if (rs.next()) {
                    Medico medico = new Medico();
                    medico.setId(rs.getLong(COLUNAS[4]));
                    medico.setNome(rs.getString("nome_medico"));
                    Paciente paciente = new Paciente();
                    paciente.setId(rs.getLong(COLUNAS[5]));
                    paciente.setNome(rs.getString("nome_paciente"));
                    return new Consulta(
                            rs.getLong(COLUNAS[0]), 
                            new java.sql.Timestamp(rs.getDate(COLUNAS[1]).getTime()).toLocalDateTime(),
                            new java.sql.Timestamp(rs.getDate(COLUNAS[6]).getTime()).toLocalDateTime(),
                            rs.getString(COLUNAS[2]), 
                            rs.getBoolean(COLUNAS[3]), 
                            medico, 
                            paciente);
                }
                return null;
            } 
        }
    }
    
    public boolean cansultarAgendamentoPaciente(Connection connection, LocalDate data, Long id_paciente) throws SQLException {
        String query = ""
            + "SELECT * "
            + "FROM " + TABELA + " "
            + "WHERE DATE(" + COLUNAS[1] + ") = ? AND " + COLUNAS[5] + " = ? AND " + COLUNAS[4] + " = false;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, Date.valueOf(data));
            ps.setLong(2, id_paciente);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    public boolean cansultarAgendamentoMedico(Connection connection, LocalDateTime data, Long id_medico) throws SQLException {
        String query = ""
            + "SELECT * "
            + "FROM " + TABELA + " "
            + "WHERE " + COLUNAS[1] + " < ? OR ("
            + COLUNAS[1] + " + INTERVAL '1 hour' > ? AND "
            + COLUNAS[1] + " + INTERVAL '1 hour' > ? + INTERVAL '1 hour') AND "
            + COLUNAS[4] + " = false;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, new Date(Timestamp.valueOf(data).getTime()));
            ps.setLong(2, id_medico);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    public void cancelar(Connection connection, Consulta consulta) throws SQLException {
        String query = ""
            + "UPDATE " + TABELA + " "
            + "SET " + COLUNAS[2] + " = ?, " + COLUNAS[2] + " = ?, " + COLUNAS[4] + " "
            + "WHERE id = ?;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, consulta.getDescriCancelamento());
            ps.setDate(2, new Date(Timestamp.valueOf(consulta.getDataCancelamento()).getTime()));
            ps.setBoolean(3, true);            
            ps.setLong(4, consulta.getPaciente().getId());
            ps.execute();
        }
    }
}
