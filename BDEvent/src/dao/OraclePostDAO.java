package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import buisnessLogic.BDE;
import buisnessLogic.BlackBoard;
import buisnessLogic.Event;
import buisnessLogic.Post;
import buisnessLogic.Team;
import buisnessLogic.User;
import buisnessLogic.UserFacade;

public class OraclePostDAO extends OracleDAO<Post> {
public OraclePostDAO(Connection conn) {
  super(conn);
}

@Override
public int create(Post obj) {
	System.out.println("Before");
	int id = getLastId()+1;
	System.out.println("Before");

	
	  String SQL_INSERT = "Insert into POSTBB " + "Values (" + id +"," + obj.getId_user_publisher() + ","
			  +"'" + obj.getTitle_postBB() + "',"
					  +"'" + obj.getText_postBB() + "',"+
					  	+ obj.getId_BDE_postBB() + ")";
	  System.out.println(SQL_INSERT);
	  // auto close connection and preparedStatement
	  try {
		  
		  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
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
public boolean delete(Post obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean update(int i, Post obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean update(Post obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Post find(String titrePost) {
	Post obj = new Post();      
    
	  String SQL_SELECT = "Select * from POSTBB where TITLE_POSTBB='"+titrePost+"'";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {

	       
	          int id_postbb = resultSet.getInt("ID_POSTBB");
	          int id_publisher = resultSet.getInt("ID_USER_PUBLISHER");
	          String title = resultSet.getString("TITLE_POSTBB");
	          String text = resultSet.getString("TEXT_POSTBB");
	          int bde = resultSet.getInt("ID_BDE");

	          
	          obj.setId_postBB(id_postbb);
	          obj.setId_user_publisher(id_publisher);
	          obj.setTitle_postBB(title);
	          obj.setText_postBB(text);
	          obj.setId_BDE_postBB(bde);
	          

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
public Post findById(int id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ArrayList<Integer> findTeams(int idBDE) {
	// TODO Auto-generated method stub
	return null;
}

private int getLastId() {
	
	int id_post=0;
	String SQL_SELECT = "Select MAX(ID_POSTBB)from POSTBB";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          "jdbc:oracle:thin:@localhost:1521:xe", "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id_post = resultSet.getInt("MAX(ID_POSTBB)"); 
	      }
	      return id_post;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return id_post;
	
}

public ArrayList<Post> findAll() {
	  
	ArrayList<Post> ret = new ArrayList<Post>();
    
  String SQL_SELECT = "Select * from POSTBB";

  // auto close connection and preparedStatement
  try (Connection conn = DriverManager.getConnection(
		  ORACLE_DB_PATH, "system", "oose");
       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

      ResultSet resultSet = preparedStatement.executeQuery();
      
      while (resultSet.next()) {
    	  
    	  Post obj = new Post();
          int id_postbb = resultSet.getInt("ID_POSTBB");
          int id_publisher = resultSet.getInt("ID_USER_PUBLISHER");
          String title = resultSet.getString("TITLE_POSTBB");
          String text = resultSet.getString("TEXT_POSTBB");
          int bde = resultSet.getInt("ID_BDE");

          
          obj.setId_postBB(id_postbb);
          obj.setId_user_publisher(id_publisher);
          obj.setTitle_postBB(title);
          obj.setText_postBB(text);
          obj.setId_BDE_postBB(bde);
          

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


public ArrayList<Post> findAllPostByBDE(User user) {
	ArrayList<Post> ret = new ArrayList<Post>();
	
    	int idbde = user.getCurrentBDE();
	  String SQL_SELECT = "Select * from POSTBB where ID_BDE="+idbde;
	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
		  
	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {
	    	  Post obj = new Post();
	          int id_postbb = resultSet.getInt("ID_POSTBB");
	          int id_publisher = resultSet.getInt("ID_USER_PUBLISHER");
	          String title = resultSet.getString("TITLE_POSTBB");
	          String text = resultSet.getString("TEXT_POSTBB");
	          int bde = resultSet.getInt("ID_BDE");

	          obj.setId_postBB(id_postbb);
	          obj.setId_user_publisher(id_publisher);
	          obj.setTitle_postBB(title);
	          obj.setText_postBB(text);
	          obj.setId_BDE_postBB(bde);

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

public ArrayList<Post> findAllPostByUser(User user) {
	ArrayList<Post> ret = new ArrayList<Post>();
	
    	int iduser = user.getId_user();
	  String SQL_SELECT = "Select * from POSTBB where ID_USER_PUBLISHER="+iduser;
	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, "system", "oose");
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
		  
	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {
	    	  Post obj = new Post();
	          int id_postbb = resultSet.getInt("ID_POSTBB");
	          int id_publisher = resultSet.getInt("ID_USER_PUBLISHER");
	          String title = resultSet.getString("TITLE_POSTBB");
	          String text = resultSet.getString("TEXT_POSTBB");
	          int bde = resultSet.getInt("ID_BDE");

	          obj.setId_postBB(id_postbb);
	          obj.setId_user_publisher(id_publisher);
	          obj.setTitle_postBB(title);
	          obj.setText_postBB(text);
	          obj.setId_BDE_postBB(bde);

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

}
