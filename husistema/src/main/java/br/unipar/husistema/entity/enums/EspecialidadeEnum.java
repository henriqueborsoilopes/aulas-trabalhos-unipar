package br.unipar.husistema.entity.enums;

public enum EspecialidadeEnum {
    
    ORTOPEDIA(1, "Ortopedia"),
    CARDIOLOGISTA(2, "Cardiologia"),
    GINECOLOGIA(3, "Ginecologia"),
    DERMATOLOGIA(4, "Dermatologia");
    
    private Integer codigo;
    private String descricao;
    
    private EspecialidadeEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public static EspecialidadeEnum paraEnum(Integer codigo){
        if (codigo == null) {
            return null;
        }
        
        for (EspecialidadeEnum especialidade : EspecialidadeEnum.values()) {
            if (especialidade.getCodigo().equals(codigo)) {
                return especialidade;
            }
        }
        
        throw new IllegalArgumentException("Código inválido! codigo: " + codigo);
    }
}
