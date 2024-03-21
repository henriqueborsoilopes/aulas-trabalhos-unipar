package br.unipar.husistema.model;

import br.unipar.husistema.model.enums.EspecialidadeEnum;

public class Medico extends Pessoa {
    
    private String crm;
    private Integer codEspecialidade;
    
    public Medico() {
        super();
    }

    public Medico(Long id, String nome, String email, String telefone, Endereco endereco, String crm, EspecialidadeEnum especialidade) {
        super(id, nome, email, telefone, endereco);
        this.crm = crm;
        this.codEspecialidade = especialidade.getCodigo();
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public EspecialidadeEnum getEspecialidade() {
        return EspecialidadeEnum.paraEnum(codEspecialidade);
    }

    public void setCodEspecialidade(EspecialidadeEnum especialidade) {
        this.codEspecialidade = especialidade.getCodigo();
    }
}
