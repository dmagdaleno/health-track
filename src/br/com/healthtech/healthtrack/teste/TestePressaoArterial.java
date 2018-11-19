package br.com.healthtech.healthtrack.teste;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.PressaoArterialDAO;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.PressaoArterial;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class TestePressaoArterial {
	
	public static void main(String[] args) {
		testa();
	}

	public static void testa() {
		PressaoArterialDAO dao = DAOFactory.getPressaoArterialDAO();
		
		// limpa a base
		dao.excluiTodos();
		
		// insere novos registros
		adicionaRegistrosDePressaoArterial(dao);
		
		// exibe os registros
		System.out.println("\nLista de pressões registradas:");
		Usuario usuario = constroiUsuario();
		dao.buscaPor(usuario).forEach(pressao -> System.out.println(pressao));
		
		// fecha conexão com o banco
		dao.fechaConexao();
	}

	private static void adicionaRegistrosDePressaoArterial(PressaoArterialDAO dao) {
		Usuario usuario = constroiUsuario();
		
		dao.insere(new PressaoArterial(new BigDecimal(140), new BigDecimal(100), novaData("2018-09-10T07:00"), usuario));
		dao.insere(new PressaoArterial(new BigDecimal(130), new BigDecimal(90), novaData("2018-09-19T07:10"), usuario));
		dao.insere(new PressaoArterial(new BigDecimal(120), new BigDecimal(80), novaData("2018-10-01T08:00"), usuario));
		dao.insere(new PressaoArterial(new BigDecimal(120), new BigDecimal(80), novaData("2018-10-02T08:00"), usuario));
		dao.insere(new PressaoArterial(new BigDecimal(120), new BigDecimal(80), novaData("2018-10-03T08:00"), usuario));
	}
	
	private static Usuario constroiUsuario() {
		String nome = "João da Silva";
		LocalDate dataNascimento = DateUtil.toDate("1989-01-01");
		String genero = "Masculino";
		BigDecimal altura = new BigDecimal(1.70);
		BigDecimal limiteCaloriaDiaria = new BigDecimal(2000);
		
		return new Usuario(1L, nome, dataNascimento, genero, altura, limiteCaloriaDiaria);
	}
	
	private static LocalDateTime novaData(String data) {
		return DateUtil.toDateTime(data);
	}

}
