package br.unipar.husistema.service;

import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.ListMedicoDTO;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.service.exception.ValidarExcecao;
import java.time.LocalDateTime;
import java.util.List;

public interface IMedicoService {
    
    public Medico inserir(InserirMedicoDTO dto) throws ValidarExcecao;
    public Long acharMedicoDisponivel(LocalDateTime data);
    public Medico acharPorId(Long id);
    public List<ListMedicoDTO> acharTodos();
}
