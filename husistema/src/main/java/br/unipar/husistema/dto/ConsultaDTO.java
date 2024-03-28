package br.unipar.husistema.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ConsultaDTO implements Serializable {
    
    private Long id;
    private LocalDateTime dataConsuta;
    private Long medicoId;
    private String medicoNome;
    private Long pacienteId;
    private String pacienteNome;
    
    public ConsultaDTO() { }

    public ConsultaDTO(Long id, LocalDateTime dataConsuta, Long medicoId, String medicoNome, Long pacienteId, String pacienteNome) {
        this.id = id;
        this.dataConsuta = dataConsuta;
        this.medicoId = medicoId;
        this.medicoNome = medicoNome;
        this.pacienteId = pacienteId;
        this.pacienteNome = pacienteNome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMedicoNome() {
        return medicoNome;
    }

    public void setMedicoNome(String medicoNome) {
        this.medicoNome = medicoNome;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getPacienteNome() {
        return pacienteNome;
    }

    public void setPacienteNome(String pacienteNome) {
        this.pacienteNome = pacienteNome;
    }
}
