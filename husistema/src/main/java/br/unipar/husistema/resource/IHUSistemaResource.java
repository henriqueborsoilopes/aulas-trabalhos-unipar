package br.unipar.husistema.resource;

import br.unipar.husistema.model.Medico;
import br.unipar.husistema.model.Paciente;
import br.unipar.husistema.service.exception.ConexaoException;
import br.unipar.husistema.service.exception.ValidacaoException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface IHUSistemaResource {
    
    @WebMethod
    public Medico inserirMedico(Medico medico) throws ValidacaoException, ConexaoException;
    
//    @WebMethod
//    public List<Medico> acharTodosMedicos() throws BancoDadosException;
//    
//    @WebMethod
//    public void atualizarMedico(Long id, Medico medico) throws BancoDadosException;
//    
//    @WebMethod
//    public void excluirMedico(Long id, boolean ativo) throws BancoDadosException;
    
    @WebMethod
    public Paciente inserirPaciente(Paciente paciente) throws ValidacaoException, ConexaoException;
    
    @WebMethod
    public List<Paciente> acharTodosPacientes() throws ConexaoException;
    
    @WebMethod
    public void atualizarPaciente(Long id, Paciente paciente) throws ConexaoException;
    
    @WebMethod
    public void excluirPaciente(Long id, boolean ativo) throws ConexaoException;
}
