package br.com.healthtech.healthtrack.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.UsuarioDAO;
import br.com.healthtech.healthtrack.modelo.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getUsuarioDAO();
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("--> post login");
    	
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        
        try {
			if(email == null || email.isEmpty()) 
				throw new IllegalArgumentException("Email inválido");
			
			if(senha == null || senha.isEmpty()) 
				throw new IllegalArgumentException("Senha inválida");
			
			Usuario usuario = validaLogin(email, senha);
			
			req.getSession().setAttribute("usuario", usuario);
			
			System.out.println("<-- post login");

			req.getRequestDispatcher("templates/dashboard.jsp").forward(req, resp);
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
			System.out.println("<-- redirecionando login");
			req.getRequestDispatcher("templates/login.jsp").forward(req, resp);
		}
    }

	private Usuario validaLogin(String email, String senha) {
		try {
			Usuario usuario = dao.buscaPor(email);
			
			if(usuario == null)
				throw new Exception();
			
			if(!senha.equals(usuario.getLogin().getSenha()))
				throw new IllegalArgumentException("Senha incorreta.");
			
			return usuario;
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Usuário não encontrado.");
		}
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("--> get login");
    	req.getRequestDispatcher("templates/login.jsp").forward(req, resp);
    }

}
