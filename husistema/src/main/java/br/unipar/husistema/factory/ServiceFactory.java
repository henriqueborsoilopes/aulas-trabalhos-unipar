package br.unipar.husistema.factory;

import br.unipar.husistema.service.ConsultaService;
import br.unipar.husistema.service.MedicoService;
import br.unipar.husistema.service.PacienteService;
import br.unipar.husistema.service.PessoaService;


public interface ServiceFactory {
    
    ConsultaService getConsultaService();
    MedicoService getMedicoService();
    PacienteService getPacienteService();
    PessoaService getPessoaService();
}
