package dao;
import java.sql.Connection;
import java.sql.DriverManager;

import buisnessLogic.BDE;
import buisnessLogic.BDEActivity;
import buisnessLogic.Contact;
import buisnessLogic.Post;
import buisnessLogic.StaffActivity;
import buisnessLogic.Team;
import buisnessLogic.TeamMember;
import buisnessLogic.User;
import buisnessLogic.Vote;
import buisnessLogic.Event;
import buisnessLogic.Poll;
import buisnessLogic.Fee;


public class OracleDAOFactory extends AbstractDAOFactory{

	  protected static final Connection conn = null;

	  public OracleDAO<User> getUserDAO(){
	    return new OracleUserDAO(conn);
	  }

	  public OracleDAO<BDE> getBDEDAO(){
		    return new OracleBDEDAO(conn);
	  }

	  public OracleDAO<Team> getTeamDAO(){
		  return new OracleTeamDAO(conn);
	  }
	  public OracleDAO<Fee> getFeeDAO(){
		  return new OracleFeeDAO(conn);
	  }
	  
	  public OracleDAO<Contact> getContactDAO(){
		  return new OracleContactDAO(conn);
	  }
	  
	  public OracleDAO<Vote> getVoteDAO(){
		  return new OracleVoteDAO(conn);
	  }
	  

	@Override
	public OracleDAO<Post> getPostDAO() {
		return new OraclePostDAO(conn);
	}
	
	@Override
	public OracleDAO<Poll> getPollDAO() {
		return new OraclePollDAO(conn);
	}

	  public OracleDAO<TeamMember> getTeamMemberDAO(){
		  return new OracleTeamMemberDAO(conn);
	  }

		public OracleDAO<Event> getEventDAO(){
		 return new OracleEventDAO(conn);
	 }
		
		public OracleDAO<BDEActivity> getBDEActivityDAO(){
			return new OracleBDEActivityDAO(conn);
		}

		public OracleDAO<StaffActivity> getStaffActivityDAO(){
			 return new OracleStaffActivityDAO(conn);
		 }

		
	}
