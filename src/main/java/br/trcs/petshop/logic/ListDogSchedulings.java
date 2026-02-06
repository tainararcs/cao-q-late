package br.trcs.petshop.logic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import br.trcs.petshop.dao.ClientDAO;
import br.trcs.petshop.dao.DogDAO;
import br.trcs.petshop.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe responsável por listar os agendamentos já finalizados de um cão específico.
 */
public class ListDogSchedulings implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String cpf = request.getParameter("cpf");
	    String dog = request.getParameter("dog");

	    // Recarrega a lista de cães para que o formulário continue aparecendo no JSP.
	    if (cpf != null && !cpf.trim().isEmpty()) {
	        ClientDAO clientDAO = new ClientDAO();
	        request.setAttribute("dogsList", clientDAO.listDogs(cpf.trim()));
	    }

	    // Carrega a lista de serviços do cão.
		if (dog != null && !dog.isEmpty()) {
			Integer idDog = Integer.valueOf(dog);
			DogDAO dogDAO = new DogDAO();
			
			List<Map<String, Object>> finishedList = dogDAO.listFinishedSchedulings(idDog);
					
			if (!finishedList.isEmpty()) 
				request.setAttribute("finishedList", finishedList);
			else 
				request.setAttribute(Consts.ERROR, Consts.SCHEDULING_NOT_FOUND_ERROR);
		}
		
		return Consts.LIST_DOG_SCHEDULINGS_JSP;
	}
}