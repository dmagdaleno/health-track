package br.com.healthtech.healthtrack.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.UsuarioDAO;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.exception.LoginInvalidoException;
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
    	try {
			String acao = req.getParameter("acao");
			
			if(acao == null) 
				throw new IllegalArgumentException("Ação não pode ser nula");
			
			switch (acao) {
			case "entrar":
				entrar(req, resp);
				break;
				
			case "sair":
				sair(req, resp);
				break;
				
			default:
				break;
			}			
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			exibeErro(req, resp, e.getMessage());
		}
    }

	private void entrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        
        try {
			if(email == null || email.isEmpty()) 
				throw new IllegalArgumentException("Email inválido");
			
			if(senha == null || senha.isEmpty()) 
				throw new IllegalArgumentException("Senha inválida");
			
			Usuario usuario = validaLogin(email, senha);
			
			req.getSession().setAttribute("usuarioLogado", usuario);
			
			String caminho = req.getContextPath() + "/perfil?acao=dashboard";
			
			resp.sendRedirect(caminho);
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			exibeErro(req, resp, e.getMessage());
		}
		catch (LoginInvalidoException e) {
			exibeErro(req, resp, e.getMessage());
		}
	}
	
	private void sair(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("usuarioLogado", null);
		req.getRequestDispatcher("").forward(req, resp);
	}

	private void exibeErro(HttpServletRequest req, HttpServletResponse resp, String mensagem)
			throws ServletException, IOException {
		req.setAttribute("erro", mensagem);
		req.getRequestDispatcher("templates/login.jsp").forward(req, resp);
	}

	private Usuario validaLogin(String email, String senha) throws LoginInvalidoException{
		try {
			Usuario usuario = dao.buscaPor(email);
			
			if(usuario == null || usuario.getLogin() == null)
				throw new LoginInvalidoException("Usuário não encontrado");
			
			String hash = usuario.getLogin().getSenha();
			
			if(!BCrypt.checkpw(senha, hash))
				throw new LoginInvalidoException("Senha incorreta");
			
			return usuario;
			
		} 
		catch (LoginInvalidoException e) {
			throw e;
		} 
		catch (DBException e) {
			e.printStackTrace();
			throw new LoginInvalidoException("Não foi possível autenticar o usuário");
		}
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");
		
		if (acao != null && acao.equals("sair")) sair(req, resp);
    	
    	req.getRequestDispatcher("templates/login.jsp").forward(req, resp);
    }

}
