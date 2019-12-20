package buisnessLogic;



import java.io.IOException;

import dao.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BDEFacade {

	AbstractDAOFactory adf;

	/**
	 *
	 * @param username
	 * @param password
	 */

	public BDEFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);
	}

	public int create(User current_user , String nameBDE, String schoolBDE) {

		BDE obj = new BDE();
		obj.setNameBDE(nameBDE);
		obj.setSchoolBDE(schoolBDE);
        obj.setCreator(current_user);
        OracleDAO<BDE> bdeDao = adf.getBDEDAO();
        int res = bdeDao.create(obj);
        if(res>=0) {
        	System.out.println(nameBDE + schoolBDE + " created");
        	current_user.setCurrentBDE(obj.getIdBDE());
        	return res;
        }
        else {
        	System.out.println("Error while creating BDE");
        	return -1;
        }
	}
	
	
	
	public int modify(int idUser,String username,String emailuser,String passworduser,String firstname, String lastname,String phonenumberuser) {

		User obj = new User();
        obj.setUsername(username);
        obj.setEmailuser(emailuser);
        obj.setPassworduser(passworduser);
        obj.setFirstname(firstname);
        obj.setLastname(lastname);
        obj.setPhonenumberuser(phonenumberuser);
        OracleDAO<User> userDao = adf.getUserDAO();
        if(userDao.update(idUser,obj)) {
        	System.out.println("User modified");
        	this.connectedUser=obj;
            sendUserRooter();
        	return 1;
        }
        else {
        	System.out.println("Error while modifing user");
        	return -1;
        }
	}
	
	public int delete(User user) {

        OracleDAO<User> userDao = adf.getUserDAO();
        if(userDao.delete(user)) {
        	System.out.println("User deleted");
        	return 1;
        }
        else {
        	System.out.println("Error while deleting user");
        	return -1;
        }
	}

	public int login(String username, String password) {
		OracleDAO<User> userDao = this.adf.getUserDAO();
		User user = userDao.find(username);
		if (user.getId_user()==0) {
			System.out.println("user null");
			return -1;
		}
		else {
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
