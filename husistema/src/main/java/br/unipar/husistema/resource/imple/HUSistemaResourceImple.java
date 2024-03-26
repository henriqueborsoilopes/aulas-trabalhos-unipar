package br.unipar.husistema.resource.imple;

import br.unipar.husistema.model.Medico;
import br.unipar.husistema.model.Paciente;
import jakarta.jws.WebService;
import br.unipar.husistema.service.PacienteService;
import br.unipar.husistema.service.exception.ValidacaoException;
import java.util.List;
import br.unipar.husistema.resource.IHUSistemaResource;
import br.unipar.husistema.service.MedicoService;
import br.unipar.husistema.service.exception.ConexaoException;

@WebService(serviceName = "HUSistemaResourceImple")
public class HUSistemaResourceImple implements IHUSistemaResource {
    
    private final MedicoService medicoService = new MedicoService();
    private final PacienteService pacienteService = new PacienteService();
    
    @Override
    public Medico inserirMedico(Medico medico) throws ValidacaoException, ConexaoException {
        return medicoService.inserir(medico);
    }

//    @Override
//    public List<Medico> acharTodosMedicos() throws ConexaoException {
//        return medicoService.acharTodos();
//    }
//    
//    @Override
//    public void atualizarMedico(Long id, Medico medico) throws ConexaoException {
//        medicoService.atualizar(id, medico);
//    }
//
//    @Override
//    public void excluirMedico(Long id, boolean ativo) throws ConexaoException {
//        medicoService.excluir(id, ativo);
//    }
    
        @Override
    public Paciente inserirPaciente(Paciente paciente) throws ValidacaoException, ConexaoException {
        return pacienteService.inserir(paciente);
    }

    @Override
    public List<Paciente> acharTodosPacientes() throws ConexaoException {
        return pacienteService.acharTodos();
    }
    
    @Override
    public void atualizarPaciente(Long id, Paciente paciente) throws ConexaoException {
        pacienteService.atualizar(id, paciente);
    }

    @Override
    public void excluirPaciente(Long id, boolean ativo) throws ConexaoException {
        pacienteService.excluir(id, ativo);
    }
}
