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

import br.com.healthtech.healthtrack.dao.AtividadeFisicaDAO;
import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.AtividadeFisica;
import br.com.healthtech.healthtrack.utils.DateUtil;

@WebServlet("/atividade")
public class AtividadeFisicaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private AtividadeFisicaDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getAtividadeFisicaDAO();
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
			int tipo = Integer.parseInt(req.getParameter("tipo"));
			BigDecimal calorias = new BigDecimal(req.getParameter("calorias"));
			String descricao = req.getParameter("descricao");
			LocalDateTime dataRegistro = DateUtil.toDateTime(req.getParameter("data"));
			
			AtividadeFisica registro = new AtividadeFisica(tipo, descricao, calorias, dataRegistro, 1L);
			
			dao.insere(registro);
			
			req.setAttribute("sucesso", "Cadastro realizado com sucesso!");
			listar(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível realizar o cadastro.");
			req.getRequestDispatcher("templates/cadastro/atividade.jsp").forward(req, resp);
		}
		
	}

	private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long id = Long.parseLong(req.getParameter("id"));
			Long idUsuario = Long.parseLong(req.getParameter("idUsuario"));
			int tipo = Integer.parseInt(req.getParameter("tipo"));
			BigDecimal calorias = new BigDecimal(req.getParameter("calorias"));
			String descricao = req.getParameter("descricao");
			LocalDateTime data = DateUtil.toDateTime(req.getParameter("data"));
			
			AtividadeFisica registro = new AtividadeFisica(id, tipo, descricao, calorias, data, idUsuario);
			
			dao.atualiza(registro);
			
			req.setAttribute("sucesso", "Registro editado com sucesso!");
			listar(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível editar o registro.");
			req.getRequestDispatcher("templates/edicao/atividade.jsp").forward(req, resp);
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
			AtividadeFisica registro = dao.buscaPor(id);
			req.setAttribute("registro", registro);
		} catch (DBException e) {
			req.setAttribute("erro", "Não foi possível carregar dados para edição.");
		}
		req.getRequestDispatcher("templates/edicao/atividade.jsp").forward(req, resp);
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			List<AtividadeFisica> registros = dao.buscaPor(new Usuario(1L));
			req.setAttribute("registros", registros);
		} catch (DBException e) {
			req.setAttribute("erro", "Não foi possível carregar os registros.");
		}
		req.getRequestDispatcher("templates/lista/atividade.jsp").forward(req, resp);
	}

}
