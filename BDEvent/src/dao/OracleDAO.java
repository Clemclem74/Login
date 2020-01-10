package dao;
import java.sql.Connection;
import java.util.ArrayList;

import buisnessLogic.Event;
import buisnessLogic.Fee;
import buisnessLogic.Post;
import buisnessLogic.StaffActivity;
import buisnessLogic.User;

public abstract class OracleDAO<T> {
  protected Connection connect = null;

 
  /*public static final String ORACLE_DB_PATH = "jdbc:oracle:thin:@localhost:1521:xe";
  public static final String ORACLE_DB_USER = "system";
  public static final String ORACLE_DB_PASSWORD = "oose";
 
  
  public OracleDAO(Connection conn){
    this.connect = conn;
  }


  */
  public static final String ORACLE_DB_PATH = "jdbc:mysql://localhost/oose";
  public static final String ORACLE_DB_USER = "root";
  public static final String ORACLE_DB_PASSWORD = "";
  
 
	

  public OracleDAO(Connection conn){
    this.connect = conn;
    
    
    try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
  }


  public abstract int create(T obj);

  public abstract int join(T obj,User user);


  public abstract boolean delete(T obj);


  public abstract boolean update(int i, T obj);

  public abstract boolean update(T obj);

  public abstract boolean leave(int id,T obj);
  

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

public ArrayList<Fee> findAllFeeByUser(User user) {
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


public abstract boolean isChief(int id_user);


public ArrayList<Integer> findMembersByTeam(int idTeam) {
	// TODO Auto-generated method stub
	return null;
}


public ArrayList<Fee> findAllFee(User user) {
	// TODO Auto-generated method stub
	return null;
}


public boolean acceptFee(Fee obj) {
	// TODO Auto-generated method stub
	return false;
}


public abstract int count_users_BDEacti(int acti);


public abstract int create(StaffActivity acti, Event event);


public abstract ArrayList<Integer> findAllStaff(int id_event);


public abstract int count_users_Staffacti(int id_activity);


public abstract int joinStaff(int acti, Event event, User user);


public ArrayList<Integer> findCollegue(int id_activity) {
	// TODO Auto-generated method stub
	return null;
}




}