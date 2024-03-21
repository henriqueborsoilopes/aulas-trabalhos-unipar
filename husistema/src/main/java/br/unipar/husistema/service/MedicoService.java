package br.unipar.husistema.service;

import br.unipar.husistema.infraestructor.DataBaseConectoin;
import br.unipar.husistema.model.Medico;
import br.unipar.husistema.repository.MedicoRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoService {
    
    private MedicoRepository repository = null;
    private Connection connection = null;
    private final PreparedStatement ps = null;
    private final ResultSet rs = null;

    public MedicoService() {
        this.repository = new MedicoRepository();
        this.connection = DataBaseConectoin.getConnection();
    }
    
    public Medico inserir(Medico medico) {
        try {
            connection.setAutoCommit(false);
            medico = repository.inserir(connection, ps, rs, medico);
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
        return medico;
    }
    
    public List<Medico> acharTodos() {
        List<Medico> medicos = new ArrayList<>();
        try {
            medicos = repository.acharTodos(connection, ps, rs);
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
        return medicos;
    }
    
    public void atualizar(Long id, Medico medico) {
        try {
            medico.setId(id);
            repository.atualizar(connection, ps, medico);
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
