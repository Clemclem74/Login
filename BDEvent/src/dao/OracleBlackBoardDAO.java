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
import buisnessLogic.Post;
import buisnessLogic.Team;
import buisnessLogic.User;
import buisnessLogic.UserFacade;

public class OracleBlackBoardDAO extends OracleDAO<BlackBoard> {
public OracleBlackBoardDAO(Connection conn) {
  super(conn);
}

@Override
public int create(BlackBoard obj) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public boolean delete(BlackBoard obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean update(int i, BlackBoard obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean update(BlackBoard obj) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public BlackBoard find(String id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public BlackBoard findById(int id) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ArrayList<Integer> findTeams(int idBDE) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public ArrayList<BlackBoard> findAll() {
	// TODO Auto-generated method stub
	return null;
}

public ArrayList<Post> findAllPost(User user) {
	ArrayList<Post> ret = new ArrayList<Post>();
	
    	int idbde = user.getCurrentBDE();
	  String SQL_SELECT = "Select * from Post WHERE ID_BDE="+idbde+";";

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
	          int bde = resultSet.getInt("ID-BDE");

	          
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

@Override
public int join(BlackBoard obj, User user) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public ArrayList<Integer> getEventByUser(User user) {
	// TODO Auto-generated method stub
	return null;
}



}


