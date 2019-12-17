package buisnessLogic;



import java.io.IOException;

import dao.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginFacade {

	User connectedUser;
	AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	

	AbstractDAOFactory adf;
	
	/**
	 * 
	 * @param username
	 * @param password
	 */
	public LoginFacade() {
		AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);
		
	}
	
	private void register(String username,String lastname,String firstname,String emailuser, String passworduser,String phonenumberuser) {
		
		User obj = new User();

        obj.setUsername(username);
        obj.setFirstname(firstname);
        obj.setLastname(lastname);
        obj.setEmailuser(emailuser);
        obj.setPassworduser(passworduser);
        obj.setPhonenumberuser(phonenumberuser);
        
        OracleDAO<User> userDao = adf.getUserDAO();
        
        if(userDao.create(obj)) {
        	System.out.println("User created");
        }
        else {
        	System.out.println("Error while creating user");
        }
        
	}
	
	private void login(String username, String password) {
		OracleDAO<User> userDao = adf.getUserDAO();
		User user = userDao.find(username);
	
		if(user.getPassworduser().equals(password)) {
			System.out.println(user.getUsername() +" Connected");
			this.connectedUser = user;
			gotoHomePage();
		}
		else {
			//COMMENT JE FAIT ??
			System.out.println(user.getPassworduser() + " Email or Password Incorrect");
		}
	}
	

	private void gotoHomePage() {
		Routing root = new Routing();
		root.login_action(this.connectedUser);
		
	}
	
	

	public void sendError() {
		// TODO - implement LoginFacade.sendError
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user
	 */
	public void sendUser(User user) {
		// TODO - implement LoginFacade.sendUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user
	 * @param password
	 */
	public void verifyInformation(User user, String password) {
		// TODO - implement LoginFacade.verifyInformation
		throw new UnsupportedOperationException();
	}

	public int getIdUserConnected() {
		// TODO - implement LoginFacade.getIdUserConnected
		throw new UnsupportedOperationException();
	}

	public String getUsernameConnected() {
		// TODO - implement LoginFacade.getUsernameConnected
		throw new UnsupportedOperationException();
	}

	public String getFirstNameConnected() {
		// TODO - implement LoginFacade.getFirstNameConnected
		throw new UnsupportedOperationException();
	}

	public String getLastNameConnected() {
		// TODO - implement LoginFacade.getLastNameConnected
		throw new UnsupportedOperationException();
	}

	public String getEmailUserConnected() {
		// TODO - implement LoginFacade.getEmailUserConnected
		throw new UnsupportedOperationException();
	}

	public String getPhoneNumberUserConnected() {
		// TODO - implement LoginFacade.getPhoneNumberUserConnected
		throw new UnsupportedOperationException();
	}

}