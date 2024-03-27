package br.unipar.husistema.repository;

import br.unipar.husistema.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {
    
    private static final String TABELA = "paciente";
    private static final String[] COLUNAS = {"id_pessoa", "cpf"};

    public void inserir(Connection connection, Paciente paciente) throws SQLException {
        String query = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[0] + ", " + COLUNAS[1] + ") "
            + "VALUES (?, ?);";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, paciente.getId());
            ps.setString(2, paciente.getCpf());
            ps.execute();
        }
    }
    
    public List<Paciente> acharTodos(Connection connection) throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        
        String query = ""
            + "SELECT * "
            + "FROM " + TABELA + ";";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.execute();
        
            try (ResultSet rs = ps.getResultSet()) {
                while(rs.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setId(rs.getLong(COLUNAS[0]));
                    paciente.setCpf(rs.getString(COLUNAS[1]));
                    pacientes.add(paciente);
                }
                return pacientes;
            } 
        }
    }
}
