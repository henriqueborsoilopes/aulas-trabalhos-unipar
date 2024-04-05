package br.unipar.husistema.mapper;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.dto.ListPacienteDTO;
import br.unipar.husistema.entity.Paciente;
import java.util.List;
import java.util.stream.Collectors;

public class PacienteMapper {
    
    public static Paciente getEntidade(AtualizarPessoaDTO dto) {
        return new Paciente(
                null, 
                dto.getNome(), 
                null, 
                dto.getTelefone(), 
                true, 
                null,
                null);
    }
    
    public static Paciente getEntidade(InserirPacienteDTO dto) {
        return new Paciente(
                null, 
                dto.getNome(), 
                dto.getEmail(), 
                dto.getTelefone(), 
                true, 
                null,
                dto.getCpf());
    }
    
    public static ListPacienteDTO getDTO(Paciente entity) {
        return new ListPacienteDTO(
                entity.getId(),
                entity.getNome(), 
                entity.getEmail(), 
                entity.getCpf(),
                entity.isAtivo());
    }

    public static List<ListPacienteDTO> getListaDTO(List<Paciente> entities) {
        return entities.stream().map(x -> getDTO(x)).collect(Collectors.toList());
    }
}
