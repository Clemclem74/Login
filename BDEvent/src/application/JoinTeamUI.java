package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDEFacade;
import buisnessLogic.Routing;
import buisnessLogic.TeamMemberFacade;
import buisnessLogic.User;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class JoinTeamUI extends Routing implements Initializable {
	//Declaration of component of the fxml file.
		@FXML
		private Button joinButton;
		@FXML
		private TextField idTeamField;
		@FXML
		private CheckBox isChief;
		@FXML
		private CheckBox isNotChief;
	  
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.
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
		    	   super.goTo("HomePageUI");
		       }
		       
	   }
}
