package dao;
import java.sql.Connection;
import java.util.ArrayList;

import buisnessLogic.Post;
import buisnessLogic.User;

public abstract class OracleDAO<T> {
  protected Connection connect = null;
  /*
  public static final String ORACLE_DB_PATH = "jdbc:mysql://localhost/oose";
  public static final String ORACLE_DB_USER = "root";
  public static final String ORACLE_DB_PASSWORD = "";
 */
 
  public static final String ORACLE_DB_PATH = "jdbc:oracle:thin:@localhost:1521:xe";
  public static final String ORACLE_DB_USER = "system";
  public static final String ORACLE_DB_PASSWORD = "oose";
 
  
  public OracleDAO(Connection conn){
    this.connect = conn;
    
  }


  public abstract int create(T obj);

  public abstract int join(T obj,User user);


  public abstract boolean delete(T obj);


  public abstract boolean update(int i, T obj);

  public abstract boolean update(T obj);


  public abstract T find(String id);

  public abstract ArrayList<T> findAll();


  public abstract T findById(int id);

  public abstract ArrayList<Integer> getEventByUser(User user);

  public abstract ArrayList<Integer> findTeams(int idBDE);


public  ArrayList<Post> findAllPostByBDE(User user){
	// TODO Auto-generated method stub
	return null;
}


public ArrayList<Post> findAllPostByUser(User user) {
	// TODO Auto-generated method stub
	return null;
}


public boolean acceptPost(Post obj) {
	// TODO Auto-generated method stub
	return false;
}


public boolean accept(Post obj) {
	// TODO Auto-generated method stub
	return false;
}

}
