package br.trcs.petshop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.trcs.petshop.db.ConnectionFactory;
import br.trcs.petshop.enums.DogSize;
import br.trcs.petshop.model.Client;
import br.trcs.petshop.model.Dog;

/**
 * Classe responsável pelo acesso e manipulação dos dados de {@link Client} no banco de dados.
 * <br>Implementa operações básicas de CRUD e consultas relacionadas, como a listagem de cães de um cliente.
 * <br>Tabela associada: <code>clients</code>
 */
public class ClientDAO {

	/** Objeto de conexão com o banco de dados, utilizado para executar comandos SQL. */
    private Connection connection;

    /** Inicializa a conexão com o banco de dados por meio da classe {@link ConnectionFactory}. */
	public ClientDAO() {
		connection = ConnectionFactory.getConnection();
	}
	
	/**
     * Insere um novo cliente no banco de dados.
     * 
     * @param client objeto {@link Client} contendo os dados a serem salvos.
     * @return {@code true} se o cliente foi inserido com sucesso, {@code false} caso contrário.
     */
	public boolean create(Client client) {
		String sql = "INSERT INTO clients (cpf, name, birthdate, email, phonenumber) VALUES (?, ?, ?, ?, ?)";
		
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setString(1, client.getCpf());
			stm.setString(2, client.getName());
			stm.setDate(3, Date.valueOf(client.getBirthDate()));
			stm.setString(4, client.getEmail());
			stm.setString(5, client.getPhoneNumber());
			
			int rowsInserted = stm.executeUpdate();
	        return rowsInserted > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return false;
	}
	
	/**
     * Busca um cliente pelo CPF.
     * 
     * @param cpf CPF do cliente que deseja localizar.
     * @return objeto {@link Client} se encontrado, ou {@code null} se não houver registro.
     */
	public Client findByCpf(String cpf) {
		String sql = "SELECT * FROM clients WHERE cpf = ?";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, cpf);
            
            try (ResultSet result = stm.executeQuery()) {
                if (result.next()) {
                    Client client = new Client();
                    client.setCpf(result.getString("cpf"));
                    client.setName(result.getString("name"));
                    client.setBirthDate(result.getDate("birthdate").toLocalDate());
                    client.setEmail(result.getString("email"));
                    client.setPhoneNumber(result.getString("phonenumber"));
                    return client;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se não encontrou.
	}
	
	/**
     * Retorna a lista de todos os clientes cadastrados no banco de dados.
     * 
     * @return lista de objetos {@link Client}.
     */
	public List<Client> list() {
		List<Client> clientList = new ArrayList<>();

        String sql = "SELECT * FROM clients ORDER BY name";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            try (ResultSet result = stm.executeQuery()) {
                while (result.next()) {
                    Client client = new Client();
                    client.setCpf(result.getString("cpf"));
                    client.setName(result.getString("name"));
                    client.setBirthDate(result.getDate("birthdate").toLocalDate());
                    client.setEmail(result.getString("email"));
                    client.setPhoneNumber(result.getString("phonenumber"));
                    clientList.add(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientList;
    }
	
	/**
     * Retorna todos os cães pertencentes a um cliente, com base no CPF informado.
     * 
     * @param cpf CPF do cliente dono dos cães.
     * @return lista de objetos {@link Dog} associados ao cliente.
     */
	public List<Dog> listDogs(String cpf) {
		List<Dog> dogList = new ArrayList<>();

        String sql = "SELECT id, name, breed, size FROM dogs WHERE cpfclient = ? ORDER BY name";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, cpf);

            try (ResultSet result = stm.executeQuery()) {
                while (result.next()) {
                    Dog dog = new Dog();
                    dog.setId(result.getInt("id"));
                    dog.setName(result.getString("name"));
                    dog.setBreed(result.getString("breed"));
                    dog.setSize(DogSize.valueOf(result.getString("size")));
                    dogList.add(dog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dogList;
    }
}