package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.service.exception.Campo;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import java.util.ArrayList;
import java.util.List;

public class MedicoValidacao {
    
    private static List<Campo> campos;
    
    public static void validarInsercaoMedico(InserirMedicoDTO dto) throws ValidacaoExcecao { 
        campos = new ArrayList<>();
        
        if (dto.getCrm().isEmpty() || dto.getCrm().isBlank()) {
            campos.add(new Campo("CRM", "Por favor, insira um CRM."));
        }

        if (dto.getTipoEspecialidade() == null) {
            campos.add(new Campo("Especialidade", "Por favor, insira uma Especialidade."));
        }
        
        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            campos.add(new Campo("Nome", "Por favor, insira um Nome."));
        }

        if (dto.getEmail().isEmpty() || dto.getEmail().isBlank()) {
            campos.add(new Campo("E-mail", "Por favor, insira um E-mail."));
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
