package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Endereco;
import br.unipar.husistema.entity.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {
    
    private static final String TABELA = "paciente";
    private static final String TABELA_PESSOA = "pessoa";
    private static final String TABELA_ENDERECO = "endereco";
    private static final String[] COLUNAS = {"id", "nome", "email", "telefone", "ativo", "id_endereco", "cpf", "id_pessoa"};
    private static final String[] COLUNAS_ENDERECO = {"id", "logradouro", "numero", "complemento", "bairro", "cidade", "uf", "cep"};

    public void inserir(Connection connection, Paciente paciente) throws SQLException {
        String query = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[7] + ", " + COLUNAS[1] + ") "
            + "VALUES (?, ?);";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, paciente.getId());
            ps.setString(2, paciente.getCpf());
            ps.execute();
        }
    }
    
    public Paciente acharPorId(Connection connection, Long id) throws SQLException {
        Paciente paciente = null;        
        String query = ""
            + "SELECT p.*, pa." + COLUNAS[6] + ", e." + COLUNAS_ENDERECO[1] + ", e." + COLUNAS_ENDERECO[2] + ", e." + COLUNAS_ENDERECO[3] + ", e." + COLUNAS_ENDERECO[4] + ", e." + COLUNAS_ENDERECO[5] + ", e." + COLUNAS_ENDERECO[6] + ", e." + COLUNAS_ENDERECO[7] + " "
            + "FROM " + TABELA + " pa "
            + "INNER JOIN " + TABELA_PESSOA + " pe ON e." + COLUNAS[0] + " = pa." + COLUNAS[0] + " "
            + "INNER JOIN " + TABELA_ENDERECO + " e ON e." + COLUNAS_ENDERECO[0] + " = pe." + COLUNAS[9] + " "
            + "WHERE pa." + COLUNAS[0] + " = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.getResultSet()) {
                if (rs.next()) {
                    Endereco endereco = new Endereco(
                            rs.getLong(COLUNAS_ENDERECO[0]), 
                            rs.getString(COLUNAS_ENDERECO[1]), 
                            rs.getString(COLUNAS_ENDERECO[2]), 
                            rs.getString(COLUNAS_ENDERECO[3]), 
                            rs.getString(COLUNAS_ENDERECO[4]), 
                            rs.getString(COLUNAS_ENDERECO[5]), 
                            rs.getString(COLUNAS_ENDERECO[6]), 
                            rs.getString(COLUNAS_ENDERECO[7]));
                    
                    paciente = new Paciente(
                            rs.getLong(COLUNAS[0]), 
                            rs.getString(COLUNAS[1]), 
                            rs.getString(COLUNAS[2]), 
                            rs.getString(COLUNAS[3]), 
                            rs.getBoolean(COLUNAS[4]), 
                            endereco, 
                            rs.getString(COLUNAS[5]));
                    
                }
                return paciente;
            }
        }
    }
       
    public List<Paciente> acharTodos(Connection connection) throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        
        String query = ""
            + "SELECT p.*, pa." + COLUNAS[6] + ", e." + COLUNAS_ENDERECO[1] + ", e." + COLUNAS_ENDERECO[2] + ", e." + COLUNAS_ENDERECO[3] + ", e." + COLUNAS_ENDERECO[4] + ", e." + COLUNAS_ENDERECO[5] + ", e." + COLUNAS_ENDERECO[6] + ", e." + COLUNAS_ENDERECO[7] + " "
            + "FROM " + TABELA + " m "
            + "INNER JOIN " + TABELA_PESSOA + " p ON p." + COLUNAS[0] + " = m." + COLUNAS[0] + " "
            + "INNER JOIN " + TABELA_ENDERECO + " e ON e." + COLUNAS_ENDERECO[0] + " = p." + COLUNAS[9] + " ";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    Endereco endereco = new Endereco(
                            rs.getLong(COLUNAS_ENDERECO[0]), 
                            rs.getString(COLUNAS_ENDERECO[1]), 
                            rs.getString(COLUNAS_ENDERECO[2]), 
                            rs.getString(COLUNAS_ENDERECO[3]), 
                            rs.getString(COLUNAS_ENDERECO[4]), 
                            rs.getString(COLUNAS_ENDERECO[5]), 
                            rs.getString(COLUNAS_ENDERECO[6]), 
                            rs.getString(COLUNAS_ENDERECO[7]));
                    
                    Paciente paciente = new Paciente(
                            rs.getLong(COLUNAS[0]), 
                            rs.getString(COLUNAS[1]), 
                            rs.getString(COLUNAS[2]), 
                            rs.getString(COLUNAS[3]), 
                            rs.getBoolean(COLUNAS[4]), 
                            endereco, 
                            rs.getString(COLUNAS[6]));
                    
                    pacientes.add(paciente);
                }
                return pacientes;
            }
        }
    }
}
