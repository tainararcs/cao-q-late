package br.trcs.petshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.trcs.petshop.db.ConnectionFactory;
import br.trcs.petshop.model.Report;

/** 
 * Classe responsável por acessar os dados de relatórios ({@link Report}) no banco de dados. 
 */
public class ReportDAO {

    /** Objeto de conexão com o banco de dados, utilizado para executar comandos SQL. */
    private Connection connection;

    /** Inicializa a conexão com o banco de dados por meio da classe {@link ConnectionFactory}. */
    public ReportDAO() {
        connection = ConnectionFactory.getConnection();
    }

    /**
     * Recupera um relatório detalhado dos serviços realizados dentro de um período especificado.
     * Cada registro do relatório contém: nome do serviço, preço, data de execução, nome do cachorro e nome do cliente.
     *
     * @param startDate a data de início do período para gerar o relatório (inclusiva).
     * @param endDate a data de fim do período para gerar o relatório (inclusiva).
     * @return uma lista de objetos {@link Report} com informações sobre cada serviço realizado.
     */
    public List<Report> getServicesReport(LocalDate startDate, LocalDate endDate) {
        List<Report> reportList = new ArrayList<>();

        String sql = """
            SELECT sv.name AS service_name, sv.price, fs.executiondate, d.name AS dog_name, c.name AS client_name
            FROM finished_schedulings fs
            JOIN schedulings sc ON fs.idscheduling = sc.id
            JOIN dogs d ON sc.iddog = d.id
            JOIN clients c ON sc.cpfclient = c.cpf
            JOIN scheduling_services ss ON sc.id = ss.idscheduling
            JOIN services sv ON ss.idservice = sv.id
            WHERE fs.executiondate BETWEEN ? AND ?
            ORDER BY fs.executiondate, sv.name
        """;

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setDate(1, java.sql.Date.valueOf(startDate));
            stm.setDate(2, java.sql.Date.valueOf(endDate));

            try (ResultSet result = stm.executeQuery()) {
                while (result.next()) {
                    Report report = new Report();
                    report.setServiceName(result.getString("service_name"));
                    report.setPrice(result.getBigDecimal("price"));
                    report.setDate(result.getDate("executiondate").toLocalDate());
                    report.setDogName(result.getString("dog_name"));
                    report.setClientName(result.getString("client_name"));
                    reportList.add(report);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportList;
    }

    /**
     * Calcula o total faturado (soma dos valores finais) de todos os agendamentos finalizados dentro de um período especificado.
     *
     * @param startDate a data de início do período para calcular o faturamento (inclusiva).
     * @param endDate a data de fim do período para calcular o faturamento (inclusiva).
     * @return o valor total faturado como {@link BigDecimal}; retorna {@link BigDecimal#ZERO} caso não haja registros.
     */
    public BigDecimal getTotalRevenue(LocalDate startDate, LocalDate endDate) {
        String sql = """
            SELECT COALESCE(SUM(fs.finalvalue), 0) AS total
            FROM finished_schedulings fs
            WHERE fs.executiondate BETWEEN ? AND ?
        """; // COALESCE retorna o primeiro valor não nulo.

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setDate(1, java.sql.Date.valueOf(startDate));
            stm.setDate(2, java.sql.Date.valueOf(endDate));

            try (ResultSet result = stm.executeQuery()) {
                if (result.next()) 
                    return result.getBigDecimal("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return BigDecimal.ZERO;
    }
}