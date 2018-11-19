package br.com.healthtech.healthtrack.teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.dao.AtividadeFisicaDAO;
import br.com.healthtech.healthtrack.dao.DAOFactory;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.AtividadeFisica;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class TesteAtividadeFisica {
	
	public static void main(String[] args) {
		try {
			testa();
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	public static void testa() throws DBException {
		AtividadeFisicaDAO dao = DAOFactory.getAtividadeFisicaDAO();
		
		// limpa a base
		dao.excluiTodos();
		
		// insere novos registros
		adicionaRegistrosDeAtividadeFisica(dao);
		
		// exibe os registros
		System.out.println("\nLista de atividades registradas:");
		dao.buscaPor(new Usuario(1L)).forEach(atividade -> System.out.println(atividade));
		
		// fecha conexão com o banco
		dao.fechaConexao();
	}

	private static void adicionaRegistrosDeAtividadeFisica(AtividadeFisicaDAO dao) throws DBException {
		dao.insere(new AtividadeFisica(1, "Caminhada", new BigDecimal(150), 
				novaData("2018-09-10T07:00"), 1L));
		dao.insere(new AtividadeFisica(2, "Corrida", new BigDecimal(800), 
				novaData("2018-09-11T09:30"), 1L));
		dao.insere(new AtividadeFisica(3, "Pedalada", new BigDecimal(1200), 
				novaData("2018-09-12T12:00"), 1L));
		dao.insere(new AtividadeFisica(4, "Musculação", new BigDecimal(100), 
				novaData("2018-09-13T16:00"), 1L));
		dao.insere(new AtividadeFisica(4, "Musculação", new BigDecimal(100), 
				novaData("2018-09-14T16:00"), 1L));
	}
	
	private static LocalDateTime novaData(String data) {
		return DateUtil.toDateTime(data);
	}

}
