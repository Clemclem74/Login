package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import buisnessLogic.User;

public class UserDAO extends DAO<User> {
public UserDAO(Connection conn) {
  super(conn);
}

public boolean create(User obj) {
  return false;
}

public boolean delete(User obj) {
  return false;
}
 
public boolean update(User obj) {
  return false;
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
          String lastname = resultSet.getString("LASTNAME");
          String firstname = resultSet.getString("FIRSTNAME");
          String emailuser = resultSet.getString("EMAILUSER");
          String passworduser = resultSet.getString("PASSWORDUSER");
          String phonenumberuser = resultSet.getString("PHONENUMBERUSER");

          obj.setId_user(id_user);
          obj.setUsername(username);
          obj.setFirstname(firstname);
          obj.setLastname(lastname);
          obj.setEmailuser(emailuser);
          obj.setPassworduser(passworduser);
          obj.setPhonenumberuser(phonenumberuser);
          

      }
      return obj;

  } catch (SQLException e) {
      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
  } catch (Exception e) {
      e.printStackTrace();
  }
return obj;
}
}