package br.com.healthtech.healthtrack;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.healthtech.healthtrack.modelo.IMC;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Peso;
import br.com.healthtech.healthtrack.modelo.registro.PressaoArterial;

public class Main {

	public static void main(String[] args) {
		String nome = "João da Silva";
		LocalDate dataNascimento = LocalDate.parse("26/09/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String genero = "Masculino";
		BigDecimal altura = new BigDecimal(1.70);
		BigDecimal limiteCaloriaDiaria = new BigDecimal(2000);
		
		LocalDateTime agora = LocalDateTime.now();
		Peso peso = new Peso(new BigDecimal(70), agora);
		
		BigDecimal pressaoMaxima = new BigDecimal(110);
		BigDecimal pressaoMinima = new BigDecimal(80);
		PressaoArterial pressao = new PressaoArterial(pressaoMaxima, pressaoMinima, agora);
				
		Usuario usuario = new Usuario(1L, nome, dataNascimento, genero, altura, limiteCaloriaDiaria, peso, pressao);
		
		System.out.println("Usuario: " + usuario);
		
		IMC imc = new IMC(usuario.getPesoAtual().getPeso(), usuario.getAltura());
		BigDecimal calculoIMC = imc.calcula();
		
		System.out.println("IMC: " + calculoIMC);
	}

}
