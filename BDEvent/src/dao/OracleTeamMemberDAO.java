package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import buisnessLogic.BDE;
import buisnessLogic.Team;
import buisnessLogic.TeamFacade;
import buisnessLogic.TeamMember;
import buisnessLogic.User;
import buisnessLogic.UserFacade;

public class OracleTeamMemberDAO extends OracleDAO<TeamMember> {
public OracleTeamMemberDAO(Connection conn) {
  super(conn);
}

public int create(TeamMember obj) {

	String SQL_INSERT;
	if (obj.isChief()) {
		SQL_INSERT = "Insert into team_member " + "Values (" + obj.getTeam().getIdTeam() + ","
				  +  obj.getUser().getId_user() + ","
						  +"'" + "1" + "'"+")";
		
	}
	else {
		SQL_INSERT = "Insert into team_member " + "Values (" + obj.getTeam().getIdTeam() + ","
				  +  obj.getUser().getId_user() + ","
						  +"'" + "0" + "'"+")";
	}
	 
	  System.out.println(SQL_INSERT);

	  // auto close connection and preparedStatement
	  try {

		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  Statement st = conn.createStatement();
		
	      st.executeUpdate(SQL_INSERT);

		  conn.close();
		  return 1;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return -1;

}

public boolean delete(TeamMember teamMember) {
	int idUser = teamMember.getUser().getId_user();
	int idTeam = teamMember.getTeam().getIdTeam();
	String SQL_DELETE = "DELETE from team_member WHERE ID_USER='"+idUser+"' AND ID_TEAM='"+idTeam+"'";
	 try {
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  
		  
		  PreparedStatement ps = conn.prepareStatement(SQL_DELETE);
		  // call executeUpdate to execute our sql update statement
		  ps.executeUpdate(); 
		  ps.close();
		  
		  return true;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	 return false;
}

public boolean isChief(int id) {

	int count = 0;
	
	String SQL_SELECT = "SELECT COUNT(*) AS MYCOUNT FROM team_member WHERE ID_USER="+id+" AND IS_CHIEF=1";
	System.out.println(SQL_SELECT);
	try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          count = resultSet.getInt("MYCOUNT"); 
	      }
	      return count>0;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return count>0;
}



public boolean update(int idBde, BDE obj) {
	return false;
}




public TeamMember findById(int id) {
	return null;
}

public ArrayList<Integer> findTeamsByUser(int idUser){

	  String SQL_SELECT = "Select * from team_member where ID_USER='"+idUser+"'";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      ArrayList<Integer> teams = new ArrayList<Integer>();
	      while (resultSet.next()) {
	    	  teams.add(resultSet.getInt("ID_TEAM"));
	      }
	      
		  conn.close();
	      return teams;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return new ArrayList<Integer>();
}


public ArrayList<Integer> findMembersByTeam(int idTeam){

	  String SQL_SELECT = "Select * from team_member where ID_TEAM="+idTeam+"";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      ArrayList<Integer> users = new ArrayList<Integer>();
	      while (resultSet.next()) {
	    	  users.add( resultSet.getInt("ID_USER"));
	      }
	      
		  conn.close();
	      return users;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return new ArrayList<Integer>();
}



public int getNumber() {
	 String SQL_SELECT = "Select * from team_member";
	  // auto close connection and preparedStatement
	
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
		  
	      ResultSet resultSet = preparedStatement.executeQuery();
	      int nb=0;
	      while (resultSet.next()) {
	    	  nb++;
	      }
		  conn.close();

	      return nb;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	
	return 0;
	
}


public TeamMember findByUserTeam(User user, Team team) {
	TeamMember obj = new TeamMember();      
    
	  String SQL_SELECT = "Select * from team_member where ID_TEAM='"+team.getIdTeam()+"' AND ID_USER='" + user.getId_user() + "' ";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {
	    	  
	          int id_team = resultSet.getInt("ID_TEAM");
	          int id_user = resultSet.getInt("ID_USER");
	          boolean ischief;
	          if (resultSet.getInt("IS_CHIEF") == 1) {
	        	  ischief=true;
	          }
	          else {
	        	  ischief = false;
	          }
	          
	          UserFacade userFacade = new UserFacade();
	          TeamFacade teamFacade = new TeamFacade();
	          obj.setChief(ischief);
	          obj.setUser(userFacade.findById(id_user));
	          obj.setTeam(teamFacade.findById(id_team));
	          

	      }
		  conn.close();
	      return obj;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return obj;
}


@Override
public boolean update(TeamMember obj) {
	return false;
}

@Override
public ArrayList<TeamMember> findAll() {
	return null;
}




}
