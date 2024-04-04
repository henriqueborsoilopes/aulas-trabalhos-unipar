package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.service.exception.ValidacaoExcecao;

public class PessoaValidacao {
    
    private static ValidacaoExcecao erro;
    
    public static void validarAtualizacaoPaciente(Long id, AtualizarPessoaDTO dto) throws ValidacaoExcecao {        
        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            erro.addErro("Nome", "Por favor, insira um Nome.");
        }
        
        if (dto.getTelefone().isEmpty() || dto.getTelefone().isBlank()) {
            erro.addErro("Telefone", "Por favor, insira um Telefone.");
        }
        
        EnderecoValidacao.validarEndereco(dto.getEndereco(), erro);

        if (!erro.getErros().isEmpty()) {
            throw erro;
        }
    }
}
