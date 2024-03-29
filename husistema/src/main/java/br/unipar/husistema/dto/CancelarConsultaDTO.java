package br.unipar.husistema.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CancelarConsultaDTO implements Serializable {

    private String descriCancelamento;
    
    public CancelarConsultaDTO() { }

    public CancelarConsultaDTO(String descriCancelamento) {
        this.descriCancelamento = descriCancelamento;
    }

    public String getDescriCancelamento() {
        return descriCancelamento;
    }

    public void setDescriCancelamento(String descriCancelamento) {
        this.descriCancelamento = descriCancelamento;
    }
}
