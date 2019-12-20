package application;

import buisnessLogic.UserFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class HomePageUI extends Routing implements Initializable {
	 @FXML
	 private Button fees_button;
	 @FXML
	 private Button communication_button;
	 @FXML
	 private Button quitBDEbutton;
	 @FXML
	 private Button events_button;
	 @FXML
	 private Button createBDE;
	 @FXML
	 private Button showBDE;
	 @FXML
	 private Button joinBDE;
	 @FXML
	 private Label username_text;
	 @FXML
	 private Label email_text;
	 @FXML
	 private Label phone_number_text;  
	 @FXML
	 private Label name_text;
	 

	@Override
	   public void initialize(URL location, ResourceBundle resources) {
		  User user=super.getCurrentUser();
	      this.username_text.setText(user.getUsername());
		  this.email_text.setText(user.getEmailuser());
	      this.phone_number_text.setText(user.getPhonenumberuser());
		  this.name_text.setText(user.getFirstname() +" "+ user.getLastname());
		  if (super.getCurrentUser().isPartOfBDE()) {
			  createBDE.setVisible(false);
			  joinBDE.setVisible(false);
		  }
		  else {
			  showBDE.setVisible(false);
			  fees_button.setVisible(false);
			  events_button.setVisible(false);
			  quitBDEbutton.setVisible(false);
			  communication_button.setVisible(false);
			  
		  }
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.S
	  
	   public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   Routing root = new Routing();
		   root.logout();
	   }
	   
	   public void modifyUser(ActionEvent event) {
		   Routing root = new Routing();
		   root.modifyUser();
	   }
	   
	   public void deleteUser(ActionEvent event) {
		   Routing root = new Routing();
		   root.deleteUser();
	   }
	   
	   public void createBDE(ActionEvent event) {
		   Routing root = new Routing();
		   root.createBDE();
	   }
	   
	   public void quitBDE(ActionEvent event) {
		   UserFacade userfacade = new UserFacade();
		   userfacade.setBDEnull(super.getCurrentUser());
		   super.homePage();
	   }
}
