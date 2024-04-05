package br.unipar.husistema.service;

import br.unipar.husistema.dto.CancelarConsultaDTO;
import br.unipar.husistema.dto.InserirConsultaDTO;
import br.unipar.husistema.entity.Consulta;
import br.unipar.husistema.service.exception.ValidarExcecao;
import java.time.LocalDateTime;

public interface IConsultaService {
    
    Consulta inserir(InserirConsultaDTO dto) throws ValidarExcecao;
    boolean consultarAgendamentoPaciente(Long id_paciente, LocalDateTime data);
    boolean consultarAgendamentoMedico(LocalDateTime data, Long id_medico);
    boolean temConsultaAgendada(Long id_pessoa);
    LocalDateTime consultarDataConsulta(Long id_consulta);
    void cancelar(Long id, CancelarConsultaDTO dto) throws ValidarExcecao;
}
