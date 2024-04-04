package br.unipar.husistema.service.imple;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.entity.Endereco;
import br.unipar.husistema.entity.Pessoa;
import br.unipar.husistema.factory.ConnectionFactory;
import br.unipar.husistema.mapper.EnderecoMapper;
import br.unipar.husistema.mapper.PessoaMapper;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import br.unipar.husistema.service.validation.PessoaValidacao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.unipar.husistema.service.IPessoaService;
import br.unipar.husistema.factory.IRepositoryFactory;

public class PessoaServiceImple implements IPessoaService {
    
    private final IRepositoryFactory repository;
    
    public PessoaServiceImple(IRepositoryFactory repository) {
        this.repository = repository;
    }
    
    @Override
    public void atualizar(Long id_usuario, Long id_endereco, AtualizarPessoaDTO dto) throws BancoDadosExcecao, ValidacaoExcecao {
        ConnectionFactory.abrirConexao();
        ConnectionFactory.manterConexaoAberta(true);
        ConnectionFactory.autoCommit(false);
        PessoaValidacao.validarAtualizacaoPaciente(id_usuario, dto);
        Pessoa pessoa = PessoaMapper.getEntity(dto);
        Endereco endereco = EnderecoMapper.getEntity(dto.getEndereco());
        try {
            pessoa.setId(id_usuario);
            endereco.setId(id_endereco);
            repository.getEnderecoRepository().atualizar(endereco);
            repository.getPessoaRepository().atualizar(pessoa);
            ConnectionFactory.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaServiceImple.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }
    
    @Override
    public void inativar(Long id) throws BancoDadosExcecao {
        ConnectionFactory.abrirConexao();
        ConnectionFactory.autoCommit(false);
        try {
            repository.getPessoaRepository().inativar(id);
            ConnectionFactory.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaServiceImple.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }
}
