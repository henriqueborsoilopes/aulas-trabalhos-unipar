package br.unipar.husistema.factory;

import br.unipar.husistema.repository.IConsultaRepository;
import br.unipar.husistema.repository.IEnderecoRepository;
import br.unipar.husistema.repository.IMedicoRepository;
import br.unipar.husistema.repository.IPacienteRepository;
import br.unipar.husistema.repository.IPessoaRepository;

public interface IRepositoryFactory {
    
    IConsultaRepository getConsultaRepository();
    IEnderecoRepository getEnderecoRepository();
    IMedicoRepository getMedicoRepository();
    IPacienteRepository getPacienteRepository();
    IPessoaRepository getPessoaRepository();
}
