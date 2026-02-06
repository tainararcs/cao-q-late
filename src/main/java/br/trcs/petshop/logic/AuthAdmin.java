package br.trcs.petshop.logic;

import java.io.IOException;

import br.trcs.petshop.dao.AdminDAO;
import br.trcs.petshop.model.Admin;
import br.trcs.petshop.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe responsável por autenticar um administrador no sistema.
 * <br>Implementa a interface {@link Logic}, que define o contrato para ações executadas pelas servlets controladoras.
 * <br>Em caso de sucesso, cria uma sessão de usuário autenticado. Caso contrário, exibe uma mensagem de erro.
 */
public class AuthAdmin implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupera os parâmetros.
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// Validação básica.
	    if (email == null || password == null || email.isBlank() || password.isBlank()) {
	        request.setAttribute(Consts.ERROR, Consts.FIELDS_ERROR);
	        return Consts.LOGIN_JSP;
	    }
	    
	    AdminDAO dao = new AdminDAO();
	    
	    // Usuário válido é redirecionado para a página incicial (home).
		if (dao.authentication(email, encryptPassword(password))) { 
			Admin admin = new Admin();
			admin.setEmail(email);
			admin.setPasswordHash(password);
			
			// Cria a sessão, armazena o usuário logado e define o tempo máximo.
	        var session = request.getSession();
	        session.setAttribute("admin", admin);
	        session.setMaxInactiveInterval(120); // 120 segundos de inatividade.
	        
			return Consts.REDIRECT_HOME_JSP; 
		}
		
		// Falha e volta para login com mensagem de erro.
        request.setAttribute(Consts.ERROR, Consts.LOGIN_ERROR); 
        return Consts.LOGIN_JSP;
    }
	
	/**
     * Criptografa (de forma simples) a senha informada. 
     * Utiliza o {@link String#hashCode()}.
     * 
     * @param password senha original digitada pelo usuário.
     * @return valor hash da senha.
     */
	private String encryptPassword(String password) {
		return String.valueOf(password.hashCode());
	}
}