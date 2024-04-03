package br.unipar.husistema.service.imple;

import br.unipar.husistema.dto.InserirMedicoDTO;
import br.unipar.husistema.dto.ListMedicoDTO;
import br.unipar.husistema.entity.Endereco;
import br.unipar.husistema.entity.Medico;
import br.unipar.husistema.factory.ConnectionFactory;
import br.unipar.husistema.factory.RepositoryFactory;
import br.unipar.husistema.mapper.EnderecoMapper;
import br.unipar.husistema.mapper.MedicoMapper;
import br.unipar.husistema.service.MedicoService;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import br.unipar.husistema.service.validation.MedicoValidacao;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicoServiceImple implements MedicoService {
    
    private final RepositoryFactory repository;
    
    public MedicoServiceImple(RepositoryFactory repository) {
        this.repository = repository;
    }
    
    @Override
    public Medico inserir(InserirMedicoDTO dto) throws BancoDadosExcecao, ValidacaoExcecao {
        ConnectionFactory.abrirConexao();
        ConnectionFactory.manterConexaoAberta(true);
        ConnectionFactory.autoCommit(false);
        MedicoValidacao.validarInsercaoMedico(dto);
        Medico entity = MedicoMapper.getEntity(dto);
        Endereco endereco = EnderecoMapper.getEntity(dto.getEndereco());
        try {
            entity.setIdEndereco(repository.getEnderecoRepository().inserir(endereco).getId());
            entity.setId(repository.getPessoaRepository().inserir(entity).getId());
            repository.getMedicoRepository().inserir(entity);
            ConnectionFactory.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaServiceImple.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.fecharConexao();
        }
        return entity;
    }
    
    @Override
    public Long acharMedicoDisponivel(LocalDateTime data) throws BancoDadosExcecao {
        ConnectionFactory.abrirConexao();
        try {
            return repository.getMedicoRepository().acharMedicoDisponivel(data);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaServiceImple.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }
    
    @Override
    public Medico acharPorId(Long id) throws BancoDadosExcecao {
        ConnectionFactory.abrirConexao();
        try {
            return repository.getMedicoRepository().acharPorId(id);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaServiceImple.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }
    
    @Override
    public List<ListMedicoDTO> acharTodos() throws BancoDadosExcecao {
        ConnectionFactory.abrirConexao();
        try {
            return MedicoMapper.getLitDTO(repository.getMedicoRepository().acharTodos());
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaServiceImple.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }
}
