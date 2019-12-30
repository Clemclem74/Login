package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import buisnessLogic.Event;
import buisnessLogic.User;

public class OracleEventDAO extends OracleDAO<Event> {
public OracleEventDAO(Connection conn) {
  super(conn);
}

public int create(Event obj) {
	System.out.println("Before");

	int id = getLastId()+1;
	
	  String SQL_INSERT = "Insert into Event " + "Values (" + id +",'" + obj.getTitle() + "',"
			  +"'" + obj.getDescription() + "',"
					  +"'" + obj.getEvent_date() + "',"
	  						+"'" + obj.getImage() + "')";
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

public boolean delete(Event event) {
	int id = event.getId_event();
	String SQL_DELETE = "DELETE from EVENT WHERE EVENT='"+id+"'";
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
    
 
public boolean update(int id_event, Event obj) {
	
	int id = id_event;
	  
	  try {
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  
		  
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE EVENT SET TITLE = ?, DESCRIPTION= ?, DATE=?, IMAGE=? WHERE EVENT = ?");

			    // set the preparedstatement parameters
			    ps.setString(1,obj.getTitle());
			    ps.setString(2,obj.getDescription());
			    ps.setString(3,obj.getEvent_date());
			    ps.setString(4,obj.getImage());
			    ps.setInt(5,id);

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
		  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

      ResultSet resultSet = preparedStatement.executeQuery();
      
      while (resultSet.next()) {
    	  
    	  Event obj = new Event();
          int id_event = resultSet.getInt("EVENT");
          String title = resultSet.getString("TITLE");
          String description = resultSet.getString("DESCRIPTION");
          String event_date = resultSet.getString("DATE");
          String image = resultSet.getString("IMAGE");

          obj.setId_event(id_event);
          obj.setTitle(title);
          obj.setDescription(description);
          obj.setEvent_date(event_date);
          obj.setImage(image);

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
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {

	    	  int id_event = resultSet.getInt("EVENT");
	          String title = resultSet.getString("TITLE");
	          String description = resultSet.getString("DESCRIPTION");
	          String event_date = resultSet.getString("DATE");
	          String image = resultSet.getString("IMAGE");

	          obj.setId_event(id_event);
	          obj.setTitle(title);
	          obj.setDescription(description);
	          obj.setEvent_date(event_date);
	          obj.setImage(image);
	          

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
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {

	    	  int id_event = resultSet.getInt("EVENT");
	          String title = resultSet.getString("TITLE");
	          String description = resultSet.getString("DESCRIPTION");
	          String event_date = resultSet.getString("DATE");
	          String image = resultSet.getString("IMAGE");

	          obj.setId_event(id_event);
	          obj.setTitle(title);
	          obj.setDescription(description);
	          obj.setEvent_date(event_date);
	          obj.setImage(image);
	          

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
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
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



public int join(Event obj,User user) {
	System.out.println("Before");

	if(this.alreadyEventbyUser(obj.getId_event(),user.getId_user())==-1) {
		
		String SQL_INSERT = "Insert into USEREVENT " + "Values (" + obj.getId_event() +",'" + user.getId_user() + "')";
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
	else {
		return -2;
	}
	
	
}



public boolean leave(int id,Event obj) {
	

	String SQL_DELETE = "DELETE from USEREVENT WHERE ID_USER='"+id+"'" + " AND " + "ID_EVENT=" + "'" + obj.getId_event() + "'";
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



public ArrayList<Integer> getEventByUser(User user) {
	ArrayList<Integer> id_list = new ArrayList<Integer>();
    
	  String SQL_SELECT = "Select * from USEREVENT where id_user="+user.getId_user();

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {
	    	  
	    	  id_list.add(resultSet.getInt("ID_EVENT"));
	    	  
	      }
	      conn.close();
	      return id_list;
	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return id_list;
}



private int alreadyEventbyUser(int id_event,int id_user) {
	
	String SQL_SELECT = "Select * from USEREVENT where id_user = "+id_user+" AND id_event = "+id_event;

	int id = -1;
	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id = resultSet.getInt("id_event"); 
	      }
	      return id;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return id;
	
}



@Override
public ArrayList<Integer> findTeams(int idBDE) {
	// TODO Auto-generated method stub
	return null;
}
}