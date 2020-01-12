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
import java.util.Date;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;

import dao.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MeetingFacade {
	
	User connectedUser;
	
	AbstractDAOFactory adf;
	
	public MeetingFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);

	}
	
	public int create(int idUser, String title, String date, int idBde) {
		Meeting obj = new Meeting();
		
		obj.setPublisher_meeting(idUser);
		obj.setTitle(title);
		obj.setMeeting_date(date);
		obj.setId_bde_meeting(idBde);
		
		OracleDAO<Meeting> meetingDao = adf.getMeetingDAO();
		
		int res = meetingDao.create(obj);
		System.out.println("creation obj database");
        if(res>=0) {
        	System.out.println( "the meeting : " + title + " is created");
        	return res;
        }
        else {
        	System.out.println("Error while creating the meeting");
        	return -1;
        }
		
	}
	
	public int delete(Meeting meeting) {
        OracleDAO<Meeting> meetingDao = adf.getMeetingDAO();
        if(meetingDao.delete(meeting)) {
        	System.out.println("meeting deleted");
        	return 1;
        }
        else {
        	System.out.println("Error while deleting meeting");
        	return -1;
        }
	}
	
	public int modify(int idmeeting, int idUser , String title, String date_meeting, int idBde) {
		System.out.println("modify dans meeting facade debut");
		Meeting obj = new Meeting();
		
		obj.setId_meeting(idmeeting);
		obj.setPublisher_meeting(idUser);
		obj.setTitle(title);
		obj.setMeeting_date(date_meeting);
		obj.setId_bde_meeting(idBde);
        
        OracleDAO<Meeting> meetingDao = adf.getMeetingDAO();
        
        if(meetingDao.update(obj)) {
        	System.out.println("meeting modified");
        	return 1;
        }
        else {
        	System.out.println("Error while modifing meeting");
        	return -1;
        }
	}
	
	public ArrayList<Meeting> findAllMeetingBDE(User user){
		OracleDAO<Meeting> meetingDao = this.adf.getMeetingDAO();
		ArrayList<Meeting> allMeeting = meetingDao.findAllMeetingByBDE(user);
		if(allMeeting == null) {
			System.out.println("no poll found");
			return null;
		}
		else {
			System.out.println(allMeeting);
			return allMeeting;
		}
	}
	
	public Meeting find(String meeting_name) {
		OracleDAO<Meeting> meetingDao = this.adf.getMeetingDAO();
		Meeting meeting = meetingDao.find(meeting_name);
		if(meeting == null) {
			System.out.println("meeting null find");
			return null;
		}
		else {
			return meeting;
		}
	}
	
	
	

}
