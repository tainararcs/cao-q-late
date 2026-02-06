package br.trcs.petshop.logic;

import java.io.IOException;
import java.util.List;

import br.trcs.petshop.dao.ClientDAO;
import br.trcs.petshop.model.Client;
import br.trcs.petshop.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe responsável por listar todos os clientes cadastrados no sistema.
 * <br>A página de retorno é definida via parâmetro {@code returnPage}, 
 * permitindo reutilizar essa lógica em diferentes fluxos do sistema.
 */
public class ListClients implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       ClientDAO dao = new ClientDAO();
       List<Client> clientsList = dao.list();
	   
       if (!clientsList.isEmpty()) 
    	   request.setAttribute("clientsList", clientsList);
	   else 
		   request.setAttribute(Consts.ERROR, Consts.CLIENT_NOT_FOUND_ERROR);
       
       String returnPage = request.getParameter("returnPage");

       if (returnPage == null || returnPage.isEmpty()) 
           return Consts.HOME_JSP; // fallback seguro.

       return returnPage;
	}
} 