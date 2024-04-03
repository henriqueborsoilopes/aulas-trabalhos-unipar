package br.unipar.husistema.factory.imple;

import br.unipar.husistema.factory.RepositoryFactory;
import br.unipar.husistema.factory.ServiceFactory;
import br.unipar.husistema.service.ConsultaService;
import br.unipar.husistema.service.MedicoService;
import br.unipar.husistema.service.PacienteService;
import br.unipar.husistema.service.PessoaService;
import br.unipar.husistema.service.imple.ConsultaServiceImple;
import br.unipar.husistema.service.imple.MedicoServiceImple;
import br.unipar.husistema.service.imple.PacienteServiceImple;
import br.unipar.husistema.service.imple.PessoaServiceImple;

public class ServiceFactoryImple implements ServiceFactory {
    
    private final RepositoryFactory repository = new RepositoryFactoryImple();

    @Override
    public ConsultaService getConsultaService() {
        return new ConsultaServiceImple(repository);
    }

    @Override
    public MedicoService getMedicoService() {
        return new MedicoServiceImple(repository);
    }

    @Override
    public PacienteService getPacienteService() {
        return new PacienteServiceImple(repository);
    }

    @Override
    public PessoaService getPessoaService() {
        return new PessoaServiceImple(repository);
    }
}
