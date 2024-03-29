package br.unipar.husistema.service.exception;

import jakarta.xml.ws.WebFault;

@WebFault
public class BancoDadosException extends Exception {
    
    public BancoDadosException(String mensagem) {
        super(mensagem);
    }
}
