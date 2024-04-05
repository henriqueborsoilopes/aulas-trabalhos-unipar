package br.unipar.husistema.service.imple;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.mapper.ConsultaMapper;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidarExcecao;
import java.time.LocalDateTime;
import br.unipar.husistema.service.IConsultaService;
import br.unipar.husistema.factory.IRepositoryFactory;
import br.unipar.husistema.service.connection.ConexaoBD;
import br.unipar.husistema.service.validation.Validar;

public class ConsultaServiceImple implements IConsultaService {
    
    private final IRepositoryFactory repository;
    
    public ConsultaServiceImple(IRepositoryFactory repository) {
        this.repository = repository;
    }

    @Override
    public Consulta inserir(InserirConsultaDTO dto) throws ValidarExcecao {
        ConexaoBD.abrirConexao();
        ConexaoBD.manterConexaoAberta(true);
        ConexaoBD.autoCommit(false);
        Validar.consulta(dto);
        Consulta consulta = ConsultaMapper.getEntidade(dto);
        consulta = repository.getConsultaRepository().inserir(consulta);
        ConexaoBD.commit();
        ConexaoBD.fecharConexao();
        return consulta;
    }
    
    @Override
    public boolean consultarAgendamentoPaciente(Long id_paciente, LocalDateTime data) throws BancoDadosExcecao {
        ConexaoBD.abrirConexao();
        boolean resultado = repository.getConsultaRepository().cansultarAgendamentoPaciente(id_paciente, data);
        ConexaoBD.fecharConexao();
        return resultado;
    }
    
    @Override
    public boolean consultarAgendamentoMedico(LocalDateTime data, Long id_medico) throws BancoDadosExcecao {
        ConexaoBD.abrirConexao();
        boolean resultado = repository.getConsultaRepository().cansultarAgendamentoMedico(data, id_medico);
        ConexaoBD.fecharConexao();
        return resultado;
    }
    
    @Override
    public LocalDateTime consultarDataConsulta(Long id_consulta) throws BancoDadosExcecao {
        ConexaoBD.abrirConexao();
        LocalDateTime data = repository.getConsultaRepository().cansultarDataConsulta(id_consulta);
        ConexaoBD.fecharConexao();
        return data;
    }
    
    @Override
    public void cancelar(Long id, CancelarConsultaDTO dto) throws BancoDadosExcecao, ValidarExcecao {
        ConexaoBD.abrirConexao();
        ConexaoBD.manterConexaoAberta(true);
        ConexaoBD.autoCommit(false);
        Validar.cancelamentoConsulta(id, dto);
        Consulta consulta = ConsultaMapper.getEntidade(dto);
        repository.getConsultaRepository().cancelar(id, consulta);
        ConexaoBD.commit();
        ConexaoBD.fecharConexao();
    }

    @Override
    public boolean temConsultaAgendada(Long id_pessoa) throws BancoDadosExcecao {
        ConexaoBD.abrirConexao();
        boolean resultado = repository.getConsultaRepository().temConsultaAgendada(id_pessoa);
        ConexaoBD.fecharConexao();
        return resultado;
    }
}
