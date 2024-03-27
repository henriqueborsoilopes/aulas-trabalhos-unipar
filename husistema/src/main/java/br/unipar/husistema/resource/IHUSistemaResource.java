package br.unipar.husistema.resource;

import br.unipar.husistema.model.Consulta;
import br.unipar.husistema.model.Medico;
import br.unipar.husistema.model.Paciente;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface IHUSistemaResource {
    
    @WebMethod
    public Medico inserirMedico(Medico medico);
    
    @WebMethod
    public List<Medico> acharTodosMedicos();
    
    @WebMethod
    public void atualizarMedico(Long id, Medico medico);
    
    @WebMethod
    public void excluirMedico(Long id);
    
    @WebMethod
    public Paciente inserirPaciente(Paciente paciente);
    
    @WebMethod
    public List<Paciente> acharTodosPacientes();
    
    @WebMethod
    public void atualizarPaciente(Long id, Paciente paciente);
    
    @WebMethod
    public void excluirPaciente(Long id);
    
    @WebMethod
    public Consulta inserirConsulta(Consulta consulta);
    
    @WebMethod
    public void atualizarConsulta(Long id, Consulta consulta);
}
