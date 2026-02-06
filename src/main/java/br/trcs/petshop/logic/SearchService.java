package br.trcs.petshop.logic;

import java.io.IOException;

import br.trcs.petshop.dao.ServiceDAO;
import br.trcs.petshop.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe responsável por buscar um serviço específico pelo nome.
 */
public class SearchService implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		
		if (name != null && !name.isEmpty()) {
		   ServiceDAO dao = new ServiceDAO();
		   request.setAttribute("service", dao.findByName(name));
		}
		
		return Consts.UPDATE_SERVICE_JSP;
	}
}
