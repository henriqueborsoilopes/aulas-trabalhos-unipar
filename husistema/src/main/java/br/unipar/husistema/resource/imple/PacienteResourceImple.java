package br.unipar.husistema.resource.imple;

import br.unipar.husistema.model.Paciente;
import jakarta.jws.WebService;
import br.unipar.husistema.resource.ICRUDResource;
import br.unipar.husistema.service.PacienteService;
import br.unipar.husistema.service.validation.BancoDadosException;
import br.unipar.husistema.service.validation.ValidacaoException;
import java.util.List;

@WebService(serviceName = "PacienteResourceImple")
public class PacienteResourceImple implements ICRUDResource<Paciente> {
    
    private final PacienteService service = new PacienteService();

    @Override
    public Paciente inserir(Paciente paciente) throws ValidacaoException, BancoDadosException {
        return service.inserir(paciente);
    }

    @Override
    public List<Paciente> acharTodos() throws BancoDadosException {
        return service.acharTodos();
    }
    
    @Override
    public void atualizar(Long id, Paciente paciente) throws BancoDadosException {
        service.atualizar(id, paciente);
    }

    @Override
    public void excluir(Long id, boolean ativo) throws BancoDadosException {
        service.excluir(id, ativo);
    }
}
