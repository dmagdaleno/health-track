package br.com.healthtech.healthtrack.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";

        boolean logado = session != null && session.getAttribute("usuarioLogado") != null;
        boolean recursoAberto = recursoNaoProtegido(request, loginURI);

        if (logado || recursoAberto) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }
    }

	private boolean recursoNaoProtegido(HttpServletRequest request, String loginURI) {
		String homeURI = request.getContextPath() + "/";
		String staticURI = request.getContextPath() + "/static";
		String cadastroURI = request.getContextPath() + "/templates/cadastro/perfil.jsp";
		String cadastroURI2 = request.getContextPath() + "/usuario";
		return 
				request.getRequestURI().startsWith(staticURI) ||
				request.getRequestURI().equals(homeURI) ||
				request.getRequestURI().equals(cadastroURI)||
				request.getRequestURI().equals(cadastroURI2)||
				request.getRequestURI().equals(loginURI);
	}
}
