package br.com.healthtech.healthtrack.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.PesoDAO;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Peso;
import br.com.healthtech.healthtrack.utils.DateUtil;

@WebServlet("/peso")
public class PesoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private PesoDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getPesoDAO();
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
			BigDecimal peso = new BigDecimal(req.getParameter("peso"));
			LocalDateTime data = DateUtil.toDateTime(req.getParameter("data"));
			
			Peso registro = new Peso(peso, data, new Usuario(1L));
			
			dao.insere(registro);
			
			req.setAttribute("sucesso", "Peso cadastrado com sucesso!");
			listar(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível cadastrar o peso.");
			req.getRequestDispatcher("templates/cadastro/peso.jsp").forward(req, resp);
		}
		
	}

	private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long id = Long.parseLong(req.getParameter("id"));
			Long idUsuario = Long.parseLong(req.getParameter("idUsuario"));
			BigDecimal peso = new BigDecimal(req.getParameter("peso"));
			LocalDateTime data = DateUtil.toDateTime(req.getParameter("data"));
			
			Peso registro = new Peso(id, peso, data, new Usuario(idUsuario));
			
			dao.atualiza(registro);
			
			req.setAttribute("sucesso", "Peso editado com sucesso!");
			listar(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível editar o peso.");
			req.getRequestDispatcher("templates/edicao/peso.jsp").forward(req, resp);
		}
	}
	
	private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long id = Long.parseLong(req.getParameter("id"));
			
			dao.exclui(id);
			
			req.setAttribute("sucesso", "Peso excluído com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível excluir o peso.");
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
		Peso registro = dao.buscaPor(id);
		req.setAttribute("registro", registro);
		req.getRequestDispatcher("templates/edicao/peso.jsp").forward(req, resp);
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Peso> registros = dao.buscaTodos();
		req.setAttribute("registros", registros);
		req.getRequestDispatcher("templates/lista/peso.jsp").forward(req, resp);
	}

}
