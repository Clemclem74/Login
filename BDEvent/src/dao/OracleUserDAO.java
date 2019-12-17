package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import buisnessLogic.User;

public class OracleUserDAO extends OracleDAO<User> {
public OracleUserDAO(Connection conn) {
  super(conn);
}

public boolean create(User obj) {
	System.out.println("Before");
	int id = getLastId()+1;
	System.out.println("Before");

	
	  String SQL_INSERT = "Insert into Users " + "Values (" + id +",'" + obj.getUsername() + "',"
			  +"'" + obj.getEmailuser() + "',"
					  +"'" + obj.getPassworduser() + "',"
							  +"'" + obj.getFirstname() + "',"
									  +"'" + obj.getLastname() + "',"
											  +"'" + obj.getPhonenumberuser() + "'"+")";
	  System.out.println(SQL_INSERT);
	  // auto close connection and preparedStatement
	  try {
		  
		  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
		  Statement st = conn.createStatement();

	      st.executeUpdate(SQL_INSERT);
		  
		  conn.close();
		  return true;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return false;
	
}

public boolean delete(User obj) {
  return false;
}
 
public boolean update(int iduser, User obj) {
	
	int id = iduser;
	
	  String SQL_UPDATE = "Update USERS " + "SET USERNAME='" + obj.getUsername() + "',EMAILUSER='"
			  + obj.getEmailuser() + "',PASSWORDUSER="
					  +"'" + obj.getPassworduser() + "',FIRSTNAME="
							  +"'" + obj.getFirstname() + "',LASTNAME="
									  +"'" + obj.getLastname() + "',PHONENUMBERUSER="
											  +"'" + obj.getPhonenumberuser() + "'"+" WHERE ID_USER="+id + ";";
	  System.out.println(SQL_UPDATE);
	  // auto close connection and preparedStatement
	  try {
		  
		  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
		  Statement st = conn.createStatement();

	      st.executeUpdate(SQL_UPDATE);
		  
		  conn.close();
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
	          "jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
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

 
public User find(String id) {
  User obj = new User();      
    
  String SQL_SELECT = "Select * from Users where emailuser='"+id+"'";

  // auto close connection and preparedStatement
  try (Connection conn = DriverManager.getConnection(
          "jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
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

          obj.setId_user(id_user);
          obj.setUsername(username);
          obj.setFirstname(firstname);
          obj.setLastname(lastname);
          obj.setEmailuser(emailuser);
          obj.setPassworduser(passworduser);
          obj.setPhonenumberuser(phonenumberuser);
          

      }
      System.out.println(obj.getPassworduser());
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
}