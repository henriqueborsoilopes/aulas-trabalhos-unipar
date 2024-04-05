package br.unipar.husistema.service.imple;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.entity.Endereco;
import br.unipar.husistema.entity.Pessoa;
import br.unipar.husistema.mapper.EnderecoMapper;
import br.unipar.husistema.mapper.PessoaMapper;
import br.unipar.husistema.service.exception.ValidarExcecao;
import br.unipar.husistema.service.IPessoaService;
import br.unipar.husistema.factory.IRepositoryFactory;
import br.unipar.husistema.service.connection.ConexaoBD;
import br.unipar.husistema.service.validation.Validar;

public class PessoaServiceImple implements IPessoaService {
    
    private final IRepositoryFactory repository;
    
    public PessoaServiceImple(IRepositoryFactory repository) {
        this.repository = repository;
    }
    
    @Override
    public void atualizar(Long id_usuario, Long id_endereco, AtualizarPessoaDTO dto) throws ValidarExcecao {
        ConexaoBD.abrirConexao();
        ConexaoBD.manterConexaoAberta(true);
        ConexaoBD.autoCommit(false);
        Validar.atualizacaoPaciente(id_usuario, dto);
        Pessoa pessoa = PessoaMapper.getEntidade(dto);
        Endereco endereco = EnderecoMapper.getEntidade(dto.getEndereco());
        pessoa.setId(id_usuario);
        endereco.setId(id_endereco);
        repository.getEnderecoRepository().atualizar(endereco);
        repository.getPessoaRepository().atualizar(pessoa);
        ConexaoBD.commit();
        ConexaoBD.fecharConexao();
    }
    
    @Override
    public void inativar(Long id) throws ValidarExcecao {
        Validar.inativacao(id);
        ConexaoBD.abrirConexao();
        ConexaoBD.autoCommit(false);
        repository.getPessoaRepository().inativar(id);
        ConexaoBD.commit();
        ConexaoBD.fecharConexao();
    }
}
