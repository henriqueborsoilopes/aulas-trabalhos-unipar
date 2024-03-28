package br.unipar.husistema.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class InserirConsultaDTO implements Serializable {
    
    private LocalDateTime dataConsuta;
    private Long medicoId;
    private Long pacienteId;
    
    public InserirConsultaDTO() { }

    public InserirConsultaDTO(LocalDateTime dataConsuta, Long medicoId, Long pacienteId) {
        this.dataConsuta = dataConsuta;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
    }

    public LocalDateTime getDataConsuta() {
        return dataConsuta;
    }

    public void setDataConsuta(LocalDateTime dataConsuta) {
        this.dataConsuta = dataConsuta;
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
