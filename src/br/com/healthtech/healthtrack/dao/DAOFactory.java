package br.com.healthtech.healthtrack.dao;

import br.com.healthtech.healthtrack.dao.impl.PesoDAOOracle;

public class DAOFactory {

  public static PesoDAO getPesoDAO() {
    return new PesoDAOOracle();
  }
  
  public static PressaoArterialDAO getPressaoArterialDAO() {
	  return new PressaoArterialDAO();
  }
  
}