package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.service.exception.Campo;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import java.util.ArrayList;
import java.util.List;

public class PessoaValidacao {
    
    private static final List<Campo> campos = new ArrayList<>();
    
    public static void validarAtualizacaoPaciente(Long id, AtualizarPessoaDTO dto) throws ValidacaoExcecao {        
        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            campos.add(new Campo("Nome", "Por favor, insira um Nome."));
        }
        
        if (dto.getTelefone().isEmpty() || dto.getTelefone().isBlank()) {
            campos.add(new Campo("Telefone", "Por favor, insira um Telefone."));
        }
        
        campos.addAll(EnderecoValidacao.validarEndereco(dto.getEndereco()));

        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }
}
