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
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

public interface IHUSistemaResourceRest {
       
    @Path("/usuarios/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public void atualizarUsuario(@PathParam("id") Long id, @PathParam("id") Long id_endereco, AtualizarPessoaDTO dto) throws ValidarExcecao;
      
    @Path("/usuarios/{id}")
    @POST
    public void inativarUsuario(@PathParam("id") Long id) throws ValidarExcecao;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/medicos")
    public void inserirMedico(InserirMedicoDTO dto) throws ValidarExcecao;
    
    @GET
    @Path("/medicos")
    public List<ListMedicoDTO> acharTodosMedicos() throws BancoDadosExcecao;
 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/pacientes")
    public void inserirPaciente(InserirPacienteDTO dto) throws ValidarExcecao;
    
    @GET
    @Path("/pacientes")
    public List<ListPacienteDTO> acharTodosPacientes() throws BancoDadosExcecao;
  
    @Path("/consultas")
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public void inserirConsulta(InserirConsultaDTO dto) throws ValidarExcecao;
        
    @Path("/consultas/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    public void cancelarConsulta(@PathParam("id") Long id, CancelarConsultaDTO dto) throws ValidarExcecao;
}
