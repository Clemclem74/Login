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

public class ContactFacade {

	User connectedUser;


	AbstractDAOFactory adf;

	/**
	 *
	 * @param username
	 * @param password
	 */

	public ContactFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);

	}
	
	public int create(String name , String company, String phoneNumber, String informations, int idTeam) {
		System.out.println("create dans contactFacade debut");
		TeamFacade teamFacade = new TeamFacade();
		Contact obj = new Contact();
		if ((name.length()>0) && (company.length()>0) && informations.length()>0){
			obj.setNameContact(name);
			obj.setCompany(company);
			obj.setInformationsContact(informations);
			obj.setPhoneNumberContact(phoneNumber);
			obj.setTeamContact(teamFacade.findById(idTeam));
	        
	        OracleDAO<Contact> contactDao = adf.getContactDAO();
       
	        int res = contactDao.create(obj);
	        
	        System.out.println("creation obj database");
	        if(res>=0) {
	        	System.out.println( "the contact : " + company + " created");
	        	return res;
	        }
	        else {
	        	System.out.println("Error while creating the post");
	        	return -1;
	        }
		}
		else {
			return -1;
		}
       
	}
	
	public int modify(int idContact, String name, String company, String phonenumber, String informations, Team team) {
		System.out.println("modify dans feefacade debut");
		Contact obj = new Contact();
		if ((name.length()>0) && (company.length()>0) && (phonenumber.length()>0) && (informations.length()>0)){
			obj.setCompany(company);
			obj.setIdContact(idContact);
			obj.setInformationsContact(informations);
			obj.setNameContact(name);
			obj.setPhoneNumberContact(phonenumber);
			obj.setTeamContact(team);

	        
	        OracleDAO<Contact> contactDao = adf.getContactDAO();
	        
	        
	        if(contactDao.update(obj)) {
	        	System.out.println("Contact modified");
	        	return 1;
	        }
	        else {
	        	System.out.println("Error while modifing fee");
	        	return -1;
	        }
		}
		else {
			return -1;
		}
	}
	

	public int delete(Contact contact) {
        OracleDAO<Contact> contactDao = adf.getContactDAO();
        if(contactDao.delete(contact)) {
        	System.out.println("Contact deleted");
        	return 1;
        }
        else {
        	System.out.println("Error while deleting Contact");
        	return -1;
        }
	} 
	
	
	public ArrayList<Contact> findAllMyContact(User user) {
		OracleDAO<Contact> contactDao = this.adf.getContactDAO();
		TeamMemberFacade teamMemberFacade = new TeamMemberFacade();
		TeamFacade teamFacade = new TeamFacade();
		ArrayList<Integer> teams = teamMemberFacade.findUserTeam(user.getId_user());
		ArrayList<Contact> allcontact = new ArrayList<Contact>();
		ArrayList<Contact> allcontactteam = new ArrayList<Contact>();
		for(int i=0;i<teams.size();i++) {
			allcontactteam = contactDao.findAllContactByTeam(teamFacade.findById(teams.get(i)));
			for(int j=0;j<allcontactteam.size();j++) {
				allcontact.add(allcontactteam.get(j));
			}
		}
		if (allcontact.size() == 0) {
			System.out.println("contact null facade");
			return null;
		}
		else {
			return allcontact;
		}
	}
	
	public Contact findById(int id) {
		OracleDAO<Contact> contactDao = this.adf.getContactDAO();
		Contact contact = contactDao.findById(id);
		if (contact == null) {
			System.out.println("contact null facade");
			return null;
		}
		else {
			return contact;
		}
	}




	


}
