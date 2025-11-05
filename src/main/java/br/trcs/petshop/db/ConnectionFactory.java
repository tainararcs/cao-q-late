package br.trcs.petshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() {	
		try {
			// Carrega o driver JDBC do PostgreSQL (necessário para aplicações web).
			Class.forName("org.postgresql.Driver");
			 
			/* Retorna uma conexão com o banco de dados.
			 * Parâmetros: URL do banco, nome de usuário e senha.
			 */
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/caoqlate", "postgres", "49999");
		} 
		// Erro ao carregar o driver JDBC.
		catch (ClassNotFoundException e) {
	        System.err.println("Driver do PostgreSQL não encontrado: " + e.getMessage());
	        e.printStackTrace();
	    } 
		// Erro ao estabelecer a conexão com o banco de dados.
		catch (SQLException e) { 
	        System.err.println("Falha na conexão com o banco de dados: " + e.getMessage());
	        e.printStackTrace();
	    }
		
		// Retorna null caso a conexão não possa ser estabelecida.
		return null;
	}
}