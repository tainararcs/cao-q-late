package br.trcs.petshop.controller;

import br.trcs.petshop.logic.Logic;
import br.trcs.petshop.utils.Consts;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Controller principal da aplicação PetShop. Responsável por direcionar requisições para a lógica correspondente.
 * <br>O controlador delega dinamicamente a execução para a classe lógica correspondente, com base no parâmetro recebido da requisição HTTP via interface ({@link Logic}).
 */
@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String logicParam = request.getParameter("logic");
        String url = Consts.LOGIN; // Página padrão para fallback. 

        try {
            // Se o parâmetro terminar com ".jsp", apenas encaminha.
            if (logicParam != null && logicParam.endsWith(".jsp")) url = logicParam;
            
            else {
                // Caso contrário, tenta carregar a classe normalmente
                String className = "br.trcs.petshop.logic." + logicParam; 
                
                // Carrega a classe dinamicamente.
                Class<?> logicClass = Class.forName(className); 
                
                // Instancia a lógica específica.
                Logic logicInstance = (Logic) logicClass.getDeclaredConstructor().newInstance();

                // Executa a lógica e obtém a URL de retorno (o destino).
                url = logicInstance.service(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Faz o redirecionamento ou exibe a mensagem de erro.
        if (url.startsWith("redirect:")) 
            response.sendRedirect(url.substring(9));
        else 
            request.getRequestDispatcher(url).forward(request, response);
    }
}