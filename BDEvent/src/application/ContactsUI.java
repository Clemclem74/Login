package application;

import buisnessLogic.Routing;
import buisnessLogic.Team;
import buisnessLogic.TeamFacade;
import buisnessLogic.TeamMemberFacade;
import buisnessLogic.User;
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

public class ContactsUI extends Routing implements Initializable {
	
	ArrayList<Contact> globalContactList = new ArrayList<Contact>();
	ArrayList<Integer> globalTeamsList = new ArrayList<Integer>();
	
	 @FXML
	 private Button homePage;
	 @FXML
	 private Button newContact; 
	 @FXML
	 private Button logoutButton;
	 @FXML
	 private Button modifyContact;
	 @FXML
	 private Button deleteContact; 
	 @FXML
	 private ListView<String> contactList;
	 @FXML
	 private Text nameText;
	 @FXML
	 private Label nameContact;
	 @FXML
	 private Text companyText;
	 @FXML
	 private Label companyContact;
	 @FXML
	 private Text phoneNumberText;
	 @FXML
	 private Label phoneNumberContact;
	 @FXML
	 private Text informationsText;
	 @FXML
	 private Label informationsContact;

	 
	 //--------------------------------Controlleur pour create Fee
	 
	 	@FXML
		private Button createContactButton;
		@FXML
		private Button backButton;
		@FXML
		private TextField nameField;
		@FXML
		private TextField companyField;
		@FXML
		private TextField phoneNumberField;
		@FXML
		private TextArea informationsField;
		@FXML
		private ListView<String> teamPane;
		@FXML
		private Label errorMessage;
		
		 //--------------------------------Controlleur pour Manage fee
		 
	 	@FXML
		private Button buttonBackFee;
		 @FXML
		 private ListView<String> applianceFeeList;
		 @FXML
		private Button payedButton;
		
	 
	
	   public void initialize(URL location, ResourceBundle resources) {
		   
		  if ( super.getVue().contentEquals("CreateContact")) {
			  TeamMemberFacade teamMemberFacade = new TeamMemberFacade();
			  TeamFacade teamFacade = new TeamFacade();
			  globalTeamsList.removeAll(globalTeamsList);
			  User user=super.getCurrentUser();
			  globalTeamsList = teamMemberFacade.findUserTeam(user.getId_user());
			  ArrayList<String> nameTeams = new ArrayList<String>();
			  globalTeamsList.forEach((n)-> nameTeams.add(teamFacade.findById(n).getNameTeam())); 
				
			  teamPane.getItems().addAll(nameTeams);
			  
			  
			  //NOTHING TO DO
		  } else 
			  
			  if( super.getVue().contentEquals("ModifyContact")) {
			 
			   Contact contact = super.getCurrentContact();
			   nameField.setText(contact.getNameContact());
			   companyField.setText(contact.getCompany());
			   phoneNumberField.setText(contact.getPhoneNumberContact());
			   informationsField.setText(contact.getInformationsContact());
		  	}
			  else {
				
				   displayContacts();
				   modifyContact.setVisible(false);
				   deleteContact.setVisible(false);
				   nameText.setVisible(false);
				   companyText.setVisible(false);
				   phoneNumberText.setVisible(false);
				   informationsText.setVisible(false);
				  
			  }
	   }
			
		  
	   private void displayContacts() {
		   globalContactList.removeAll(globalContactList);
		   super.setCurrentContact(null);
		   User user=super.getCurrentUser();
		   ContactFacade contactFacade = new ContactFacade();
		   globalContactList = contactFacade.findAllMyContact(user);
		   ArrayList<String> companyContact = new ArrayList<String>();
		   if (this.globalContactList == null) {
		   }
		   else {
			   this.globalContactList.forEach((n)-> companyContact.add(n.getIdContact()+ " : " + n.getCompany())); 
				
			   contactList.getItems().addAll(companyContact);
		   }
		  
		}
	   
