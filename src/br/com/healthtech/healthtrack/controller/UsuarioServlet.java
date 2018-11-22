package br.com.healthtech.healthtrack.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.UsuarioDAO;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Login;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.utils.DateUtil;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

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
			case "cadastrar":
				cadastrar(req, resp);
				break;
				
			case "editar":
				editar(req, resp);
				break;
				
			case "excluir":
				excluir(req, resp);
				break;

			default:
				break;
			}			
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
		
	}

	private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = req.getParameter("email");
			String senha = req.getParameter("senha");
			String nome = req.getParameter("nome");
			LocalDate dataNascimento = DateUtil.toDate(req.getParameter("data"));
			BigDecimal altura = new BigDecimal(req.getParameter("altura"));
			String genero = req.getParameter("genero");
			BigDecimal limiteCaloria = new BigDecimal(req.getParameter("limiteCalorico"));
			LocalDateTime ultimoLogin = DateUtil.now();
			
			Login login = new Login(email, senha, ultimoLogin);
			Usuario usuario = new Usuario(nome, dataNascimento, genero, altura, limiteCaloria, login);
			
			dao.insere(usuario);
			
			req.setAttribute("sucesso", "Cadastro realizado com sucesso!");
			listar(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível realizar o cadastro.");
			req.getRequestDispatcher("templates/cadastro/perfil.jsp").forward(req, resp);
		}
		
	}

	private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = req.getParameter("email");
			String senha = req.getParameter("senha");
			String nome = req.getParameter("nome");
			LocalDate dataNascimento = DateUtil.toDate(req.getParameter("data"));
			BigDecimal altura = new BigDecimal(req.getParameter("altura"));
			String genero = req.getParameter("genero");
			BigDecimal limiteCaloria = new BigDecimal(req.getParameter("limiteCalorico"));
			LocalDateTime ultimoLogin = DateUtil.now();
			
			Login login = new Login(email, senha, ultimoLogin);
			Usuario usuario = new Usuario(nome, dataNascimento, genero, altura, limiteCaloria, login);
			
			dao.atualiza(usuario);
			
			req.setAttribute("sucesso", "Registro editado com sucesso!");
			listar(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível editar o registro.");
			req.getRequestDispatcher("templates/edicao/perfil.jsp").forward(req, resp);
		}
	}
	
	private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long id = Long.parseLong(req.getParameter("id"));
			
			dao.exclui(id);
			
			req.setAttribute("sucesso", "Registro excluído com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível excluir o registro.");
		}
		
		listar(req, resp);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String acao = req.getParameter("acao");
			
			if(acao == null) 
				throw new IllegalArgumentException("Ação não pode ser nula");
			
			switch (acao) {
			case "listar":
				listar(req, resp);
				break;
				
			case "editar":
				abrirFormularioEdicao(req, resp);
				break;

			default:
				break;
			}			
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			req.setAttribute("erro", e.getMessage());
		}
	}

	private void abrirFormularioEdicao(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		try {
			Usuario usuario = dao.buscaPor(id);
			req.setAttribute("usuario", usuario);
		} catch (DBException e) {
			req.setAttribute("erro", "Não foi possível carregar dados para edição.");
		}
		req.getRequestDispatcher("templates/edicao/perfil.jsp").forward(req, resp);
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<Usuario> usuarios = dao.buscaTodos();
		System.out.println(usuarios);
		req.setAttribute("usuarios", usuarios);
		
		req.getRequestDispatcher("/").forward(req, resp);
	}

}
