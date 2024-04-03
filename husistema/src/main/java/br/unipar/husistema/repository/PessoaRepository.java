package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Pessoa;
import java.sql.SQLException;

public interface PessoaRepository {
    
    public Pessoa inserir(Pessoa pessoa) throws SQLException;
    public void atualizar(Pessoa pessoa) throws SQLException;
    public void inativar(Long id) throws SQLException;
}
