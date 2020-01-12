package buisnessLogic;

import java.util.ArrayList;

import dao.AbstractDAOFactory;
import dao.OracleDAO;


public class VoteFacade {
	
	User connectedUser;
	
	AbstractDAOFactory adf;
	
	public VoteFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);
	}
	
	public int addVote(int idUser, int choice, int idBde, int idPoll) {
		Vote obj = new Vote();
		
		obj.setId_poll(idPoll);
		obj.setId_voter(idUser);
		obj.setId_bde_voter(idBde);
		obj.setUserChoice(choice);
		
		OracleDAO<Vote> voteDao = adf.getVoteDAO();
		
		int res = voteDao.create(obj);
			System.out.println("creation obj database");
	        if(res>=0) {
	        	System.out.println( "the vote has been created");
	        	return res;
	        }
	        else {
	        	System.out.println("Error while creating the vote");
	        	return -1;
	        }
	}
	
	public ArrayList<Vote> findAllVoteBDE(int idPoll, User user){
		OracleDAO<Vote> voteDAO = this.adf.getVoteDAO();
		ArrayList<Vote> allvote = voteDAO.findAllVoteByBDE(user);
		if(allvote == null) {
			System.out.println("no votes found");
			return null;
		}
		else {
			return allvote;
		}
	}
	
	public ArrayList<Integer> counterVotes(ArrayList<Vote> list, int idpoll){
		int c1=0,c2=0,c3 =0;
		ArrayList<Integer> ret = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getUserChoice()==1 && idpoll==list.get(i).getId_poll()) {
				c1 = c1 + 1;
			}
			else if(list.get(i).getUserChoice()==2 && idpoll==list.get(i).getId_poll()) {
				c2 = c2 + 1;
			}
			else if(list.get(i).getUserChoice()==3 && idpoll==list.get(i).getId_poll()) {
				c3 = c3 + 1;
			}
		}
		ret.add(c1);
		ret.add(c2);
		ret.add(c3);
		return ret;
		
	}
	
	public boolean alreadyvoted(int idpoll, User user) {
		
		OracleDAO<Vote> voteDAO = this.adf.getVoteDAO();
		ArrayList<Vote> allvote = voteDAO.findAllVoteByBDE(user);
		if(allvote == null) {
			System.out.println("no votes found");
		}
		else {
			for(int i=0;i<allvote.size();i++) {
				if(allvote.get(i).getId_poll() == idpoll && allvote.get(i).getId_voter() == user.getId_user()) {
					return true;
				}
			}
			return false;
			
		}
		System.out.println("no vote");
		return false;
	}
	

}
