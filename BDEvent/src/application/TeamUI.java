package application;

import buisnessLogic.Routing;
import buisnessLogic.Team;
import buisnessLogic.TeamFacade;
import buisnessLogic.TeamMember;
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
	 @FXML
	 private Button quitButton;
	 
	 
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
		@FXML
		private Label errorMessage;
	  
		
	 
	
	   public void initialize(URL location, ResourceBundle resources) {
		   
		  if ( super.getVue().contentEquals("CreateTeam")) {			  
			  //NOTHING TO DO
		  } 
		  else if( super.getVue().contentEquals("ModifyTeam")) {
			 
		  	}
		  else if (super.getVue().contentEquals("JoinTeam")) {
			  BDEFacade bdeFacade = new BDEFacade();
			  TeamFacade teamFacade = new TeamFacade();
			  ArrayList<Integer> idteams = bdeFacade.getListTeams(super.getCurrentUser().getCurrentBDE());
			  ArrayList<String> nameTeams = new ArrayList<String>();
			  for(Integer idteam:idteams) {
				  Team team = teamFacade.findById(idteam);
				  nameTeams.add(team.getIdTeam() + " : " + team.getNameTeam());
			  }
			  globalTeamsList.removeAll(globalTeamsList);
			  tableTeams.getItems().addAll(nameTeams);
			  
		  }
		  else if (super.getVue().contentEquals("ManageMyTeams")) {
			  TeamMemberFacade teamMemberFacade = new TeamMemberFacade();
			  TeamFacade teamFacade = new TeamFacade();
			  ArrayList<Integer> idTeams = teamMemberFacade.findUserTeam(super.getCurrentUser().getId_user());
			  ArrayList<String> teams = new ArrayList<String>();
			   for (int idteam : idTeams) {
				   Team team = teamFacade.findById(idteam);
				   teams.add(team.getIdTeam() + " : " + team.getNameTeam());
			   }
			   globalTeamsList.removeAll(globalTeamsList);
			   tableTeams.getItems().addAll(teams);
			   quitButton.setVisible(false);
			  
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
	   
	   
	   public void displaySelectedList(MouseEvent event) {
			TeamFacade teamFacade = new TeamFacade();
			String team1 =tableTeams.getSelectionModel().getSelectedItem();
			Team teamSelected = teamFacade.findById(Integer.parseInt(team1.split(" : ")[0]));
			super.setCurrentTeam(teamSelected);
			quitButton.setVisible(true);
	   }
	   
	   public void displaySelectedListJoin(MouseEvent event) {
			TeamFacade teamFacade = new TeamFacade();
			String team1 =tableTeams.getSelectionModel().getSelectedItem();
			System.out.println(team1);
			Team teamSelected = teamFacade.findById(Integer.parseInt(team1.split(" : ")[0]));
			super.setCurrentTeam(teamSelected);
	   }
  
	   
	   
	   public void joinAction(ActionEvent event) {
	       TeamMemberFacade teamMemberFacade = new TeamMemberFacade();
	       User user = super.getCurrentUser(); 
	       int res = teamMemberFacade.add_member(user, super.getCurrentTeam().getIdTeam(), isChief.isSelected());
	       if (res < 0 ) {
	    	   //ERROR MESSAGE 
	       }
	       else {
	    	   //Confirm message
	    	   super.hideConfirmMessage();
	    	   super.setVue("ManageMyTeams");
	    	   super.goTo("ManageMyTeamsUI");
	       }
	       
	   }
	   
	   public void joinTeam(ActionEvent event) {
		   super.setVue("JoinTeam");
		   super.goToLittleWindow("JoinTeamUI");
	   }
	   
	   public void quitTeam(ActionEvent event) {
		   TeamMemberFacade teamMemberFacade = new TeamMemberFacade();
		   TeamMember tm = teamMemberFacade.findByUserTeam(super.getCurrentUser(), super.getCurrentTeam());
		   teamMemberFacade.delete(tm);
		   super.setVue("ManageMyTeams");
		   super.goTo("ManageMyTeamsUI");
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
