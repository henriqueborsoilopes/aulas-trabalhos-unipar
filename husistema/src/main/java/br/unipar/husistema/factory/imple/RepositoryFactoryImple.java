package br.unipar.husistema.factory.imple;

import br.unipar.husistema.factory.RepositoryFactory;
import br.unipar.husistema.repository.ConsultaRepository;
import br.unipar.husistema.repository.EnderecoRepository;
import br.unipar.husistema.repository.MedicoRepository;
import br.unipar.husistema.repository.PacienteRepository;
import br.unipar.husistema.repository.PessoaRepository;
import br.unipar.husistema.repository.imple.ConsultaRepositoryImple;
import br.unipar.husistema.repository.imple.EnderecoRepositoryImple;
import br.unipar.husistema.repository.imple.MedicoRepositoryImple;
import br.unipar.husistema.repository.imple.PacienteRepositoryImple;
import br.unipar.husistema.repository.imple.PessoaRepositoryImple;

public class RepositoryFactoryImple implements RepositoryFactory {
    
    @Override
    public ConsultaRepository getConsultaRepository() {
        return new ConsultaRepositoryImple();
    }
    
    @Override
    public EnderecoRepository getEnderecoRepository() {
        return new EnderecoRepositoryImple();
    }
    
    @Override
    public MedicoRepository getMedicoRepository() {
        return new MedicoRepositoryImple();
    }
    
    @Override
    public PacienteRepository getPacienteRepository() {
        return new PacienteRepositoryImple();
    }
    
    @Override
    public PessoaRepository getPessoaRepository() {
        return new PessoaRepositoryImple();
    }
}
