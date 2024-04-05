package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Endereco;

public interface IEnderecoRepository {
    
    public Endereco inserir(Endereco endereco);
    public void atualizar(Endereco endereco);
}
