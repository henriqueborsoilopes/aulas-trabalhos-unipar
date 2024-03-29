package br.unipar.husistema.mapper;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.dto.ListPacienteDTO;
import br.unipar.husistema.entity.Paciente;
import java.util.List;
import java.util.stream.Collectors;

public class PacienteMapper {
    
    public Paciente getEntity(AtualizarPessoaDTO dto) {
        return new Paciente(
                null, 
                dto.getNome(), 
                null, 
                dto.getTelefone(), 
                true, 
                EnderecoMapper.getEntity(dto.getEndereco()),
                null);
    }
    
    public Paciente getEntity(InserirPacienteDTO dto) {
        return new Paciente(
                null, 
                dto.getNome(), 
                dto.getEmail(), 
                dto.getTelefone(), 
                true, 
                EnderecoMapper.getEntity(dto.getEndereco()),
                dto.getCpf());
    }
    
    public ListPacienteDTO getDTO(Paciente entity) {
        return new ListPacienteDTO( 
                entity.getNome(), 
                entity.getEmail(), 
                entity.getCpf());
    }

    public List<ListPacienteDTO> getLitDTO(List<Paciente> entities) {
        return entities.stream().map(this::getDTO).collect(Collectors.toList());
    }
}
