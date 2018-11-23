package br.com.healthtech.healthtrack.teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.PressaoArterialDAO;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.PressaoArterial;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class TestePressaoArterial {
	
	public static void main(String[] args) {
		try {
			testa();
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	public static void testa() throws DBException {
		PressaoArterialDAO dao = DAOFactory.getPressaoArterialDAO();
		
		// limpa a base
		dao.excluiTodos();
		
		// insere novos registros
		adicionaRegistrosDePressaoArterial(dao);
		
		// exibe os registros
		System.out.println("\nLista de pressões registradas:");
		dao.buscaPor(new Usuario(1L), 3).forEach(pressao -> System.out.println(pressao));
	}

	private static void adicionaRegistrosDePressaoArterial(PressaoArterialDAO dao) throws DBException {
		dao.insere(new PressaoArterial(new BigDecimal(140), new BigDecimal(100), novaData("2018-09-10T07:00"), 1L));
		dao.insere(new PressaoArterial(new BigDecimal(130), new BigDecimal(90), novaData("2018-09-19T07:10"), 1L));
		dao.insere(new PressaoArterial(new BigDecimal(120), new BigDecimal(80), novaData("2018-10-01T08:00"), 1L));
		dao.insere(new PressaoArterial(new BigDecimal(120), new BigDecimal(80), novaData("2018-10-02T08:00"), 1L));
		dao.insere(new PressaoArterial(new BigDecimal(120), new BigDecimal(80), novaData("2018-10-03T08:00"), 1L));
	}

	private static LocalDateTime novaData(String data) {
		return DateUtil.toDateTime(data);
	}

}
