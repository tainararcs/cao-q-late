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
        	request.setAttribute(Consts.ERROR, Consts.CPF_ERROR);
            return Consts.ADD_CLIENT_JSP;
        }
        
        // Valida o email.
        if (!ValidationUtils.isValidEmail(email)) {
        	request.setAttribute(Consts.ERROR, Consts.EMAIL_ERROR);
            return Consts.ADD_CLIENT_JSP;
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
		
		if (dao.findByCpf(cpf) != null) {
			request.setAttribute(Consts.ERROR, Consts.CLIENT_ALREADY_ADDED_ERROR);
            return Consts.ADD_CLIENT_JSP;
		}
		
		boolean created = dao.create(newClient);
		
	    if (created) 
	    	request.setAttribute(Consts.MSG, Consts.ADD_CLIENT_SUCCESS);
	    else 
	    	request.setAttribute(Consts.ERROR, Consts.ADD_CLIENT_ERROR);
	    
        return Consts.ADD_CLIENT_JSP;
	}
}