package br.unipar.husistema.service;

import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.ListMedicoDTO;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import java.time.LocalDateTime;
import java.util.List;

public interface MedicoService {
    
    public Medico inserir(InserirMedicoDTO dto) throws BancoDadosExcecao, ValidacaoExcecao;
    public Long acharMedicoDisponivel(LocalDateTime data) throws BancoDadosExcecao;
    public Medico acharPorId(Long id) throws BancoDadosExcecao;
    public List<ListMedicoDTO> acharTodos() throws BancoDadosExcecao;
}
