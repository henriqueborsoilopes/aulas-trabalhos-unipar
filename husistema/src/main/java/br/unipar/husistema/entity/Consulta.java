package br.unipar.husistema.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Consulta implements Serializable {
    
    private Long id;
    private LocalDateTime dataConsulta;
    private LocalDateTime dataCancelamento;
    private String descriCancelamento;
    private boolean cancelado;
    private Long idMedico;
    private Long idPaciente;
    
    public Consulta() { }

    public Consulta(Long id, LocalDateTime dataConsulta, LocalDateTime dataCancelamento, String descriCancelamento, boolean cancelado, Long idMedico, Long idPaciente) {
        this.id = id;
        this.dataConsulta = dataConsulta;
        this.dataCancelamento = dataCancelamento;
        this.descriCancelamento = descriCancelamento;
        this.cancelado = cancelado;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getDescriCancelamento() {
        return descriCancelamento;
    }

    public void setDescriCancelamento(String descriCancelamento) {
        this.descriCancelamento = descriCancelamento;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consulta other = (Consulta) obj;
        return Objects.equals(this.id, other.id);
    }
}
