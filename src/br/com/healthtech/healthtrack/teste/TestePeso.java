package br.com.healthtech.healthtrack.teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.dao.PesoDAO;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Peso;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class TestePeso {
	
	public static void main(String[] args) {
		try {
			testa();
		} catch (DBException e) {
			e.printStackTrace();
		}
	}
	
	public static void testa() throws DBException {
		PesoDAO dao = DAOFactory.getPesoDAO();
		
		// limpa a base
		dao.excluiTodos();
		
		// insere novos registros
		adicionaRegistrosDePeso(dao);
		
		// exibe os registros
		System.out.println("\nLista de pesos registrados:");
		dao.buscaPor(new Usuario(1L)).forEach(peso -> System.out.println(peso));
		
		// fecha conexão com o banco
		dao.fechaConexao();
	}

	private static void adicionaRegistrosDePeso(PesoDAO dao) throws DBException {
		dao.insere(new Peso(new BigDecimal(83), novaData("2018-09-10T07:00"), 1L));
		dao.insere(new Peso(new BigDecimal(81), novaData("2018-09-19T07:10"), 1L));
		dao.insere(new Peso(new BigDecimal(79.3), novaData("2018-10-01T08:00"), 1L));
		dao.insere(new Peso(new BigDecimal(79), novaData("2018-10-02T08:00"), 1L));
		dao.insere(new Peso(new BigDecimal(78.5), novaData("2018-10-03T08:00"), 1L));
	}
	
	private static LocalDateTime novaData(String data) {
		return DateUtil.toDateTime(data);
	}

}
