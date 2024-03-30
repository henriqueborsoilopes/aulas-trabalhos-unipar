package br.unipar.husistema.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InserirConsultaDTO implements Serializable {

    private String data;
    private String hora;
    private Long medicoId;
    private Long pacienteId;
    
    public InserirConsultaDTO() { }

    public InserirConsultaDTO(String data, String hora, Long medicoId, Long pacienteId) {
        this.data = data;
        this.hora = hora;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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
    
    public LocalDateTime getDataHorario() {
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterHorario = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime dataHora = LocalDateTime.of(LocalDate.parse(data, formatterData), LocalTime.parse(hora, formatterHorario));
        return dataHora;
    }
        
}
