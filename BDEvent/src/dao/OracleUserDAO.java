package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import buisnessLogic.User;

public class OracleUserDAO extends OracleDAO<User> {
public OracleUserDAO(Connection conn) {
  super(conn);
}

public int create(User obj) {
	System.out.println("Before");
	int id = getLastId()+1;
	System.out.println("Before");

	
	  String SQL_INSERT = "Insert into Users " + "Values (" + id +",'" + obj.getUsername() + "',"
			  +"'" + obj.getEmailuser() + "',"
					  +"'" + obj.getPassworduser() + "',"
							  +"'" + obj.getFirstname() + "',"
									  +"'" + obj.getLastname() + "',"
											  +"'" + obj.getPhonenumberuser() + "',"
											  		+ -1 +")";
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

public boolean delete(User user) {
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
    
 
public boolean update(int iduser, User obj) {
	
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

private int getLastId() {
	
	int id_user=0;
	String SQL_SELECT = "Select MAX(ID_USER)from Users";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id_user = resultSet.getInt("MAX(ID_USER)"); 
	      }
	      return id_user;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return id_user;
	
}

 
public User find(String email) {
  User obj = new User();      
    
  String SQL_SELECT = "Select * from Users where emailuser='"+email+"'";

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


public User findById(int id) {
	  User obj = new User();      
	    
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
public boolean update(User obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public ArrayList<Integer> findTeams(int idBDE) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ArrayList<User> findAll() {
	// TODO Auto-generated method stub
	return null;
}
}