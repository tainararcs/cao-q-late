package br.trcs.petshop.logic;

import java.io.IOException;

import br.trcs.petshop.dao.ClientDAO;
import br.trcs.petshop.dao.DogDAO;
import br.trcs.petshop.enums.DogSize;
import br.trcs.petshop.model.Client;
import br.trcs.petshop.model.Dog;
import br.trcs.petshop.utils.Consts;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe responsável por realizar o cadastro de um novo cachorro no sistema.
 * <br>Processa os dados enviados via formulário de cadastro de cachorro, validando a existência do cliente associado.
 */
public class AddDog implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String breed = request.getParameter("breed");
		String size = request.getParameter("size");
		String cpfClient = request.getParameter("client");

		Dog newDog = new Dog();
		newDog.setName(name);
		newDog.setBreed(breed);
		newDog.setSize(DogSize.valueOf(size));
		newDog.setCpfClient(cpfClient);
		
		DogDAO dao = new DogDAO();		
		Client client = new ClientDAO().findByCpf(cpfClient); 

		if (client == null) {
		    request.setAttribute(Consts.ERROR, Consts.CLIENT_NOT_FOUND_ERROR);
		    return Consts.ADD_DOG_JSP; 
		}
		
		boolean created = dao.create(newDog);

	    if (created) 
	        request.getSession().setAttribute(Consts.MSG, Consts.ADD_DOG_SUCCESS);
	    else
	    	request.getSession().setAttribute(Consts.ERROR, Consts.ADD_DOG_ERROR);
	    
        return Consts.REDIRECT_ADD_DOG_JSP; 
	}
}