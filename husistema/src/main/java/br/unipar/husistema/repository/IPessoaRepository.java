package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Pessoa;

public interface IPessoaRepository {
    
    public Pessoa inserir(Pessoa pessoa);
    public void atualizar(Pessoa pessoa);
    public void inativar(Long id);
}
