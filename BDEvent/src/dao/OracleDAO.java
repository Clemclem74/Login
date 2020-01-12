package dao;
import java.sql.Connection;
import java.util.ArrayList;

import buisnessLogic.BDE;
import buisnessLogic.Contact;
import buisnessLogic.Event;
import buisnessLogic.Fee;
import buisnessLogic.Poll;
import buisnessLogic.Post;
import buisnessLogic.Team;
import buisnessLogic.TeamMember;
import buisnessLogic.User;
import buisnessLogic.Vote;

public abstract class OracleDAO<T> {
  protected Connection connect = null;


  public static final String ORACLE_DB_PATH = "jdbc:oracle:thin:@localhost:1521:xe";
  public static final String ORACLE_DB_USER = "system";
  public static final String ORACLE_DB_PASSWORD = "oose";




//  public static final String ORACLE_DB_PATH = "jdbc:mysql://localhost/oose";
//  public static final String ORACLE_DB_USER = "root";
//  public static final String ORACLE_DB_PASSWORD = "";




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
  
  public abstract int create(T obj,Event event);

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

public  ArrayList<Poll> findAllPollByBDE(User user){
	// TODO Auto-generated method stub
	return null;
}

public ArrayList<Vote> findAllVoteByBDE(User user){
	
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


public boolean isChief(int id_user){
  return false;
}


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
  return null;
}


  public ArrayList<Integer> findTeamsByUser(int idUser){
  	return null;
  }


  public Team findByName(String nameTeam) {
  	// TODO Auto-generated method stub
  	return null;
  }


  public ArrayList<Contact> findAllContactByTeam(Team findById) {
  // TODO Auto-generated method stub
	return null;
  }


public abstract int getNumber();


public BDE findBySchool(String school) {
	// TODO Auto-generated method stub
	return null;
}


public TeamMember findByUserTeam(User user, Team team) {
	// TODO Auto-generated method stub
	return null;
}

}
