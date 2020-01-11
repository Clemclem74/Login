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
	 private Button poll_button;
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
	 private Button goToBB;
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
	 @FXML
	 private Button manageMyTeams;
	 @FXML
	 private Button activities;
	 

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
			  BDELabel.setText(bdeuser.getNameBDE() + " " + bdeuser.getSchoolBDE());
			  if (user.isAdminOfHisBDE()) {
				  showBDE.setVisible(true);
				  quitBDEbutton.setVisible(false);
			  }
			  else {
				  showBDE.setVisible(false);
			  }

		  }
		  else {
			  manageMyTeams.setVisible(false);
			  showBDE.setVisible(false);
			  poll_button.setVisible(false);
			  fees_button.setVisible(false);
			  events_button.setVisible(false);
			  quitBDEbutton.setVisible(false);
			  communication_button.setVisible(false);
			  BDELabel.setVisible(false);

		  }
	     
	   }

	   // When user click on myButton
	   // this method will be called.S
	   public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.goTo("LoginUI");
	   }

	   // When user click on modifyUserButton
	   // this method will be called.
	   public void modifyUser(ActionEvent event) {
		   super.goTo("ModifyUserUI");
	   }

	   // When user click on deleteAccountButton
	   // this method will be called.
	   public void deleteUser(ActionEvent event) {
		   super.goTo("DeleteUserUI");
	   }

	   // When user click on createBdeButton
	   // this method will be called.
	   public void createBDE(ActionEvent event) {
		   super.goTo("CreateBDEUI");
	   }

	   public void quitBDE(ActionEvent event) {
		   UserFacade userfacade = new UserFacade();
		   userfacade.setBDEnull(super.getCurrentUser());
		   super.goTo("HomePageUI");
	   }
	   
	   public void joinBDE(ActionEvent event) {
		   super.goToLittleWindow("JoinBDEUI");
	    	   //this.idBDELabel.setText("8");
	   }
	   
	   public void manageBDEAction(ActionEvent envent) {
		   super.goTo("ManageBDEUI");
	   }
	   
	   public void go_acti(ActionEvent envent) {
		   super.goTo("ActivityUI");
	   }
	   
	   public void goToBB(ActionEvent envent) {
		   super.setVue("BasicBB");
		   super.goTo("BlackBoardUI");
	   }
	   public void goToPoll(ActionEvent event) {
		   super.setVue("BasicPoll");
		   super.goTo("PollUI");
	   }
	   
	   public void goToFee(ActionEvent envent) {
		   super.setVue("BasicFee");
		   super.goTo("FeeUI");
	   }
	   
	   
	   public void manageMyTeamsAction(ActionEvent event) {
		   super.goTo("ManageMyTeamsUI");
	   }
	   
	   public void manageMyEventsAction(ActionEvent event) {
		   super.goTo("EventUI");
	   }
	       
   
}
