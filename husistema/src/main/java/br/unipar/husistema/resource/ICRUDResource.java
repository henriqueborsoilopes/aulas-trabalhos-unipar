package br.unipar.husistema.resource;

import br.unipar.husistema.service.validation.BancoDadosException;
import br.unipar.husistema.service.validation.ValidacaoException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface ICRUDResource<T> {
    
    @WebMethod
    public T inserir(T modelo) throws ValidacaoException, BancoDadosException;
    
    @WebMethod
    public List<T> acharTodos() throws BancoDadosException;
    
    @WebMethod
    public void atualizar(Long id, T modelo) throws BancoDadosException;
    
    @WebMethod
    public void excluir(Long id, boolean ativo) throws BancoDadosException;
}
