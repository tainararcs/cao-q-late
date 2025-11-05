package br.trcs.petshop.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        if (shouldAllowAccess(request)) {
            chain.doFilter(req, res);
        } else {
            handleAccessDenied(response);
        }
    }
    
    /**
     * Verifica se a requisição deve ter acesso permitido
     */
    private boolean shouldAllowAccess(HttpServletRequest request) {
        return isPublicResource(request) || isUserLoggedIn(request);
    }
    
    /**
     * Verifica se é um recurso público (login, logout, auth) ou recurso estático
     */
    private boolean isPublicResource(HttpServletRequest request) {
        String path = request.getServletPath();
        String logic = request.getParameter("logic");
        
        return isLoginPage(path) || isAuthAction(logic) || isLogoutAction(logic) || isStaticResource(path);
    }
    
    /**
     * Verifica se é página de login
     */
    private boolean isLoginPage(String path) {
        return path.endsWith("login.jsp");
    }
    
    /**
     * Verifica se é ação de autenticação
     */
    private boolean isAuthAction(String logic) {
        return "AuthAdmin".equals(logic);
    }
    
    /**
     * Verifica se é ação de logout
     */
    private boolean isLogoutAction(String logic) {
        return "Logout".equals(logic);
    }
    
    /**
     * Verifica se é um recurso estático (CSS, JS, imagens, etc)
     */
    private boolean isStaticResource(String path) {
        return path.startsWith("/css/") || 
               path.startsWith("/js/") || 
               path.startsWith("/images/") ||
               path.endsWith(".css") || 
               path.endsWith(".js") || 
               path.endsWith(".png") || 
               path.endsWith(".jpg") ||
               path.endsWith(".ico");
    }
    
    /**
     * Verifica se usuário está logado
     */
    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("admin") != null;
    }
    
    /**
     * Trata acesso negado - redireciona para login
     */
    private void handleAccessDenied(HttpServletResponse response) throws IOException {
        System.out.println("\nAcesso negado - sessão expirada ou não autenticado");
        response.sendRedirect("login.jsp?expired=true");
    }
}