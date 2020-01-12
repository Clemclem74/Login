package dao;
import buisnessLogic.User;
import buisnessLogic.Vote;
import buisnessLogic.Activity;
import buisnessLogic.BDE;
import buisnessLogic.BDEActivity;
import buisnessLogic.Contact;
import buisnessLogic.Post;
import buisnessLogic.StaffActivity;
import buisnessLogic.Event;
import buisnessLogic.Fee;
import buisnessLogic.Team;
import buisnessLogic.TeamMember;
import buisnessLogic.Poll;

public abstract class AbstractDAOFactory {
	  public static final int ORACLE_DAO_FACTORY = 0;
	  public static final int XML_DAO_FACTORY = 1;

	  //Retourne un objet Classe interagissant avec la BDD
	  public abstract OracleDAO<User> getUserDAO();
	  public abstract OracleDAO<BDE> getBDEDAO();
	  public abstract OracleDAO<Team> getTeamDAO();
	  public abstract OracleDAO<Post> getPostDAO();

	  public abstract OracleDAO<Poll> getPollDAO();
	  public abstract OracleDAO<Vote> getVoteDAO();
	  public abstract OracleDAO<BlackBoard> getBlackBoardDAO();
	   

	  public abstract OracleDAO<Fee> getFeeDAO();
	  public abstract OracleDAO<BDEActivity> getBDEActivityDAO();
	  public abstract OracleDAO<StaffActivity> getStaffActivityDAO();
	  
	  //M�thode permettant de r�cup�rer les Factory
	  public abstract OracleDAO<TeamMember> getTeamMemberDAO();
	
	  public abstract OracleDAO<Event> getEventDAO();


	  //M�thode permettant de r�cup�rer les Factory
	  public static AbstractDAOFactory getFactory(int type){
	    switch(type){
	      case ORACLE_DAO_FACTORY:
	        return new OracleDAOFactory();
	      case XML_DAO_FACTORY:
	        return new XMLDAOFactory();
	      default:
	        return null;
	    }
	  }
	  
	  
	public abstract OracleDAO<Contact> getContactDAO();
	}
