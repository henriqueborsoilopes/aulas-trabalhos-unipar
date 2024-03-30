package br.unipar.husistema.resource;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.dto.ListMedicoDTO;
import br.unipar.husistema.dto.ListPacienteDTO;
import br.unipar.husistema.service.exception.BancoDadosException;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface IHUSistemaResource {
       
    @WebMethod
    public void atualizarUsuario(Long id, AtualizarPessoaDTO dto) throws BancoDadosException, ValidacaoExcecao ;
      
    @WebMethod
    public void inativarUsuario(Long id) throws BancoDadosException;
    
    @WebMethod
    public void inserirMedico(InserirMedicoDTO dto) throws ValidacaoExcecao, Exception;
    
    @WebMethod
    public List<ListMedicoDTO> acharTodosMedicos() throws BancoDadosException;
 
    @WebMethod
    public void inserirPaciente(InserirPacienteDTO dto) throws ValidacaoExcecao, Exception;
    
    @WebMethod
    public List<ListPacienteDTO> acharTodosPacientes() throws BancoDadosException;
  
    @WebMethod
    public void inserirConsulta(InserirConsultaDTO dto) throws BancoDadosException, ValidacaoExcecao ;
        
    @WebMethod
    public void cancelarConsulta(Long id, CancelarConsultaDTO dto) throws BancoDadosException, ValidacaoExcecao ;
}
