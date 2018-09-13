package br.com.healthtech.healthtrack.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.healthtech.healthtrack.modelo.registro.Alimentacao;

public class AlimentacaoDAO {
	
	private List<Alimentacao> alimentacoes;
	
	public AlimentacaoDAO() {
		alimentacoes = new ArrayList<>(10);
	}
	
	public void add(Alimentacao alimentacao) {
		this.alimentacoes.add(alimentacao);
	}
	
	public void add(List<Alimentacao> alimentacoes) {
		for(Alimentacao alimentacao: alimentacoes) {
			this.alimentacoes.add(alimentacao);
		}
	}
	
	public List<Alimentacao> getAll() {
		return Collections.unmodifiableList(this.alimentacoes);
	}

}
