package br.unipar.husistema.repository;

import br.unipar.husistema.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {
    
    private static final String[] COLUNAS = {"id_pessoa", "crm", "cod_especialidade"};
        
    private static final String QUERY_INSERIR = ""
            + "INSERT INTO paciente (id_pessoa, cpf) "
            + "VALUES (?, ?)";
    
    private static final String QUERY_ACHAR_POR_ID = ""
            + "SELECT * "
            + "FROM paciente "
            + "WHERE id_pessoa = ?";
    
    private static final String QUERY_ACHARTODOS = ""
            + "SELECT * "
            + "FROM paciente ";
    
    private final PessoaRepository pessoaRepository = new PessoaRepository();
    
    public Paciente inserir(Connection connection, PreparedStatement ps, ResultSet rs, Paciente paciente) throws SQLException {
        //paciente.setId(pessoaRepository.inserir(connection, ps, rs, paciente).getId());
        ps = connection.prepareStatement(QUERY_INSERIR);
        ps.setLong(1, paciente.getId());
        ps.setString(2, paciente.getCpf());
        
        return paciente;
    }
    
    public Paciente acharPorId(Connection connection, PreparedStatement ps, ResultSet rs, Long id) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setId(id);
        paciente = (Paciente) pessoaRepository.acharPorId(connection, ps, rs, paciente);
        ps = connection.prepareStatement(QUERY_ACHAR_POR_ID);
        ps.setLong(1, paciente.getId());
        rs = ps.getResultSet();
        
        if (rs.next()) {
            paciente.setCpf(rs.getString(COLUNAS[1]));
        }
         return paciente;
    }
    
    public List<Paciente> acharTodos(Connection connection, PreparedStatement ps, ResultSet rs) throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        ps = connection.prepareStatement(QUERY_ACHARTODOS);
        rs = ps.getResultSet();
        while(rs.next()) {
            Paciente paciente = new Paciente();
            paciente.setId(rs.getLong(COLUNAS[0]));
            paciente = (Paciente) pessoaRepository.acharPorId(connection, ps, rs, paciente);
            paciente.setCpf(rs.getString(COLUNAS[5]));
            pacientes.add(paciente);
        }
        return pacientes;        
    }
    
    public void atualizar(Connection connection, PreparedStatement ps, Paciente paciente) throws SQLException {
        pessoaRepository.atualizar(connection, ps, paciente);
    }
    
    public void excluir(Connection connection, PreparedStatement ps, ResultSet rs, Long id, boolean ativo) throws SQLException {
        pessoaRepository.excluir(connection, ps, rs, id, ativo);
    }
}
