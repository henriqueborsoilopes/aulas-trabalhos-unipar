package br.unipar.husistema.factory;

import br.unipar.husistema.repository.ConsultaRepository;
import br.unipar.husistema.repository.EnderecoRepository;
import br.unipar.husistema.repository.MedicoRepository;
import br.unipar.husistema.repository.PacienteRepository;
import br.unipar.husistema.repository.PessoaRepository;


public interface RepositoryFactory {
    
    ConsultaRepository getConsultaRepository();
    EnderecoRepository getEnderecoRepository();
    MedicoRepository getMedicoRepository();
    PacienteRepository getPacienteRepository();
    PessoaRepository getPessoaRepository();
}
