package dao;
import java.sql.Connection;
import java.sql.DriverManager;

import buisnessLogic.User;

public class OracleDAOFactory extends AbstractDAOFactory{
	
	  protected static final Connection conn = null;

	  public OracleDAO<User> getUserDAO(){
	    return new OracleUserDAO(conn);
	  }

	}