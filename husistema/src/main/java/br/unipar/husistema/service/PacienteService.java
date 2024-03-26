package br.unipar.husistema.service;

import br.unipar.husistema.infraestructor.ConnectionFactory;
import br.unipar.husistema.model.Paciente;
import br.unipar.husistema.repository.PacienteRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteService {
    
    private PacienteRepository repository = null;
    private Connection connection = null;
    private final PreparedStatement ps = null;
    private final ResultSet rs = null;

    public PacienteService() {
        this.repository = new PacienteRepository();
    }
    
    public Paciente inserir(Paciente paciente) {
        try {
            connection.setAutoCommit(false);
            paciente = repository.inserir(connection, ps, rs, paciente);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
               System.out.println(ex.getMessage()); 
            }
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return paciente;
    }
    
    public List<Paciente> acharTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        try {
            pacientes = repository.acharTodos(connection, ps, rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return pacientes;
    }
    
    public void atualizar(Long id, Paciente paciente) {
        try {
            paciente.setId(id);
            repository.atualizar(connection, ps, paciente);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void excluir(Long id, boolean ativo) {
        try {
            repository.excluir(connection, ps, rs, id, ativo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
