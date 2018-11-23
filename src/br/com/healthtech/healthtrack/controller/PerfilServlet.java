package br.com.healthtech.healthtrack.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtech.healthtrack.dao.AlimentacaoDAO;
import br.com.healthtech.healthtrack.dao.AtividadeFisicaDAO;
import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.PesoDAO;
import br.com.healthtech.healthtrack.dao.PressaoArterialDAO;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.IMC;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Alimentacao;
import br.com.healthtech.healthtrack.modelo.registro.AtividadeFisica;
import br.com.healthtech.healthtrack.modelo.registro.Peso;
import br.com.healthtech.healthtrack.modelo.registro.PressaoArterial;

@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private PressaoArterialDAO pressaoDao;
	private PesoDAO pesoDao;
	private AtividadeFisicaDAO atividadeDao;
	private AlimentacaoDAO alimentacaoDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		pressaoDao = DAOFactory.getPressaoArterialDAO();
		pesoDao = DAOFactory.getPesoDAO();
		atividadeDao = DAOFactory.getAtividadeFisicaDAO();
		alimentacaoDao = DAOFactory.getAlimentacaoDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String acao = req.getParameter("acao");
			
			if(acao == null) 
				throw new IllegalArgumentException("Ação não pode ser nula");
			
			switch (acao) {
			case "perfil":
				carregarPerfil(req, resp);
				break;
				
			case "dashboard":
				carregarDashboard(req, resp);
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

	private void carregarPerfil(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try {
			Usuario usuario = recuperarUsuario(req);
			Peso peso = recuperarUltimoPeso(usuario);
			PressaoArterial pressao = recuperarUltimaPressaoArterial(usuario);
			
			if(peso != null) {
				IMC imc = new IMC(peso.getPeso(), usuario.getAltura());
				req.setAttribute("ultimoRegistroPeso", peso);
				req.setAttribute("imc", imc.calcula().doubleValue());
			}
			
			if(pressao != null) {
				req.setAttribute("ultimoRegistroPressao", pressao);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível carregar os dados do perfil");
		}
		
		req.getRequestDispatcher("templates/perfil.jsp").forward(req, resp);
	}
	
	private void carregarDashboard(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try {
			Usuario usuario = recuperarUsuario(req);
			Peso peso = recuperarUltimoPeso(usuario);
			PressaoArterial pressao = recuperarUltimaPressaoArterial(usuario);
			
			List<AtividadeFisica> atividades = recuperarUltimasAtividades(usuario);
			List<Alimentacao> alimentacoes = recuperarUltimasAlimentacoes(usuario);

			if(peso != null) {
				IMC imc = new IMC(peso.getPeso(), usuario.getAltura());
				req.setAttribute("ultimoRegistroPeso", peso);
				req.setAttribute("imc", imc.calcula().doubleValue());
			}
			
			if(pressao != null) {
				req.setAttribute("ultimoRegistroPressao", pressao);
			}
			
			req.setAttribute("atividades", atividades);
			req.setAttribute("alimentacoes", alimentacoes);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("erro", "Não foi possível carregar os dados do dashboard");
		}
		
		req.getRequestDispatcher("templates/dashboard.jsp").forward(req, resp);
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
	
	private List<AtividadeFisica> recuperarUltimasAtividades(Usuario usuario) throws DBException {
		return atividadeDao.buscaPor(usuario, 3);
	}

	private List<Alimentacao> recuperarUltimasAlimentacoes(Usuario usuario) throws DBException {
		return alimentacaoDao.buscaPor(usuario, 3);
	}
}
