package br.com.healthtech.healthtrack;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.healthtech.healthtrack.dao.AlimentacaoDAO;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Alimentacao;
import br.com.healthtech.healthtrack.modelo.registro.TipoAlimentacao;

public class Teste {

	public static void main(String[] args) {		
		AlimentacaoDAO dao = new AlimentacaoDAO();
		adicionaRegistrosDeAlimentacao(dao);
		
		System.out.println("Lista de alimentações registradas:");
		dao.getAll().forEach(alimentacao -> System.out.println(alimentacao));
	}

	private static void adicionaRegistrosDeAlimentacao(AlimentacaoDAO dao) {
		Usuario usuario = constroiUsuario();
		
		dao.add(new Alimentacao(1L, TipoAlimentacao.CAFE_DA_MANHA, "Cereal, iogurte e mel", 
				new BigDecimal(350), novaData("10/09/2018 07:00"), usuario));
		dao.add(new Alimentacao(2L, TipoAlimentacao.FRUTA, "Uma maçã grande", 
				new BigDecimal(100), novaData("10/09/2018 09:30"), usuario));
		dao.add(new Alimentacao(3L, TipoAlimentacao.ALMOCO, "Arroz, feijão, carne e salada", 
				new BigDecimal(650), novaData("10/09/2018 12:00"), usuario));
		dao.add(new Alimentacao(4L, TipoAlimentacao.LANCHE_LEVE, "Lanche", 
				new BigDecimal(220), novaData("10/09/2018 16:00"), usuario));
		dao.add(new Alimentacao(5L, TipoAlimentacao.JANTAR, "Filé de frango e salada", 
				new BigDecimal(580), novaData("10/09/2018 19:00"), usuario));
		dao.add(new Alimentacao(6L, TipoAlimentacao.CAFE_DA_MANHA, "Cereal, iogurte e mel", 
				new BigDecimal(350), novaData("11/09/2018 07:00"), usuario));
		dao.add(new Alimentacao(7L, TipoAlimentacao.FRUTA, "Uma maçã grande", 
				new BigDecimal(100), novaData("11/09/2018 09:30"), usuario));
		dao.add(new Alimentacao(8L, TipoAlimentacao.ALMOCO, "Arroz, feijão, carne e salada", 
				new BigDecimal(650), novaData("11/09/2018 12:00"), usuario));
		dao.add(new Alimentacao(9L, TipoAlimentacao.LANCHE_LEVE, "Lanche", 
				new BigDecimal(220), novaData("11/09/2018 16:00"), usuario));
		dao.add(new Alimentacao(10L, TipoAlimentacao.JANTAR, "Filé de frango e salada", 
				new BigDecimal(580), novaData("11/09/2018 19:00"), usuario));
	}
	
	private static Usuario constroiUsuario() {
		String nome = "João da Silva";
		LocalDate dataNascimento = LocalDate.parse(
				"26/09/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String genero = "Masculino";
		BigDecimal altura = new BigDecimal(1.70);
		BigDecimal limiteCaloriaDiaria = new BigDecimal(2000);
		
		return new Usuario(1L, nome, dataNascimento, genero, altura, limiteCaloriaDiaria);
	}
	
	private static LocalDateTime novaData(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return LocalDateTime.parse(data, formatter);
	}

}
