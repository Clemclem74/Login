package dao;

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
import buisnessLogic.Vote;

public class XMLDAOFactory extends AbstractDAOFactory {
	  
	public OracleDAO getUserDAO() {      
	    return null;
	}

	@Override
	public OracleDAO<BDE> getBDEDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OracleDAO<Team> getTeamDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OracleDAO<Post> getPostDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override 
	public OracleDAO<Poll> getPollDAO(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OracleDAO<TeamMember> getTeamMemberDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OracleDAO<Event> getEventDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public OracleDAO<Meeting> getMeetingDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public OracleDAO<Participe> getParticipeDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OracleDAO<Fee> getFeeDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OracleDAO<Vote> getVoteDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OracleDAO<dao.BlackBoard> getBlackBoardDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OracleDAO<BDEActivity> getBDEActivityDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OracleDAO<StaffActivity> getStaffActivityDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OracleDAO<Contact> getContactDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
}