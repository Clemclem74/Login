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
	 private Button events_button;
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
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.S
	   public void HomePageAction(ActionEvent event) {
//		  User user=super.getCurrentUser();
//		  System.out.println("Email : " + user.getEmailuser());
//	      this.username_text.setText(user.getUsername());
//		  this.email_text.setText(user.getEmailuser());
//	      this.phone_number_text.setText(user.getPhonenumberuser());
//		  this.name_text.setText(user.getFirstname() +" "+ user.getLastname());
	   }
	   
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
}
