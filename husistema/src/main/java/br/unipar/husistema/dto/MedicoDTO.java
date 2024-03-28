package br.unipar.husistema.dto;

import java.io.Serializable;

public class MedicoDTO implements Serializable  {
    
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private boolean ativo;
    private String crm;
    private Integer tipoEspecialidade;
    
    private EnderecoDTO endereco;
    
    public MedicoDTO() { }

    public MedicoDTO(Long id, String nome, String email, String telefone, boolean ativo, String crm, Integer tipoEspecialidade, EnderecoDTO endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
        this.crm = crm;
        this.tipoEspecialidade = tipoEspecialidade;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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