	   @FXML
	 	private void displaySelectedList(MouseEvent event) {
	 			ContactFacade contactFacade = new ContactFacade();
	 			String contact1 =contactList.getSelectionModel().getSelectedItem();
	 			Contact contactSelected = contactFacade.findById(Integer.parseInt(contact1.split(" : ")[0]));
	 			super.setCurrentContact(contactSelected);
	 			if(contact1 != null) {
 					this.nameText.setVisible(true);
 					this.companyText.setVisible(true);
 					this.phoneNumberText.setVisible(true);
 					this.informationsText.setVisible(true);
 					this.nameContact.setText(contactSelected.getNameContact());
	 				this.companyContact.setText(contactSelected.getCompany());
	 				this.phoneNumberContact.setText(contactSelected.getPhoneNumberContact());
	 				this.informationsContact.setText(contactSelected.getInformationsContact());

	 				
	 				modifyContact.setVisible(true);
	 				deleteContact.setVisible(true);
	 			
	 				}
	 				
	 	}
	   
	   @FXML	
		private void deleteSelected(ActionEvent event) {
			ContactFacade contactFacade = new ContactFacade();
			contactFacade.delete(super.getCurrentContact());
			contactList.getItems().clear();
			globalContactList.removeAll(globalContactList);
			displayContacts();
			
			
			super.openPopUp("You're contact has been deleted", "congratulations, you can continue to the application");
			
			
			this.nameContact.setText("");
			this.companyContact.setText("");
			this.informationsContact.setText("");
			this.phoneNumberContact.setText("");
			this.nameText.setVisible(false);
			this.companyText.setVisible(false);
			this.phoneNumberText.setVisible(false);
			this.informationsText.setVisible(false);
		}
	 			
	// --------------ROUTING FUNCTION
	   
	   public void newContact(ActionEvent event) {
			super.setVue("CreateContact");
		   super.goTo("CreateContactUI");
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
	   
	   @FXML	
		private void modifySelected(ActionEvent event) {
			super.setVue("ModifyContact");
			super.goTo("ModifyContactUI");
			 
		}
	   
	  

	   
	   public void createContact(ActionEvent event) {
		   ContactFacade contactFacade = new ContactFacade();
		   TeamFacade teamFacade = new TeamFacade();
	       System.out.println("cration de contact en cours");
	       String team1 =teamPane.getSelectionModel().getSelectedItem();
	       Team teamSelected = teamFacade.findByName(team1.split(" : ")[0]);
	       try {
	    	    int res = contactFacade.create(nameField.getText(),companyField.getText(),phoneNumberField.getText(),informationsField.getText(),teamSelected.getIdTeam() );
	 	       System.out.println("L'ID du nouveau contact est le : " + res);
	 	       
	 		   if (res < 0 ) {
	 	    	   this.errorMessage.setText("Please make sure the contact has all the informations");
	 	       }
	 	       else {
	 	    	  super.setVue("BasicContact");
	 	    	   super.goTo("ContactsUI");
	 	    	   ConfirmMessageUI.setParams(Integer.toString(res));
	 	    	   super.openPopUp("Your Contact has been created", "Your team can see it ");
	 	    	  
	 	       }
	    	  
	    	} catch (NumberFormatException nfe) {
	    		this.errorMessage.setText("Please make sure the amount is a number and not a string");
	    	}
	      
	   }
	   
	   public void modifyContact(ActionEvent event) {
		   Contact contact=super.getCurrentContact();
		   ContactFacade contactFacade = new ContactFacade();
	       System.out.println("modification de Contact en cours");
	       try {
	    	   
		       int res = contactFacade.modify( contact.getIdContact(), nameField.getText(),companyField.getText(),phoneNumberField.getText(), informationsField.getText(), contact.getTeamContact());
			   
			   if (res < 0 ) {
				   this.errorMessage.setText("Please make sure the contact is not null");
		       }
		       else {
				   super.setVue("BasicContact");
		    	   super.goTo("ContactsUI");
		    	   ConfirmMessageUI.setParams(Integer.toString(res));
		    	   super.openPopUp("Your Contact has been modified","");
		    	   super.setCurrentPost(null);
		       }
	       } catch (NumberFormatException nfe) {
	    		this.errorMessage.setText("Problem of format");
	    	}
	   }
	   
	   
	   //-------------ROUTING FUNCTION
	   
	   public void backContact(ActionEvent event) {
			super.setVue("BasicContact");
		   super.goTo("ContactsUI");
	   }
	   
	 
	   

	   	   
	   
	       
   
}
