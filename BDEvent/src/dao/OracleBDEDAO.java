package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import buisnessLogic.BDE;

public class OracleBDEDAO extends OracleDAO<BDE> {
public OracleBDEDAO(Connection conn) {
  super(conn);
}

public int create(BDE obj) {
	int id = getLastId()+1;

	  String SQL_INSERT = "Insert into BDE " + "Values (" + id +",'" + obj.getCreator().getId_user() + "',"
			  +"'" + obj.getNameBDE() + "',"
					  +"'" + obj.getSchoolBDE() + "'"+")";
	  System.out.println(SQL_INSERT);

	  // auto close connection and preparedStatement
	  try {

		  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
		  Statement st = conn.createStatement();
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE Users SET USERNAME = ?, EMAILUSER = ?, PASSWORDUSER= ?, FIRSTNAME=?, LASTNAME=?, PHONENUMBERUSER=?, ID_BDE=? WHERE ID_USER = ? ");

			    // set the preparedstatement parameters
			    ps.setString(1,obj.getCreator().getUsername());
			    ps.setString(2,obj.getCreator().getEmailuser());
			    ps.setString(3,obj.getCreator().getPassworduser());
			    ps.setString(4,obj.getCreator().getFirstname());
			    ps.setString(5,obj.getCreator().getLastname());
			    ps.setString(6,obj.getCreator().getPhonenumberuser());
			    ps.setInt(7, obj.getIdBDE());
			    ps.setInt(8,obj.getCreator().getId_user());

			    // call executeUpdate to execute our sql update statement
			    ps.executeUpdate();
			    ps.close();



	      st.executeUpdate(SQL_INSERT);

		  conn.close();
		  return id;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return -1;

}

public boolean delete(BDE obj) {
  return false;
}

public boolean update(int idBde, BDE obj) {

	int id = idBde;




	  try {
		  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");


		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE Users SET USERNAME = ?, EMAILUSER = ?, PASSWORDUSER= ?, FIRSTNAME=?, LASTNAME=?, PHONENUMBERUSER=? WHERE ID_USER = ? ");

			    // set the preparedstatement parameters
			    ps.setString(1,obj.getUsername());
			    ps.setString(2,obj.getEmailuser());
			    ps.setString(3,obj.getPassworduser());
			    ps.setString(4,obj.getFirstname());
			    ps.setString(5,obj.getLastname());
			    ps.setString(6,obj.getPhonenumberuser());
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

private int getLastId() {

	int id_bde=0;
	String SQL_SELECT = "Select MAX(ID_BDE)from BDE";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          "jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id_bde = resultSet.getInt("MAX(ID_BDE)");
	      }
	      return id_bde;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return id_bde;

}


public BDE find(String id) {
  BDE obj = new BDE();

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
public boolean update(BDE obj) {
	// TODO Auto-generated method stub
	return false;
}
}
