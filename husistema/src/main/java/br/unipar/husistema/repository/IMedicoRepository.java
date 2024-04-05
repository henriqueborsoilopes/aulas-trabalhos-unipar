package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Medico;
import java.time.LocalDateTime;
import java.util.List;

public interface IMedicoRepository {
    
    public void inserir(Medico medico);
    public Long acharMedicoDisponivel(LocalDateTime data);
    public Medico acharPorId(Long id);
    public List<Medico> acharTodos();
}
