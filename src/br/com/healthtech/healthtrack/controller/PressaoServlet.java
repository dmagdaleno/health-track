package br.com.healthtech.healthtrack.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.PressaoArterialDAO;

@WebServlet("/pressao")
public class PressaoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private PressaoArterialDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getPressaoArterialDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		try {
			String pressaoMax = req.getParameter("peso");
			String pressaoMin = req.getParameter("peso");
			String data = req.getParameter("data");
			
			req.setAttribute("sucesso", "sucesso!!");
		}
		catch (Exception e) {
			req.setAttribute("erro", "erro!!");
		}
		
		req.getRequestDispatcher("cadastro/peso.jsp").forward(req, resp);
	}

}
