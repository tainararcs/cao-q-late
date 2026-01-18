package br.trcs.petshop.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por fornecer conexões com o banco de dados.
 * <br>Essa classe implementa o padrão de projeto <b>Factory</b>, centralizando a criação e configuração de conexões JDBC. 
 * <br>É utilizada em todas as classes DAO do sistema.
 */
public class ConnectionFactory {
	
	 /**
     * Cria e retorna uma nova conexão JDBC com o banco PostgreSQL.
     * 
     * @return {@link java.sql.Connection} ativa com o banco de dados, ou {@code null} se a conexão falhar.
     */
	public static Connection getConnection() {	
		try {
			// Carrega o driver JDBC do PostgreSQL (necessário para aplicações web).
			Class.forName("org.postgresql.Driver");
			 
			/* Retorna uma conexão com o banco de dados.
			 * Parâmetros: URL do banco, nome de usuário e senha.
			 */
			return DriverManager.getConnection("jdbc:postgresql://postgres-db:5432/caoqlate", "postgres", "postgres");
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
