package br.unipar.husistema.dto;

import java.io.Serializable;

public class AtualizarPessoaDTO implements Serializable  {
    
    private String nome;
    private String telefone;
    
    private EnderecoDTO endereco;
    
    public AtualizarPessoaDTO() { }

    public AtualizarPessoaDTO(String nome, String telefone, EnderecoDTO endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
