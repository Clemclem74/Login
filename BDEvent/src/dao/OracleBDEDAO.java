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
import buisnessLogic.User;
import buisnessLogic.UserFacade;

public class OracleBDEDAO extends OracleDAO<BDE> {
public OracleBDEDAO(Connection conn) {
  super(conn);
}

public int create(BDE obj) {
	int id = getLastId()+1;

	  String SQL_INSERT = "Insert into BDE " + "Values (" + id +",'" + obj.getCreator().getId_user() + "',"
			  +"'" + obj.getNameBDE() + "',"
					  +"'" + obj.getSchoolBDE() + "'"+")";
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

public boolean delete(BDE bde) {
	int id = bde.getIdBDE();
	String SQL_DELETE = "DELETE from BDE WHERE ID_BDE='"+id+"'";
	 try {
		  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
		  
		  
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

	int id = idBde;

	  try {
		  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");


		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE BDE SET IDCREATOR = ?, NAMEBDE = ?, SCHOOLBDE= ? WHERE ID_BDE = ? ");

			    // set the preparedstatement parameters
			    ps.setInt(1,obj.getCreator().getId_user());
			    ps.setString(2,obj.getNameBDE());
			    ps.setString(3,obj.getSchoolBDE());
			    ps.setInt(4,id);

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

private int getLastId() {

	int id_bde=0;
	String SQL_SELECT = "Select MAX(ID_BDE)from BDE";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          "jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id_bde = resultSet.getInt("MAX(ID_BDE)");
	      }
	      return id_bde;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return id_bde;

}


public BDE findById(int id) {
  BDE obj = new BDE();

  String SQL_SELECT = "Select * from BDE where ID_BDE='"+id+"'";

  // auto close connection and preparedStatement
  try (Connection conn = DriverManager.getConnection(
          "jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

      ResultSet resultSet = preparedStatement.executeQuery();
      UserFacade userfacade = new UserFacade();
      
      while (resultSet.next()) {

          int id_BDE = resultSet.getInt("ID_BDE");
          User creator = userfacade.findById(Integer.parseInt(resultSet.getString("IDCREATOR")));
          String nameBDE = resultSet.getString("NAMEBDE");
          String schoolBDE = resultSet.getString("SCHOOLBDE");

          obj.setIdBDE(id_BDE);
          obj.setCreator(creator);
          obj.setNameBDE(nameBDE);
          obj.setSchoolBDE(schoolBDE);
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

public ArrayList<Integer> findTeams(int idBDE){

	  String SQL_SELECT = "Select * from Team where ID_BDE='"+idBDE+"'";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          "jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      ArrayList<Integer> team = new ArrayList<Integer>();
	      while (resultSet.next()) {
	    	  team.add( resultSet.getInt("ID_TEAM"));
	      }
	      System.out.println(team.toString());
	      
		  conn.close();
	      return team;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return new ArrayList<Integer>();
}





@Override
public boolean update(BDE obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public BDE find(String id) {
	// TODO Auto-generated method stub
	return null;
}


}
