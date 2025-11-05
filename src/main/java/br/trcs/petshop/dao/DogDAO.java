package br.trcs.petshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.trcs.petshop.db.ConnectionFactory;
import br.trcs.petshop.enums.DogSize;
import br.trcs.petshop.model.Dog;
import br.trcs.petshop.model.FinishedScheduling;

/**
 * Classe responsável pelas operações de acesso a dados relacionadas aos cães ({@link Dog}).
 * <br>Permite criar novos registros, buscar cães pelo nome e listar os agendamentos finalizados de um cão específico.
 * <br>Tabela associada: <code>dogs</code>
 */
public class DogDAO {

	/** Objeto de conexão com o banco de dados, utilizado para executar comandos SQL. */
    private Connection connection;

    /** Inicializa a conexão com o banco de dados por meio da classe {@link ConnectionFactory}. */
	public DogDAO() {
		connection = ConnectionFactory.getConnection();
	}
	
	/**
     * Insere um novo cão no banco de dados.
     * 
     * @param dog objeto {@link Dog} com os dados a serem cadastrados.
     * @return {@code true} se o cão foi criado com sucesso, {@code false} caso contrário.
     */
	public boolean create(Dog dog) {	
		String sql = "INSERT INTO dogs (name, breed, size, cpfclient) VALUES (?, ?, ?, ?)";
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, dog.getName());
			stm.setString(2, dog.getBreed());
			stm.setString(3, dog.getSize().toString()); 
			stm.setObject(4, dog.getCpfClient());
			
			int rowsInserted = stm.executeUpdate();
	        return rowsInserted > 0;
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return false;
	}
	
	/**
     * Busca um cão pelo nome (insensível a maiúsculas/minúsculas).
     * 
     * @param name nome do cão a ser pesquisado.
     * @return objeto {@link Dog} correspondente ou {@code null} se não encontrado.
     */
	public Dog findByName(String name) {
		String sql = "SELECT * FROM dogs WHERE name ILIKE ?";
		
		Dog dog = null;
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, name.trim());
			
			try (ResultSet result = stm.executeQuery()) {
				 if (result.next()) { 
					dog = new Dog();
					dog.setId(result.getInt("id"));
					dog.setName(result.getString("name"));
					dog.setBreed(result.getString("breed"));
					dog.setSize(DogSize.fromLabel(result.getString("size")));
					dog.setCpfClient(result.getString("cpfclient"));
	            }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dog;
	}
	
	/**
     * Retorna uma lista de agendamentos finalizados de um cão específico.
     * 
     * O método faz uma junção entre várias tabelas relacionadas ({@code dogs}, {@code schedulings}, {@code finished_schedulings},
     * {@code scheduling_services} e {@code services}) para montar uma visão detalhada de cada serviço concluído.
     * 
     * @param idDog identificador do cão.
     * @return lista de mapas, onde cada mapa contém:
     *         <ul>
     *             <li><b>finished</b> → objeto {@link FinishedScheduling}</li>
     *             <li><b>serviceName</b> → nome do serviço</li>
     *             <li><b>servicePrice</b> → valor do serviço</li>
     *         </ul>
     */
	public List<Map<String, Object>> listFinishedSchedulings(Integer idDog) {
		List<Map<String, Object>> finishedList = new ArrayList<>();
		
		String sql = """
			SELECT d.id AS dog_id, d.name AS dog_name, d.breed, 
			       s.id AS scheduling_id, fs.executiondate, fs.grossvalue, fs.discount, fs.finalvalue,
			       sv.id AS service_id, sv.name AS service_name, sv.price AS service_price
			FROM schedulings s
			LEFT JOIN finished_schedulings fs ON s.id = fs.idscheduling
			LEFT JOIN scheduling_services ss ON s.id = ss.idscheduling
			LEFT JOIN services sv ON ss.idservice = sv.id
			INNER JOIN dogs d ON s.iddog = d.id
			WHERE s.iddog = ? AND s.status = 'FINISHED'
			ORDER BY fs.executiondate DESC;
		""";
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, idDog);

			try (ResultSet result = stm.executeQuery()) {
                while (result.next()) {
                    FinishedScheduling finished = new FinishedScheduling();

                    finished.setIdScheduling(result.getInt("scheduling_id"));
                    finished.setExecutionDate(result.getDate("executiondate").toLocalDate());
                    finished.setGrossPrice(result.getBigDecimal("grossvalue"));
                    finished.setDiscount(result.getBoolean("discount"));
                    finished.setFinalPrice(result.getBigDecimal("finalvalue"));

                    // Cria um mapa para combinar o FinishedScheduling com os dados extras.
                    Map<String, Object> map = new HashMap<>();
                    
                    map.put("finished", finished);
                    map.put("serviceName", result.getString("service_name"));
                    map.put("servicePrice", result.getBigDecimal("service_price"));

                    finishedList.add(map);
                }
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return finishedList;
	}
}