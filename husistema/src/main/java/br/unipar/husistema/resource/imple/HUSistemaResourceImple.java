package br.unipar.husistema.resource.imple;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.Paciente;
import jakarta.jws.WebService;
import br.unipar.husistema.service.PacienteService;
import java.util.List;
import br.unipar.husistema.resource.IHUSistemaResource;
import br.unipar.husistema.service.ConsultaService;
import br.unipar.husistema.service.MedicoService;
import br.unipar.husistema.service.PessoaService;
import br.unipar.husistema.service.exception.ValidacaoExcecao;

@WebService(serviceName = "HUSistemaResourceImple")
public class HUSistemaResourceImple implements IHUSistemaResource {
    
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final ConsultaService consultaService;
    private final PessoaService pessoaService;
    
    public HUSistemaResourceImple() {
        this.medicoService = new MedicoService();
        this.pacienteService = new PacienteService();
        this.consultaService = new ConsultaService();
        this.pessoaService = new PessoaService();
    }
    
    @Override
    public void inativar(Long id) {
        try {
            pessoaService.inativar(id);
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void inserirMedico(Medico medico) throws ValidacaoExcecao, Exception {
        try {
           medicoService.inserir(medico); 
        } catch (Exception e) {
            
        }
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
    public void inserirPaciente(Paciente paciente) throws ValidacaoExcecao, Exception {
        pacienteService.inserir(paciente);
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
    public void inserirConsulta(InserirConsultaDTO consultaDTO) {
        try {
            consultaService.inserir(consultaDTO);
        } catch (Exception e) {
            
        }
    }

    @Override
    public void cancelarConsulta(Long id, CancelarConsultaDTO consultaDTO) {
        try {
            consultaService.cancelar(id, consultaDTO);
        } catch (Exception e) {
            
        }
    }
}
