package br.unipar.husistema.model;

public class Paciente extends Pessoa {
    
    private String cpf;
    
    public Paciente() {
        super();
    }

    public Paciente(String cpf, Long id, String nome, String email, String telefone, Endereco endereco) {
        super(id, nome, email, telefone, endereco);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
