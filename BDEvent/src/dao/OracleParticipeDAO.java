package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import buisnessLogic.Participe;
import buisnessLogic.User;
public class OracleParticipeDAO extends OracleDAO<Participe>{
	
	public OracleParticipeDAO(Connection conn) {
		  super(conn);
		}
	
	public int create(Participe obj) {
		System.out.println("Before");
		int id = getLastId()+1;
		System.out.println("Before");
		
		 String SQL_INSERT = "Insert into participe (ID_PARTICIPE, ID_MEETING, ID_PARTICIPER, CHOICE_PARTICIPE, ID_BDE) " + "Values (" + id +"," + obj.getId_meeting()+ "," + obj.getId_participer() + ",'"
				   + obj.getChoice()+ "',"+ obj.getId_bde_participer() + ")";
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
		int id_participe = 0;
		String SQL_SELECT = "Select MAX(ID_PARTICIPE) from participe";
		
		try (Connection conn = DriverManager.getConnection(
				  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

		      ResultSet resultSet = preparedStatement.executeQuery();
		      while (resultSet.next()) {
		          id_participe = resultSet.getInt("MAX(ID_PARTICIPE)"); 
		      }
		      return id_participe;

		  } catch (SQLException e) {
		      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		  } catch (Exception e) {
		      e.printStackTrace();
		  }
		  return id_participe;
	}
	
	public ArrayList<Participe> findAllParticipeByBDE(User user) {
		ArrayList<Participe> ret = new ArrayList<>();
		
	    	int idbde = user.getCurrentBDE();
		  String SQL_SELECT = "Select * from participe where ID_BDE="+idbde;
		  // auto close connection and preparedStatement
		  try (Connection conn = DriverManager.getConnection(
				  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
			  
		      ResultSet resultSet = preparedStatement.executeQuery();
		      
		      while (resultSet.next()) {
		    	  Participe obj = new Participe();
		    	  int id_participe = resultSet.getInt("ID_PARTICIPE");
		          int id_participer = resultSet.getInt("ID_PARTICIPER");
		          String choice_participe = resultSet.getString("CHOICE_PARTICIPE");
		          int id_meeting = resultSet.getInt("ID_MEETING");
		          int bde = resultSet.getInt("ID_BDE");
		          
		          
		          obj.setId_participe(id_participe);
		          obj.setId_participer(id_participer);
		          obj.setChoice(choice_participe);
		          obj.setId_meeting(id_meeting);
		          obj.setId_bde_participer(bde);
		          

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
	public boolean delete(Participe obj) {
		return false;
	}

	@Override
	public boolean update(Participe obj) {
		return false;
	}

	@Override
	public ArrayList<Participe> findAll() {
		return null;
	}

	@Override
	public Participe findById(int id) {
		return null;
	}

	@Override
	public int getNumber() {
		return 0;
	}

}
