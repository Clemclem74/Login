package dao;
import buisnessLogic.User;
import buisnessLogic.BDE;
import buisnessLogic.Event;

public abstract class AbstractDAOFactory {
	  public static final int ORACLE_DAO_FACTORY = 0;
	  public static final int XML_DAO_FACTORY = 1;

	  //Retourne un objet Classe interagissant avec la BDD
	  public abstract OracleDAO<User> getUserDAO();
	  public abstract OracleDAO<BDE> getBDEDAO();
	  public abstract OracleDAO<Event> getEventDAO();
	   
	  
	  //Méthode permettant de récupérer les Factory
	  public static AbstractDAOFactory getFactory(int type){
	    switch(type){
	      case ORACLE_DAO_FACTORY:
	        return new OracleDAOFactory();
	      case XML_DAO_FACTORY: 
	        return new XMLDAOFactory();
	      default:
	        return null;
	    }
	  }
	}
