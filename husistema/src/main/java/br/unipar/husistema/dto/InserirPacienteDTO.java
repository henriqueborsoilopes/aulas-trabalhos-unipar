package br.unipar.husistema.dto;

import java.io.Serializable;

public class InserirPacienteDTO implements Serializable {

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    
    private EnderecoDTO endereco;
    
    public InserirPacienteDTO() { }

    public InserirPacienteDTO(String nome, String email, String telefone, String cpf, EnderecoDTO endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
