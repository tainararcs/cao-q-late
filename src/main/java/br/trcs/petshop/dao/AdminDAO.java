package br.trcs.petshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.trcs.petshop.db.ConnectionFactory;

/**
 * Classe responsável pelo acesso aos dados do administrador do sistema.
 * <br>Permite autenticar administradores cadastrados na base de dados, verificando suas credenciais (e-mail e senha) para conceder acesso ao sistema de gerenciamento.
 * <br>Tabela associada: <code>admin</code>
 */
public class AdminDAO {

	/** Objeto de conexão com o banco de dados, utilizado para executar comandos SQL. */
    private Connection connection;

    /** Inicializa a conexão com o banco de dados por meio da classe {@link ConnectionFactory}. */
	public AdminDAO() {
		connection = ConnectionFactory.getConnection();
	}
	
	/**
     * Realiza a autenticação de um administrador com base em seu e-mail e senha.
     * 
     * @param email e-mail do administrador que deseja realizar o login.
     * @param password senha correspondente ao e-mail informado.
     * @return {@code true} se as credenciais forem válidas, {@code false} caso contrário.
     */
	public boolean authentication(String email, String password) {
		String sql = "SELECT * FROM admin WHERE email=? and password=?"; 

		try (PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, email);
			stm.setString(2, password);
			ResultSet result = stm.executeQuery();
			
			if (result.next()) return true; // Login válido.
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false; // Login inválido.
	}
}