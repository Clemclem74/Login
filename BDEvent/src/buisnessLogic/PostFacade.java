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

public class PostFacade {

	User connectedUser;


	AbstractDAOFactory adf;

	/**
	 *
	 * @param username
	 * @param password
	 */

	public PostFacade() {
		this.adf=AbstractDAOFactory.getFactory(AbstractDAOFactory.ORACLE_DAO_FACTORY);

	}

	public int create(int idUser , String Title, String text, int idBde) {
		System.out.println("create dans postfacade debut");
		Post obj = new Post();
		if ((Title.length()>0) && (text.length()>0)){
			obj.setId_user_publisher(idUser);
			obj.setTitle_postBB(Title);
			obj.setText_postBB(text);
			obj.setId_BDE_postBB(idBde);
	        
	        OracleDAO<Post> postDao = adf.getPostDAO();
       
	        int res = postDao.create(obj);
	        
	        System.out.println("creation obj database");
	        if(res>=0) {
	        	System.out.println( "the post : " + Title + " created");
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
	
	public int delete(Post post) {
        OracleDAO<Post> postDao = adf.getPostDAO();
        if(postDao.delete(post)) {
        	System.out.println("post deleted");
        	return 1;
        }
        else {
        	System.out.println("Error while deleting post");
        	return -1;
        }
	} 
	
	public int modify(int idPost, int idUser , String Title, String text, int idBde) {
		System.out.println("modify dans postfacade debut");
		Post obj = new Post();
		if ((Title.length()>0) && (text.length()>0)){
			obj.setId_postBB(idPost);
			obj.setId_user_publisher(idUser);
			obj.setTitle_postBB(Title);
			obj.setText_postBB(text);
			obj.setId_BDE_postBB(idBde);
	        
	        OracleDAO<Post> postDao = adf.getPostDAO();
	        
	        if(postDao.update(obj)) {
	        	System.out.println("post modified");
	        	return 1;
	        }
	        else {
	        	System.out.println("Error while modifing post");
	        	return -1;
	        }
		}
		else {
			return -1;
		}
	}
	
	public int accept(int idPost) {
		System.out.println("accept dans postfacade debut");
		Post obj = new Post();
		
		obj.setId_postBB(idPost);
		
        
        OracleDAO<Post> postDao = adf.getPostDAO();
        System.out.println("apres creation dao");
        
        if(postDao.acceptPost(obj)) {
        	System.out.println("post accepted");
        	return 1;
        }
        else {
        	System.out.println("Error while accepting post");
        	return -1;
        }
	}
	
	public ArrayList<Post> findAllPostBDE(User user) {
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
	
	public ArrayList<Post> findAllValidatePostBDE(User user) {
		
		ArrayList<Post> allpost = findAllPostBDE(user);
		ArrayList<Post> validatePost = new ArrayList<Post>();
		
		for ( int i = 0 ; i < allpost.size(); i++) {
			if (allpost.get(i).getState() == 1) {
				validatePost.add(allpost.get(i));
			}
		}
		System.out.println(validatePost);
		return validatePost;
		
	}
	
public ArrayList<Post> findAllWaitingPostBDE(User user) {
		
		ArrayList<Post> allpost = findAllPostBDE(user);
		ArrayList<Post> waitingPost = new ArrayList<Post>();
		for ( int i = 0 ; i < allpost.size(); i++) {
			if (allpost.get(i).getState() == 0) {
				waitingPost.add(allpost.get(i));
			}
		}
		
		return waitingPost;
		
	}
	
	public ArrayList<Post> findAllPostUser(User user) {
		OracleDAO<Post> postDao = this.adf.getPostDAO();
		ArrayList<Post> allpost = postDao.findAllPostByUser(user);
		if (allpost == null) {
			System.out.println("event null facade");
			return null;
		}
		else {
			return allpost;
		}
	}
	
	public Post find(String post_name) {
		OracleDAO<Post> postDao = this.adf.getPostDAO();
		Post post = postDao.find(post_name);
		if (post == null) {
			System.out.println("event null facade");
			return null;
		}
		else {
			return post;
		}
	}

}
