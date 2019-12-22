package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Routing;
import buisnessLogic.TeamFacade;
import buisnessLogic.User;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class CreateTeamUI extends Routing implements Initializable {
	//Declaration of component of the fxml file.
		@FXML
		private Button joinButton;
		@FXML
		private TextField nameBDEField;
	  
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void createAction(ActionEvent event) {
		   TeamFacade teamFacade = new TeamFacade();
		   BDEFacade bdeFacade = new BDEFacade();
		   BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
	       int res = teamFacade.create(bde, nameBDEField.getText());
	       if (res < 0 ) {
	    	   //ERROR MESSAGE 
	       }
	       else {
	    	   //Confirm message
	    	   super.hideConfirmMessage();
	    	   super.goTo("ManageTeamsUI");
	       }
	       
	   }
}
