package br.unipar.husistema.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Consulta implements Serializable {
    
    private Long id;
    private Date dataConsulta;
    private Date dataCancelamento;
    private String descriCancelamento;
    private boolean cancelado;
    
    private Medico medico;
    private Paciente paciente;
    
    public Consulta() { }

    public Consulta(Long id, Date dataConsulta, Date dataCancelamento, String descriCancelamento, boolean cancelado, Medico medico, Paciente paciente) {
        this.id = id;
        this.dataConsulta = dataConsulta;
        this.dataCancelamento = dataCancelamento;
        this.descriCancelamento = descriCancelamento;
        this.cancelado = cancelado;
        this.medico = medico;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Date getTerminoConsulta() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataConsulta);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
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
