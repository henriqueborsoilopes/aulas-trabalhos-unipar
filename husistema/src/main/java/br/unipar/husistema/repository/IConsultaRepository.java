package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Consulta;
import java.time.LocalDateTime;

public interface IConsultaRepository {
    
    public Consulta inserir(Consulta consulta);
    public boolean cansultarAgendamentoPaciente(Long id_paciente, LocalDateTime date);
    public boolean cansultarAgendamentoMedico(LocalDateTime data, Long id_medico);
    public boolean temConsultaAgendada(Long id_pessoa);
    public LocalDateTime cansultarDataConsulta(Long id_consulta);
    public void cancelar(Long id, Consulta consulta);
    
}
