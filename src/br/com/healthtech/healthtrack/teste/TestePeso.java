package br.com.healthtech.healthtrack.teste;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.PesoDAO;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Peso;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class TestePeso {
	
	public static void testa() {
		PesoDAO dao = DAOFactory.getPesoDAO();
		
		// limpa a base
		dao.excluiTodos();
		
		// insere novos registros
		adicionaRegistrosDePeso(dao);
		
		// exibe os registros
		System.out.println("\nLista de pesos registrados:");
		dao.buscaTodos().forEach(peso -> System.out.println(peso));
		
		// fecha conexão com o banco
		dao.fechaConexao();
	}

	private static void adicionaRegistrosDePeso(PesoDAO dao) {
		Usuario usuario = constroiUsuario();
		
		dao.insere(new Peso(new BigDecimal(83), novaData("2018-09-10T07:00"), usuario));
		dao.insere(new Peso(new BigDecimal(81), novaData("2018-09-19T07:10"), usuario));
		dao.insere(new Peso(new BigDecimal(79.3), novaData("2018-10-01T08:00"), usuario));
		dao.insere(new Peso(new BigDecimal(79), novaData("2018-10-02T08:00"), usuario));
		dao.insere(new Peso(new BigDecimal(78.5), novaData("2018-10-03T08:00"), usuario));
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
