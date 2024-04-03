package br.unipar.husistema.repository;

import br.unipar.husistema.entity.Consulta;
import java.sql.SQLException;
import java.time.LocalDateTime;

public interface ConsultaRepository {
    
    public Consulta inserir(Consulta consulta) throws SQLException;
    public boolean cansultarAgendamentoPaciente(Long id_paciente, LocalDateTime date) throws SQLException;
    public boolean cansultarAgendamentoMedico(LocalDateTime data, Long id_medico) throws SQLException;
    public LocalDateTime cansultarDataConsulta(Long id_consulta) throws SQLException;
    public void cancelar(Long id, Consulta consulta) throws SQLException;
}
