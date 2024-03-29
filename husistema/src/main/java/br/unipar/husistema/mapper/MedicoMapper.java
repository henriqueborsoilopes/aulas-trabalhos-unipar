package br.unipar.husistema.mapper;

import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.ListMedicoDTO;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.enums.EspecialidadeEnum;
import java.util.List;
import java.util.stream.Collectors;

public class MedicoMapper {
    
    public Medico getEntity(InserirMedicoDTO dto) {
        return new Medico(
                null, 
                dto.getNome(), 
                dto.getEmail(), 
                dto.getTelefone(), 
                true, 
                EnderecoMapper.getEntity(dto.getEndereco()), 
                dto.getCrm(), 
                EspecialidadeEnum.paraEnum(dto.getTipoEspecialidade()));
    }
    
    public ListMedicoDTO getDTO(Medico entity) {
        return new ListMedicoDTO(
                entity.getNome(), 
                entity.getEmail(), 
                entity.getCrm(), 
                entity.getTipoEspecialidade().getCodigo());
    }

    public List<ListMedicoDTO> getLitDTO(List<Medico> entities) {
        return entities.stream().map(this::getDTO).collect(Collectors.toList());
    }
}
