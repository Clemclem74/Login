package application;

import buisnessLogic.BlackBoard;
import buisnessLogic.BlackBoardFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class BlackBoardUI extends Routing implements Initializable {
	 @FXML
	 private Button homePage;
	 @FXML
	 private Button newPost; 
	 @FXML
	 private Button seePost;
	 @FXML
	 private Button logoutButton;
	 

	@Override
	   public void initialize(URL location, ResourceBundle resources) {
		  User user=super.getCurrentUser();
		  BlackBoardFacade blackboardFacade = new BlackBoardFacade();
		  BlackBoard bb = new BlackBoard();
	     
	   }

	
	   
	   public void homePage(ActionEvent event) {
		   super.goTo("HomePageUI");
	   }
	   
	   public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.goTo("LoginUI");
	   }
	   
	   public void newPost(ActionEvent event) {
		   super.goTo("CreatePostUI");
	   }
	   
	  
	
	       
   
}
