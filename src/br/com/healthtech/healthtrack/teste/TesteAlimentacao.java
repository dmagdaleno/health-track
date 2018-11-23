package br.com.healthtech.healthtrack.teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.dao.AlimentacaoDAO;
import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Alimentacao;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class TesteAlimentacao {
	
	public static void main(String[] args) {
		try {
			testa();
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	public static void testa() throws DBException {
		AlimentacaoDAO dao = DAOFactory.getAlimentacaoDAO();
		
		// limpa a base
		dao.excluiTodos();
		
		// insere novos registros
		adicionaRegistrosDeAlimentacao(dao);
		
		// exibe os registros
		System.out.println("Lista de alimentações registradas:");
		dao.buscaPor(new Usuario(1L)).forEach(alimentacao -> System.out.println(alimentacao));
	}

	private static void adicionaRegistrosDeAlimentacao(AlimentacaoDAO dao) throws DBException {
		
		dao.insere(new Alimentacao(1, "Cereal, iogurte e mel", 
				new BigDecimal(350), novaData("2018-09-10T07:00"), 1L));
		
		dao.insere(new Alimentacao(5, "Uma maçã grande", 
				new BigDecimal(100), novaData("2018-09-10T09:30"), 1L));
		
		dao.insere(new Alimentacao(3, "Arroz, feijão, carne e salada", 
				new BigDecimal(650), novaData("2018-09-10T12:00"), 1L));
		
		dao.insere(new Alimentacao(4, "Lanche", 
				new BigDecimal(220), novaData("2018-09-10T16:00"), 1L));
		
		dao.insere(new Alimentacao(3, "Filé de frango e salada", 
				new BigDecimal(580), novaData("2018-09-10T19:00"), 1L));
		
		dao.insere(new Alimentacao(1, "Cereal, iogurte e mel", 
				new BigDecimal(350), novaData("2018-09-11T07:00"), 1L));
		
		dao.insere(new Alimentacao(2, "Uma maçã grande", 
				new BigDecimal(100), novaData("2018-09-11T09:30"), 1L));
		
		dao.insere(new Alimentacao(3, "Arroz, feijão, carne e salada", 
				new BigDecimal(650), novaData("2018-09-11T12:00"), 1L));
		
		dao.insere(new Alimentacao(4, "Lanche", 
				new BigDecimal(220), novaData("2018-09-11T16:00"), 1L));
		
		dao.insere(new Alimentacao(3, "Filé de frango e salada", 
				new BigDecimal(580), novaData("2018-09-11T19:00"), 1L));
	}
	
	private static LocalDateTime novaData(String data) {
		return DateUtil.toDateTime(data);
	}

}
