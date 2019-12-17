package application;

import buisnessLogic.UserFacade;
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
	   
	   @FXML
	   private Label errorMessage;
	  
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	 
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void loginAction(ActionEvent event) {
	       
	       UserFacade loginFacade = new UserFacade();
	       
	       int res = loginFacade.login(emailField.getText(),passwordField.getText());
	       if (res>0) {
	    	   Routing root = new Routing();
	    	   root.homePage();
	       }
	       else {
	    	   this.passwordField.clear();
	    	   this.errorMessage.setText("Wrong Password or Username");
	       }
	   }
	   
	   public void wrongConnection() {
		   this.errorMessage.setText("Wrong email or wrong Password");
	       UserFacade loginFacade = new UserFacade();
	       loginFacade.login(emailField.getText(),passwordField.getText());
	   }	
	   
	   public void registerAction(ActionEvent event) {
		   Routing root = new Routing();
		   root.goToRegister();
	   }
}
