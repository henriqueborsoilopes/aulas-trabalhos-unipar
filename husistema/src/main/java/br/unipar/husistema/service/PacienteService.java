package br.unipar.husistema.service;

import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.dto.ListPacienteDTO;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import java.util.List;

public interface PacienteService {
    
    public Paciente inserir(InserirPacienteDTO dto) throws BancoDadosExcecao, ValidacaoExcecao;
    public Paciente acharPorId(Long id) throws BancoDadosExcecao;
    public List<ListPacienteDTO> acharTodos() throws BancoDadosExcecao;
}
