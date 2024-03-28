package br.unipar.husistema.mapper;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.ConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.Paciente;

public class ConsultaMapper {
    
    public static Consulta getEntity(CancelarConsultaDTO dto) {
        return new Consulta(
                null, 
                null, 
                dto.getDataCancelamento(), 
                dto.getDescriCancelamento(), 
                true, 
                null, 
                null);
    }
    
    public static Consulta getEntity(InserirConsultaDTO dto) {
        Medico medico = new Medico();
        medico.setId(dto.getMedicoId());
        Paciente paciente = new Paciente();
        paciente.setId(dto.getPacienteId());
        return new Consulta(
                null, 
                dto.getDataConsuta(), 
                null, 
                null, 
                false, 
                medico, 
                paciente);
    }
    
    public static ConsultaDTO getDTO(Consulta entity) {
        return new ConsultaDTO(
                entity.getId(), 
                entity.getDataConsulta(), 
                entity.getMedico().getId(), 
                entity.getMedico().getNome(), 
                entity.getPaciente().getId(), 
                entity.getPaciente().getNome());
    }
}
