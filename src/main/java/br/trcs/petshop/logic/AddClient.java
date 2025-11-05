package br.trcs.petshop.logic;

import java.io.IOException;
import java.time.LocalDate;

import br.trcs.petshop.dao.ClientDAO;
import br.trcs.petshop.model.Client;
import br.trcs.petshop.utils.Consts;
import br.trcs.petshop.utils.ValidationUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Lógica responsável por realizar o cadastro de um novo cliente no sistema.
 */
public class AddClient implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String cpf = request.getParameter("cpf");
		String birthDate = request.getParameter("birthDate");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		
		// Valida o cpf.
        if (!ValidationUtils.isValidCPF(cpf)) {
        	request.getSession().setAttribute(Consts.ERROR, "CPF inválido");
            return Consts.REDIRECT_ADD_CLIENT;
        }
        
        // Valida o email.
        if (!ValidationUtils.isValidEmail(email)) {
        	request.getSession().setAttribute(Consts.ERROR, "E-mail inválido");
            return Consts.REDIRECT_ADD_CLIENT;
        }
        
		// Formata a data.
		LocalDate formattedDate = LocalDate.parse(birthDate);

		Client newClient = new Client();;
		newClient.setName(name);
		newClient.setCpf(cpf);
		newClient.setBirthDate(formattedDate);
		newClient.setEmail(email);
		newClient.setPhoneNumber(phoneNumber);
		
		ClientDAO dao = new ClientDAO();		
		boolean created = dao.create(newClient);
		
	    if (created) {
	    	// Precisa acessar a sessão pois troca de página.
	    	request.getSession().setAttribute(Consts.MSG, "Cliente cadastrado com sucesso");
	        
	        // Redireciona para a página inicial após o processamento.
	        return Consts.REDIRECT_HOME;
	    } 
	    
    	request.getSession().setAttribute(Consts.ERROR, "Erro ao cadastrar cliente");
        return Consts.REDIRECT_ADD_CLIENT;
	}
}