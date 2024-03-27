package br.unipar.husistema.service.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoExcecao extends Exception {
        
    private final List<Campo> campos = new ArrayList<>();
    
    public ValidacaoExcecao(List<Campo> campos) {
        this.campos.addAll(campos);
    }

    public List<Campo> getCampos() {
        return campos;
    }
}
