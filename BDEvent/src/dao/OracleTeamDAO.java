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
import buisnessLogic.Team;
import buisnessLogic.User;
import buisnessLogic.UserFacade;

public class OracleTeamDAO extends OracleDAO<Team> {
public OracleTeamDAO(Connection conn) {
  super(conn);
}

public int create(Team obj) {
	int id = getLastId()+1;

	  String SQL_INSERT = "Insert into Team " + "Values (" + id +",'" + obj.getNameTeam() + "',"
			  +"'" + obj.getBde().getIdBDE() + "'"
					  +")";
	  System.out.println(SQL_INSERT);

	  // auto close connection and preparedStatement
	  try {

		  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
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

public boolean delete(Team obj) {
  return false;
}

public boolean update(int idtTeam, Team obj) {
	return true;
}

private int getLastId() {

	int id_team=0;
	String SQL_SELECT = "Select MAX(ID_TEAM)from Team";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          "jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
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

  String SQL_SELECT = "Select * from TEAM where ID_TEAM='"+id+"'";

  // auto close connection and preparedStatement
  try (Connection conn = DriverManager.getConnection(
          "jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
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

@Override
public boolean update(Team obj) {
	// TODO Auto-generated method stub
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
}
