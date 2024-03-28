package br.unipar.husistema.mapper;

import br.unipar.husistema.dto.PacienteDTO;
import br.unipar.husistema.entity.Paciente;
import java.util.List;
import java.util.stream.Collectors;

public class PacienteMapper {
    
    public Paciente getEntity(PacienteDTO dto) {
        return new Paciente(
                dto.getId(), 
                dto.getNome(), 
                dto.getEmail(), 
                dto.getTelefone(), 
                dto.isAtivo(), 
                EnderecoMapper.getEntity(dto.getEndereco()),
                dto.getCpf());
    }
    
    public PacienteDTO getDTO(Paciente entity) {
        return new PacienteDTO(
                entity.getId(), 
                entity.getNome(), 
                entity.getEmail(), 
                entity.getTelefone(), 
                entity.isAtivo(),
                entity.getCpf(),
                EnderecoMapper.getDTO(entity.getEndereco()));
    }

    public List<PacienteDTO> getLitDTO(List<Paciente> entities) {
        return entities.stream().map(this::getDTO).collect(Collectors.toList());
    }
}
