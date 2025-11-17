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
 * Classe responsável por atualizar o preço de um serviço existente no sistema.
 * <br>Esta lógica busca o serviço pelo nome informado, e caso o serviço exista, atualiza seu preço no banco de dados.
 */
public class UpdateService implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String price = request.getParameter("newPrice");
		
		// Converte o preço.
		BigDecimal newPrice = new BigDecimal(price);
		
		ServiceDAO dao = new ServiceDAO();
		
		// Busca o serviço pelo nome informado.
		Service service = dao.findByName(name);
		
		if (service != null) {
			boolean update = dao.update(service, newPrice);
		
			if (update) {
		        request.getSession().setAttribute(Consts.MSG, "Serviço alterado com sucesso!");
		        return Consts.REDIRECT_HOME;
			}
		}
		request.getSession().setAttribute(Consts.ERROR, "Erro ao alterar serviço");
		return Consts.REDIRECT_UPDATE_SERVCICE;
	}
}