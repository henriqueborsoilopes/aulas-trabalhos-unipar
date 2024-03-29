package br.unipar.husistema.dto;

import br.unipar.husistema.jsonadapter.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class InserirConsultaDTO implements Serializable {

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private Date dataConsulta;
    private Long medicoId;
    private Long pacienteId;
    
    public InserirConsultaDTO() { }

    public InserirConsultaDTO(Date dataConsulta, Long medicoId, Long pacienteId) {
        this.dataConsulta = dataConsulta;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
    }
    
    public Date dataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}
