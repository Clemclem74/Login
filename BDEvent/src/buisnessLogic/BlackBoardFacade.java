package buisnessLogic;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;

import dao.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BlackBoardFacade {

	User connectedUser;


	AbstractDAOFactory adf;

	/**
	 *
	 * @param username
	 * @param password
	 */

	public BlackBoardFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);

	}
	
	public ArrayList<Post> findAll(User user) {
		OracleDAO<Post> postDao = this.adf.getPostDAO();
		ArrayList<Post> allpost = postDao.findAllPostByBDE(user);
		if (allpost == null) {
			System.out.println("event null facade");
			return null;
		}
		else {
			return allpost;
		}
	}

	
//
//	public int register(String username,String emailuser,String passworduser,String firstname, String lastname,String phonenumberuser) {
//
//		User obj = new User();
//		String hashedpassword = hash(passworduser);
//
//
//        obj.setUsername(username);
//        obj.setEmailuser(emailuser);
//        obj.setPassworduser(hashedpassword);
//        obj.setFirstname(firstname);
//        obj.setLastname(lastname);
//        obj.setPhonenumberuser(phonenumberuser);
//        obj.setCurrentBDE(-1);
//
//        OracleDAO<User> userDao = adf.getUserDAO();
//
//        if(userDao.create(obj)>=0) {
//        	System.out.println("User created");
//        	return 1;
//        }
//        else {
//        	System.out.println("Error while creating user");
//        	return -1;
//        }
//	}
//
//
//	
//	public int join(User user, int idbde) {
//		OracleDAO<User> userDao = adf.getUserDAO();
//		user.setCurrentBDE(idbde);
//		if(userDao.update(user.getId_user(),user)) {
//        	System.out.println("User modified");
//        	this.connectedUser=user;
//            sendUserRooter();
//        	return 1;
//        }
//        else {
//        	System.out.println("Error while modifing user");
//        	return -1;
//        }
//	}
//	
//	
//
//	public int modify(int idUser,String username,String emailuser,String passworduser,String firstname, String lastname,String phonenumberuser,int idBDE) {
//		User obj = new User();
//		obj.setId_user(idUser);
//        obj.setUsername(username);
//        obj.setEmailuser(emailuser);
//        obj.setPassworduser(passworduser);
//        obj.setFirstname(firstname);
//        obj.setLastname(lastname);
//        obj.setPhonenumberuser(phonenumberuser);
//        obj.setCurrentBDE(idBDE);
//        OracleDAO<User> userDao = adf.getUserDAO();
//        if(userDao.update(idUser,obj)) {
//        	System.out.println("User modified");
//        	this.connectedUser=obj;
//            sendUserRooter();
//        	return 1;
//        }
//        else {
//        	System.out.println("Error while modifing user");
//        	return -1;
//        }
//	}
//
//	public int delete(User user) {
//
//        OracleDAO<User> userDao = adf.getUserDAO();
//        if(userDao.delete(user)) {
//        	System.out.println("User deleted");
//        	return 1;
//        }
//        else {
//        	System.out.println("Error while deleting user");
//        	return -1;
//        }
//	}
//
//	public int login(String username, String password) {
//		OracleDAO<User> userDao = this.adf.getUserDAO();
//		User user = userDao.find(username);
//		if (user.getId_user()==0) {
//			System.out.println("user null");
//			return -1;
//		}
//		else {
//			if(user.getPassworduser().equals(hash(password))) {
//				System.out.println(user.getUsername() +" Connected");
//				this.connectedUser = user;
//				sendUserRooter();
//				return 1;
//			}
//			else {
//				//COMMENT JE FAIT ??
//				System.out.println(user.getPassworduser() + " Email or Password Incorrect");
//				return -1;
//			}
//		}
//	}
//
//	
//	public User findById(int iduser) {
//		OracleDAO<User> userDao = this.adf.getUserDAO();
//		User user = userDao.findById(iduser);
//		if (user.getId_user()==0) {
//			System.out.println("user null facade");
//			return null;
//		}
//		else {
//			return user;
//		}
//	}
//
//
//
//	private void sendUserRooter() {
//		Routing.setCurrentUser(this.connectedUser);
//	}
//
//
//	public void sendError() {
//		// TODO - implement LoginFacade.sendError
//		throw new UnsupportedOperationException();
//	}
//	
//	public void setBDEnull(User user) {
//		modify(user.getId_user(),user.getUsername(),user.getEmailuser(),user.getPassworduser(),user.getFirstname(), user.getLastname(),user.getPhonenumberuser(),-1);
//	}
//	
//	
//
//	/**
//	 *
//	 * @param user
//	 */
//	public void sendUser(User user) {
//		// TODO - implement LoginFacade.sendUser
//		throw new UnsupportedOperationException();
//	}
//
//	/**
//	 *
//	 * @param user
//	 * @param password
//	 */
//	public void verifyInformation(User user, String password) {
//		// TODO - implement LoginFacade.verifyInformation
//		throw new UnsupportedOperationException();
//	}
//
//	public int getIdUserConnected() {
//		// TODO - implement LoginFacade.getIdUserConnected
//		throw new UnsupportedOperationException();
//	}
//
//	public String getUsernameConnected() {
//		// TODO - implement LoginFacade.getUsernameConnected
//		throw new UnsupportedOperationException();
//	}
//
//	public String getFirstNameConnected() {
//		// TODO - implement LoginFacade.getFirstNameConnected
//		throw new UnsupportedOperationException();
//	}
//
//	public String getLastNameConnected() {
//		// TODO - implement LoginFacade.getLastNameConnected
//		throw new UnsupportedOperationException();
//	}
//
//	public String getEmailUserConnected() {
//		// TODO - implement LoginFacade.getEmailUserConnected
//		throw new UnsupportedOperationException();
//	}
//
//	public String getPhoneNumberUserConnected() {
//		// TODO - implement LoginFacade.getPhoneNumberUserConnected
//		throw new UnsupportedOperationException();
//	}

}
