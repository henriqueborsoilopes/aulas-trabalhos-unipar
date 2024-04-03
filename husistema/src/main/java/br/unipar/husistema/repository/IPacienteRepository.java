package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Paciente;
import java.sql.SQLException;
import java.util.List;

public interface IPacienteRepository {
    
    public void inserir(Paciente paciente) throws SQLException;
    public Paciente acharPorId(Long id) throws SQLException;
    public List<Paciente> acharTodos() throws SQLException;
}
