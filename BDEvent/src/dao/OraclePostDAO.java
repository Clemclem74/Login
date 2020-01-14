package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import buisnessLogic.Post;
import buisnessLogic.User;

public class OraclePostDAO extends OracleDAO<Post> {
public OraclePostDAO(Connection conn) {
  super(conn);
}

@Override
public int create(Post obj) {
	System.out.println("Before");
	int id = getLastId()+1;
	System.out.println("Before");

	
	  String SQL_INSERT = "Insert into postbb " + "Values (" + id +"," + obj.getId_user_publisher() + ","
			  +"'" + obj.getTitle_postBB() + "',"
					  +"'" + obj.getText_postBB() + "',"+
					  	+ obj.getId_BDE_postBB() + "," + 0 + ")";
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
public boolean delete(Post post) {
	int id = post.getId_postBB();
	String SQL_DELETE = "DELETE from postbb WHERE ID_POSTBB='"+id+"'";
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
public boolean update(Post obj) {
	
	int id = obj.getId_postBB();
	  
	  try {
		
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  System.out.println(id);
		  System.out.println(obj.getTitle_postBB());
		  System.out.println(obj.getText_postBB());
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE postbb SET TITLE_POSTBB = ?, TEXT_POSTBB = ?, STATE = 0 WHERE ID_POSTBB = ? ");
		  
			    // set the preparedstatement parameters
			    ps.setString(1,obj.getTitle_postBB());
			    ps.setString(2,obj.getText_postBB());
			    ps.setInt(3,id);
			  
			   System.out.println(obj.getTitle_postBB());
			   
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
public Post find(String titrePost) {
	Post obj = new Post();      
    
	  String SQL_SELECT = "Select * from postbb where TITLE_POSTBB='"+titrePost+"'";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {

	       
	          int id_postbb = resultSet.getInt("ID_POSTBB");
	          int id_publisher = resultSet.getInt("ID_USER_PUBLISHER");
	          String title = resultSet.getString("TITLE_POSTBB");
	          String text = resultSet.getString("TEXT_POSTBB");
	          int bde = resultSet.getInt("ID_BDE");
	          int state = resultSet.getInt("STATE");


	          
	          obj.setId_postBB(id_postbb);
	          obj.setId_user_publisher(id_publisher);
	          obj.setTitle_postBB(title);
	          obj.setText_postBB(text);
	          obj.setId_BDE_postBB(bde);
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
	
	int id_post=0;
	String SQL_SELECT = "Select MAX(ID_POSTBB)from postbb";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
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
    
  String SQL_SELECT = "Select * from postbb";

  // auto close connection and preparedStatement
  try (Connection conn = DriverManager.getConnection(
		  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

      ResultSet resultSet = preparedStatement.executeQuery();
      
      while (resultSet.next()) {
    	  
    	  Post obj = new Post();
          int id_postbb = resultSet.getInt("ID_POSTBB");
          int id_publisher = resultSet.getInt("ID_USER_PUBLISHER");
          String title = resultSet.getString("TITLE_POSTBB");
          String text = resultSet.getString("TEXT_POSTBB");
          int bde = resultSet.getInt("ID_BDE");
          int state = resultSet.getInt("STATE");


          
          obj.setId_postBB(id_postbb);
          obj.setId_user_publisher(id_publisher);
          obj.setTitle_postBB(title);
          obj.setText_postBB(text);
          obj.setId_BDE_postBB(bde);
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


public ArrayList<Post> findAllPostByBDE(User user) {
	ArrayList<Post> ret = new ArrayList<Post>();
	
    	int idbde = user.getCurrentBDE();
	  String SQL_SELECT = "Select * from postbb where ID_BDE="+idbde;
	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
		  
	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {
	    	  Post obj = new Post();
	          int id_postbb = resultSet.getInt("ID_POSTBB");
	          int id_publisher = resultSet.getInt("ID_USER_PUBLISHER");
	          String title = resultSet.getString("TITLE_POSTBB");
	          String text = resultSet.getString("TEXT_POSTBB");
	          int bde = resultSet.getInt("ID_BDE");
	          int state = resultSet.getInt("STATE");


	          obj.setId_postBB(id_postbb);
	          obj.setId_user_publisher(id_publisher);
	          obj.setTitle_postBB(title);
	          obj.setText_postBB(text);
	          obj.setId_BDE_postBB(bde);
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

public ArrayList<Post> findAllPostByUser(User user) {
	ArrayList<Post> ret = new ArrayList<Post>();
	
    	int iduser = user.getId_user();
	  String SQL_SELECT = "Select * from postbb where ID_USER_PUBLISHER="+iduser;
	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
		  
	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {
	    	  Post obj = new Post();
	          int id_postbb = resultSet.getInt("ID_POSTBB");
	          int id_publisher = resultSet.getInt("ID_USER_PUBLISHER");
	          String title = resultSet.getString("TITLE_POSTBB");
	          String text = resultSet.getString("TEXT_POSTBB");
	          int bde = resultSet.getInt("ID_BDE");
	          int state = resultSet.getInt("STATE");

	          obj.setId_postBB(id_postbb);
	          obj.setId_user_publisher(id_publisher);
	          obj.setTitle_postBB(title);
	          obj.setText_postBB(text);
	          obj.setId_BDE_postBB(bde);
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
public boolean acceptPost(Post obj) {
	int id = obj.getId_postBB();
	System.out.println("id : "+ id);
	  
	  try {
		
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE postbb SET STATE = 1 WHERE ID_POSTBB =? ");
		  
			    // set the preparedstatement parameters
			    
			    ps.setInt(1,id);
			    System.out.println("id : "+ id);

			   
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

public int getNumber() {
	 String SQL_SELECT = "Select * from postbb";
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
public Post findById(int id) {
	// TODO Auto-generated method stub
	return null;
}



}
