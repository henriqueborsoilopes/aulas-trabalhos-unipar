package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.EnderecoDTO;
import br.unipar.husistema.service.exception.Campo;
import java.util.ArrayList;
import java.util.List;

public class EnderecoValidacao {
    
    private static List<Campo> campos;
    
    public static List<Campo> validarEndereco(EnderecoDTO dto) {
        campos = new ArrayList<>();
        
        if (dto.getLogradouro().isEmpty() || dto.getLogradouro().isBlank()) {
            campos.add(new Campo("Logradouro", "Por favor, insira um Logradouro."));
        }
        
        if (dto.getBairro().isEmpty() || dto.getBairro().isBlank()) {
            campos.add(new Campo("Bairro", "Por favor, insira um Bairro."));
        }
        
        if (dto.getCidade().isEmpty() || dto.getCidade().isBlank()) {
            campos.add(new Campo("Cidade", "Por favor, insira uma Cidade"));
        }
        
        if (dto.getUf().isEmpty() || dto.getUf().isBlank()) {
            campos.add(new Campo("UF", "Por favor, insira um UF."));
        }
        
        if (dto.getCep().isEmpty() || dto.getCep().isBlank()) {
            campos.add(new Campo("CEP", "Por favor, insira um CEP."));
        }
        
        return campos;
    }
}
