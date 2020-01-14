package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Contact;
import buisnessLogic.Team;
import buisnessLogic.User;
import buisnessLogic.UserFacade;

public class OracleTeamDAO extends OracleDAO<Team> {
public OracleTeamDAO(Connection conn) {
  super(conn);
}

public int create(Team obj) {
	int id = getLastId()+1;

	  String SQL_INSERT = "Insert into team " + "Values (" + id +",'" + obj.getNameTeam() + "',"
			  +"'" + obj.getBde().getIdBDE() + "'"
					  +")";
	  System.out.println(SQL_INSERT);

	  // auto close connection and preparedStatement
	  try {

		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  Statement st = conn.createStatement();
		
	      st.executeUpdate(SQL_INSERT);

		  conn.close();
		  return id;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return -1;

}

public boolean delete(Team team) {
	int id = team.getIdTeam();
	String SQL_DELETE = "DELETE from team WHERE ID_TEAM ='"+id+"'";
	 try {
		  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  
		  
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



public boolean update(int idtTeam, Team obj) {
	return true;
}

private int getLastId() {

	int id_team=0;
	String SQL_SELECT = "Select MAX(ID_TEAM)from team";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id_team = resultSet.getInt("MAX(ID_TEAM)");
	      }
	      return id_team;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return id_team;

}


public Team findById(int id) {
  Team obj = new Team();

  String SQL_SELECT = "Select * from team where ID_TEAM='"+id+"'";

  // auto close connection and preparedStatement
  try (Connection conn = DriverManager.getConnection(
          ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

      ResultSet resultSet = preparedStatement.executeQuery();
      BDEFacade bdefacade = new BDEFacade();
      
      while (resultSet.next()) {

          int idteam = resultSet.getInt("ID_TEAM");
          String nameTeam = resultSet.getString("NAME_TEAM");
          int idBDE = resultSet.getInt("ID_BDE");

          obj.setIdTeam(idteam);
          obj.setNameTeam(nameTeam);
          obj.setBde(bdefacade.findById(idBDE));

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





public Team findByName(String name) {
	  Team obj = new Team();

	  String SQL_SELECT = "Select * from team where NAME_TEAM='"+name+"'";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      BDEFacade bdefacade = new BDEFacade();
	      
	      while (resultSet.next()) {

	          int idteam = resultSet.getInt("ID_TEAM");
	          String nameTeam = resultSet.getString("NAME_TEAM");
	          int idBDE = resultSet.getInt("ID_BDE");

	          obj.setIdTeam(idteam);
	          obj.setNameTeam(nameTeam);
	          obj.setBde(bdefacade.findById(idBDE));

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




public int getNumber() {
	 String SQL_SELECT = "Select * from team";
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






@Override
public boolean update(Team obj) {
	int id = obj.getIdTeam();
	  try {
		
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE team SET NAME_TEAM = ?, ID_BDE = ? WHERE ID_TEAM = ? ");
		  
			    // set the prepared statement parameters
			    ps.setString(1,obj.getNameTeam());
			    ps.setInt(2, obj.getBde().getIdBDE());
			    ps.setInt(3,id);
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



@Override
public Team find(String id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ArrayList<Integer> findTeams(int idBDE) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ArrayList<Team> findAll() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int join(Team obj, User user) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public ArrayList<Integer> getEventByUser(User user) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean leave(int id, Team obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean isChief(int id_user) {
	// TODO Auto-generated method stub
	return false;
}
}
