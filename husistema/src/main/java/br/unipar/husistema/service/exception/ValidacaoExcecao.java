package br.unipar.husistema.service.exception;

import jakarta.xml.ws.WebFault;
import java.util.ArrayList;
import java.util.List;

@WebFault(name = "validacao")
public class ValidacaoExcecao extends Exception {
        
    private List<Campo> campos = new ArrayList<>();
    
    public ValidacaoExcecao(List<Campo> campos) {
        this.campos = campos;
    }

    public List<Campo> getCampos() {
        return campos;
    }
}
