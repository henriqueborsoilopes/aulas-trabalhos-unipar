package br.unipar.husistema.mapper;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Consulta;

public class ConsultaMapper {
    
    public static Consulta getEntity(CancelarConsultaDTO dto) {
        return new Consulta(
                null, 
                null, 
                null, 
                dto.getDescriCancelamento(), 
                true, 
                null, 
                null);
    }
    
    public static Consulta getEntity(InserirConsultaDTO dto) {
        return new Consulta(
                null, 
                dto.getDataHorario(), 
                null, 
                null, 
                false, 
                dto.getMedicoId(), 
                dto.getPacienteId());
    }
}
