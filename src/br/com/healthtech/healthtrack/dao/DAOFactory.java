package br.com.healthtech.healthtrack.dao;

public class DAOFactory {

  public static PesoDAO getPesoDAO() {
    return new PesoDAO();
  }
  
  public static PressaoArterialDAO getPressaoArterialDAO() {
	  return new PressaoArterialDAO();
  }
  
}