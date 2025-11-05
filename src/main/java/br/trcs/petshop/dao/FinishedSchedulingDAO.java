package br.trcs.petshop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.trcs.petshop.db.ConnectionFactory;
import br.trcs.petshop.model.FinishedScheduling;

/**
 * Classe responsável pelas operações de acesso a dados relacionadas aos agendamentos finalizados ({@link FinishedScheduling}).
 * <br>Permite registrar no banco de dados o término de um agendamento e verificar se um agendamento já foi finalizado anteriormente.
 * <br>Tabela associada: <code>finished_schedulings</code>
 */
public class FinishedSchedulingDAO {
	
	/** Objeto de conexão com o banco de dados, utilizado para executar comandos SQL. */
    private Connection connection;

    /** Inicializa a conexão com o banco de dados por meio da classe {@link ConnectionFactory}. */
    public FinishedSchedulingDAO() {
        connection = ConnectionFactory.getConnection();
    }

    /**
     * Insere um novo registro na tabela <code>finished_schedulings</code>, representando um agendamento que foi concluído.
     * 
     * @param finishedScheduling objeto {@link FinishedScheduling} contendo os dados a serem persistidos.
     * @return {@code true} se o registro foi inserido com sucesso, {@code false} caso contrário.
     */
    public boolean create(FinishedScheduling finishedScheduling) {
        String sql = """
            INSERT INTO finished_schedulings (idscheduling, executiondate, grossvalue, discount, finalvalue)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, finishedScheduling.getIdScheduling());
            stm.setDate(2, Date.valueOf(finishedScheduling.getExecutionDate()));
            stm.setBigDecimal(3, finishedScheduling.getGrossPrice());
            stm.setBoolean(4, finishedScheduling.getDiscount());
            stm.setBigDecimal(5, finishedScheduling.getFinalPrice());

            int rowsInserted = stm.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Verifica se já existe um registro de finalização para o agendamento informado.
     * 
     * @param idScheduling identificador do agendamento a verificar.
     * @return {@code true} se o agendamento já foi registrado em <code>finished_schedulings</code>, {@code false} caso contrário.
     */
    public boolean searchSchedulingId(Integer idScheduling) {
        String sql = "SELECT 1 FROM finished_schedulings WHERE idscheduling = ? LIMIT 1";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, idScheduling);

            try (ResultSet rs = stm.executeQuery()) {
                return rs.next(); // Se há resultado, já existe.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}