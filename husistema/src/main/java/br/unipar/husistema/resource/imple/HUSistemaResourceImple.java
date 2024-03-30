package br.unipar.husistema.resource.imple;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.dto.ListMedicoDTO;
import br.unipar.husistema.dto.ListPacienteDTO;
import jakarta.jws.WebService;
import br.unipar.husistema.service.PacienteService;
import java.util.List;
import br.unipar.husistema.resource.IHUSistemaResource;
import br.unipar.husistema.service.ConsultaService;
import br.unipar.husistema.service.MedicoService;
import br.unipar.husistema.service.PessoaService;
import br.unipar.husistema.service.exception.BancoDadosException;
import br.unipar.husistema.service.exception.ValidacaoExcecao;

@WebService(serviceName = "HUSistemaResourceImple")
public class HUSistemaResourceImple implements IHUSistemaResource {
    
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final ConsultaService consultaService;
    private final PessoaService pessoaService;
    
    public HUSistemaResourceImple() {
        this.medicoService = new MedicoService();
        this.pacienteService = new PacienteService();
        this.consultaService = new ConsultaService();
        this.pessoaService = new PessoaService();
    }
    
    @Override
    public void atualizarUsuario(Long id, AtualizarPessoaDTO dto) throws BancoDadosException, ValidacaoExcecao {
        pessoaService.atualizar(id, dto);
    }
    
    @Override
    public void inativarUsuario(Long id) throws BancoDadosException {
        pessoaService.inativar(id);
    }
    
    @Override
    public void inserirMedico(InserirMedicoDTO dto) throws ValidacaoExcecao, BancoDadosException {
        medicoService.inserir(dto);
    }

    @Override
    public List<ListMedicoDTO> acharTodosMedicos() throws BancoDadosException {
        return medicoService.acharTodos();
    }
    
    @Override
    public void inserirPaciente(InserirPacienteDTO dto) throws ValidacaoExcecao, Exception {
        pacienteService.inserir(dto);
    }

    @Override
    public List<ListPacienteDTO> acharTodosPacientes() throws BancoDadosException {
        return pacienteService.acharTodos();
    }

    @Override
    public void inserirConsulta(InserirConsultaDTO dto) throws BancoDadosException, ValidacaoExcecao {
        consultaService.inserir(dto);
    }

    @Override
    public void cancelarConsulta(Long id, CancelarConsultaDTO dto) throws BancoDadosException, ValidacaoExcecao {
        consultaService.cancelar(id, dto);
    }
}
