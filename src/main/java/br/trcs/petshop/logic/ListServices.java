package br.trcs.petshop.logic;

import java.io.IOException;
import java.util.List;

import br.trcs.petshop.dao.ServiceDAO;
import br.trcs.petshop.model.Service;
import br.trcs.petshop.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe responsável por listar todos os serviços cadastrados no sistema.
 *<br>A página de retorno é definida via parâmetro {@code returnPage},
 * permitindo reutilizar essa lógica em diferentes fluxos do sistema.
 */
public class ListServices implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   // Carrega a lista de serviços.
	   ServiceDAO dao = new ServiceDAO();
	   List<Service> servicesList = dao.list();
	   
	   if (!servicesList.isEmpty()) 
		   request.setAttribute("servicesList", servicesList);
	   else 
		   request.setAttribute(Consts.ERROR, Consts.SERVICE_NOT_FOUND_ERROR);
		   
	   String returnPage = request.getParameter("returnPage");

       if (returnPage == null || returnPage.isEmpty()) 
           return Consts.HOME_JSP; 

       return returnPage;
	}
}