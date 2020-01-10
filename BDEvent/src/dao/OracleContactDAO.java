package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import buisnessLogic.Contact;
import buisnessLogic.Fee;
import buisnessLogic.Team;
import buisnessLogic.TeamFacade;
import buisnessLogic.User;

public class OracleContactDAO extends OracleDAO<Contact> {
public OracleContactDAO(Connection conn) {
  super(conn);
}

@Override
public int create(Contact obj) {
	int id = getLastId()+1;

	  String SQL_INSERT = "Insert into CONTACT " + "Values (" + id +",'" + obj.getNameContact()+ "',"
			  +"'" + obj.getCompany() + "','"
					   + obj.getPhoneNumberContact()+ "','" + obj.getInformationsContact()+ "'," + obj.getTeamContact().getIdTeam() + ")";
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
	
	int id_contact=0;
	String SQL_SELECT = "Select MAX(ID_CONTACT)from CONTACT";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id_contact = resultSet.getInt("MAX(ID_CONTACT)"); 
	      }
	      return id_contact;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return id_contact;
	
}



@Override
public boolean delete(Contact contact) {
	int id = contact.getIdContact();
	String SQL_DELETE = "DELETE from CONTACT WHERE ID_CONTACT='"+id+"'";
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
public boolean update(Contact obj) {
	int id = obj.getIdContact();
	  try {
		
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE CONTACT SET NAME_CONTACT = ?, COMPANY_CONTACT = ?, PHONENUMBER_CONTACT = ? , INFORMATIONS_CONTACT = ? , ID_TEAM_CONTACT = ? WHERE ID_CONTACT = ? ");
		  
			    // set the prepared statement parameters
			    ps.setString(1,obj.getNameContact());
			    ps.setString(2, obj.getCompany());
			    ps.setString(3, obj.getPhoneNumberContact());
			    ps.setString(4, obj.getInformationsContact());
			    ps.setInt(5, obj.getTeamContact().getIdTeam());
			    ps.setInt(6,id);
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










public Contact findById(int id) {
	Contact obj = new Contact();      
    
	  String SQL_SELECT = "Select * from CONTACT where ID_CONTACT='"+id+"' ";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {
	    	  
	          int id_contact = resultSet.getInt("ID_CONTACT");
	          String name_contact = resultSet.getString("NAME_CONTACT");
	          String company_contact = resultSet.getString("COMPANY_CONTACT");
	          String phone_number_contact = resultSet.getString("PHONENUMBER_CONTACT");
	          String informations_contact = resultSet.getString("INFORMATIONS_CONTACT");
	          int id_team = resultSet.getInt("ID_TEAM_CONTACT");

	          TeamFacade teamFacade = new TeamFacade();
	          obj.setIdContact(id_contact);
	          obj.setCompany(company_contact);
	          obj.setNameContact(name_contact);
	          obj.setPhoneNumberContact(phone_number_contact);
	          obj.setInformationsContact(informations_contact);
	          obj.setTeamContact(teamFacade.findById(id_team));

	          

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













public ArrayList<Contact> findAllContactByTeam(Team team) {
	ArrayList<Contact> ret = new ArrayList<Contact>();
	 int id = team.getIdTeam();
	  String SQL_SELECT = "Select * from CONTACT where ID_TEAM_CONTACT="+id;
	  // auto close connection and preparedStatement
	
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
		  
	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {
	    	  
	    	  Contact obj = new Contact();
	          int id_contact = resultSet.getInt("ID_CONTACT");
	          String name_contact = resultSet.getString("NAME_CONTACT");
	          String company_contact = resultSet.getString("COMPANY_CONTACT");
	          String phone_number_contact = resultSet.getString("PHONENUMBER_CONTACT");
	          String informations_contact = resultSet.getString("INFORMATIONS_CONTACT");
	          int id_team = resultSet.getInt("ID_TEAM_CONTACT");

	          TeamFacade teamFacade = new TeamFacade();
	          obj.setIdContact(id_contact);
	          obj.setCompany(company_contact);
	          obj.setNameContact(name_contact);
	          obj.setPhoneNumberContact(phone_number_contact);
	          obj.setInformationsContact(informations_contact);
	          obj.setTeamContact(teamFacade.findById(id_team));

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



public ArrayList<Fee> findAllFee(User user) {
	ArrayList<Fee> ret = new ArrayList<Fee>();
		int idUser = user.getId_user();
    	int idbde = user.getCurrentBDE();
	  String SQL_SELECT = "Select DISTINCT(ID_FEE),TITLE_FEE,COMMENT_FEE,AMOUNT_FEE,STATE_FEE,ID_USER_FEE from FEE,USERS where ID_USER_FEE = ID_USER AND ID_BDE="+idbde;
	  // auto close connection and preparedStatement
	  System.out.println("avant try");
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
		  
	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	    	  Fee obj = new Fee();
	          int id_fee = resultSet.getInt("ID_FEE");
	          String title_fee = resultSet.getString("TITLE_FEE");
	          String comment_fee = resultSet.getString("COMMENT_FEE");
	          int amount_fee = resultSet.getInt("AMOUNT_FEE");
	          int state_fee = resultSet.getInt("STATE_FEE");
	          int id_user_fee = resultSet.getInt("ID_USER_FEE");


	          obj.setId_fee(id_fee);
	          obj.setTitle_fee(title_fee);
	          obj.setComment_fee(comment_fee);
	          obj.setAmount_fee(amount_fee);
	          obj.setState_fee(state_fee);
	          obj.setId_user_fee(id_user_fee);


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
public int join(Contact obj, User user) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public boolean update(int i, Contact obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean leave(int id, Contact obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Contact find(String id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ArrayList<Contact> findAll() {
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

@Override
public boolean isChief(int id_user) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public int count_users_BDEacti(int acti) {
	// TODO Auto-generated method stub
	return 0;
}




}
