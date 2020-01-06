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

public class ActivityFacade {

	User connectedUser;


	AbstractDAOFactory adf;


	public ActivityFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);

	}


	public ArrayList<BDEActivity> findAll() {
		OracleDAO<BDEActivity> BDEActivityDao = this.adf.getBDEActivityDAO();
		ArrayList<BDEActivity> acti = BDEActivityDao.findAll();
		if (acti == null) {
			System.out.println("BDEActivity null facade");
			return null;
		}
		else {
			return acti;
		}
	}
	
	public ArrayList<StaffActivity> findAllStaff(Event event) {
		OracleDAO<StaffActivity> StaffActivityDao = this.adf.getStaffActivityDAO();
		
		ArrayList<Integer> id_acti = StaffActivityDao.findAllStaff(event.getId_event());
		
		if (id_acti == null) {
			System.out.println("Activity null facade");
			return null;
		}
		else {
		
			ArrayList<StaffActivity> acti = new ArrayList<StaffActivity>();
			
			id_acti.forEach((n)->{
				
				StaffActivity aActi = new StaffActivity();
				aActi = StaffActivityDao.findById(n);
				acti.add(aActi);
			});
			
			return acti;
		}
	}
	
	
	public BDEActivity find(int id) {
		OracleDAO<BDEActivity> BDEActivityDao = this.adf.getBDEActivityDAO();
		BDEActivity acti = BDEActivityDao.findById(id);
		if (acti == null) {
			System.out.println("BDEActivity null facade");
			return null;
		}
		else {
			return acti;
		}
	}
	
	public StaffActivity findStaff(int id) {
		OracleDAO<StaffActivity> StaffActivityDao = this.adf.getStaffActivityDAO();
		StaffActivity acti = StaffActivityDao.findById(id);
		if (acti == null) {
			System.out.println("StaffActivity null facade");
			return null;
		}
		else {
			return acti;
		}
	}
	
	
	public void deleteStaff(StaffActivity acti) {
		OracleDAO<StaffActivity> actiDao = this.adf.getStaffActivityDAO();
		Boolean bool = actiDao.delete(acti);
		if (bool == false) {
			System.out.println("event null facade");
		}
	}
	
	
	
	public int count_users_BDEacti(BDEActivity activity) {
		OracleDAO<BDEActivity> BDEActivityDao = this.adf.getBDEActivityDAO();
		int acti = BDEActivityDao.count_users_BDEacti(activity.getId_activity());
		
		return acti;
		
	}
	
	public int count_users_Staffacti(StaffActivity activity) {
		OracleDAO<StaffActivity> StaffActivityDao = this.adf.getStaffActivityDAO();
		int acti = StaffActivityDao.count_users_Staffacti(activity.getId_activity());
		
		return acti;
		
	}
	
	public void delete(BDEActivity acti) {
		OracleDAO<BDEActivity> actiDao = this.adf.getBDEActivityDAO();
		Boolean bool = actiDao.delete(acti);
		if (bool == false) {
			System.out.println("event null facade");
		}
	}
	
	public void create(BDEActivity acti) {
		OracleDAO<BDEActivity> activityDao = this.adf.getBDEActivityDAO();
		int bool = activityDao.create(acti);
		if (bool == -1) {
			System.out.println("event null facade");
		}
	}
	
	public void createStaff(StaffActivity acti,Event event) {
		OracleDAO<StaffActivity> activityDao = this.adf.getStaffActivityDAO();
		int bool = activityDao.create(acti,event);
		if (bool == -1) {
			System.out.println("event null facade");
		}
	}
	
	
	
	public void modify(BDEActivity acti,int id) {
		OracleDAO<BDEActivity> BDEActivityDao = this.adf.getBDEActivityDAO();
		boolean bool = BDEActivityDao.update(id,acti);
		if (!bool) {
			System.out.println("event null facade");
		}
	}
	
	
	public void modifyStaff(StaffActivity acti,int id) {
		OracleDAO<StaffActivity> StaffActivityDao = this.adf.getStaffActivityDAO();
		boolean bool = StaffActivityDao.update(id,acti);
		if (!bool) {
			System.out.println("acti null facade");
		}
	}
	
	
	public int join(int id_acti,User user) {
		OracleDAO<BDEActivity> BDEActivityDao = this.adf.getBDEActivityDAO();
		int bool = BDEActivityDao.join(BDEActivityDao.findById(id_acti),user);
		if (bool == -1) {
			System.out.println("event null facade");
			return -1;
		}
		if(bool == -2) {
			return -2;
		}
		return 0;
	}
	
	
	public int joinStaff(int id_acti,Event event,User user) {
		OracleDAO<StaffActivity> StaffActivityDao = this.adf.getStaffActivityDAO();
		int bool = StaffActivityDao.joinStaff(id_acti,event,user);
		if (bool == -1) {
			System.out.println("event null facade");
			return -1;
		}
		if(bool == -2) {
			return -2;
		}
		return 0;
	}
	
	
	
	public int leave(int id,Event event) {
		OracleDAO<Event> eventDao = this.adf.getEventDAO();
		boolean bool = eventDao.leave(id,event);
		if (bool == false) {
			System.out.println("event null facade");
			return -1;
		}
		return 0;
	}
	
	
	public ArrayList<Event> getEventbyUser(User user) {
		OracleDAO<Event> eventDao = this.adf.getEventDAO();
		
		ArrayList<Integer> event_id = eventDao.getEventByUser(user);
		ArrayList<Event> event = new ArrayList<Event>();
		event_id.forEach((n) -> System.out.println(n));
		event_id.forEach((n) -> event.add(eventDao.findById(n)));
		
		return event;
	}


	public void sendError() {
		// TODO - implement LoginFacade.sendError
		throw new UnsupportedOperationException();
	}
	

	

}
