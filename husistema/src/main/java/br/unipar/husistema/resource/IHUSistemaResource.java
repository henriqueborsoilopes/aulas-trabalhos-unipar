package br.unipar.husistema.resource;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.dto.ListMedicoDTO;
import br.unipar.husistema.dto.ListPacienteDTO;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidarExcecao;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface IHUSistemaResource {
       
    @WebMethod(operationName = "atualizar_usuario", action = "")
    public void atualizarUsuario(@WebParam(header = true, name = "id_usuario") Long id_usuario, @WebParam(header = true, name = "id_endereco") Long id_endereco, @WebParam(name = "usuario") AtualizarPessoaDTO dto) throws ValidarExcecao;
      
    @WebMethod(operationName = "inativar_usuario")
    public void inativarUsuario(@WebParam(header = true, name = "id_usuario") Long id_usuario) throws ValidarExcecao;
    
    @WebMethod(operationName = "inserir_medico")
    public void inserirMedico(@WebParam(name = "medico") InserirMedicoDTO dto) throws ValidarExcecao;
    
    @WebMethod(operationName = "listar_medico")
    @WebResult(name = "medico")
    public List<ListMedicoDTO> acharTodosMedicos() throws BancoDadosExcecao;
 
    @WebMethod(operationName = "inserir_paciente")
    public void inserirPaciente(@WebParam(name = "paciente") InserirPacienteDTO dto) throws ValidarExcecao;
    
    @WebMethod(operationName = "listar_paciente")
    @WebResult(name = "paciente")
    public List<ListPacienteDTO> acharTodosPacientes() throws BancoDadosExcecao;
  
    @WebMethod(operationName = "salvar_consulta")
    public void inserirConsulta(@WebParam(name = "consulta") InserirConsultaDTO dto) throws ValidarExcecao;
        
    @WebMethod(operationName = "cancelar_consulta")
    public void cancelarConsulta(@WebParam(header = true, name = "id_consulta") Long id, @WebParam(name = "consulta") CancelarConsultaDTO dto) throws ValidarExcecao;
}
