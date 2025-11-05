package br.trcs.petshop.logic;

import java.io.IOException;
import java.math.BigDecimal;

import br.trcs.petshop.dao.ServiceDAO;
import br.trcs.petshop.model.Service;
import br.trcs.petshop.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe responsável por realizar a lógica de cadasatro de um novo serviço no sistema.
 * <br>Valida se o serviço já existe e, caso não exista, o insere no banco de dados.
 */
public class AddService implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		
		Service newService = new Service();
		newService.setName(name);
		newService.setPrice(new BigDecimal(price)); // Converte o valor textual em BigDecimal.
		
		ServiceDAO dao = new ServiceDAO();
		
		// Verifica se já existe um serviço cadastrado com o mesmo nome.
		if (dao.findByName(name) != null) {
			request.getSession().setAttribute(Consts.ERROR, "Serviço já cadastrado");
	        return Consts.REDIRECT_ADD_SERVICE;
		}
		
		boolean created = dao.create(newService);
		
		if (created) {
	        request.getSession().setAttribute(Consts.MSG, "Serviço cadastrado com sucesso");
	        return Consts.REDIRECT_HOME;
	    } 
        
		request.getSession().setAttribute(Consts.ERROR, "Erro ao cadastrar serviço");
        return Consts.REDIRECT_ADD_SERVICE;
	}
}