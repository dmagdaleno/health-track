package br.com.healthtech.healthtrack.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.PesoDAO;
import br.com.healthtech.healthtrack.dao.PressaoArterialDAO;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.IMC;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Peso;
import br.com.healthtech.healthtrack.modelo.registro.PressaoArterial;

@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private PressaoArterialDAO pressaoDao;
	private PesoDAO pesoDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		pressaoDao = DAOFactory.getPressaoArterialDAO();
		pesoDao = DAOFactory.getPesoDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Usuario usuario = recuperarUsuario(req);
			Peso peso = recuperarUltimoPeso(usuario);
			PressaoArterial pressao = recuperarUltimaPressaoArterial(usuario);
			IMC imc = new IMC(peso.getPeso(), usuario.getAltura());
			
			req.setAttribute("ultimoRegistroPeso", peso);
			req.setAttribute("ultimoRegistroPressao", pressao);
			req.setAttribute("imc", imc.calcula().doubleValue());
			
			req.getRequestDispatcher("templates/perfil.jsp").forward(req, resp);
		} 
		catch (DBException e) {
			e.printStackTrace();
		}
	}
	
	private Usuario recuperarUsuario(HttpServletRequest req) {
		return (Usuario)req.getSession().getAttribute("usuarioLogado");
	}

	private Peso recuperarUltimoPeso(Usuario usuario) throws DBException {
		List<Peso> registros = pesoDao.buscaPor(usuario, 1);
		
		Peso registro = null;
		if(registros != null && registros.size() > 0)
			registro = registros.get(0);
		
		return registro;
	}
	
	private PressaoArterial recuperarUltimaPressaoArterial(Usuario usuario) throws DBException {
		List<PressaoArterial> registros = pressaoDao.buscaPor(usuario, 1);
		
		PressaoArterial registro = null;
		if(registros != null && registros.size() > 0)
			registro = registros.get(0);
		
		return registro;
	}
}
