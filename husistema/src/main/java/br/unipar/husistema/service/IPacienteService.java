package br.unipar.husistema.service;

import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.dto.ListPacienteDTO;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.service.exception.ValidarExcecao;
import java.util.List;

public interface IPacienteService {
    
    public Paciente inserir(InserirPacienteDTO dto) throws ValidarExcecao;
    public Paciente acharPorId(Long id);
    public List<ListPacienteDTO> acharTodos();
}
