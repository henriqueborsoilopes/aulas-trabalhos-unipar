package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Paciente;
import java.util.List;

public interface IPacienteRepository {
    
    public void inserir(Paciente paciente);
    public Paciente acharPorId(Long id);
    public List<Paciente> acharTodos();
}
