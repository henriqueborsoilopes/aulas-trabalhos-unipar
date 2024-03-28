package br.unipar.husistema.resource.imple;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.dto.MedicoDTO;
import br.unipar.husistema.dto.PacienteDTO;
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
    public void inserirMedico(MedicoDTO dto) throws ValidacaoExcecao, Exception {
        try {
           medicoService.inserir(dto); 
        } catch (Exception e) {
            
        }
    }

    @Override
    public List<MedicoDTO> acharTodosMedicos() {
        try {
            return medicoService.acharTodos();
        } catch (Exception e) {
            
        }
        return null;
    }
    
    @Override
    public void atualizarMedico(Long id, MedicoDTO dto) {
        try {
            medicoService.atualizar(id, dto);
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void inserirPaciente(PacienteDTO dto) throws ValidacaoExcecao, Exception {
        pacienteService.inserir(dto);
    }

    @Override
    public List<PacienteDTO> acharTodosPacientes() {
        try {
            return pacienteService.acharTodos();
        } catch (Exception e) {
            
        }
        return null;
    }
    
    @Override
    public void atualizarPaciente(Long id, PacienteDTO dto) {
        try {
            pacienteService.atualizar(id, dto);
        } catch (Exception e) {
            
        }
    }

    @Override
    public void inserirConsulta(InserirConsultaDTO dto) {
        try {
            consultaService.inserir(dto);
        } catch (Exception e) {
            
        }
    }

    @Override
    public void cancelarConsulta(Long id, CancelarConsultaDTO dto) {
        try {
            consultaService.cancelar(id, dto);
        } catch (Exception e) {
            
        }
    }
}
