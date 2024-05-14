package br.unipar.husistema.resource.imple;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.dto.ListMedicoDTO;
import br.unipar.husistema.dto.ListPacienteDTO;
import br.unipar.husistema.factory.imple.ServiceFactoryImple;
import br.unipar.husistema.service.exception.ValidarExcecao;
import java.util.List;
import br.unipar.husistema.factory.IServiceFactory;
import br.unipar.husistema.resource.IHUSistemaResourceRest;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/resources")
@Produces(MediaType.APPLICATION_JSON)
public class HUSistemaResourceRestImple implements IHUSistemaResourceRest {
    public HUSistemaResourceRestImple() {
        
    }
    
    private final IServiceFactory service = new ServiceFactoryImple();
    
    @Override
    public void atualizarUsuario(Long id_usuario, Long id_endereco, AtualizarPessoaDTO dto) throws ValidarExcecao {
        service.getPessoaService().atualizar(id_usuario, id_endereco, dto);
    }
    
    @Override
    public void inativarUsuario(Long id) throws ValidarExcecao {
        service.getPessoaService().inativar(id);
    }
    
    @Override
    public void inserirMedico(InserirMedicoDTO dto) throws ValidarExcecao {
        service.getMedicoService().inserir(dto);
    }

    @Override
    public List<ListMedicoDTO> acharTodosMedicos() {
        return service.getMedicoService().acharTodos();
    }
    
    @Override
    public void inserirPaciente(InserirPacienteDTO dto) throws ValidarExcecao {
        service.getPacienteService().inserir(dto);
    }

    @Override
    public List<ListPacienteDTO> acharTodosPacientes() {
        return service.getPacienteService().acharTodos();
    }

    @Override
    public void inserirConsulta(InserirConsultaDTO dto) throws ValidarExcecao {
        service.getConsultaService().inserir(dto);
    }

    @Override
    public void cancelarConsulta(Long id, CancelarConsultaDTO dto) throws ValidarExcecao {
        service.getConsultaService().cancelar(id, dto);
    }
}
