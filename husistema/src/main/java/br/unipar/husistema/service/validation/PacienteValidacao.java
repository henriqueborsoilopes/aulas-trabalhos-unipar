package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.service.exception.Campo;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import java.util.ArrayList;
import java.util.List;

public class PacienteValidacao {
    
    private static final List<Campo> campos = new ArrayList<>();
 
    public static void validarInsercaoPaciente(InserirPacienteDTO dto) throws ValidacaoExcecao {
        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            campos.add(new Campo("Nome", "Por favor, insira um Nome."));
        }

        if (dto.getEmail().isEmpty() || dto.getEmail().isBlank()) {
            campos.add(new Campo("E-mail", "Por favor, insira um E-mail."));
        }
        
        if (dto.getTelefone().isEmpty() || dto.getTelefone().isBlank()) {
            campos.add(new Campo("Telefone", "Por favor, insira um Telefone."));
        }
        
        if (dto.getCpf().isEmpty() || dto.getCpf().isBlank()) {
            campos.add(new Campo("CPF", "Por favor, insira um CPF."));
        }
        
        campos.addAll(EnderecoValidacao.validarEndereco(dto.getEndereco()));

        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }
}
