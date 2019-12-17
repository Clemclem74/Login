package application;

import buisnessLogic.LoginFacade;
import buisnessLogic.Routing;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class LoginUI extends Routing implements Initializable {
		@FXML
	   private Button loginButton;
	  
	   @FXML
	   private TextField emailField;
	   
	   @FXML
	   private PasswordField passwordField;
	  
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	 
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void loginAction(ActionEvent event) {
	       LoginFacade loginFacade = new LoginFacade();
	       loginFacade.login(emailField.getText(),passwordField.getText());
	   }	
	   
	   public void registerAction(ActionEvent event) {
		   Routing root = new Routing();
		   root.goToRegister();
	   }
}
