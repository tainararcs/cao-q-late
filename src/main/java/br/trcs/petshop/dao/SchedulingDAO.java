package br.trcs.petshop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.trcs.petshop.db.ConnectionFactory;
import br.trcs.petshop.enums.SchedulingStatus;
import br.trcs.petshop.model.Scheduling;
import br.trcs.petshop.model.Service;

/**
 * Classe responsável por realizar as operações de acesso ao banco de dados (DAO) relacionadas à entidade {@link Scheduling}.
 * <br>Manipula os dados de agendamentos e seus respectivos serviços, permitindo CRUD sobre registros nas tabelas.
 * <br>Tabelas associadas: <code>schedulings</code> e <code>scheduling_services</code>
 */
public class SchedulingDAO {

	/** Objeto de conexão com o banco de dados, utilizado para executar comandos SQL. */
    private Connection connection;

    /** Inicializa a conexão com o banco de dados por meio da classe {@link ConnectionFactory}. */
    public SchedulingDAO() {
        connection = ConnectionFactory.getConnection();
    }

    /**
     * Insere um novo agendamento na tabela <b>schedulings</b> e associa os serviços correspondentes na tabela <b>scheduling_services</b>.
     *
     * @param scheduling Objeto {@link Scheduling} contendo as informações do agendamento.
     * @return {@code true} se o agendamento foi criado com sucesso; {@code false} caso contrário.
     */
    public boolean create(Scheduling scheduling) {
        String sql = "INSERT INTO schedulings (status, date, cpfclient, iddog) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, scheduling.getStatus().toString());
            stm.setDate(2, Date.valueOf(scheduling.getDate()));
            stm.setString(3, scheduling.getCpfClient());
            stm.setInt(4, scheduling.getIdDog());
            stm.executeUpdate();

            // Recupera o ID gerado automaticamente (auto_increment).
            ResultSet result = stm.getGeneratedKeys();
            if (result.next()) {
                int idScheduling = result.getInt(1);
                insertSchedulingServices(idScheduling, scheduling.getIdServicesList());
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Insere, na tabela <b>scheduling_services</b>, os serviços associados a um agendamento específico.
     * Cada registro inserido estabelece a relação entre o agendamento (idscheduling) e um dos serviços selecionados (idservice).
     * 
     * @param idScheduling Identificador do agendamento recém-criado.
     * @param idServicesList Lista de identificadores dos serviços selecionados para o agendamento.
     * @throws SQLException Caso ocorra algum erro durante a execução do comando SQL.
     */
    private void insertSchedulingServices(Integer idScheduling, List<Integer> idServicesList) throws SQLException {
        String sql = "INSERT INTO scheduling_services (idscheduling, idservice) VALUES (?, ?)";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            for (Integer idService : idServicesList) {
            	stm.setInt(1, idScheduling);
            	stm.setInt(2, idService);
            	stm.addBatch(); // addBatch() adiciona o comando atual a um lote de instruções SQL que serão executadas juntas.
            }
            stm.executeBatch(); // Executa todos os inserts de uma só vez.
        }
    }
    
    /**
     * Verifica se existe algum agendamento na data informada.
     *
     * @param date Data desejada.
     * @return {@code true} se a data estiver livre; {@code false} se já existir um agendamento.
     */
    public boolean isDateAvailable(LocalDate date) {
        String sql = "SELECT COUNT(*) FROM schedulings WHERE date = ?";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setDate(1, Date.valueOf(date));

            ResultSet result = stm.executeQuery();
            if (result.next()) {
                int count = result.getInt(1);
                return count == 0;  // true se não existir agendamento na data selecionada.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Atualiza o status de um agendamento existente.
     *
     * @param idScheduling ID do agendamento.
     * @param newStatus Novo status a ser definido.
     * @return {@code true} se o status foi atualizado com sucesso; {@code false} caso contrário.
     */
    public boolean updateStatus(Integer idScheduling, SchedulingStatus newStatus) {
        String sql = "UPDATE schedulings SET status = ? WHERE id = ?";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, SchedulingStatus.fromStatus(newStatus.getStatus()).toString());
            stm.setInt(2, idScheduling);
            
            int rowsUpdated = stm.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Exclui um agendamento da base de dados.
     *
     * @param idScheduling ID do agendamento a ser removido.
     * @return {@code true} se foi excluído com sucesso; {@code false} caso contrário.
     */
    public boolean delete(Integer idScheduling) {
        String sql = "DELETE FROM schedulings WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, idScheduling);
            
            int rowsDeleted = stm.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Busca um agendamento pelo seu ID.
     *
     * @param idScheduling ID do agendamento.
     * @return Objeto {@link Scheduling} se encontrado, ou {@code null} se não existir.
     */
    public Scheduling findById(Integer idScheduling) {
        String sql = "SELECT id, date, cpfclient, iddog, status FROM schedulings WHERE id = ?";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, idScheduling);
            
            try (ResultSet result = stm.executeQuery()) {
                if (result.next()) {
                    Scheduling scheduling = new Scheduling();
                    scheduling.setId(result.getInt("id"));
                    scheduling.setDate(result.getDate("date").toLocalDate());
                    scheduling.setCpfClient(result.getString("cpfclient"));
                    scheduling.setIdDog(result.getInt("iddog"));
                    scheduling.setStatus(SchedulingStatus.valueOf(result.getString("status")));
                    return scheduling;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
	
	/**
	 * Retorna todos os serviços associados a um determinado agendamento.
	 * 
	 * @param scheduling Objeto contendo o ID do agendamento cujo serviços serão listados.
	 * @return Lista de objetos {@link Service} contendo id, nome e preço dos serviços.
	 */
	public List<Service> listServices(Scheduling scheduling) {
	    String sql = """
	        SELECT sv.id, sv.name, sv.price
	        FROM scheduling_services ss
	        JOIN services sv ON sv.id = ss.idservice
	        WHERE ss.idscheduling = ?
	    """;
        
        List<Service> servicesList = new ArrayList<Service>();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
        	stm.setInt(1, scheduling.getId());

            try (ResultSet result = stm.executeQuery()) {
                while (result.next()) {
                	Service service = new Service();
                    service.setId(result.getInt("id"));
                    service.setName(result.getString("name"));
                    service.setPrice(result.getBigDecimal("price"));
                    servicesList.add(service);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicesList;
    }
	
	/**
     * Lista todos os agendamentos futuros com status 'SCHEDULED' (não finalizados) a partir de uma determinada data.
     * <br>Inclui o nome do cão e os serviços concatenados.
     * <br>Também adiciona uma flag {@code canCancel}, que indica se o agendamento pode ser cancelado.
     *
     * @param startDate Data inicial para a listagem.
     * @return Lista de {@link Map} com os dados dos agendamentos.
     */
	public List<Map<String, Object>> listNotFinishedFromDate(LocalDate startDate) {
	    String sql = """
	        SELECT sc.id, sc.date, d.name AS dogName, STRING_AGG(sv.name, ', ') AS services
			FROM schedulings sc
			LEFT JOIN dogs d ON sc.iddog = d.id
			LEFT JOIN scheduling_services ss ON sc.id = ss.idscheduling
			LEFT JOIN services sv ON ss.idservice = sv.id
			WHERE sc.status = 'SCHEDULED' AND sc.date >= ?
			GROUP BY sc.id, sc.date, d.name
			ORDER BY sc.date ASC;
	    """;

	    List<Map<String, Object>> list = new ArrayList<>();

	    try (PreparedStatement stm = connection.prepareStatement(sql)) {
	        stm.setDate(1, java.sql.Date.valueOf(startDate));

	        try (ResultSet result = stm.executeQuery()) {
	            while (result.next()) {
	                Map<String, Object> row = new HashMap<>();
	                
	                // Recupera e converete a data do agendamento.
	                LocalDate schedulingDate = result.getDate("date").toLocalDate();
	                LocalDate now = LocalDate.now();

	                // Permitir cancelamento apenas se o agendamento for mais que 24h.
	                row.put("canCancel", schedulingDate.isAfter(now.plusDays(1)));
	                row.put("id", result.getInt("id"));
	                row.put("date", schedulingDate);
	                row.put("dogName", result.getString("dogName"));
	                row.put("services", result.getString("services")); // Serviços concatenados.
	                list.add(row);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
}