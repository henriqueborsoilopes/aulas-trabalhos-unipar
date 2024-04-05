package br.unipar.husistema.service.imple;

import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.ListMedicoDTO;
import br.unipar.husistema.entity.Endereco;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.mapper.EnderecoMapper;
import br.unipar.husistema.mapper.MedicoMapper;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidarExcecao;
import java.time.LocalDateTime;
import java.util.List;
import br.unipar.husistema.service.IMedicoService;
import br.unipar.husistema.factory.IRepositoryFactory;
import br.unipar.husistema.service.connection.ConexaoBD;
import br.unipar.husistema.service.validation.Validar;

public class MedicoServiceImple implements IMedicoService {
    
    private final IRepositoryFactory repository;
    
    public MedicoServiceImple(IRepositoryFactory repository) {
        this.repository = repository;
    }
    
    @Override
    public Medico inserir(InserirMedicoDTO dto) throws ValidarExcecao {
        ConexaoBD.abrirConexao();
        ConexaoBD.manterConexaoAberta(true);
        ConexaoBD.autoCommit(false);
        Validar.insercaoMedico(dto);
        Medico entity = MedicoMapper.getEntidade(dto);
        Endereco endereco = EnderecoMapper.getEntidade(dto.getEndereco());
        entity.setIdEndereco(repository.getEnderecoRepository().inserir(endereco).getId());
        entity.setId(repository.getPessoaRepository().inserir(entity).getId());
        repository.getMedicoRepository().inserir(entity);
        ConexaoBD.commit();
        ConexaoBD.fecharConexao();
        return entity;
    }
    
    @Override
    public Long acharMedicoDisponivel(LocalDateTime data) throws BancoDadosExcecao {
        ConexaoBD.abrirConexao();
        Long id_medico = repository.getMedicoRepository().acharMedicoDisponivel(data);
        ConexaoBD.fecharConexao();
        return id_medico;
    }
    
    @Override
    public Medico acharPorId(Long id) throws BancoDadosExcecao {
        ConexaoBD.abrirConexao();
        Medico medico = repository.getMedicoRepository().acharPorId(id);
        ConexaoBD.fecharConexao();
        return medico;
    }
    
    @Override
    public List<ListMedicoDTO> acharTodos() throws BancoDadosExcecao {
        ConexaoBD.abrirConexao();
        List<Medico> medicos = repository.getMedicoRepository().acharTodos();
        ConexaoBD.fecharConexao();
        return MedicoMapper.getListaDTO(medicos);
    }
}
