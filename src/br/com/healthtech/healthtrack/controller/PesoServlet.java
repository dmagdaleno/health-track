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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		try {
			BigDecimal peso = new BigDecimal(req.getParameter("peso"));
			LocalDateTime data = DateUtil.toDateTime(req.getParameter("data"));
			
			Peso registro = new Peso(peso, data, new Usuario(1L));
			
			dao.insert(registro);
			
			req.setAttribute("sucesso", "Peso cadastrado com sucesso!");
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível cadastrar o peso.");
		}
		
		req.getRequestDispatcher("templates/cadastro/peso.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		System.out.println(">>> PesoServlet.doGet");
		List<Peso> registros = dao.getAll();
		System.out.println("Registros: " + registros);
		System.out.println("<<< PesoServlet.doGet");
		req.setAttribute("registros", registros);
		req.getRequestDispatcher("templates/lista/peso.jsp").forward(req, resp);
	}

}
