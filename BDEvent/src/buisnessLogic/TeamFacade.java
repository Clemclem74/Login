package buisnessLogic;



import java.io.IOException;

import dao.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TeamFacade {

	AbstractDAOFactory adf;


	public TeamFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);
	}

	public int create(BDE bde, String nameTeam) {

		Team obj = new Team();
		obj.setBde(bde);
		obj.setNameTeam(nameTeam);
        OracleDAO<Team> teamDao = adf.getTeamDAO();
        int res = teamDao.create(obj);
        if(res>=0) {
        	System.out.println(nameTeam + " created");
        	return res;
        }
        else {
        	System.out.println("Error while creating BDE");
        	return -1;
        }
	}
	
	

	
	
	
	
	
	public int modify(int idTeam, String nameTeam, int idBDE ) {
		BDEFacade bdeFacade = new BDEFacade();
		Team obj = new Team();
		obj.setIdTeam(idTeam);
		obj.setNameTeam(nameTeam);
		obj.setBde(bdeFacade.findById(idBDE));
		
     
        OracleDAO<Team> teamDao = adf.getTeamDAO();
        if(teamDao.update(obj)) {
        	System.out.println("Team modified");
        	return 1;
        }
        else {
        	System.out.println("Error while modifing user");
        	return -1;
        }
	}
	
	public int delete(Team team) {

        OracleDAO<Team> teamDao = adf.getTeamDAO();
        if(teamDao.delete(team)) {
        	System.out.println("Team deleted");
        	return 1;
        }
        else {
        	System.out.println("Error while deleting Team");
        	return -1;
        }
	}

	public Team findById(int idTeam) {
		OracleDAO<Team> teamDao = this.adf.getTeamDAO();
		Team team = teamDao.findById(idTeam);
		if (team.getIdTeam()==0) {
			System.out.println("Team null");
			return null;
		}
		else {
			return team;
		}
	}
	
	
	public Team findByName(String nameTeam) {
		OracleDAO<Team> teamDao = this.adf.getTeamDAO();
		Team team = teamDao.findByName(nameTeam);
		if (team.getIdTeam()==0) {
			System.out.println("Team null");
			return null;
		}
		else {
			return team;
		}
	}


	
	public int getNumber(){
		OracleDAO<Team> teamDao = this.adf.getTeamDAO();
		int nb = teamDao.getNumber();
		return nb;
	}

	

	public void sendError() {
		// TODO - implement LoginFacade.sendError
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param user
	 */
	public void sendUser(User user) {
		// TODO - implement LoginFacade.sendUser
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param user
	 * @param password
	 */
	public void verifyInformation(User user, String password) {
		// TODO - implement LoginFacade.verifyInformation
		throw new UnsupportedOperationException();
	}

	public int getIdUserConnected() {
		// TODO - implement LoginFacade.getIdUserConnected
		throw new UnsupportedOperationException();
	}

	public String getUsernameConnected() {
		// TODO - implement LoginFacade.getUsernameConnected
		throw new UnsupportedOperationException();
	}

	public String getFirstNameConnected() {
		// TODO - implement LoginFacade.getFirstNameConnected
		throw new UnsupportedOperationException();
	}

	public String getLastNameConnected() {
		// TODO - implement LoginFacade.getLastNameConnected
		throw new UnsupportedOperationException();
	}

	public String getEmailUserConnected() {
		// TODO - implement LoginFacade.getEmailUserConnected
		throw new UnsupportedOperationException();
	}

	public String getPhoneNumberUserConnected() {
		// TODO - implement LoginFacade.getPhoneNumberUserConnected
		throw new UnsupportedOperationException();
	}

}
