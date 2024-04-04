package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.EnderecoDTO;
import br.unipar.husistema.service.exception.ValidacaoExcecao;

public class EnderecoValidacao {
    
    public static void validarEndereco(EnderecoDTO dto, ValidacaoExcecao erro) {
        
        if (dto.getLogradouro().isEmpty() || dto.getLogradouro().isBlank()) {
            erro.addErro("Logradouro", "Por favor, insira um Logradouro.");
        }
        
        if (dto.getBairro().isEmpty() || dto.getBairro().isBlank()) {
            erro.addErro("Bairro", "Por favor, insira um Bairro.");
        }
        
        if (dto.getCidade().isEmpty() || dto.getCidade().isBlank()) {
            erro.addErro("Cidade", "Por favor, insira uma Cidade");
        }
        
        if (dto.getUf().isEmpty() || dto.getUf().isBlank()) {
            erro.addErro("UF", "Por favor, insira um UF.");
        }
        
        if (dto.getCep().isEmpty() || dto.getCep().isBlank()) {
            erro.addErro("CEP", "Por favor, insira um CEP.");
        }
    }
}
