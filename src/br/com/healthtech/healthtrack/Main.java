package br.com.healthtech.healthtrack;

import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.teste.TesteAlimentacao;
import br.com.healthtech.healthtrack.teste.TesteAtividadeFisica;
import br.com.healthtech.healthtrack.teste.TestePeso;
import br.com.healthtech.healthtrack.teste.TestePressaoArterial;

public class Main {

	public static void main(String[] args) {
		try {
			TesteAlimentacao.testa();
			TesteAtividadeFisica.testa();
			TestePeso.testa();
			TestePressaoArterial.testa();
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

}
