package dao;
import java.sql.Connection;
import java.util.ArrayList;

import buisnessLogic.BDE;
import buisnessLogic.BDEActivity;
import buisnessLogic.Contact;
import buisnessLogic.Event;
import buisnessLogic.Fee;
import buisnessLogic.Meeting;
import buisnessLogic.Participe;
import buisnessLogic.Poll;
import buisnessLogic.Post;
import buisnessLogic.StaffActivity;
import buisnessLogic.Team;
import buisnessLogic.TeamMember;
import buisnessLogic.User;
import buisnessLogic.Vote;

public abstract class OracleDAO<T> {
  protected Connection connect = null;


//public static final String ORACLE_DB_PATH = "jdbc:oracle:thin:@localhost:1521:xe";
//public static final String ORACLE_DB_USER = "system";
//public static final String ORACLE_DB_PASSWORD = "oose";



  //public static final String ORACLE_DB_PATH = "jdbc:mysql://localhost/oose";
  //public static final String ORACLE_DB_USER = "root";
  //public static final String ORACLE_DB_PASSWORD = "";



  public static final String ORACLE_DB_PATH = "jdbc:mysql://mysql-bdevent.alwaysdata.net/bdevent_oose";
  public static final String ORACLE_DB_USER = "bdevent";
  public static final String ORACLE_DB_PASSWORD = "oose_password";



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

  public abstract boolean delete(T obj);

  public abstract boolean update(T obj);
  
  public abstract ArrayList<T> findAll();

  public abstract T findById(int id);
  
  public abstract int getNumber();
  
  
  

  public boolean update(int i, T obj) {
	  return false ;
  }
  
  public int join(T obj,User user) {
	return 0;
  }
  

  public int create(T obj,Event event) {
	  return 0;
  }
  
  
  public boolean leave(int id,T obj) {
	  return false;
  }


  public T find(String id) {
	  return null;
  }  

  public ArrayList<Integer> getEventByUser(User user){
	return null;
  }

  public ArrayList<Integer> findTeams(int idBDE){
	  return null;
  }


public  ArrayList<Post> findAllPostByBDE(User user){
	// TODO Auto-generated method stub
	return null;
}

public  ArrayList<Poll> findAllPollByBDE(User user){
	// TODO Auto-generated method stub
	return null;
}

public ArrayList<Meeting> findAllMeetingByBDE(User user){
	// TODO Auto-generated method stub
	return null;
}

public ArrayList<Participe> findAllParticipeByBDE(User user){
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


public int count_users_BDEacti(int acti) {
	return 0;
}


public int create_acti_event(StaffActivity acti, Event event) {
	return 0;
};


public ArrayList<Integer> findAllStaff(int id_event){
	return null;
}


public int count_users_Staffacti(int id_activity) {
	return 0;
}


public int joinStaff(int acti, Event event, User user) {
	return 0;
}


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





public BDE findBySchool(String school) {
	// TODO Auto-generated method stub
	return null;
}


public TeamMember findByUserTeam(User user, Team team) {
	// TODO Auto-generated method stub
	return null;
}


public ArrayList<T> findAll(int bDE_id) {
	// TODO Auto-generated method stub
	return null;
}



}
