package br.unipar.husistema.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CancelarConsultaDTO implements Serializable {
    
    private LocalDateTime dataCancelamento;
    private String descriCancelamento;
    
    public CancelarConsultaDTO() { }

    public CancelarConsultaDTO(LocalDateTime dataCancelamento, String descriCancelamento) {
        this.dataCancelamento = dataCancelamento;
        this.descriCancelamento = descriCancelamento;
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
}
