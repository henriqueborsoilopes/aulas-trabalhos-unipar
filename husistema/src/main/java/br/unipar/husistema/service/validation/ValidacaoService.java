package br.unipar.husistema.service.validation;

import br.unipar.husistema.model.Endereco;
import br.unipar.husistema.model.Medico;
import br.unipar.husistema.model.Paciente;
import br.unipar.husistema.model.Pessoa;
import br.unipar.husistema.service.exception.Campo;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import java.util.ArrayList;
import java.util.List;

public class ValidacaoService {

    private static List<Campo> campos;

    public static void validarMedico(Medico medico) throws ValidacaoExcecao {
        campos = new ArrayList<>();
        
        if (medico == null) {
            campos.add(new Campo("Todos", "Devem ser preenchido!"));
            throw new ValidacaoExcecao(campos);
        }

        if (medico.getCrm().isEmpty() || medico.getCrm().isBlank()) {
            campos.add(new Campo("CRM", "Deve ser preenchido!"));
        }

        if (medico.getEspecialidade() == null) {
            campos.add(new Campo("Especialidade", "Deve ser preenchido!"));
        }

        validarPessoa(medico);

        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }

    public static void validarPaciente(Paciente paciente) throws ValidacaoExcecao {
        campos = new ArrayList<>();
        
        if (paciente == null) {
            campos.add(new Campo("Todos", "Devem ser preenchido!"));
            throw new ValidacaoExcecao(campos);
        }

        if (paciente.getNome().isEmpty() || paciente.getNome().isBlank()) {
            campos.add(new Campo("Nome", "Deve ser preenchido!"));
        }

        if (paciente.getEmail().isEmpty() || paciente.getEmail().isBlank()) {
            campos.add(new Campo("E-mail", "Deve ser preenchido!"));
        }

        validarPessoa(paciente);

        if (!campos.isEmpty()) {
            throw new ValidacaoExcecao(campos);
        }
    }

    private static void validarPessoa(Pessoa pessoa) {
        if (pessoa.getNome().isEmpty() || pessoa.getNome().isBlank()) {
            campos.add(new Campo("Nome", "Deve ser preenchido!"));
        }
        
        if (pessoa.getEmail().isEmpty() || pessoa.getEmail().isBlank()) {
            campos.add(new Campo("E-mail", "Deve ser preenchido!"));
        }
        
        if (pessoa.getTelefone().isEmpty() || pessoa.getTelefone().isBlank()) {
            campos.add(new Campo("Telefone", "Deve ser preenchido!"));
        }
        
        validarEndereco(pessoa.getEndereco());
    }
    
    private static void validarEndereco(Endereco endereco) {
        if (endereco.getLogradouro().isEmpty() || endereco.getLogradouro().isBlank()) {
            campos.add(new Campo("Logradouro", "Deve ser preenchido!"));
        }
        
        if (endereco.getBairro().isEmpty() || endereco.getBairro().isBlank()) {
            campos.add(new Campo("Bairro", "Deve ser preenchido!"));
        }
        
        if (endereco.getCidade().isEmpty() || endereco.getCidade().isBlank()) {
            campos.add(new Campo("Cidade", "Deve ser preenchido!"));
        }
        
        if (endereco.getUf().isEmpty() || endereco.getUf().isBlank()) {
            campos.add(new Campo("UF", "Deve ser preenchido!"));
        }
        
        if (endereco.getCep().isEmpty() || endereco.getCep().isBlank()) {
            campos.add(new Campo("CEP", "Deve ser preenchido!"));
        }
    }
}
