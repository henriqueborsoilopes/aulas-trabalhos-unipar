package br.unipar.husistema.service;

import br.unipar.husistema.dto.AtualizarPessoaDTO;
import br.unipar.husistema.entity.Pessoa;
import br.unipar.husistema.factory.ConnectionFactory;
import br.unipar.husistema.mapper.PessoaMapper;
import br.unipar.husistema.repository.EnderecoRepository;
import br.unipar.husistema.repository.PessoaRepository;
import br.unipar.husistema.service.exception.BancoDadosException;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import br.unipar.husistema.service.validation.ValidacaoService;
import java.sql.Connection;
import java.sql.SQLException;

public class PessoaService {
    
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaMapper pessoaMapper;
    
    public PessoaService() {
        this.pessoaRepository = new PessoaRepository();
        this.pessoaMapper = new PessoaMapper();
        this.enderecoRepository = new EnderecoRepository();
    }
    
    public void atualizar(Long id, AtualizarPessoaDTO dto) throws BancoDadosException, ValidacaoExcecao {
        ValidacaoService.validarAtualizacaoPaciente(id, dto);
        Pessoa pessoa = pessoaMapper.getEntity(dto);
        Connection connection = ConnectionFactory.getConnection();
        try {
            pessoa.setId(id);
            connection.setAutoCommit(false);
            enderecoRepository.atualizar(connection, id, pessoa.getEndereco());
            pessoaRepository.atualizar(connection, pessoa);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new BancoDadosException("Falha na conexão");
            } catch (SQLException ex) {
                throw new BancoDadosException("Falha na conexão");
            }
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public void inativar(Long id) throws BancoDadosException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            pessoaRepository.inativar(connection, id);
        } catch (SQLException e) {
            throw new BancoDadosException("Falha na conexão");
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
}
