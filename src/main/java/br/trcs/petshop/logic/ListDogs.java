package br.trcs.petshop.logic;

import java.io.IOException;
import java.util.List;

import br.trcs.petshop.dao.ClientDAO;
import br.trcs.petshop.model.Dog;
import br.trcs.petshop.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe responsável por listar os cães de um cliente específico, identificado pelo CPF.
 * <br>A página de retorno é definida via parâmetro {@code returnPage},
 * permitindo reutilizar essa lógica em diferentes fluxos do sistema.
 */
public class ListDogs implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("dogsList");
		
		String cpf = request.getParameter("cpf");
		
		if (cpf != null) {
			cpf = cpf.trim();
			
			if (!cpf.isEmpty()) {
				ClientDAO dao = new ClientDAO();
				List<Dog> dogsList = dao.listDogs(cpf);
				
				if (!dogsList.isEmpty()) 
					request.setAttribute("dogsList", dogsList);
				else 
					request.setAttribute(Consts.ERROR, Consts.SEARCH_DOG_ERROR);
			}
		}
		   
		String returnPage = request.getParameter("returnPage");

		if (returnPage == null || returnPage.isEmpty()) 
			return Consts.HOME_JSP; 

        return returnPage;
	}
}