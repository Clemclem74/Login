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
import buisnessLogic.StaffActivity;
import buisnessLogic.User;

public class OracleStaffActivityDAO extends OracleDAO<StaffActivity> {

	
public OracleStaffActivityDAO(Connection conn) {
  super(conn);
}

public int create(BDEActivity obj) {

	int id = getLastId()+1;
	
	  String SQL_INSERT = "Insert into bdeactivity " + "Values (" + id +",'" + obj.getName_activity() + "',"
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


public int create(StaffActivity obj,Event event) {

	int id = getLastId()+1;
	
	  String SQL_INSERT = "Insert into staffactivity " + "Values (" + id +",'" + obj.getName_activity() + "',"
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

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  
	  SQL_INSERT = "Insert into APPLIANCE " + "Values (" + id +"," + event.getId_event() +"," +"0"+ ")";
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



public boolean delete(StaffActivity acti) {
	int id = acti.getId_activity();
	String SQL_DELETE = "DELETE from staffactivity WHERE ID_ACTIVITY='"+id+"'";
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
	 
	 SQL_DELETE = "DELETE from appliance WHERE ID_STAFF_ACTIVITY='"+id+"'";
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
    
 
 
public ArrayList<Integer> findAllStaff(int id) {
  
	ArrayList<Integer> ret = new ArrayList<Integer>();
    
  String SQL_SELECT = "Select DISTINCT ID_STAFF_ACTIVITY from appliance WHERE ID_EVENT="+id;

  // auto close connection and preparedStatement
  try (Connection conn = DriverManager.getConnection(
		  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

      ResultSet resultSet = preparedStatement.executeQuery();
      
      while (resultSet.next()) {
    	  
    	  
          int id_activity = resultSet.getInt("ID_STAFF_ACTIVITY");
          
          ret.add(id_activity);

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


public StaffActivity findById(int id) {
	StaffActivity obj = new StaffActivity();     
	    
	  String SQL_SELECT = "Select * from staffactivity where ID_ACTIVITY='"+id+"'";

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





private int getLastId() {
	
	int id_event=0;
	String SQL_SELECT = "Select MAX(ID_ACTIVITY) from staffactivity";

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
	String SQL_SELECT = "Select COUNT(ID_USER) from userbdeactivity WHERE ID_ACTIVITY="+id;

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
		
		String SQL_INSERT = "Insert into userbdeactivity " + "Values (" + obj.getId_activity() +",'" + user.getId_user() + "')";
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








public ArrayList<Integer> findCollegue(int id) {
	
	String SQL_SELECT = "Select * from appliance where id_staff_activity = "+id+" AND id_user!=0";
	ArrayList<Integer> ret = new ArrayList<Integer>();
	
	System.out.println(SQL_SELECT);
	

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          int id_ret = resultSet.getInt("id_user");
	          ret.add(id_ret);
	      }
	      return ret;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return ret;

	
	
	
}



private int alreadyActibyUser(int id_acti,int id_user) {
	
	String SQL_SELECT = "Select * from userbdeactivity where id_activity = "+id_acti+" AND id_user = "+id_user ;

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
public boolean update(int i, BDEActivity obj) {
	int id = i;
	  
	  try {
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  
		  
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE bdeactivity SET TITLE = ?, DESCRIPTION= ?, DATE=?, START_HOUR=?, DURATION=?, NB_USERS=? WHERE ID_ACTIVITY = ?");

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

@Override
public int create(StaffActivity obj) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int join(StaffActivity obj, User user) {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public boolean update(int i, StaffActivity obj) {
	int id = i;
	  
	  try {
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  
		  
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE STAFFACTIVITY SET TITLE = ?, DESCRIPTION= ?, DATE=?, START_HOUR=?, DURATION=?, NB_USERS=? WHERE ID_ACTIVITY = ?");

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
public boolean update(StaffActivity obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean leave(int id, StaffActivity obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public ArrayList<StaffActivity> findAll() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int count_users_Staffacti(int id_activity) {
	int id_event=0;
	String SQL_SELECT = "Select COUNT(ID_USER) from APPLIANCE WHERE ID_STAFF_ACTIVITY="+id_activity+" AND ID_USER != 0";

	System.out.println(SQL_SELECT);
	
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

@Override
public int joinStaff(int id_acti, Event event, User user) {
	
	  String SQL_INSERT = "";	
	  if(!alreadyAppliance(id_acti,event.getId_event())) {
		  
		  SQL_INSERT = "UPDATE APPLIANCE SET ID_STAFF_ACTIVITY = "+id_acti+", ID_EVENT= "+event.getId_event()+", ID_USER="+user.getId_user()+" WHERE ID_STAFF_ACTIVITY = "+id_acti+" AND ID_EVENT = "+event.getId_event();
		  
	  }
	  else {
		  SQL_INSERT = "Insert into APPLIANCE " + "Values (" + id_acti +"," + event.getId_event() +"," +user.getId_user()+ ")"; 
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



private boolean alreadyAppliance(int id_acti,int id_event) {
	
	int count = 0;
	boolean res = false;
	String SQL_SELECT = "Select COUNT(ID_USER) from APPLIANCE WHERE ID_STAFF_ACTIVITY="+id_acti+" AND ID_USER = 0";

	System.out.println(SQL_SELECT);
	
	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          count = resultSet.getInt("COUNT(ID_USER)"); 
	      }
	      
	      if(count>0) {
	    	  res=true;
	      }
	      
	      return res;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return res;
	
}

}