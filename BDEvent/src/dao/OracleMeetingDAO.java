package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import buisnessLogic.Poll;
import buisnessLogic.Post;
import buisnessLogic.User;


import buisnessLogic.Meeting;

public class OracleMeetingDAO extends OracleDAO<Meeting> {
	
	public OracleMeetingDAO(Connection conn) {
		  super(conn);
		}
	
	public int create(Meeting obj) {
		int id = getLastId()+1;
		
		 String SQL_INSERT = "Insert into meeting (ID_MEETING, PUBLISHER_MEETING, TITLE_MEETING, DATE_MEETING, ID_BDE) " + "Values (" + id +"," + obj.getPublisher_meeting() + ",'"
				  + obj.getTitle() + "','" + obj.getMeeting_date() + "',"+
						  	+ obj.getId_bde_meeting() + ")";
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
	
	@Override
	public boolean delete(Meeting meeting) {
		int id = meeting.getId_meeting();
		String SQL_DELETE = "DELETE from meeting WHERE ID_MEETING='"+id+"'";
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
	
	@Override
	public boolean update(Meeting obj) {
		int id = obj.getId_meeting();

		  
		  try {
			
			  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
			  PreparedStatement ps = conn.prepareStatement(
				      "UPDATE meeting SET TITLE_MEETING = ?, DATE_MEETING = ? WHERE ID_MEETING = ? ");
			  
				    // set the preparedstatement parameters
				    ps.setString(1,obj.getTitle());
				    ps.setString(2,obj.getMeeting_date().toString());
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

	private int getLastId() {
		int id_meeting=0;
		String SQL_SELECT = "Select MAX(ID_MEETING)from meeting";

		  // auto close connection and preparedStatement
		  try (Connection conn = DriverManager.getConnection(
				  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

		      ResultSet resultSet = preparedStatement.executeQuery();
		      while (resultSet.next()) {
		          id_meeting = resultSet.getInt("MAX(ID_MEETING)"); 
		      }
		      return id_meeting;

		  } catch (SQLException e) {
		      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		  } catch (Exception e) {
		      e.printStackTrace();
		  }
		  return id_meeting;

	}
	
	public ArrayList<Meeting> findAllMeetingByBDE(User user){
		ArrayList<Meeting> ret = new ArrayList<>();
		
		int idbde = user.getCurrentBDE();
		String SQL_SELECT = "Select * from meeting where ID_BDE="+idbde;
		  // auto close connection and preparedStatement
		  try (Connection conn = DriverManager.getConnection(
				  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
			  
		      ResultSet resultSet = preparedStatement.executeQuery();
		      
		      while (resultSet.next()) {
		    	  Meeting obj = new Meeting();
		    	  int id_meeting = resultSet.getInt("ID_MEETING");
		          int id_publisher = resultSet.getInt("PUBLISHER_MEETING");
		          String title = resultSet.getString("TITLE_MEETING");
		          String date = resultSet.getString("DATE_MEETING");
		          int bde = resultSet.getInt("ID_BDE");
		          
		          
		          obj.setId_meeting(id_meeting);
		          obj.setPublisher_meeting(id_publisher);
		          obj.setTitle(title);
		          obj.setMeeting_date(date);
		          obj.setId_bde_meeting(bde);

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
	
	public Meeting find(String titreMeeting) {
		Meeting obj = new Meeting();      
	    
		  String SQL_SELECT = "Select * from meeting where TITLE_MEETING='"+titreMeeting+"'";

		  // auto close connection and preparedStatement
		  try (Connection conn = DriverManager.getConnection(
				  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

		      ResultSet resultSet = preparedStatement.executeQuery();
		      
		      while (resultSet.next()) {

		       
		          int id_meeting = resultSet.getInt("ID_MEETING");
		          int id_publisher = resultSet.getInt("PUBLISHER_MEETING");
		          String title = resultSet.getString("TITLE_MEETING");
		          String date = resultSet.getString("DATE_MEETING");
		          int bde = resultSet.getInt("ID_BDE");
		          
		          obj.setId_meeting(id_meeting);
		          obj.setPublisher_meeting(id_publisher);
		          obj.setTitle(title);
		          obj.setMeeting_date(date);
		          obj.setId_bde_meeting(bde);
		          
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
		 String SQL_SELECT = "Select * from meeting";
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
	public ArrayList<Meeting> findAll() {
		return null;
	}

	@Override
	public Meeting findById(int id) {
		return null;
	}
	

}
