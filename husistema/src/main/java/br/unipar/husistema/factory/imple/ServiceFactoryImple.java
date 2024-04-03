package br.unipar.husistema.factory.imple;

import br.unipar.husistema.service.imple.ConsultaServiceImple;
import br.unipar.husistema.service.imple.MedicoServiceImple;
import br.unipar.husistema.service.imple.PacienteServiceImple;
import br.unipar.husistema.service.imple.PessoaServiceImple;
import br.unipar.husistema.service.IConsultaService;
import br.unipar.husistema.service.IMedicoService;
import br.unipar.husistema.service.IPacienteService;
import br.unipar.husistema.service.IPessoaService;
import br.unipar.husistema.factory.IRepositoryFactory;
import br.unipar.husistema.factory.IServiceFactory;

public class ServiceFactoryImple implements IServiceFactory {
    
    private final IRepositoryFactory repository = new RepositoryFactoryImple();

    @Override
    public IConsultaService getConsultaService() {
        return new ConsultaServiceImple(repository);
    }

    @Override
    public IMedicoService getMedicoService() {
        return new MedicoServiceImple(repository);
    }

    @Override
    public IPacienteService getPacienteService() {
        return new PacienteServiceImple(repository);
    }

    @Override
    public IPessoaService getPessoaService() {
        return new PessoaServiceImple(repository);
    }
}
