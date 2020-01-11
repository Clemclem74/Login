package application;

import buisnessLogic.Routing;
import buisnessLogic.Team;
import buisnessLogic.TeamFacade;
import buisnessLogic.TeamMemberFacade;
import buisnessLogic.User;
import buisnessLogic.UserFacade;
import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Contact;
import buisnessLogic.ContactFacade;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class BDEUI extends Routing implements Initializable {
	
	ArrayList<Contact> globalContactList = new ArrayList<Contact>();
	ArrayList<Integer> globalTeamsList = new ArrayList<Integer>();
	
	//Create BDE
	@FXML
	private Button createButton;
	@FXML
	private TextField nameBDEField;
	@FXML
	private TextField schoolBDEField;
	@FXML
	private Label idBDELabel;
	
	
	//Manage My BDE
	 @FXML
	 private Label nameBDE_text;
	 @FXML
	 private Label school_text;
	 @FXML
	 private Label id_text;
	 @FXML
	 private Label name_text;
	 
	 
	 //Modify BDE
	 @FXML
	private Button saveButton;
	 
	 //Join BDE
	 @FXML
	 private TextField idBDEField;
		
	 
	
	   public void initialize(URL location, ResourceBundle resources) {
		   
		  if ( super.getVue().contentEquals("CreateBDE")) {			  
			  //NOTHING TO DO
		  } 
		  else if( super.getVue().contentEquals("ModifyBDE")) {
			 
				BDEFacade bdeFacade = new BDEFacade();
			    BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
			    nameBDEField.setText(bde.getNameBDE());
			    schoolBDEField.setText(bde.getSchoolBDE());
		  	}
		  else if (super.getVue().contentEquals("JoinBDE")) {
			  //NOTHING TO DO 
		  }
		  else { //Manage
				  User user=super.getCurrentUser();
				  BDEFacade bdeFacade = new BDEFacade();
				  BDE bde = bdeFacade.findById(user.getCurrentBDE());
			      this.nameBDE_text.setText(bde.getNameBDE());
				  this.school_text.setText(bde.getSchoolBDE());
			      this.id_text.setText(Integer.toString(bde.getIdBDE()));
				  this.name_text.setText(user.getFirstname() +" "+ user.getLastname());
				  
			  }
	   }
			
	   
	   
	   public void createAction(ActionEvent event) {
	       BDEFacade bdeFacade = new BDEFacade();
	       UserFacade userFacade = new UserFacade();
	       User user = super.getCurrentUser();
	       System.out.println(user.getPassworduser());
	       int res = bdeFacade.create(user, nameBDEField.getText(),schoolBDEField.getText());
	       //System.out.println("L'ID du nouveau BDE est le : " + res);
	       userFacade.join(user, res);
	       super.getCurrentUser().setCurrentBDE(res);
	       if (res < 0 ) {
	    	   //ERROR MESSAGE 
	       }
	       else {
	    	   ConfirmMessageUI.setParams(Integer.toString(res));
	    	   super.goToLittleWindow("ConfirmMessageBDEUI");
	       }
	       
	   }
	   
	   
	   public void modifyAction(ActionEvent event) {
	       BDEFacade bdeFacade = new BDEFacade();
	       bdeFacade.modify(super.getCurrentUser().getCurrentBDE(), nameBDEField.getText(),schoolBDEField.getText());
	       super.setVue("ManageBDE");
	       super.goTo("ManageBDEUI");
	   }
	   
	   
	   public void joinAction(ActionEvent event) {
	       UserFacade userFacade = new UserFacade();
	       User user = super.getCurrentUser();
	       int res = userFacade.join(user, Integer.parseInt(idBDEField.getText()));
	       if (res < 0 ) {
	    	   //ERROR MESSAGE 
	       }
	       else {
	    	   //Confirm message
	    	   super.hideConfirmMessage();
	    	   super.setVue("HomePage");
	    	   super.goTo("HomePageUI");
	       }
	       
   }
	   
	   
	   
	   
	   
	   
	   
	   public void homePage(ActionEvent event) {
		   super.setVue("HomePage");
		   super.goTo("HomePageUI");
	   }
	   
	   
	   public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.setVue("Login");
		   super.goTo("LoginUI");
	   }
	   

	   // When user click on modifyUserButton
	   // this method will be called.
	   public void modifyBDE(ActionEvent event) {
		   super.setVue("ModifyBDE");
		   super.goTo("ModifyBDEUI");
	   }

	   // When user click on deleteAccountButton
	   // this method will be called.
	   
	   
	   public void deleteBDE(ActionEvent event) {
		   BDEFacade bdeFacade = new BDEFacade();
		   BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
		   bdeFacade.delete(bde);
		   UserFacade userFacade = new UserFacade();
		   userFacade.setBDEnull(super.getCurrentUser());
		   super.getCurrentUser().setCurrentBDE(-1);
		   homePage();
	   }

	   // When user click on createBdeButton
	   // this method will be called.
	  
	   public void manageTeams(ActionEvent event) {
		   super.setVue("ManageTeams");
		   super.goTo("ManageTeamsUI");
	   }
	   	   
	       
   
}
