package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
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
	 @FXML
	 private Label noBDE;
	 @FXML
	 private Label BDELabel;
	 

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
			  noBDE.setVisible(false);
			  BDEFacade bdefacade = new BDEFacade();
			  BDE bdeuser = bdefacade.findById(user.getCurrentBDE());
			  System.out.println(bdeuser.getNameBDE() + " " + bdeuser.getSchoolBDE());
			  BDELabel.setText(bdeuser.getNameBDE() + " " + bdeuser.getSchoolBDE());

		  }
		  else {
			  showBDE.setVisible(false);
			  fees_button.setVisible(false);
			  events_button.setVisible(false);
			  quitBDEbutton.setVisible(false);
			  communication_button.setVisible(false);
			  BDELabel.setVisible(false);

		  }
	       // TODO (don't really need to do anything here).
	   }

	   // When user click on myButton
	   // this method will be called.S
	   public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   Routing root = new Routing();
		   root.goTo("LoginUi");
	   }

	   // When user click on modifyUserButton
	   // this method will be called.
	   public void modifyUser(ActionEvent event) {
		   Routing root = new Routing();
		   root.goTo("ModifyUserUi");
	   }

	   // When user click on deleteAccountButton
	   // this method will be called.
	   public void deleteUser(ActionEvent event) {
		   Routing root = new Routing();
		   root.goTo("DeleteUserUi");
	   }

	   // When user click on createBdeButton
	   // this method will be called.
	   public void createBDE(ActionEvent event) {
		   Routing root = new Routing();
		   root.goTo("CreateBDEUI");
	   }

	   public void quitBDE(ActionEvent event) {
		   UserFacade userfacade = new UserFacade();
		   userfacade.setBDEnull(super.getCurrentUser());
		   super.goTo("HomePageUI");
	   }
	   
	   public void joinBDE(ActionEvent event) {
    	   super.joinBDE();
	    	   //this.idBDELabel.setText("8");
	   }
	       
   
}
