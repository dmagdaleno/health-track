package br.com.healthtech.healthtrack.teste;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.dao.PesoDAO;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Peso;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class TestePeso {

	public static void testa() {
		PesoDAO dao = new PesoDAO();
		
		// limpa a base
		dao.deleteAll();
		
		// insere novos registros
		adicionaRegistrosDePeso(dao);
		
		// exibe os registros
		System.out.println("\nLista de pesos registrados:");
		dao.getAll().forEach(peso -> System.out.println(peso));
		
		// fecha conexão com o banco
		dao.fechaConexao();
	}

	private static void adicionaRegistrosDePeso(PesoDAO dao) {
		Usuario usuario = constroiUsuario();
		
		dao.insert(new Peso(1L, new BigDecimal(83), novaData("10/09/2018 07:00"), usuario));
		dao.insert(new Peso(2L, new BigDecimal(81), novaData("19/09/2018 07:10"), usuario));
		dao.insert(new Peso(3L, new BigDecimal(79.3), novaData("01/10/2018 08:00"), usuario));
		dao.insert(new Peso(4L, new BigDecimal(79), novaData("02/10/2018 08:00"), usuario));
		dao.insert(new Peso(5L, new BigDecimal(78.5), novaData("03/10/2018 08:00"), usuario));
	}
	
	private static Usuario constroiUsuario() {
		String nome = "João da Silva";
		LocalDate dataNascimento = DateUtil.toDate("26/09/1989 00:00").toLocalDate();
		String genero = "Masculino";
		BigDecimal altura = new BigDecimal(1.70);
		BigDecimal limiteCaloriaDiaria = new BigDecimal(2000);
		
		return new Usuario(1L, nome, dataNascimento, genero, altura, limiteCaloriaDiaria);
	}
	
	private static LocalDateTime novaData(String data) {
		return DateUtil.toDate(data);
	}

}
