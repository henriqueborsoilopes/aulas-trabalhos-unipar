package br.unipar.husistema.mapper;

import br.unipar.husistema.dto.MedicoDTO;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.enums.EspecialidadeEnum;
import java.util.List;
import java.util.stream.Collectors;

public class MedicoMapper {
    
    public Medico getEntity(MedicoDTO dto) {
        return new Medico(
                dto.getId(), 
                dto.getNome(), 
                dto.getEmail(), 
                dto.getTelefone(), 
                dto.isAtivo(), 
                EnderecoMapper.getEntity(dto.getEndereco()), 
                dto.getCrm(), 
                EspecialidadeEnum.paraEnum(dto.getTipoEspecialidade()));
    }
    
    public MedicoDTO getDTO(Medico entity) {
        return new MedicoDTO(
                entity.getId(), 
                entity.getNome(), 
                entity.getEmail(), 
                entity.getTelefone(), 
                entity.isAtivo(),  
                entity.getCrm(), 
                entity.getTipoEspecialidade().getCodigo(),
                EnderecoMapper.getDTO(entity.getEndereco()));
    }

    public List<MedicoDTO> getLitDTO(List<Medico> entities) {
        return entities.stream().map(this::getDTO).collect(Collectors.toList());
    }
}
