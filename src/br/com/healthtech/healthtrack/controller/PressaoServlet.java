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
import br.com.healthtech.healthtrack.dao.PressaoArterialDAO;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.PressaoArterial;
import br.com.healthtech.healthtrack.utils.DateUtil;

@WebServlet("/pressao-arterial")
public class PressaoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private PressaoArterialDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getPressaoArterialDAO();
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
			BigDecimal pressaoMax = new BigDecimal(req.getParameter("pressaoMax"));
			BigDecimal pressaoMin = new BigDecimal(req.getParameter("pressaoMin"));
			LocalDateTime data = DateUtil.toDateTime(req.getParameter("data"));
			
			PressaoArterial registro = new PressaoArterial(pressaoMax, pressaoMin, data, new Usuario(1L));
			
			dao.insere(registro);
			
			req.setAttribute("sucesso", "Cadastro realizado com sucesso!");
			listar(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível realizar o cadastro.");
			req.getRequestDispatcher("templates/cadastro/pressao-arterial.jsp").forward(req, resp);
		}
		
	}

	private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long id = Long.parseLong(req.getParameter("id"));
			Long idUsuario = Long.parseLong(req.getParameter("idUsuario"));
			BigDecimal pressaoMax = new BigDecimal(req.getParameter("pressaoMax"));
			BigDecimal pressaoMin = new BigDecimal(req.getParameter("pressaoMin"));
			LocalDateTime data = DateUtil.toDateTime(req.getParameter("data"));
			
			PressaoArterial registro = new PressaoArterial(id, pressaoMax, pressaoMin, data, new Usuario(idUsuario));
			
			dao.atualiza(registro);
			
			req.setAttribute("sucesso", "Registro editado com sucesso!");
			listar(req, resp);
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível editar o registro.");
			req.getRequestDispatcher("templates/edicao/pressao-arterial.jsp").forward(req, resp);
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
			PressaoArterial registro = dao.buscaPor(id);
			req.setAttribute("registro", registro);
		} catch (DBException e) {
			req.setAttribute("erro", "Não foi possível carregar dados para edição.");
		}
		req.getRequestDispatcher("templates/edicao/pressao-arterial.jsp").forward(req, resp);
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			List<PressaoArterial> registros = dao.buscaPor(new Usuario(1L));
			req.setAttribute("registros", registros);
		} catch (DBException e) {
			req.setAttribute("erro", "Não foi possível carregar os registros.");
		}
		req.getRequestDispatcher("templates/lista/pressao-arterial.jsp").forward(req, resp);
	}

}
