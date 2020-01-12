package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import buisnessLogic.Poll;
import buisnessLogic.User;
import buisnessLogic.Vote;

public class OracleVoteDAO extends OracleDAO<Vote> {
	
	public OracleVoteDAO(Connection conn) {
		  super(conn);
		}
	
	public int create(Vote obj) {
		System.out.println("Before");
		int id = getLastId()+1;
		System.out.println("Before");
		
		 String SQL_INSERT = "Insert into VOTE (ID_VOTE, ID_POLL, ID_VOTER, CHOICE_VOTE, ID_BDE) " + "Values (" + id +"," + obj.getId_poll()+ "," + obj.getId_voter() + ","
				   + obj.getUserChoice()+ ","+ obj.getId_bde_voter() + ")";
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

	private int getLastId() {
		int id_vote = 0;
		String SQL_SELECT = "Select MAX(ID_VOTE) from POLLBB";
		
		try (Connection conn = DriverManager.getConnection(
				  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

		      ResultSet resultSet = preparedStatement.executeQuery();
		      while (resultSet.next()) {
		          id_vote = resultSet.getInt("MAX(ID_VOTE)"); 
		      }
		      return id_vote;

		  } catch (SQLException e) {
		      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		  } catch (Exception e) {
		      e.printStackTrace();
		  }
		  return id_vote;
	}
	
	public ArrayList<Vote> findAllVoteByBDE(User user) {
		ArrayList<Vote> ret = new ArrayList<>();
		
	    	int idbde = user.getCurrentBDE();
		  String SQL_SELECT = "Select * from VOTE where ID_BDE="+idbde;
		  // auto close connection and preparedStatement
		  try (Connection conn = DriverManager.getConnection(
				  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
			  
		      ResultSet resultSet = preparedStatement.executeQuery();
		      
		      while (resultSet.next()) {
		    	  Vote obj = new Vote();
		    	  int id_vote = resultSet.getInt("ID_VOTE");
		          int id_voter = resultSet.getInt("ID_VOTER");
		          int choice_vote = resultSet.getInt("CHOICE_VOTE");
		          int id_poll = resultSet.getInt("ID_POLL");
		          int bde = resultSet.getInt("ID_BDE");
		          
		          
		          obj.setId_vote(id_vote);
		          obj.setId_voter(id_voter);
		          obj.setUserChoice(choice_vote);
		          obj.setId_poll(id_poll);
		          obj.setId_bde_voter(bde);
		          

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
	public boolean delete(Vote obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(int i, Vote obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Vote obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vote find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Vote> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vote findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
