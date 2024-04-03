package br.unipar.husistema.resource.imple;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.dto.ListMedicoDTO;
import br.unipar.husistema.dto.ListPacienteDTO;
import br.unipar.husistema.factory.ServiceFactory;
import br.unipar.husistema.factory.imple.ServiceFactoryImple;
import br.unipar.husistema.resource.IHUSistemaResource;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import jakarta.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "br.unipar.husistema.resource.IHUSistemaResource")
public class HUSistemaResourceImple implements IHUSistemaResource {
    
    private final ServiceFactory service = new ServiceFactoryImple();
    
    @Override
    public void atualizarUsuario(Long id_usuario, Long id_endereco, AtualizarPessoaDTO dto) throws BancoDadosExcecao, ValidacaoExcecao {
        service.getPessoaService().atualizar(id_usuario, id_endereco, dto);
    }
    
    @Override
    public void inativarUsuario(Long id) throws BancoDadosExcecao {
        service.getPessoaService().inativar(id);
    }
    
    @Override
    public void inserirMedico(InserirMedicoDTO dto) throws BancoDadosExcecao, ValidacaoExcecao {
        service.getMedicoService().inserir(dto);
    }

    @Override
    public List<ListMedicoDTO> acharTodosMedicos() throws BancoDadosExcecao {
        return service.getMedicoService().acharTodos();
    }
    
    @Override
    public void inserirPaciente(InserirPacienteDTO dto) throws BancoDadosExcecao, ValidacaoExcecao {
        service.getPacienteService().inserir(dto);
    }

    @Override
    public List<ListPacienteDTO> acharTodosPacientes() throws BancoDadosExcecao {
        return service.getPacienteService().acharTodos();
    }

    @Override
    public void inserirConsulta(InserirConsultaDTO dto) throws BancoDadosExcecao, ValidacaoExcecao {
        service.getConsultaService().inserir(dto);
    }

    @Override
    public void cancelarConsulta(Long id, CancelarConsultaDTO dto) throws BancoDadosExcecao, ValidacaoExcecao {
        service.getConsultaService().cancelar(id, dto);
    }
}
