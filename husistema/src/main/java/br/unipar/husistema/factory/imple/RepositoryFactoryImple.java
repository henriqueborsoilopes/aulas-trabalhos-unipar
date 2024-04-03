package br.unipar.husistema.factory.imple;

import br.unipar.husistema.repository.imple.ConsultaRepositoryImple;
import br.unipar.husistema.repository.imple.EnderecoRepositoryImple;
import br.unipar.husistema.repository.imple.MedicoRepositoryImple;
import br.unipar.husistema.repository.imple.PacienteRepositoryImple;
import br.unipar.husistema.repository.imple.PessoaRepositoryImple;
import br.unipar.husistema.repository.IConsultaRepository;
import br.unipar.husistema.repository.IEnderecoRepository;
import br.unipar.husistema.repository.IMedicoRepository;
import br.unipar.husistema.repository.IPacienteRepository;
import br.unipar.husistema.repository.IPessoaRepository;
import br.unipar.husistema.factory.IRepositoryFactory;

public class RepositoryFactoryImple implements IRepositoryFactory {
    
    @Override
    public IConsultaRepository getConsultaRepository() {
        return new ConsultaRepositoryImple();
    }
    
    @Override
    public IEnderecoRepository getEnderecoRepository() {
        return new EnderecoRepositoryImple();
    }
    
    @Override
    public IMedicoRepository getMedicoRepository() {
        return new MedicoRepositoryImple();
    }
    
    @Override
    public IPacienteRepository getPacienteRepository() {
        return new PacienteRepositoryImple();
    }
    
    @Override
    public IPessoaRepository getPessoaRepository() {
        return new PessoaRepositoryImple();
    }
}
