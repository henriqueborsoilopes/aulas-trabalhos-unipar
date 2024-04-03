package br.unipar.husistema.entity;

public class Paciente extends Pessoa {
    
    private String cpf;
    
    public Paciente() {
        super();
    }

    public Paciente(Long id, String nome, String email, String telefone, boolean ativo, Long idEndereco, String cpf) {
        super(id, nome, email, telefone, ativo, idEndereco);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
