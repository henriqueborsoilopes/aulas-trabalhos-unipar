package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Medico;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository {
    
    public void inserir(Medico medico) throws SQLException;
    public Long acharMedicoDisponivel(LocalDateTime data) throws SQLException;
    public Medico acharPorId(Long id) throws SQLException;
    public List<Medico> acharTodos() throws SQLException;
}
