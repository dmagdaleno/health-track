package br.com.healthtech.healthtrack.teste;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.dao.AtividadeFisicaDAO;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.AtividadeFisica;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class TesteAtividadeFisica {

	public static void testa() {
		AtividadeFisicaDAO dao = new AtividadeFisicaDAO();
		
		// limpa a base
		dao.deleteAll();
		
		// insere novos registros
		adicionaRegistrosDeAtividadeFisica(dao);
		
		// exibe os registros
		System.out.println("\nLista de atividades registradas:");
		dao.getAll().forEach(atividade -> System.out.println(atividade));
		
		// fecha conexão com o banco
		dao.fechaConexao();
	}

	private static void adicionaRegistrosDeAtividadeFisica(AtividadeFisicaDAO dao) {
		Usuario usuario = constroiUsuario();
		
		dao.insert(new AtividadeFisica(1, "Caminhada", new BigDecimal(150), 
				novaData("2018-09-10T07:00"), usuario));
		dao.insert(new AtividadeFisica(2, "Corrida", new BigDecimal(800), 
				novaData("2018-09-11T09:30"), usuario));
		dao.insert(new AtividadeFisica(3, "Pedalada", new BigDecimal(1200), 
				novaData("2018-09-12T12:00"), usuario));
		dao.insert(new AtividadeFisica(4, "Musculação", new BigDecimal(100), 
				novaData("2018-09-13T16:00"), usuario));
		dao.insert(new AtividadeFisica(4, "Musculação", new BigDecimal(100), 
				novaData("2018-09-14T16:00"), usuario));
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
