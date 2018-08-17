package br.com.healthtech.healthtrack;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.healthtech.healthtrack.modelo.Peso;
import br.com.healthtech.healthtrack.modelo.PressaoArterial;
import br.com.healthtech.healthtrack.modelo.Usuario;

public class Main {

	public static void main(String[] args) {
		String nome = "João da Silva";
		LocalDate dataNascimento = LocalDate.parse("26/09/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String genero = "Masculino";
		BigDecimal altura = new BigDecimal(1.70);
		BigDecimal limiteCaloriaDiaria = new BigDecimal(2000);
		
		LocalDate hoje = LocalDate.now();
		Peso peso = new Peso(new BigDecimal(70), hoje);
		
		BigDecimal pressaoMaxima = new BigDecimal(110);
		BigDecimal pressaoMinima = new BigDecimal(80);
		PressaoArterial pressao = new PressaoArterial(pressaoMaxima, pressaoMinima, hoje.atStartOfDay());
				
		Usuario usuario = new Usuario(1L, nome, dataNascimento, genero, altura, limiteCaloriaDiaria, peso, pressao);
		
		System.out.println(usuario);
	}

}
