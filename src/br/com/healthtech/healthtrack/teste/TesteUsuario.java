package br.com.healthtech.healthtrack.teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.UsuarioDAO;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Login;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class TesteUsuario {
	
	public static void main(String[] args) {
		try {
			testa();
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	private static void testa() throws DBException {
		UsuarioDAO dao = DAOFactory.getUsuarioDAO();
		
		LocalDateTime ultimoLogin = DateUtil.now(); 
		Login login = new Login("joao3@gmail.com", "grandesertaoveredas", ultimoLogin);
		Usuario usuario = new Usuario(
				"João Guimarães Rosa",
				DateUtil.toDate("1908-06-27"),
				"M",
				new BigDecimal(1.7),
				new BigDecimal(2000),
				login);
		
		dao.insere(usuario);
		
		System.out.println("Lista de alimentações registradas:");
		dao.buscaTodos().forEach(u -> System.out.println(u));
	}

}
