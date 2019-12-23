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

	int id = getLastId()+1;
	
	  String SQL_INSERT = "Insert into Event " + "Values (" + id +",'" + obj.getTitle() + "',"
			  +"'" + obj.getDescription() + "',"
					  +"'" + obj.getEvent_date() + "')";
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

public boolean delete(Event event) {
	int id = event.getId_event();
	String SQL_DELETE = "DELETE from EVENT WHERE EVENT='"+id+"'";
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
    
 
public boolean update(int id_event, Event obj) {
	
	int id = id_event;
	  
	  try {
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, "system", "oose");
		  
		  
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE Users SET TITLE = ?, DESCRIPTION= ?, DATE=? WHERE EVENT = ?");

			    // set the preparedstatement parameters
			    ps.setString(1,obj.getTitle());
			    ps.setString(2,obj.getDescription());
			    ps.setString(3,obj.getEvent_date());
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


public Event findById(int id) {
	  Event obj = new Event();      
	    
	  String SQL_SELECT = "Select * from Event where EVENT='"+id+"'";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {

	          int id_event = resultSet.getInt("EVENT");
	          String title = resultSet.getString("TITLE");
	          String description = resultSet.getString("DESCRIPTION");
	          String event_date = resultSet.getString("DATE");

	          obj.setId_event(id_event);
	          obj.setTitle(title);
	          obj.setDescription(description);
	          obj.setEvent_date(event_date);
	          

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

@Override
public Event find(String id) {
	Event obj = new Event();      
    
	  String SQL_SELECT = "Select * from Event where TITLE='"+id+"'";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {

	          int id_event = resultSet.getInt("EVENT");
	          String title = resultSet.getString("TITLE");
	          String description = resultSet.getString("DESCRIPTION");
	          String event_date = resultSet.getString("DATE");

	          obj.setId_event(id_event);
	          obj.setTitle(title);
	          obj.setDescription(description);
	          obj.setEvent_date(event_date);
	          

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

private int getLastId() {
	
	int id_event=0;
	String SQL_SELECT = "Select MAX(EVENT)from Event";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id_event = resultSet.getInt("MAX(EVENT)"); 
	      }
	      return id_event;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return id_event;
	
}


@Override
public ArrayList<Integer> findTeams(int idBDE) {
	// TODO Auto-generated method stub
	return null;
}
}