package buisnessLogic;

import java.io.IOException;
import java.util.ArrayList;


import dao.AbstractDAOFactory;
import dao.OracleDAO;


public class PollFacade {
	
	User connectedUser;
	
	AbstractDAOFactory adf;
	
	public PollFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);
	}
	
	public int createPoll(int idUser, String title,ArrayList<String> choices, int idBde) {
		Poll obj = new Poll();
		
		obj.setId_user_publisher(idUser);
		obj.setTitle_pollBB(title);
		obj.setId_BDE_pollBB(idBde);
		for(int i = 0;i<choices.size();i++) {
			obj.setChoices_pollBB(choices.get(i));
		}
		
		 OracleDAO<Poll> pollDao = adf.getPollDAO();
		 
		 int res = pollDao.create(obj);
	        System.out.println("creation obj database");
	        if(res>=0) {
	        	System.out.println( "the poll : " + title + " created");
	        	return res;
	        }
	        else {
	        	System.out.println("Error while creating the poll");
	        	return -1;
	        }
		
	}
	
	public int delete(Poll poll) {
        OracleDAO<Poll> pollDao = adf.getPollDAO();
        if(pollDao.delete(poll)) {
        	System.out.println("poll deleted");
        	return 1;
        }
        else {
        	System.out.println("Error while deleting Poll");
        	return -1;
        }
	}
	
	public int modify(int idPoll, int idUser , String title,ArrayList<String> choices, int idBde) {
		System.out.println("modify dans pollfacade debut");
		Poll obj = new Poll();
		
		obj.setId_pollBB(idPoll);
		obj.setId_user_publisher(idUser);
		obj.setTitle_pollBB(title);
		obj.removeAllChoices(choices);
		for(int i=0; i<choices.size();i++) {
			obj.setChoices_pollBB(choices.get(i));
		}
		obj.setId_BDE_pollBB(idBde);
        
        OracleDAO<Poll> pollDao = adf.getPollDAO();
        
        if(pollDao.update(obj)) {
        	System.out.println("poll modified");
        	return 1;
        }
        else {
        	System.out.println("Error while modifing poll");
        	return -1;
        }
	}
	
	public ArrayList<Poll> findAllPollBDE(User user){
		OracleDAO<Poll> pollDao = this.adf.getPollDAO();
		ArrayList<Poll> allpoll = pollDao.findAllPollByBDE(user);
		if(allpoll == null) {
			System.out.println("no poll found");
			return null;
		}
		else {
			return allpoll;
		}
	}
	
	public Poll find(String poll_name) {
		OracleDAO<Poll> pollDao = this.adf.getPollDAO();
		Poll poll = pollDao.find(poll_name);
		if(poll == null) {
			System.out.println("poll null find");
			return null;
		}
		else {
			return poll;
		}
	}
	

}
