package dao;
import java.sql.Connection;
import java.sql.DriverManager;

import buisnessLogic.BDE;
import buisnessLogic.User;
import buisnessLogic.Event;

public class OracleDAOFactory extends AbstractDAOFactory{
	
	  protected static final Connection conn = null;

	  public OracleDAO<User> getUserDAO(){
	    return new OracleUserDAO(conn);
	  }
	  
	  public OracleDAO<BDE> getBDEDAO(){
		    return new OracleBDEDAO(conn);
		  }
	  
	  public OracleDAO<Event> getEventDAO(){
		    return new OracleEventDAO(conn);
		  }

	}