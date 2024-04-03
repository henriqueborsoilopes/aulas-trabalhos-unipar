package br.unipar.husistema.service.imple;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.factory.ConnectionFactory;
import br.unipar.husistema.factory.RepositoryFactory;
import br.unipar.husistema.mapper.ConsultaMapper;
import br.unipar.husistema.service.ConsultaService;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import br.unipar.husistema.service.validation.ConsultaValidacao;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultaServiceImple implements ConsultaService {
    
    private final RepositoryFactory repository;
    
    public ConsultaServiceImple(RepositoryFactory repository) {
        this.repository = repository;
    }

    @Override
    public Consulta inserir(InserirConsultaDTO dto) throws BancoDadosExcecao, ValidacaoExcecao {
        ConnectionFactory.abrirConexao();
        ConnectionFactory.manterConexaoAberta(true);
        ConnectionFactory.autoCommit(false);
        ConsultaValidacao.validarConsulta(dto);
        Consulta consulta = ConsultaMapper.getEntity(dto);
        try {
            consulta = repository.getConsultaRepository().inserir(consulta);
            ConnectionFactory.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaServiceImple.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        } finally {
            ConnectionFactory.fecharConexao();
        }
        return consulta;
    }
    
    @Override
    public boolean cansultarAgendamentoPaciente(Long id_paciente, LocalDateTime data) throws BancoDadosExcecao {
        ConnectionFactory.abrirConexao();
        try {
            return repository.getConsultaRepository().cansultarAgendamentoPaciente(id_paciente, data);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaServiceImple.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }
    
    @Override
    public boolean cansultarAgendamentoMedico(LocalDateTime data, Long id_medico) throws BancoDadosExcecao {
        ConnectionFactory.abrirConexao();
        try {
            return repository.getConsultaRepository().cansultarAgendamentoMedico(data, id_medico);
        } catch ( SQLException ex) {
            Logger.getLogger(ConsultaServiceImple.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }
    
    @Override
    public LocalDateTime cansultarDataConsulta(Long id_consulta) throws BancoDadosExcecao {
        ConnectionFactory.abrirConexao();
        try {
            return repository.getConsultaRepository().cansultarDataConsulta(id_consulta);
        } catch ( SQLException ex) {
            Logger.getLogger(ConsultaServiceImple.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }
    
    @Override
    public void cancelar(Long id, CancelarConsultaDTO dto) throws BancoDadosExcecao, ValidacaoExcecao {
        ConnectionFactory.abrirConexao();
        ConnectionFactory.manterConexaoAberta(true);
        ConnectionFactory.autoCommit(false);
        ConsultaValidacao.validarCancelamentoConsulta(id, dto);
        Consulta consulta = ConsultaMapper.getEntity(dto);
        try {
            repository.getConsultaRepository().cancelar(id, consulta);
            ConnectionFactory.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaServiceImple.class.getName()).log(Level.SEVERE, null, ex);
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        } finally {
            ConnectionFactory.fecharConexao();
        }
    }
}
