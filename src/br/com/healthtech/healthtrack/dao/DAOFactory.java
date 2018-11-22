package br.com.healthtech.healthtrack.dao;

import br.com.healthtech.healthtrack.dao.impl.AlimentacaoDAOOracle;
import br.com.healthtech.healthtrack.dao.impl.AtividadeFisicaDAOOracle;
import br.com.healthtech.healthtrack.dao.impl.PesoDAOOracle;
import br.com.healthtech.healthtrack.dao.impl.PressaoArterialDAOOracle;
import br.com.healthtech.healthtrack.dao.impl.UsuarioDAOOracle;

public class DAOFactory {

  public static PesoDAO getPesoDAO() {
    return new PesoDAOOracle();
  }
  
  public static PressaoArterialDAO getPressaoArterialDAO() {
	  return new PressaoArterialDAOOracle();
  }
  
  public static AtividadeFisicaDAO getAtividadeFisicaDAO() {
	  return new AtividadeFisicaDAOOracle();
  }
  
  public static AlimentacaoDAO getAlimentacaoDAO() {
	  return new AlimentacaoDAOOracle();
  }

public static UsuarioDAO getUsuarioDAO() {
	return new UsuarioDAOOracle();
}
  
}