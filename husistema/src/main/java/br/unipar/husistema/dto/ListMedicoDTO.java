package br.unipar.husistema.dto;

import java.io.Serializable;

public class ListMedicoDTO implements Serializable  {
    
    private String nome;
    private String email;
    private String crm;
    private Integer tipoEspecialidade;
    
    public ListMedicoDTO() { }

    public ListMedicoDTO(String nome, String email, String crm, Integer tipoEspecialidade) {
        this.nome = nome;
        this.email = email;
        this.crm = crm;
        this.tipoEspecialidade = tipoEspecialidade;
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
}
