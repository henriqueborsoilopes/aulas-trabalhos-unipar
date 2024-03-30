package br.unipar.husistema.mapper;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.Paciente;

public class ConsultaMapper {
    
    public Consulta getEntity(CancelarConsultaDTO dto) {
        return new Consulta(
                null, 
                null, 
                null, 
                dto.getDescriCancelamento(), 
                true, 
                null, 
                null);
    }
    
    public Consulta getEntity(InserirConsultaDTO dto) {
        Medico medico = new Medico();
        medico.setId(dto.getMedicoId());
        Paciente paciente = new Paciente();
        paciente.setId(dto.getPacienteId());
        return new Consulta(
                null, 
                dto.getDataHorario(), 
                null, 
                null, 
                false, 
                medico, 
                paciente);
    }
}
