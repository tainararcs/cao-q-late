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

/**
 * Filtro responsável pelo controle de autenticação e autorização de acesso.
 * <br>Iintercepta todas as requisições da aplicação (mapeada para "/*") e valida se o administrador está autenticado antes de permitir o acesso. 
 * <ul>
 *   <li>Permite livre acesso ao login.</li>
 *   <li>Permite acesso a recursos estáticos (CSS, JS, imagens, ícones, etc).</li>
 *   <li>Bloqueia qualquer tentativa de acesso sem sessão válida de administrador.</li>
 *   <li>Redireciona para <b>login.jsp?expired=true</b> quando a sessão expira ou o usuário não está autenticado.</li>
 * </ul>
 */
@WebFilter("/*")
public class AuthFilter implements Filter {
    
	/**
     * Método principal do filtro. Intercepta todas as requisições HTTP.
     */
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
     * Verifica se a requisição deve ter acesso permitido.
     */
    private boolean shouldAllowAccess(HttpServletRequest request) {
        return isPublicResource(request) || isUserLoggedIn(request);
    }
    
    /**
     * Verifica se é um recurso público (login, logout, auth) ou recurso estático (css, js e imagens).
     */
    private boolean isPublicResource(HttpServletRequest request) {
        String path = request.getServletPath();
        String logic = request.getParameter("logic");
        
        return isLoginPage(path) || isAuthAction(logic) || isLogoutAction(logic) || isStaticResource(path);
    }
    
    /**
     * Verifica se é página de login.
     */
    private boolean isLoginPage(String path) {
        return path.endsWith("login.jsp");
    }
    
    /**
     * Verifica se é ação de autenticação.
     */
    private boolean isAuthAction(String logic) {
        return "AuthAdmin".equals(logic);
    }
    
    /**
     * Verifica se é ação de logout.
     */
    private boolean isLogoutAction(String logic) {
        return "Logout".equals(logic);
    }
    
    /**
     * Verifica se é um recurso estático (CSS, JS, imagens, etc).
     */
    private boolean isStaticResource(String path) {
        return path.startsWith("/css/") || 
               path.startsWith("/js/") || 
               path.startsWith("/img/") ||
               path.endsWith(".css") || 
               path.endsWith(".js") || 
               path.endsWith(".png") || 
               path.endsWith(".jpg") ||
               path.endsWith(".ico");
    }
    
    /**
     * Verifica se usuário está logado.
     */
    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("admin") != null;
    }
    
    /**
     * Trata acesso negado: redireciona para login.
     */
    private void handleAccessDenied(HttpServletResponse response) throws IOException {
        System.out.println("\nAcesso negado - sessão expirada ou não autenticado");
        response.sendRedirect("login.jsp?expired=true");
    }
}