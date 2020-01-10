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

public class UserUI extends Routing implements Initializable {
		@FXML
	   private Button loginButton;
	  
	   @FXML
	   private TextField emailField;
	   
	   @FXML
	   private PasswordField passwordField;
	   
	   @FXML
	   private Label errorMessage;
	  //------------------------ MODIFY USER CONTROLER : 
	   
	   @FXML
		private Button registerButtonM;
		@FXML
		private TextField firstNameField;
		@FXML
		private TextField lastNameField;
		@FXML
		private TextField usernameField;
	   @FXML
	   private TextField emailFieldM;
	   @FXML
	   private TextField phoneNumberField;
	   @FXML
	   private Button saveButton;
	   
	   //------------------------------------------
	  
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   if (super.getVue().equals("modifyUser")) {
			   User user = super.getCurrentUser();
			   firstNameField.setText(user.getFirstname());
			   emailFieldM.setText(user.getEmailuser());
			   lastNameField.setText(user.getLastname());
			   usernameField.setText(user.getUsername());
			   phoneNumberField.setText(user.getPhonenumberuser());
			   
		   } else if ( super.getVue().equals("DeleteUser")){
			   User user = super.getCurrentUser();
		   }else {
	       // TODO (don't really need to do anything here).
		   }
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void loginAction(ActionEvent event) {
	       
	       UserFacade loginFacade = new UserFacade();
	       
	       int res = loginFacade.login(emailField.getText(),passwordField.getText());
	       if (res>0) {
	    	   Routing root = new Routing();
	    	   root.goTo("HomePageUI");
	       }
	       else {
	    	   this.passwordField.clear();
	    	   this.errorMessage.setText("Wrong Password or Username");
	       }
	   }
	   
	   //Display an error message when the informations don't allow the user to connect
	   //The email stay on the field.
	   public void wrongConnection() {
		   this.errorMessage.setText("Wrong email or wrong Password");
	       UserFacade loginFacade = new UserFacade();
	       loginFacade.login(emailField.getText(),passwordField.getText());
	   }	
	   
	   // When user click on Register
	   // this method will be called. And redirect the user to the register scene.
	   public void goToregister(ActionEvent event) {
		   Routing root = new Routing();
		   root.goTo("RegisterUI");
	   }
	   
	   
	   //CONSTRUCTEUR REGSITER -----------------------
	   
		@FXML
		private Button registerButton;
		@FXML
		private TextField firstNameFieldR;
		@FXML
		private TextField lastNameFieldR;
		@FXML
		private TextField usernameFieldR;
		@FXML
		private TextField emailFieldR;
		@FXML
		private TextField phoneNumberFieldR;
		@FXML
		private PasswordField passwordFieldR;
		@FXML
		private PasswordField passwordField2R;
		@FXML
		private Label errorMessageR;
	   
		public void registerAction(ActionEvent event) {
			if (passwordFieldR.getText().length()>0 && passwordField2R.getText().length()>0 && firstNameFieldR.getText().length()>0 && lastNameFieldR.getText().length()>0 && usernameFieldR.getText().length()>0 && emailFieldR.getText().length()>0 && phoneNumberFieldR.getText().length()>0) {
				if (passwordFieldR.getText().contentEquals(passwordField2R.getText())) {
					if (isValidEmail(emailFieldR.getText())) {

						UserFacade userFacade = new UserFacade();
						userFacade.register(usernameFieldR.getText(),emailFieldR.getText(),passwordFieldR.getText(),firstNameFieldR.getText(),lastNameFieldR.getText(),phoneNumberFieldR.getText());
						super.goTo("LoginUI");


					}
					else {
						this.errorMessage.setText("The email does not match with email type");
					}
				}
				else {
					this.errorMessage.setText("The two password are not the same");
				}
			}
				else {
					this.errorMessage.setText("Please make sure all fill are not null");
				}
			}
		

			public boolean isValidEmail(String email) {
				String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
				java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
				java.util.regex.Matcher m = p.matcher(email);
				return m.matches();
			}

			public boolean isValidPhone(String phone) {
				
				 String ePattern = "^((\\\\+)33|0)[1-9](\\\\d{2}){4}$";
					java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
					java.util.regex.Matcher m = p.matcher(phone);
					return m.matches();
			}
			
			//--------------Modify user controler : 
			
			 public void saveAction(ActionEvent event) {
				   UserFacade userFacade = new UserFacade();
				   System.out.println(super.getCurrentUser().getPassworduser());
			       int res = userFacade.modify(super.getCurrentUser().getId_user(),usernameField.getText(),emailFieldM.getText(),super.getCurrentUser().getPassworduser(),firstNameField.getText(),lastNameField.getText(),phoneNumberField.getText(),super.getCurrentUser().getCurrentBDE());
			       super.goTo("HomePageUI");
			   }
			 
			 //Delete user controller : 
			 
			 @FXML
				private Button discardDeleteButton;
				@FXML
				private Button deleteAccountButton;
				
				 public void discardDelete(ActionEvent event) {
				       super.goTo("HomePageUI");
				   }
				   
				   // When user click on deleteAccountButton
				   // this method will be called.
				   public void deleteAccount(ActionEvent event) {
					   UserFacade userFacade = new UserFacade();
					   System.out.println(super.getCurrentUser().getPassworduser());
				       int res = userFacade.delete(super.getCurrentUser());
				       super.goTo("LoginUI");
				   }
				
}
