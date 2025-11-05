package br.trcs.petshop.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.trcs.petshop.db.ConnectionFactory;
import br.trcs.petshop.model.Service;

/**
 * Classe responsável pelo acesso aos dados de serviços do pet shop.
 * <br>Tabela associada: <code>services</code>
 */
public class ServiceDAO {

	/** Objeto de conexão com o banco de dados, utilizado para executar comandos SQL. */
    private Connection connection;

    /** Inicializa a conexão com o banco de dados por meio da classe {@link ConnectionFactory}. */
	public ServiceDAO() {
		connection = ConnectionFactory.getConnection();
	}
	
	/**
     * Insere um novo serviço no banco de dados.
     * 
     * @param service Objeto {@link Service} contendo nome e preço do serviço.
     * @return {@code true} se o serviço foi inserido com sucesso, {@code false} caso contrário.
     */
	public boolean create(Service service) {
		String sql = "INSERT INTO services (name, price) VALUES (?, ?)";
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, service.getName());
			stm.setBigDecimal(2, service.getPrice());
			
			int rowsInserted = stm.executeUpdate();
	        return rowsInserted > 0;
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return false;
	}
	
	 /**
     * Atualiza o preço de um serviço existente.
     * 
     * @param service Objeto {@link Service} representando o serviço a ser atualizado.
     * @param newPrice Novo preço do serviço.
     * @return {@code true} se a atualização foi bem-sucedida, {@code false} caso contrário.
     */
	public boolean update(Service service, BigDecimal newPrice) {
		String sql = "UPDATE services SET price = ? WHERE id = ?";
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setBigDecimal(1, newPrice);
			stm.setInt(2, service.getId());
			
			int rowsUpdated = stm.executeUpdate();
	        return rowsUpdated > 0;
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
     * Busca um serviço pelo seu nome (case-insensitive).
     * 
     * @param name Nome do serviço a ser buscado.
     * @return Objeto {@link Service} correspondente, ou {@code null} se não encontrado.
     */
	public Service findByName(String name) {
		String sql = "SELECT * FROM services WHERE name ILIKE ?";
		
		Service service = null;
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, name.trim());
			
			try (ResultSet result = stm.executeQuery()) {
				 if (result.next()) { 
	                service = new Service();
	                service.setId(result.getInt("id"));
	                service.setName(result.getString("name"));
	                service.setPrice(result.getBigDecimal("price"));
	            }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return service;
	}
	
	/**
     * Retorna todos os serviços cadastrados no banco de dados,
     * ordenados pelo nome do serviço.
     * 
     * @return Lista de objetos {@link Service}.
     */
	public List<Service> list() {
		List<Service> services = new ArrayList<>();
		
	    String sql = "SELECT * FROM services ORDER BY name";
	
	    try (PreparedStatement stm = connection.prepareStatement(sql);
	        ResultSet result = stm.executeQuery()) {
	
	        while (result.next()) {
	            Service service = new Service();
	            service.setId(result.getInt("id"));
	            service.setName(result.getString("name"));
	            service.setPrice(result.getBigDecimal("price"));
	            
	            services.add(service);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	    return services;
	}
}