package br.unipar.husistema.factory;

import br.unipar.husistema.service.IConsultaService;
import br.unipar.husistema.service.IMedicoService;
import br.unipar.husistema.service.IPacienteService;
import br.unipar.husistema.service.IPessoaService;


public interface IServiceFactory {
    
    IConsultaService getConsultaService();
    IMedicoService getMedicoService();
    IPacienteService getPacienteService();
    IPessoaService getPessoaService();
}
