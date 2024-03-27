package br.unipar.husistema.resource.imple;

import br.unipar.husistema.model.Consulta;
import br.unipar.husistema.model.Medico;
import br.unipar.husistema.model.Paciente;
import jakarta.jws.WebService;
import br.unipar.husistema.service.PacienteService;
import java.util.List;
import br.unipar.husistema.resource.IHUSistemaResource;
import br.unipar.husistema.service.ConsultaService;
import br.unipar.husistema.service.MedicoService;

@WebService(serviceName = "HUSistemaResourceImple")
public class HUSistemaResourceImple implements IHUSistemaResource {
    
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final ConsultaService consultaService;
    
    public HUSistemaResourceImple() {
        this.medicoService = new MedicoService();
        this.pacienteService = new PacienteService();
        this.consultaService = new ConsultaService();
    }
    
    @Override
    public Medico inserirMedico(Medico medico) {
        try {
           return medicoService.inserir(medico); 
        } catch (Exception e) {
            
        }
        return null;
    }

    @Override
    public List<Medico> acharTodosMedicos() {
        try {
            return medicoService.acharTodos();
        } catch (Exception e) {
            
        }
        return null;
    }
    
    @Override
    public void atualizarMedico(Long id, Medico medico) {
        try {
            medicoService.atualizar(id, medico);
        } catch (Exception e) {
            
        }
    }

    @Override
    public void excluirMedico(Long id) {
        try {
            medicoService.excluir(id);
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public Paciente inserirPaciente(Paciente paciente) {
        try {
            return pacienteService.inserir(paciente);
        } catch (Exception e) {
            
        }
        return null;
    }

    @Override
    public List<Paciente> acharTodosPacientes() {
        try {
            return pacienteService.acharTodos();
        } catch (Exception e) {
            
        }
        return null;
    }
    
    @Override
    public void atualizarPaciente(Long id, Paciente paciente) {
        try {
            pacienteService.atualizar(id, paciente);
        } catch (Exception e) {
            
        }
    }

    @Override
    public void excluirPaciente(Long id) {
        try {
            pacienteService.excluir(id);
        } catch (Exception e) {
            
        }
    }

    @Override
    public Consulta inserirConsulta(Consulta consulta) {
        try {
            return null;
        } catch (Exception e) {
            
        }
        return null;
    }

    @Override
    public void atualizarConsulta(Long id, Consulta consulta) {
        try {
            //consultaService.atualizar(id, consulta);
        } catch (Exception e) {
            
        }
    }
}
