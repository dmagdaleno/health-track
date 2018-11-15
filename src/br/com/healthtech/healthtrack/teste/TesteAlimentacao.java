package br.com.healthtech.healthtrack.teste;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.dao.AlimentacaoDAO;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Alimentacao;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class TesteAlimentacao {

	public static void testa() {
		AlimentacaoDAO dao = new AlimentacaoDAO();
		
		// limpa a base
		dao.deleteAll();
		
		// insere novos registros
		adicionaRegistrosDeAlimentacao(dao);
		
		// exibe os registros
		System.out.println("Lista de alimentações registradas:");
		dao.getAll().forEach(alimentacao -> System.out.println(alimentacao));
		
		// fecha conexão com o banco
		dao.fechaConexao();
	}

	private static void adicionaRegistrosDeAlimentacao(AlimentacaoDAO dao) {
		Usuario usuario = constroiUsuario();
		
		dao.insert(new Alimentacao(1, "Cereal, iogurte e mel", 
				new BigDecimal(350), novaData("10/09/2018 07:00"), usuario));
		dao.insert(new Alimentacao(2, "Uma maçã grande", 
				new BigDecimal(100), novaData("10/09/2018 09:30"), usuario));
		dao.insert(new Alimentacao(3, "Arroz, feijão, carne e salada", 
				new BigDecimal(650), novaData("10/09/2018 12:00"), usuario));
		dao.insert(new Alimentacao(4, "Lanche", 
				new BigDecimal(220), novaData("10/09/2018 16:00"), usuario));
		dao.insert(new Alimentacao(5, "Filé de frango e salada", 
				new BigDecimal(580), novaData("10/09/2018 19:00"), usuario));
		dao.insert(new Alimentacao(1, "Cereal, iogurte e mel", 
				new BigDecimal(350), novaData("11/09/2018 07:00"), usuario));
		dao.insert(new Alimentacao(2, "Uma maçã grande", 
				new BigDecimal(100), novaData("11/09/2018 09:30"), usuario));
		dao.insert(new Alimentacao(3, "Arroz, feijão, carne e salada", 
				new BigDecimal(650), novaData("11/09/2018 12:00"), usuario));
		dao.insert(new Alimentacao(4, "Lanche", 
				new BigDecimal(220), novaData("11/09/2018 16:00"), usuario));
		dao.insert(new Alimentacao(5, "Filé de frango e salada", 
				new BigDecimal(580), novaData("11/09/2018 19:00"), usuario));
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
