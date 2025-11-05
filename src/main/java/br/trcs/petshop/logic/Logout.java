package br.trcs.petshop.logic;

import java.io.IOException;

import br.trcs.petshop.utils.Consts;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Classe responsável por realizar o encerramento da sessão do administrador no sistema.
 * <br>Esta lógica invalida a sessão atual, removendo todos os atributos e redirecionando o usuário para a página de login.
 */
public class Logout implements Logic {

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// Obtém a sessão atual, sem criar uma nova se ela não existir.
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            session.invalidate(); // Destrói completamente a sessão.
            System.out.println("\nSessão finalizada com sucesso");
        }
        
        // Redireciona para a página de login com parâmetro indicando logout bem-sucedido.
        return Consts.REDIRECT_LOGIN + "?logout=true";
    }
}