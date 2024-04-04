package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.service.exception.ValidacaoExcecao;

public class PacienteValidacao {
    
    private static ValidacaoExcecao erro;
 
    public static void validarInsercaoPaciente(InserirPacienteDTO dto) throws ValidacaoExcecao {
        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            erro.addErro("Nome", "Por favor, insira um Nome.");
        }

        if (dto.getEmail().isEmpty() || dto.getEmail().isBlank()) {
            erro.addErro("E-mail", "Por favor, insira um E-mail.");
        }
        
        if (dto.getTelefone().isEmpty() || dto.getTelefone().isBlank()) {
            erro.addErro("Telefone", "Por favor, insira um Telefone.");
        }
        
        if (dto.getCpf().isEmpty() || dto.getCpf().isBlank()) {
            erro.addErro("CPF", "Por favor, insira um CPF.");
        }
        
        EnderecoValidacao.validarEndereco(dto.getEndereco(), erro);

        if (!erro.getErros().isEmpty()) {
            throw erro;
        }
    }
}
