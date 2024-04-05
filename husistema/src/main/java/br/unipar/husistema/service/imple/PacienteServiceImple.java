package br.unipar.husistema.service.imple;

import br.unipar.husistema.dto.InserirPacienteDTO;
import br.unipar.husistema.dto.ListPacienteDTO;
import br.unipar.husistema.entity.Endereco;
import br.unipar.husistema.entity.Paciente;
import br.unipar.husistema.mapper.EnderecoMapper;
import br.unipar.husistema.mapper.PacienteMapper;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidarExcecao;
import java.util.List;
import br.unipar.husistema.service.IPacienteService;
import br.unipar.husistema.factory.IRepositoryFactory;
import br.unipar.husistema.service.connection.ConexaoBD;
import br.unipar.husistema.service.validation.Validar;

public class PacienteServiceImple implements IPacienteService {
    
    private final IRepositoryFactory repository;
    
    public PacienteServiceImple(IRepositoryFactory repository) {
        this.repository = repository;
    }
    
    @Override
    public Paciente inserir(InserirPacienteDTO dto) throws ValidarExcecao {
        ConexaoBD.abrirConexao();
        ConexaoBD.manterConexaoAberta(true);
        ConexaoBD.autoCommit(false);
        Validar.insercaoPaciente(dto);
        Paciente paciente = PacienteMapper.getEntidade(dto);
        Endereco endereco = EnderecoMapper.getEntidade(dto.getEndereco());
        paciente.setIdEndereco(repository.getEnderecoRepository().inserir(endereco).getId());
        paciente.setId(repository.getPessoaRepository().inserir(paciente).getId());
        repository.getPacienteRepository().inserir(paciente);
        ConexaoBD.commit();
        ConexaoBD.fecharConexao();
        return paciente;
    }
    
    @Override
    public Paciente acharPorId(Long id) throws BancoDadosExcecao {
        ConexaoBD.abrirConexao();
        Paciente paciente = repository.getPacienteRepository().acharPorId(id);
        ConexaoBD.fecharConexao();
        return paciente;
    }
    
    @Override
    public List<ListPacienteDTO> acharTodos() throws BancoDadosExcecao {
        ConexaoBD.abrirConexao();
        List<Paciente> pacientes = repository.getPacienteRepository().acharTodos();
        ConexaoBD.fecharConexao();
        return PacienteMapper.getListaDTO(pacientes);
    }
}
