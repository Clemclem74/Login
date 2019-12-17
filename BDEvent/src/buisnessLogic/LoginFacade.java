package buisnessLogic;



import java.io.IOException;

import dao.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginFacade {

	User connectedUser;


	AbstractDAOFactory adf;

	/**
	 *
	 * @param username
	 * @param password
	 */

	public LoginFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);


	}

	public int register(String username,String emailuser,String passworduser,String firstname, String lastname,String phonenumberuser) {

		User obj = new User();

        obj.setUsername(username);
        obj.setEmailuser(emailuser);
        obj.setPassworduser(passworduser);
        obj.setFirstname(firstname);
        obj.setLastname(lastname);
        obj.setPhonenumberuser(phonenumberuser);

        OracleDAO<User> userDao = adf.getUserDAO();

        if(userDao.create(obj)) {
        	System.out.println("User created");
        	return 1;
        }
        else {
        	System.out.println("Error while creating user");
        	return -1;
        }
	}
	
	
	
	public int update(String username,String emailuser,String passworduser,String firstname, String lastname,String phonenumberuser) {

		User obj = new User();

        obj.setUsername(username);
        obj.setEmailuser(emailuser);
        obj.setPassworduser(passworduser);
        obj.setFirstname(firstname);
        obj.setLastname(lastname);
        obj.setPhonenumberuser(phonenumberuser);

        OracleDAO<User> userDao = adf.getUserDAO();

        if(userDao.create(obj)) {
        	System.out.println("User created");
        	return 1;
        }
        else {
        	System.out.println("Error while creating user");
        	return -1;
        }
	}
	
	

	public int login(String username, String password) {
		OracleDAO<User> userDao = this.adf.getUserDAO();
		User user = userDao.find(username);

		if(user.getPassworduser().equals(password)) {
			System.out.println(user.getUsername() +" Connected");
			this.connectedUser = user;
			sendUserRooter();
			return 1;
		}
		else {
			//COMMENT JE FAIT ??
			System.out.println(user.getPassworduser() + " Email or Password Incorrect");
			return -1;
		}
	}



	private void sendUserRooter() {
		Routing.setCurrentUser(this.connectedUser);
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
