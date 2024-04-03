package br.unipar.husistema.service;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.service.exception.BancoDadosExcecao;
import br.unipar.husistema.service.exception.ValidacaoExcecao;
import java.time.LocalDateTime;

public interface IConsultaService {
    
    public Consulta inserir(InserirConsultaDTO dto) throws BancoDadosExcecao, ValidacaoExcecao;
    public boolean cansultarAgendamentoPaciente(Long id_paciente, LocalDateTime data) throws BancoDadosExcecao;
    public boolean cansultarAgendamentoMedico(LocalDateTime data, Long id_medico) throws BancoDadosExcecao;
    public LocalDateTime cansultarDataConsulta(Long id_consulta) throws BancoDadosExcecao;
    public void cancelar(Long id, CancelarConsultaDTO dto) throws BancoDadosExcecao, ValidacaoExcecao;
}
