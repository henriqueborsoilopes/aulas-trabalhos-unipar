package br.unipar.husistema.resource;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface IHUSistemaResource {
    
    @WebMethod
    public void inativar(Long id);
    
    @WebMethod
    public void inserirMedico(Medico medico) throws ValidacaoExcecao, Exception;
    
    @WebMethod
    public List<Medico> acharTodosMedicos();
    
    @WebMethod
    public void atualizarMedico(Long id, Medico medico);
    
    @WebMethod
    public void inserirPaciente(Paciente paciente) throws ValidacaoExcecao, Exception;
    
    @WebMethod
    public List<Paciente> acharTodosPacientes();
    
    @WebMethod
    public void atualizarPaciente(Long id, Paciente paciente);
        
    @WebMethod
    public void inserirConsulta(InserirConsultaDTO consultaDTO);
        
    @WebMethod
    public void cancelarConsulta(Long id, CancelarConsultaDTO consultaDTO);
}
