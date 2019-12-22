package application;

import buisnessLogic.UserFacade;
import buisnessLogic.Routing;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class RegisterUI extends Routing implements Initializable {
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
	 
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void registerAction(ActionEvent event) {
	       if (passwordField.getText().contentEquals(passwordField2.getText())) {
		       UserFacade userFacade = new UserFacade();
		       userFacade.register(usernameField.getText(),emailField.getText(),passwordField.getText(),firstNameField.getText(),lastNameField.getText(),phoneNumberField.getText());
		       super.goTo("LoginUi");
	       }
	   }
}
