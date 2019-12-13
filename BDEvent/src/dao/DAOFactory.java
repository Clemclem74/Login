package dao;
import java.sql.Connection;
import java.sql.DriverManager;

import buisnessLogic.User;

public class DAOFactory extends AbstractDAOFactory{
	
	  protected static final Connection conn = null;

	  public DAO<User> getUserDAO(){
	    return new UserDAO(conn);
	  }

	}