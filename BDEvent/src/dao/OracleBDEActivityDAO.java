package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import buisnessLogic.BDEActivity;
import buisnessLogic.Event;
import buisnessLogic.User;

public class OracleBDEActivityDAO extends OracleDAO<BDEActivity> {

	
	public OracleBDEActivityDAO(Connection conn) {
  super(conn);
}

public int create(BDEActivity obj) {

	int id = getLastId()+1;
	
	  String SQL_INSERT = "Insert into BDEACTIVITY " + "Values (" + id +",'" + obj.getName_activity() + "',"
			  +"'" + obj.getDescription() + "',"
					  +"'" + obj.getDate() + "',"
							  +"'" + obj.getStart_hour() + "',"
	  								+"'" + obj.getDuration() + "',"
	  									+"'" + obj.getNb_users() + "')";
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
		  

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	 
	 SQL_DELETE = "DELETE from USEREVENT WHERE ID_EVENT='"+id+"'";
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

 
public ArrayList<BDEActivity> findAll() {
  
	ArrayList<BDEActivity> ret = new ArrayList<BDEActivity>();
    
  String SQL_SELECT = "Select * from BDEACTIVITY";

  // auto close connection and preparedStatement
  try (Connection conn = DriverManager.getConnection(
		  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

      ResultSet resultSet = preparedStatement.executeQuery();
      
      while (resultSet.next()) {
    	  
    	  BDEActivity obj = new BDEActivity();
          int id_activity = resultSet.getInt("ID_ACTIVITY");
          String title = resultSet.getString("TITLE");
          String description = resultSet.getString("DESCRIPTION");
          String event_date = resultSet.getString("DATE");
          String start_hour = resultSet.getString("START_HOUR");
          String duration = resultSet.getString("DURATION");
          int nb_users = resultSet.getInt("NB_USERS");
          
          
          obj.setId_activity(id_activity);
          obj.setName_activity(title);
          obj.setDescription(description);
          obj.setDate(event_date);
          obj.setStart_hour(start_hour);
          obj.setDuration(duration);
          obj.setNb_users(nb_users);
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


public BDEActivity findById(int id) {
	BDEActivity obj = new BDEActivity();     
	    
	  String SQL_SELECT = "Select * from BDEActivity where ID_ACTIVITY='"+id+"'";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {

	    	  
	          int id_activity = resultSet.getInt("ID_ACTIVITY");
	          String title = resultSet.getString("TITLE");
	          String description = resultSet.getString("DESCRIPTION");
	          String event_date = resultSet.getString("DATE");
	          String start_hour = resultSet.getString("START_HOUR");
	          String duration = resultSet.getString("DURATION");
	          int nb_users = resultSet.getInt("NB_USERS");
	          
	          
	          obj.setId_activity(id_activity);
	          obj.setName_activity(title);
	          obj.setDescription(description);
	          obj.setDate(event_date);
	          obj.setStart_hour(start_hour);
	          obj.setDuration(duration);
	          obj.setNb_users(nb_users);

	          

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
	          int responsible = resultSet.getInt("RESPONSIBLE");

	          obj.setId_event(id_event);
	          obj.setTitle(title);
	          obj.setDescription(description);
	          obj.setEvent_date(event_date);
	          obj.setImage(image);
	          obj.setResponsible(responsible);
	          

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
	String SQL_SELECT = "Select MAX(ID_ACTIVITY) from BDEACTIVITY";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id_event = resultSet.getInt("MAX(ID_ACTIVITY)"); 
	      }
	      return id_event;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return id_event;
	
}


public int count_users_BDEacti(int id) {
	
	int id_event=0;
	String SQL_SELECT = "Select COUNT(ID_USER) from USERBDEACTIVITY WHERE ID_ACTIVITY="+id;

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id_event = resultSet.getInt("COUNT(ID_USER)"); 
	      }
	      return id_event;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return id_event;
	
}



public int join(BDEActivity obj,User user) {
	System.out.println("Before join" + obj.getId_activity() );

	if(this.alreadyActibyUser(obj.getId_activity(),user.getId_user())==-1) {
		
		String SQL_INSERT = "Insert into USERBDEACTIVITY " + "Values (" + obj.getId_activity() +",'" + user.getId_user() + "')";
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



private int alreadyActibyUser(int id_acti,int id_user) {
	
	String SQL_SELECT = "Select * from USERBDEACTIVITY where id_activity = "+id_acti+" AND id_user = "+id_user ;

	System.out.println(SQL_SELECT);
	
	int id = -1;
	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id = resultSet.getInt("id_activity"); 
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

@Override
public boolean isChief(int id_user) {
	// TODO Auto-generated method stub
	return false;
}




@Override
public boolean delete(BDEActivity obj) {
	
	int id = obj.getId_activity();
	String SQL_DELETE = "DELETE from BDEACTIVITY WHERE ID_ACTIVITY='"+id+"'";
	 try {
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  
		  
		  PreparedStatement ps = conn.prepareStatement(SQL_DELETE);
		  // call executeUpdate to execute our sql update statement
		  ps.executeUpdate(); 
		  ps.close();
		  

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	 
	return false;
	
}

@Override
public boolean update(int i, BDEActivity obj) {
	int id = i;
	  
	  try {
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  
		  
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE BDEACTIVITY SET TITLE = ?, DESCRIPTION= ?, DATE=?, START_HOUR=?, DURATION=?, NB_USERS=? WHERE ID_ACTIVITY = ?");

			    // set the preparedstatement parameters
			    ps.setString(1,obj.getName_activity());
			    ps.setString(2,obj.getDescription());
			    ps.setString(3,obj.getDate());
			    ps.setString(4,obj.getStart_hour());
			    ps.setString(5,obj.getDuration());
			    ps.setInt(6,obj.getNb_users());
			    ps.setInt(7,id);

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
public boolean update(BDEActivity obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean leave(int id, BDEActivity obj) {
	// TODO Auto-generated method stub
	return false;
}
}