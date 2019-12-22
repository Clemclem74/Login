package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDEFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class JoinBDEUI extends Routing implements Initializable {
	//Declaration of component of the fxml file.
		@FXML
		private Button joinButton;
		@FXML
		private TextField idBDEField;
	  
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void joinAction(ActionEvent event) {
		       UserFacade userFacade = new UserFacade();
		       User user = super.getCurrentUser();
		       int res = userFacade.join(user, Integer.parseInt(idBDEField.getText()));
		       if (res < 0 ) {
		    	   //ERROR MESSAGE 
		       }
		       else {
		    	   //Confirm message
		    	   super.goTo("HomePageUI");
		    	   super.hideConfirmMessage();
		       }
		       
	   }
}
