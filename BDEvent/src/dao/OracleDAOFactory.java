package dao;
import java.sql.Connection;
import java.sql.DriverManager;

import buisnessLogic.BDE;
import buisnessLogic.BlackBoard;
import buisnessLogic.Post;
import buisnessLogic.Team;
import buisnessLogic.User;

public class OracleDAOFactory extends AbstractDAOFactory{
	
	  protected static final Connection conn = null;

	  public OracleDAO<User> getUserDAO(){
	    return new OracleUserDAO(conn);
	  }
	  
	  public OracleDAO<BDE> getBDEDAO(){
		    return new OracleBDEDAO(conn);
	  }
	  
	  public OracleDAO<Team> getTeamDAO(){
		  return new OracleTeamDAO(conn);
	  }
	  
	  public OracleDAO<BlackBoard> getBlackBoardDAO(){
		  return new OracleBlackBoardDAO(conn);
	  }

	@Override
	public OracleDAO<Post> getPostDAO() {
		return new OraclePostDAO(conn);
	}

	}