package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Endereco;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.enums.EspecialidadeEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {//, "pessoa", "endereco"
    
    private static final String TABELA = "medico";
    private static final String TABELA_CONSULTA = "consulta";
    private static final String TABELA_PESSOA = "pessoa";
    private static final String TABELA_ENDERECO = "endereco";
    private static final String[] COLUNAS = {"id", "nome", "email", "telefone", "ativo", "id_endereco", "crm", "tipo_especialidade", "id_pessoa"};
    private static final String[] COLUNAS_CONSULTA = {"id_medico"};
    private static final String[] COLUNAS_ENDERECO = {"id", "logradouro", "numero", "complemento", "bairro", "cidade", "uf", "cep"};
        
    public void inserir(Connection connection, Medico medico) throws SQLException {
        String query = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[8] + ", " + COLUNAS[1] + ", " + COLUNAS[2] + ") "
            + "VALUES (?, ?, ?);";
         
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, medico.getId());
            ps.setString(2, medico.getCrm());
            ps.setInt(3, medico.getTipoEspecialidade().getCodigo());
            ps.execute();
        }
    }
    
    public Long acharMedicoDisponivel(Connection connection) throws SQLException {
        String query = ""
            + "SELECT m.* "
            + "FROM " + TABELA + " m "
            + "LEFT JOIN " + TABELA_CONSULTA + " c ON c." + COLUNAS_CONSULTA[0] + " = m." + COLUNAS[0] + " "
            + "WHERE c." + COLUNAS_CONSULTA[0] + " IS NULL;";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(COLUNAS[0]);
                }
                return null;
            }
        }
    }
    
    public Medico acharPorId(Connection connection, Long id) throws SQLException {
        Medico medico = null;        
        String query = ""
            + "SELECT p.*, m." + COLUNAS[6] + ", m." + COLUNAS[7] + ", e." + COLUNAS_ENDERECO[1] + ", e." + COLUNAS_ENDERECO[2] + ", e." + COLUNAS_ENDERECO[3] + ", e." + COLUNAS_ENDERECO[4] + ", e." + COLUNAS_ENDERECO[5] + ", e." + COLUNAS_ENDERECO[6] + ", e." + COLUNAS_ENDERECO[7] + " "
            + "FROM " + TABELA + " m "
            + "INNER JOIN " + TABELA_PESSOA + " p ON p." + COLUNAS[0] + " = m." + COLUNAS[0] + " "
            + "INNER JOIN " + TABELA_ENDERECO + " e ON e." + COLUNAS_ENDERECO[0] + " = p." + COLUNAS[9] + " "
            + "WHERE m." + COLUNAS[0] + " = ?";
        
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
                    
                    medico = new Medico(
                            rs.getLong(COLUNAS[0]), 
                            rs.getString(COLUNAS[1]), 
                            rs.getString(COLUNAS[2]), 
                            rs.getString(COLUNAS[3]), 
                            rs.getBoolean(COLUNAS[4]), 
                            endereco, 
                            rs.getString(COLUNAS[6]), 
                            EspecialidadeEnum.paraEnum(rs.getInt(COLUNAS[7])));
                    
                }
                return medico;
            }
        }
    }
       
    public List<Medico> acharTodos(Connection connection) throws SQLException {
        List<Medico> medicos = new ArrayList<>();
        
        String query = ""
            + "SELECT p.*, m." + COLUNAS[6] + ", m." + COLUNAS[7] + ", e." + COLUNAS_ENDERECO[1] + ", e." + COLUNAS_ENDERECO[2] + ", e." + COLUNAS_ENDERECO[3] + ", e." + COLUNAS_ENDERECO[4] + ", e." + COLUNAS_ENDERECO[5] + ", e." + COLUNAS_ENDERECO[6] + ", e." + COLUNAS_ENDERECO[7] + " "
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
                    
                    Medico medico = new Medico(
                            rs.getLong(COLUNAS[0]), 
                            rs.getString(COLUNAS[1]), 
                            rs.getString(COLUNAS[2]), 
                            rs.getString(COLUNAS[3]), 
                            rs.getBoolean(COLUNAS[4]), 
                            endereco, 
                            rs.getString(COLUNAS[5]), 
                            EspecialidadeEnum.paraEnum(rs.getInt(COLUNAS[6])));
                    
                    medicos.add(medico);
                }
                return medicos;
            }
        }
    }
}
