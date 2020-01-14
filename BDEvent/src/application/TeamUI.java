package application;

import buisnessLogic.Routing;
import buisnessLogic.Team;
import buisnessLogic.TeamFacade;
import buisnessLogic.TeamMemberFacade;
import buisnessLogic.User;
import buisnessLogic.UserFacade;
import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Contact;
import buisnessLogic.ContactFacade;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class TeamUI extends Routing implements Initializable {
	

	ArrayList<Integer> globalTeamsList = new ArrayList<Integer>();
	
	//Create Team
	@FXML
	private Button createButton;
	@FXML
	private TextField nameTeamField;
	
	
	//Manage Teams
	@FXML
	 private ListView<String> tableTeams;
	 @FXML 
	 private Button editTeam;
	 
	 
	 //Modify Team
	 @FXML
	private Button saveButton;
	 
	 //Join Team
	 @FXML
		private Button joinButton;
		@FXML
		private TextField idTeamField;
		@FXML
		private CheckBox isChief;
		@FXML
		private CheckBox isNotChief;
	  
		
	 
	
	   public void initialize(URL location, ResourceBundle resources) {
		   
		  if ( super.getVue().contentEquals("CreateTeam")) {			  
			  //NOTHING TO DO
		  } 
		  else if( super.getVue().contentEquals("ModifyTeam")) {
			 
		  	}
		  else if (super.getVue().contentEquals("JoinTeam")) {
			  //NOTHING TO DO 
		  }
		  else if (super.getVue().contentEquals("ManageMyTeams")) {
			  TeamMemberFacade teamMemberFacade = new TeamMemberFacade();
			  TeamFacade teamFacade = new TeamFacade();
			  ArrayList<Integer> idTeams = teamMemberFacade.findUserTeam(super.getCurrentUser().getId_user());
			  ArrayList<String> teams = new ArrayList<String>();
			   for (int team : idTeams) {
				   teams.add(teamFacade.findById(team).getNameTeam());
			   }
			   globalTeamsList.removeAll(globalTeamsList);
			   tableTeams.getItems().addAll(teams);
			  
		  }
		  else if (super.getVue().contentEquals("ManageTeams")) { //Les teams du BDE
			  
			  BDEFacade bdeFacade = new BDEFacade();
			   TeamFacade teamFacade = new TeamFacade();
			   ArrayList<Integer> idTeams = bdeFacade.getListTeams(super.getCurrentUser().getCurrentBDE());
			   ArrayList<String> teams = new ArrayList<String>();
			   for (int team : idTeams) {
				   teams.add(teamFacade.findById(team).getNameTeam());
				   System.out.println(teamFacade.findById(team).getNameTeam());
			   }
			   globalTeamsList.removeAll(globalTeamsList);
			   tableTeams.getItems().addAll(teams);
			   
		  } 
		  else { 				  
			  }
	   }
			
	   
	   
	   
	   public void createAction(ActionEvent event) {
		   TeamFacade teamFacade = new TeamFacade();
		   BDEFacade bdeFacade = new BDEFacade();
		   BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
		   System.out.println(bde);
	       System.out.println(nameTeamField.getText());
	       int res = teamFacade.create(bde, nameTeamField.getText());
	       

	       if (res < 0 ) {
	    	   //ERROR MESSAGE 
	       }
	       else {
	    	   //Confirm message
	    	   super.hideConfirmMessage();
	    	   super.setVue("ManageTeams");
	    	   super.goTo("ManageTeamsUI");
	       }
	       
	   }
	   
	   
	   public void manageBDE(ActionEvent event) {
		   super.setVue("ManageBDE");
		   super.goTo("ManageBDEUI");
	   }

	  
	   public void addTeam(ActionEvent event) {
		   super.setVue("CreateTeam");
		   super.goToLittleWindow("CreateTeamUI");
	   }
	   
	   
	   public void joinAction(ActionEvent event) {
	       TeamMemberFacade teamMemberFacade = new TeamMemberFacade();
	       User user = super.getCurrentUser();
	       int res = teamMemberFacade.add_member(user, Integer.parseInt(idTeamField.getText()), isChief.isSelected());
	       if (res < 0 ) {
	    	   //ERROR MESSAGE 
	       }
	       else {
	    	   //Confirm message
	    	   super.hideConfirmMessage();
	    	   super.setVue("HomePage");
	    	   super.goTo("HomePageUI");
	       }
	       
	   }
	   
	   public void joinTeam(ActionEvent event) {
		   super.setVue("JoinTeam");
		   super.goToLittleWindow("JoinTeamUI");
	   }
	   
	   
	   
	   public void homePage(ActionEvent event) {
		   super.setVue("HomePage");
		   super.goTo("HomePageUI");
	   }
	   
	   
	   public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.setVue("Login");
		   super.goTo("LoginUI");
	   }
	   

	  
	   	   
	       
   
}
