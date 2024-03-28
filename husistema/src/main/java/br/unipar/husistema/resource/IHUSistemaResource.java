package br.unipar.husistema.resource;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.dto.MedicoDTO;
import br.unipar.husistema.dto.PacienteDTO;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface IHUSistemaResource {
    
    @WebMethod
    public void inativar(Long id);
    
    @WebMethod
    public void inserirMedico(MedicoDTO dto) throws ValidacaoExcecao, Exception;
    
    @WebMethod
    public List<MedicoDTO> acharTodosMedicos();
    
    @WebMethod
    public void atualizarMedico(Long id, MedicoDTO dto);
    
    @WebMethod
    public void inserirPaciente(PacienteDTO dto) throws ValidacaoExcecao, Exception;
    
    @WebMethod
    public List<PacienteDTO> acharTodosPacientes();
    
    @WebMethod
    public void atualizarPaciente(Long id, PacienteDTO dto);
        
    @WebMethod
    public void inserirConsulta(InserirConsultaDTO dto);
        
    @WebMethod
    public void cancelarConsulta(Long id, CancelarConsultaDTO dto);
}
