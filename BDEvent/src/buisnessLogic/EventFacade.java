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

public class EventFacade {

	User connectedUser;


	AbstractDAOFactory adf;


	public EventFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);

	}


	public ArrayList<Event> findAll() {
		OracleDAO<Event> eventDao = this.adf.getEventDAO();
		ArrayList<Event> event = eventDao.findAll();
		if (event == null) {
			System.out.println("event null facade");
			return null;
		}
		else {
			return event;
		}
	}
	
	public Event find(String event_name) {
		OracleDAO<Event> eventDao = this.adf.getEventDAO();
		Event event = eventDao.find(event_name);
		if (event == null) {
			System.out.println("event null facade");
			return null;
		}
		else {
			return event;
		}
	}
	
	public void delete(Event event) {
		OracleDAO<Event> eventDao = this.adf.getEventDAO();
		Boolean bool = eventDao.delete(event);
		if (bool == false) {
			System.out.println("event null facade");
		}
	}
	
	public void create(Event event) {
		OracleDAO<Event> eventDao = this.adf.getEventDAO();
		int bool = eventDao.create(event);
		if (bool == -1) {
			System.out.println("event null facade");
		}
	}
	


	public void sendError() {
		// TODO - implement LoginFacade.sendError
		throw new UnsupportedOperationException();
	}
	

	

}
