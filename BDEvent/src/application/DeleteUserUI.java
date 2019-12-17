package application;

import buisnessLogic.LoginFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class DeleteUserUI extends Routing implements Initializable {
		@FXML
		private Button discardDeleteButton;
		@FXML
		private Button deleteAccountButton;
		
	  
	  
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   User user = super.getCurrentUser();
		   
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void discardDelete(ActionEvent event) {
	       super.homePage();
	   }
	   
	
	   public void deleteAccount(ActionEvent event) {
		   LoginFacade userFacade = new LoginFacade();
		   System.out.println(super.getCurrentUser().getPassworduser());
	       int res = userFacade.delete(super.getCurrentUser());
	       super.logout();
	   }
}
