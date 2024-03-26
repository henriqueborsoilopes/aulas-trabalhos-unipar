package br.unipar.husistema.service;

import br.unipar.husistema.model.Medico;
import br.unipar.husistema.repository.MedicoRepository;
import br.unipar.husistema.service.exception.ConexaoException;

public class MedicoService {
    
    private MedicoRepository repository = null;

    public MedicoService() {
        this.repository = new MedicoRepository();
    }
    
    public Medico inserir(Medico medico) throws ConexaoException {
        return repository.inserir(medico);
    }
}
