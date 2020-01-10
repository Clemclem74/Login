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


public class OraclePollDAO extends OracleDAO<Poll> {
	public OraclePollDAO(Connection conn) {
		  super(conn);
		}
	public int create(Poll obj) {
		System.out.println("Before");
		int id = getLastId()+1;
		System.out.println("Before");
		
		 String SQL_INSERT = "Insert into POLLBB " + "Values (" + id +"," + obj.getId_user_publisher() + ","
				  +"'" + obj.getTitle_pollBB() + "',"
						  +"'" + obj.getchoices_pollBB() + "',"+
						  	+ obj.getId_BDE_pollBB() + "," + 0 + ")";
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
	public boolean delete(Poll poll) {
		int id = poll.getId_pollBB();
		String SQL_DELETE = "DELETE from POLLBB WHERE ID_POLLBB='"+id+"'";
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
	public boolean update(Poll obj) {
		
		int id = obj.getId_pollBB();
		  
		  try {
			
			  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
			  System.out.println(id);
			  System.out.println(obj.getTitle_pollBB());
			  System.out.println(obj.getchoices_pollBB());
			  PreparedStatement ps = conn.prepareStatement(
				      "UPDATE POSTBB SET TITLE_POLLBB = ?, CHOICES_POLLBB = ?, STATE = 0 WHERE ID_POLLBB = ? ");
			  
				    // set the preparedstatement parameters
				    ps.setString(1,obj.getTitle_pollBB());
				    ps.setString(2,obj.getchoices_pollBB());
				    ps.setInt(3,id);
				  
				   System.out.println(obj.getTitle_pollBB());
				   
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
	public Poll find(String titrePoll) {
		Poll obj = new Poll();      
	    
		  String SQL_SELECT = "Select * from POLLBB where TITLE_POLLBB='"+titrePoll+"'";

		  // auto close connection and preparedStatement
		  try (Connection conn = DriverManager.getConnection(
				  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

		      ResultSet resultSet = preparedStatement.executeQuery();
		      
		      while (resultSet.next()) {

		       
		          int id_pollbb = resultSet.getInt("ID_POLLBB");
		          int id_publisher = resultSet.getInt("ID_USER_PUBLISHER");
		          String title = resultSet.getString("TITLE_POLLBB");
		          String choices = resultSet.getString("CHOICES_POLLBB");
		          int bde = resultSet.getInt("ID_BDE");
		          int state = resultSet.getInt("STATE");


		          
		          obj.setId_pollBB(id_pollbb);
		          obj.setId_user_publisher(id_publisher);
		          obj.setTitle_pollBB(title);
		          obj.setChoices_pollBB(choices);
		          obj.setId_BDE_pollBB(bde);
		          obj.setState(state);
		          
		          

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
		
		int id_poll=0;
		String SQL_SELECT = "Select MAX(ID_POLLBB)from POLLBB";

		  // auto close connection and preparedStatement
		  try (Connection conn = DriverManager.getConnection(
		          "jdbc:oracle:thin:@localhost:1521:xe", ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

		      ResultSet resultSet = preparedStatement.executeQuery();
		      while (resultSet.next()) {
		          id_poll = resultSet.getInt("MAX(ID_POLLBB)"); 
		      }
		      return id_poll;

		  } catch (SQLException e) {
		      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		  } catch (Exception e) {
		      e.printStackTrace();
		  }
		  return id_poll;
	}
	
	public ArrayList<Poll> findAll() {
		  
		ArrayList<Poll> ret = new ArrayList<>();
	    
	  String SQL_SELECT = "Select * from POLLBB";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {
	    	  
	    	  Poll obj = new Poll();
	          int id_pollbb = resultSet.getInt("ID_POLLBB");
	          int id_publisher = resultSet.getInt("ID_USER_PUBLISHER");
	          String title = resultSet.getString("TITLE_POLLBB");
	          String choices = resultSet.getString("CHOICES_POLLBB");
	          int bde = resultSet.getInt("ID_BDE");
	          int state = resultSet.getInt("STATE");


	          
	          obj.setId_pollBB(id_pollbb);
	          obj.setId_user_publisher(id_publisher);
	          obj.setTitle_pollBB(title);
	          obj.setChoices_pollBB(choices);
	          obj.setId_BDE_pollBB(bde);
	          obj.setState(state);
	          

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
	@Override
	public int join(Poll obj, User user) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean update(int i, Poll obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Poll findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Integer> getEventByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Integer> findTeams(int idBDE) {
		// TODO Auto-generated method stub
		return null;
	}
			

}
	
	
