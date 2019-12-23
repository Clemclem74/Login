package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import buisnessLogic.Event;

public class OracleEventDAO extends OracleDAO<Event> {
public OracleEventDAO(Connection conn) {
  super(conn);
}

public int create(Event obj) {
	System.out.println("Before");


	
	  String SQL_INSERT = "Insert into Event " + "Values (" + obj.getId_event() +",'" + obj.getTitle() + "',"
			  +"'" + obj.getDescription() + "',"
					  +"'" + obj.getEvent_date() + ")";
	  System.out.println(SQL_INSERT);
	  // auto close connection and preparedStatement
	  try {
		  
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, "system", "oose");
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

public boolean delete(Event user) {
	int id = user.getId_user();
	String SQL_DELETE = "DELETE from Users WHERE ID_USER='"+id+"'";
	 try {
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, "system", "oose");
		  
		  
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
    
 
public boolean update(int iduser, Event obj) {
	
	int id = iduser;
	  
	  try {
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, "system", "oose");
		  
		  
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE Users SET USERNAME = ?, EMAILUSER = ?, PASSWORDUSER= ?, FIRSTNAME=?, LASTNAME=?, PHONENUMBERUSER=?, ID_BDE=? WHERE ID_USER = ? ");

			    // set the preparedstatement parameters
			    ps.setString(1,obj.getUsername());
			    ps.setString(2,obj.getEmailuser());
			    ps.setString(3,obj.getPassworduser());
			    ps.setString(4,obj.getFirstname());
			    ps.setString(5,obj.getLastname());
			    ps.setString(6,obj.getPhonenumberuser());
			    ps.setInt(7, obj.getCurrentBDE());
			    ps.setInt(8,id);

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

 
public ArrayList<Event> findAll() {
  
	ArrayList<Event> ret = new ArrayList<Event>();
    
  String SQL_SELECT = "Select * from EVENT";

  // auto close connection and preparedStatement
  try (Connection conn = DriverManager.getConnection(
		  ORACLE_DB_PATH, "system", "oose");
       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

      ResultSet resultSet = preparedStatement.executeQuery();
      
      while (resultSet.next()) {
    	  
    	  Event obj = new Event();
          int id_event = resultSet.getInt("EVENT");
          String title = resultSet.getString("TITLE");
          String description = resultSet.getString("DESCRIPTION");
          String event_date = resultSet.getString("DATE");

          obj.setId_event(id_event);
          obj.setTitle(title);
          obj.setDescription(description);
          obj.setEvent_date(event_date);

          ret.add(obj);

      }
	  conn.close();

      return ret;

  } catch (SQLException e) {
      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
  } catch (Exception e) {
      e.printStackTrace();
  }
return ret;
}


public User findById(int id) {
	  Event obj = new Event();      
	    
	  String SQL_SELECT = "Select * from Users where ID_USER='"+id+"'";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {

	          int id_user = resultSet.getInt("ID_USER");
	          String username = resultSet.getString("USERNAME");
	          String emailuser = resultSet.getString("EMAILUSER");
	          String passworduser = resultSet.getString("PASSWORDUSER");
	          String lastname = resultSet.getString("LASTNAME");
	          String firstname = resultSet.getString("FIRSTNAME");
	          String phonenumberuser = resultSet.getString("PHONENUMBERUSER");
	          int idbde = resultSet.getInt("ID_BDE");

	          obj.setId_user(id_user);
	          obj.setUsername(username);
	          obj.setFirstname(firstname);
	          obj.setLastname(lastname);
	          obj.setEmailuser(emailuser);
	          obj.setPassworduser(passworduser);
	          obj.setPhonenumberuser(phonenumberuser);
	          obj.setCurrentBDE(idbde);
	          

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
public boolean update(Event obj) {
	// TODO Auto-generated method stub
	return false;
}
}