package application;

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

public class ManageBDEUI extends Routing implements Initializable {
	 @FXML
	 private Label nameBDE_text;
	 @FXML
	 private Label school_text;
	 @FXML
	 private Label id_text;
	 @FXML
	 private Label name_text;
	 

	@Override
	   public void initialize(URL location, ResourceBundle resources) {
		  User user=super.getCurrentUser();
		  BDEFacade bdeFacade = new BDEFacade();
		  BDE bde = bdeFacade.findById(user.getCurrentBDE());
	      this.nameBDE_text.setText(bde.getNameBDE());
		  this.school_text.setText(bde.getSchoolBDE());
	      this.id_text.setText(Integer.toString(bde.getIdBDE()));
		  this.name_text.setText(user.getFirstname() +" "+ user.getLastname());
	       // TODO (don't really need to do anything here).
	   }

	   // When user click on myButton
	   // this method will be called.S
	   public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.goTo("LoginUI");
	   }

	   // When user click on modifyUserButton
	   // this method will be called.
	   public void modifyBDE(ActionEvent event) {
		   super.goTo("ModifyBDEUI");
	   }

	   // When user click on deleteAccountButton
	   // this method will be called.
	   
	   
	   public void deleteBDE(ActionEvent event) {
		   super.goTo("DeleteBDEUI");
	   }

	   
	   public void homePage(ActionEvent event) {
		   super.homePage();
	   }
	   // When user click on createBdeButton
	   // this method will be called.
	  
	   public void manageTeams(ActionEvent event) {
		   super.goTo("ManageTeamsUI");
	   }
	       
   
}
