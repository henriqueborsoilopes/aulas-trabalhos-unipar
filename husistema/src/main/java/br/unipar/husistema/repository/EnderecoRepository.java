package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Endereco;
import java.sql.SQLException;

public interface EnderecoRepository {
    
    public Endereco inserir(Endereco endereco) throws SQLException;
    public void atualizar(Endereco endereco) throws SQLException;
}
