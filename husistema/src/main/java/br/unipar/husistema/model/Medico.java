package br.unipar.husistema.model;

import br.unipar.husistema.model.enums.EspecialidadeEnum;

public class Medico extends Pessoa {
    
    private String crm;
    private Integer especialidade;
    
    public Medico() {
        super();
    }

    public Medico(Long id, String nome, String email, String telefone, Endereco endereco, String crm, EspecialidadeEnum especialidade) {
        super(id, nome, email, telefone, endereco);
        this.crm = crm;
        this.especialidade = especialidade.getCodigo();
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Integer getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Integer especialidade) {
        this.especialidade = especialidade;
    }
}
