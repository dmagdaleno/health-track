package br.com.healthtech.healthtrack.teste;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.dao.PressaoArterialDAO;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.PressaoArterial;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class TestePressaoArterial {

	public static void testa() {
		PressaoArterialDAO dao = new PressaoArterialDAO();
		
		// limpa a base
		dao.deleteAll();
		
		// insere novos registros
		adicionaRegistrosDePressaoArterial(dao);
		
		// exibe os registros
		System.out.println("\nLista de pressões registradas:");
		dao.getAll().forEach(pressao -> System.out.println(pressao));
		
		// fecha conexão com o banco
		dao.fechaConexao();
	}

	private static void adicionaRegistrosDePressaoArterial(PressaoArterialDAO dao) {
		Usuario usuario = constroiUsuario();
		
		dao.insert(new PressaoArterial(1L, new BigDecimal(140), new BigDecimal(100), novaData("10/09/2018 07:00"), usuario));
		dao.insert(new PressaoArterial(2L, new BigDecimal(130), new BigDecimal(90), novaData("19/09/2018 07:10"), usuario));
		dao.insert(new PressaoArterial(3L, new BigDecimal(120), new BigDecimal(80), novaData("01/10/2018 08:00"), usuario));
		dao.insert(new PressaoArterial(4L, new BigDecimal(120), new BigDecimal(80), novaData("02/10/2018 08:00"), usuario));
		dao.insert(new PressaoArterial(5L, new BigDecimal(120), new BigDecimal(80), novaData("03/10/2018 08:00"), usuario));
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
