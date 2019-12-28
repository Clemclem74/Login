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
		SQL_INSERT = "Insert into TEAM_MEMBER " + "Values (" + obj.getUser().getId_user() + ","
				  + obj.getTeam().getIdTeam() + ","
						  +"'" + "1" + "'"+")";
	}
	else {
		SQL_INSERT = "Insert into TEAM_MEMBER " + "Values (" + obj.getUser().getId_user() + ","
				  + obj.getTeam().getIdTeam() + ","
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
	int idUser = teamMember.getId_user();
	int idTeam = teamMember.getTeam().getIdTeam();
	String SQL_DELETE = "DELETE from TEAM_MEMBER WHERE ID_USER='"+idUser+"' AND ID_TEAM='"+idTeam+"'";
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

public boolean update(int idBde, BDE obj) {
	return false;
}




public TeamMember findById(int id) {
	return null;
}

public ArrayList<Integer> findTeamsByUser(int idUser){

	  String SQL_SELECT = "Select * from Team where ID_USER='"+idUser+"'";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      ArrayList<Integer> teams = new ArrayList<Integer>();
	      while (resultSet.next()) {
	    	  teams.add( resultSet.getInt("ID_TEAM"));
	      }
	      System.out.println(teams.toString());
	      
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

	  String SQL_SELECT = "Select * from Team where ID_TEAM='"+idTeam+"'";

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


@Override
public boolean update(int i, TeamMember obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean update(TeamMember obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public TeamMember find(String id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ArrayList<Integer> findTeams(int idBDE) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ArrayList<TeamMember> findAll() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int join(TeamMember obj, User user) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public ArrayList<Integer> getEventByUser(User user) {
	// TODO Auto-generated method stub
	return null;
}






}
