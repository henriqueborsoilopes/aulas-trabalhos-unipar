package br.unipar.husistema.service.exception;

import jakarta.xml.ws.WebFault;

@WebFault(name = "excecao")
public class BancoDadosExcecao extends RuntimeException {
    
    public BancoDadosExcecao(String mensagem) {
        super(mensagem);
    }
}
