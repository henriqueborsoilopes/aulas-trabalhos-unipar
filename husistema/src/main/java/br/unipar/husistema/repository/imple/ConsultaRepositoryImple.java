package br.unipar.husistema.repository.imple;

import br.unipar.husistema.entity.Consulta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import br.unipar.husistema.repository.IConsultaRepository;
import br.unipar.husistema.service.connection.ConexaoBD;
import br.unipar.husistema.service.exception.BancoDadosExcecao;

public class ConsultaRepositoryImple implements IConsultaRepository {
    
    private static final String TABELA = "consulta";
    private static final String[] COLUNAS = {"id", "data_consulta", "data_cancelamento", "descri_cancelamento", "cancelado", "id_medico", "id_paciente"};
    
    @Override
    public Consulta inserir(Consulta consulta) {
        String query = ""
            + "INSERT INTO " + TABELA + " (" + COLUNAS[1] + ", " + COLUNAS[4] + ", "  + COLUNAS[5] + ", " + COLUNAS[6] + ") "
            + "VALUES (?, ?, ?, ?);";
        
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, Timestamp.valueOf(consulta.getDataConsulta()));
            ps.setBoolean(2, false);
            ps.setLong(3, consulta.getIdMedico());
            ps.setLong(4, consulta.getIdPaciente());
            ps.execute();        
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    consulta.setId(rs.getLong(COLUNAS[0]));
                }
                return consulta;
            } catch (SQLException e) {
                throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
            }
        } catch (SQLException e) {
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
    
    @Override
    public boolean cansultarAgendamentoPaciente(Long id_paciente, LocalDateTime date) {
        String query = ""
            + "SELECT c." + COLUNAS[1] + " "
            + "FROM " + TABELA + " c "
            + "WHERE c." + COLUNAS[6] + " = ? "
            + "AND DATE(c." + COLUNAS[1] + ") = DATE(?) "
            + "AND c." + COLUNAS[4] + " = false;";
        
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(query)) {
            ps.setLong(1, id_paciente);
            ps.setTimestamp(2, Timestamp.valueOf(date));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException e) {
                throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
            }
        } catch (SQLException e) {
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
        
    @Override
    public boolean cansultarAgendamentoMedico(LocalDateTime data, Long id_medico) {
        String query = ""
            + "SELECT * "
            + "FROM " + TABELA + " "
            + "WHERE " + COLUNAS[5] + " = ? "
            + "AND " + COLUNAS[4] + " = false "
            + "AND DATE(" + COLUNAS[1] + ") = DATE(?) "
            + "AND (? BETWEEN " + COLUNAS[1] + " AND " + COLUNAS[1] + " + INTERVAL '1 hour' "
            + "OR ? BETWEEN " + COLUNAS[1] + " AND " + COLUNAS[1] + " + INTERVAL '1 hour');";
        
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(query)) {
            ps.setLong(1, id_medico);
            ps.setTimestamp(2, Timestamp.valueOf(data));
            ps.setTimestamp(3, Timestamp.valueOf(data));
            ps.setTimestamp(4, Timestamp.valueOf(data.plusHours(1)));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException e) {
                throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
            }
        } catch (SQLException e) {
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
    
    @Override
    public LocalDateTime cansultarDataConsulta(Long id_consulta) {
        LocalDateTime data = null;
        String query = ""
            + "SELECT c." + COLUNAS[1] + " "
            + "FROM " + TABELA + " c "
            + "WHERE c." + COLUNAS[0] + " = ?;";
        
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(query)) {
            ps.setLong(1, id_consulta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getTimestamp(COLUNAS[1]).toLocalDateTime();
                }
                return data;
            } catch (SQLException e) {
                throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
            }
        } catch (SQLException e) {
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
    
    @Override
    public void cancelar(Long id, Consulta consulta) {        
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String query = ""
            + "UPDATE " + TABELA + " "
            + "SET " + COLUNAS[2] + " = ?, " + COLUNAS[3] + " = ?, " + COLUNAS[4] + " = ? "
            + "WHERE " + COLUNAS[0] + " = ?;";
        
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(query)) {
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().format(formatterData))); 
            ps.setString(2, consulta.getDescriCancelamento());
            ps.setBoolean(3, true);            
            ps.setLong(4, id);
            ps.execute();
        } catch (SQLException e) {
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }

    @Override
    public boolean temConsultaAgendada(Long id_pessoa) {
        String query = ""
            + "SELECT * "
            + "FROM " + TABELA + " c "
            + "WHERE c." + COLUNAS[4] + " = false "
            + "AND (c." + COLUNAS[5] + " = ? "
            + "OR c." + COLUNAS[6] + " = ?);";
        
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(query)) {
            ps.setLong(1, id_pessoa);
            ps.setLong(2, id_pessoa);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException e) {
                throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
            }
        } catch (SQLException e) {
            throw new BancoDadosExcecao("Desculpe, ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde.");
        }
    }
}
