package br.unipar.husistema.dto;

import java.io.Serializable;

public class InserirMedicoDTO implements Serializable  {

    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Integer tipoEspecialidade;
    
    private EnderecoDTO endereco;
    
    public InserirMedicoDTO() { }

    public InserirMedicoDTO(String nome, String email, String telefone, String crm, Integer tipoEspecialidade, EnderecoDTO endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.tipoEspecialidade = tipoEspecialidade;
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Integer getTipoEspecialidade() {
        return tipoEspecialidade;
    }

    public void setTipoEspecialidade(Integer tipoEspecialidade) {
        this.tipoEspecialidade = tipoEspecialidade;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
