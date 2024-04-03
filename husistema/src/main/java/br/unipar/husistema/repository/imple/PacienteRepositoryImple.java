package br.unipar.husistema.repository.imple;

import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.factory.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.unipar.husistema.repository.IPacienteRepository;

public class PacienteRepositoryImple implements IPacienteRepository {
    
    private static final String TABELA = "paciente";
    private static final String[] COLUNAS = {"id_pessoa", "cpf"};
    private static final String TABELA_PESSOA = "pessoa";
    private static final String[] COLUNAS_PESSOA = {"id", "nome", "email", "telefone", "ativo", "id_endereco"};

    @Override
    public void inserir(Paciente paciente) throws SQLException {
        String query = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[0] + ", " + COLUNAS[1] + ") "
            + "VALUES (?, ?);";
        
        try (PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(query)) {
            ps.setLong(1, paciente.getId());
            ps.setString(2, paciente.getCpf());
            ps.execute();
        }
    }
    
    @Override
    public Paciente acharPorId(Long id) throws SQLException {
        Paciente paciente = null;        
        String query = ""
            + "SELECT * "
            + "FROM " + TABELA + " pa "
            + "INNER JOIN " + TABELA_PESSOA + " pe ON pe." + COLUNAS_PESSOA[0] + " = pa." + COLUNAS[0] + " "
            + "WHERE pa." + COLUNAS[0] + " = ?;";
        
        try (PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(query)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {                    
                    paciente = new Paciente(
                            rs.getLong(COLUNAS_PESSOA[0]), 
                            rs.getString(COLUNAS_PESSOA[1]), 
                            rs.getString(COLUNAS_PESSOA[2]), 
                            rs.getString(COLUNAS_PESSOA[3]), 
                            rs.getBoolean(COLUNAS_PESSOA[4]), 
                            null, 
                            rs.getString(COLUNAS[1]));
                    
                }
                return paciente;
            }
        }
    }
       
    @Override
    public List<Paciente> acharTodos() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        
        String query = ""
            + "SELECT * "
            + "FROM " + TABELA + " pa "
            + "INNER JOIN " + TABELA_PESSOA + " pe ON pe." + COLUNAS_PESSOA[0] + " = pa." + COLUNAS[0] + " "
            + "ORDER BY pe." + COLUNAS_PESSOA[1] + " ASC;";
        
        try (PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {                    
                    Paciente paciente = new Paciente(
                            rs.getLong(COLUNAS_PESSOA[0]), 
                            rs.getString(COLUNAS_PESSOA[1]), 
                            rs.getString(COLUNAS_PESSOA[2]), 
                            rs.getString(COLUNAS_PESSOA[3]), 
                            rs.getBoolean(COLUNAS_PESSOA[4]), 
                            null, 
                            rs.getString(COLUNAS[1]));
                    
                    pacientes.add(paciente);
                }
                return pacientes;
            }
        }
    }
}
