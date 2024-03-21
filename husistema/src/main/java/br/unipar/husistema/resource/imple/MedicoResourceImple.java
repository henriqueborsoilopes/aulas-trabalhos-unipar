package br.unipar.husistema.resource.imple;

import br.unipar.husistema.model.Medico;
import jakarta.jws.WebService;
import br.unipar.husistema.resource.ICRUDResource;
import br.unipar.husistema.service.MedicoService;
import br.unipar.husistema.service.validation.BancoDadosException;
import br.unipar.husistema.service.validation.ValidacaoException;
import java.util.List;

@WebService(serviceName = "MedicoResourceImple")
public class MedicoResourceImple implements ICRUDResource<Medico> {
    
    private final MedicoService service = new MedicoService();

    @Override
    public Medico inserir(Medico medico) throws ValidacaoException, BancoDadosException {
        return service.inserir(medico);
    }

    @Override
    public List<Medico> acharTodos() throws BancoDadosException {
        return service.acharTodos();
    }
    
    @Override
    public void atualizar(Long id, Medico modelo) throws BancoDadosException {
        service.atualizar(id, modelo);
    }

    @Override
    public void excluir(Long id, boolean ativo) throws BancoDadosException {
        service.excluir(id, ativo);
    }
}
