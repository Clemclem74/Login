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
	@FXML
	private Label errorMessage;



	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO (don't really need to do anything here).
	}

	// When user click on myButton
	// this method will be called.
	public void registerAction(ActionEvent event) {
		if (passwordField.getText().length()>0 && passwordField2.getText().length()>0 && firstNameField.getText().length()>0 && lastNameField.getText().length()>0 && usernameField.getText().length()>0 && emailField.getText().length()>0 && phoneNumberField.getText().length()>0) {
			if (passwordField.getText().contentEquals(passwordField2.getText())) {
				if (isValidEmail(emailField.getText())) {

					UserFacade userFacade = new UserFacade();
					userFacade.register(usernameField.getText(),emailField.getText(),passwordField.getText(),firstNameField.getText(),lastNameField.getText(),phoneNumberField.getText());
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

	}
