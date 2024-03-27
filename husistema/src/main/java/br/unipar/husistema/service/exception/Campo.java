package br.unipar.husistema.service.exception;

import java.io.Serializable;

public class Campo implements Serializable {
    
    private String nome;
    private String descricao;
    
    public Campo() { }

    public Campo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
