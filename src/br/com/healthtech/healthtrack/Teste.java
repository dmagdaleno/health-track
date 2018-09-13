package br.com.healthtech.healthtrack;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.healthtech.healthtrack.modelo.IMC;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Peso;

public class Main {

	public static void main(String[] args) {
		String nome = "João da Silva";
		LocalDate dataNascimento = LocalDate.parse("26/09/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String genero = "Masculino";
		BigDecimal altura = new BigDecimal(1.70);
		BigDecimal limiteCaloriaDiaria = new BigDecimal(2000);
		
		Usuario usuario = new Usuario(1L, nome, dataNascimento, genero, altura, limiteCaloriaDiaria);
		
		LocalDateTime agora = LocalDateTime.now();
		Peso peso = new Peso(usuario, new BigDecimal(70), agora);
		
		System.out.println("Nome: " + usuario.getNome());
		System.out.println("Peso: " +peso.getPeso());
		System.out.println("Altura: " + usuario.getAltura());
		
		IMC imc = new IMC(peso.getPeso(), usuario.getAltura());
		BigDecimal calculoIMC = imc.calcula();
		
		System.out.println("IMC: " + calculoIMC);
	}

}
