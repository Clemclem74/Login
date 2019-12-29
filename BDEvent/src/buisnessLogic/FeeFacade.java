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

public class FeeFacade {

	User connectedUser;


	AbstractDAOFactory adf;

	/**
	 *
	 * @param username
	 * @param password
	 */

	public FeeFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);

	}
	
	public int create(int idUser , String Title, String text, int amount) {
		System.out.println("create dans feefacade debut");
		Fee obj = new Fee();
		if ((Title.length()>0) && (text.length()>0) && amount>0){
			obj.setId_user_fee(idUser);
			obj.setTitle_fee(Title);
			obj.setComment_fee(text);
			obj.setAmount_fee(amount);
	        
	        OracleDAO<Fee> feeDao = adf.getFeeDAO();
       
	        int res = feeDao.create(obj);
	        
	        System.out.println("creation obj database");
	        if(res>=0) {
	        	System.out.println( "the fee : " + Title + " created");
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
	
	public int modify(int idFee, String Title, String text, int amount) {
		System.out.println("modify dans feefacade debut");
		Fee obj = new Fee();
		if ((Title.length()>0) && (text.length()>0) && amount>0){
			obj.setTitle_fee(Title);
			obj.setComment_fee(text);
			obj.setAmount_fee(amount);
	        
	        OracleDAO<Fee> feeDao = adf.getFeeDAO();
	        
	        
	        if(feeDao.update(obj)) {
	        	System.out.println("fee modified");
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
	

	public int delete(Fee fee) {
        OracleDAO<Fee> feeDao = adf.getFeeDAO();
        if(feeDao.delete(fee)) {
        	System.out.println("fee deleted");
        	return 1;
        }
        else {
        	System.out.println("Error while deleting fee");
        	return -1;
        }
	} 
	
	
	public ArrayList<Fee> findAllMyFee(User user) {
		OracleDAO<Fee> feeDao = this.adf.getFeeDAO();
		ArrayList<Fee> allfee = feeDao.findAllFeeByUser(user);
		if (allfee == null) {
			System.out.println("fee null facade");
			return null;
		}
		else {
			return allfee;
		}
	}
	
	public Fee find(String fee_name) {
		OracleDAO<Fee> feeDao = this.adf.getFeeDAO();
		Fee fee = feeDao.find(fee_name);
		if (fee == null) {
			System.out.println("fee null facade");
			return null;
		}
		else {
			return fee;
		}
	}



}
