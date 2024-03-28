package br.unipar.husistema.entity;

import br.unipar.husistema.entity.enums.EspecialidadeEnum;

public class Medico extends Pessoa {
    
    private String crm;
    private Integer tipoEspecialidade;
    
    public Medico() {
        super();
    }

    public Medico(Long id, String nome, String email, String telefone, boolean ativo, Endereco endereco, String crm, EspecialidadeEnum tipoEspecialidade) {
        super(id, nome, email, telefone, ativo, endereco);
        this.crm = crm;
        this.tipoEspecialidade = tipoEspecialidade.getCodigo();
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public EspecialidadeEnum getTipoEspecialidade() {
        return EspecialidadeEnum.paraEnum(tipoEspecialidade);
    }

    public void setEspecialidade(EspecialidadeEnum tipoEspecialidade) {
        this.tipoEspecialidade = tipoEspecialidade.getCodigo();
    }
}
