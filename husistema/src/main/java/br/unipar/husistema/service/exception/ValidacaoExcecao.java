package br.unipar.husistema.service.exception;

import jakarta.xml.ws.WebFault;
import java.util.HashMap;
import java.util.Map;

@WebFault(name = "validacao")
public class ValidacaoExcecao extends Exception {
        
    private final Map<String, String> erros = new HashMap<>();
    
    public ValidacaoExcecao(String msg) {
        super(msg);
    }
    
    public Map<String, String> getErros() {
        return erros;
    }
    
    public void addErro(String campo, String descricao) {
        this.erros.put(campo, descricao);
    }
    
    public void addAllErro(Map<String, String> erros) {
        this.erros.putAll(erros);
    }
}
