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
	


	public void sendError() {
		// TODO - implement LoginFacade.sendError
		throw new UnsupportedOperationException();
	}
	

	

}
