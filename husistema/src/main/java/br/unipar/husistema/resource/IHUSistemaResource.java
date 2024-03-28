package br.unipar.husistema.resource;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.dto.MedicoDTO;
import br.unipar.husistema.dto.PacienteDTO;
import br.unipar.husistema.service.exception.BancoDadosException;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface IHUSistemaResource {
    
    @WebMethod
    public void inativar(Long id) throws BancoDadosException;
    
    @WebMethod
    public void inserirMedico(MedicoDTO dto) throws ValidacaoExcecao, Exception;
    
    @WebMethod
    public List<MedicoDTO> acharTodosMedicos() throws BancoDadosException;
    
    @WebMethod
    public void atualizarMedico(Long id, MedicoDTO dto) throws BancoDadosException, ValidacaoExcecao;
    
    @WebMethod
    public void inserirPaciente(PacienteDTO dto) throws ValidacaoExcecao, Exception;
    
    @WebMethod
    public List<PacienteDTO> acharTodosPacientes() throws BancoDadosException;
    
    @WebMethod
    public void atualizarPaciente(Long id, PacienteDTO dto) throws BancoDadosException, ValidacaoExcecao ;
        
    @WebMethod
    public void inserirConsulta(InserirConsultaDTO dto) throws BancoDadosException, ValidacaoExcecao ;
        
    @WebMethod
    public void cancelarConsulta(Long id, CancelarConsultaDTO dto) throws BancoDadosException, ValidacaoExcecao ;
}
