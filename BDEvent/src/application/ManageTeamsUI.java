package application;

import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Routing;
import buisnessLogic.Team;
import buisnessLogic.TeamFacade;
import buisnessLogic.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class ManageTeamsUI extends Routing implements Initializable {
	 @FXML
	 private TableView tableTeams;
	 @FXML 
	 private TableColumn nameTeam;
	 @FXML 
	 private TableColumn detailsTeam;
	 @FXML 
	 private TableColumn editTeam;
	 
	 
	 

	@Override
	   public void initialize(URL location, ResourceBundle resources) {
		 
	   }

	   // When user click on myButton
	   // this method will be called.S
	   public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.goTo("LoginUI");
	   }
	   
	   public void generate(ActionEvent event) {
		   System.out.println("Generate");
		   BDEFacade bdeFacade = new BDEFacade();
		   TeamFacade teamFacade = new TeamFacade();
		   ArrayList<Integer> idTeams = bdeFacade.getListTeams(super.getCurrentUser().getCurrentBDE());
		   ArrayList<Team> teams = new ArrayList<Team>();
		   for (int team : idTeams) {
			   teams.add(teamFacade.findById(team));
			   System.out.println(teamFacade.findById(team).getNameTeam());
		   }
		  
	   }

	   
	   public void manageBDE(ActionEvent event) {
		   super.goTo("ManageBDEUI");
	   }
	   // When user click on createBdeButton
	   // this method will be called.
	  
	   public void addTeam(ActionEvent event) {
		   super.goToLittleWindow("CreateTeamUI");
	   }
	       
   
}
