package br.unipar.husistema.service.validation;

import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.service.exception.ValidacaoExcecao;

public class MedicoValidacao {
    
    private static ValidacaoExcecao erro;
    
    public static void validarInsercaoMedico(InserirMedicoDTO dto) throws ValidacaoExcecao { 
        erro = new ValidacaoExcecao("Validação");
        
        if (dto.getCrm().isEmpty() || dto.getCrm().isBlank()) {
            erro.addErro("CRM", "Por favor, insira um CRM.");
        }

        if (dto.getTipoEspecialidade() == null) {
            erro.addErro("Especialidade", "Por favor, insira uma Especialidade.");
        }
        
        if (dto.getNome().isEmpty() || dto.getNome().isBlank()) {
            erro.addErro("Nome", "Por favor, insira um Nome.");
        }

        if (dto.getEmail().isEmpty() || dto.getEmail().isBlank()) {
            erro.addErro("E-mail", "Por favor, insira um E-mail.");
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
