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

public class ModifyUserUI extends Routing implements Initializable {
		@FXML
		private Button registerButton;
		@FXML
		private TextField firstNameField;
		@FXML
		private TextField lastNameField;
		@FXML
		private TextField usernameField;
	   @FXML
	   private TextField emailField;
	   @FXML
	   private TextField phoneNumberField;
	   @FXML
	   private PasswordField passwordField;
	   @FXML
	   private PasswordField passwordField2;
	  
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   User user = super.getCurrentUser();
		   firstNameField.setText(user.getFirstname());
		   emailField.setText(user.getEmailuser());
		   lastNameField.setText(user.getLastname());
		   usernameField.setText(user.getUsername());
		   phoneNumberField.setText(user.getPhonenumberuser());
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void saveAction(ActionEvent event) {
		   UserFacade userFacade = new UserFacade();
		   System.out.println(super.getCurrentUser().getPassworduser());
	       int res = userFacade.modify(super.getCurrentUser().getId_user(),usernameField.getText(),emailField.getText(),super.getCurrentUser().getPassworduser(),firstNameField.getText(),lastNameField.getText(),phoneNumberField.getText(),super.getCurrentUser().getCurrentBDE());
	       super.homePage();
	   }
}
