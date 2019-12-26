package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDEFacade;
import buisnessLogic.BlackBoard;
import buisnessLogic.BlackBoardFacade;
import buisnessLogic.Post;
import buisnessLogic.PostFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class ModifyPostUI extends Routing implements Initializable {
	//Declaration of component of the fxml file.
		@FXML
		private Button modifyButton;
		@FXML
		private Button backButton;
		@FXML
		private TextField titlePost;
		@FXML
		private TextArea textPost;
		
	  
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   Post post = super.getCurrentPost();
		   titlePost.setText(post.getTitle_postBB());
		   textPost.setText(post.getText_postBB());
		   
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void modifyPost(ActionEvent event) {
		   Post post=super.getCurrentPost();
		   PostFacade postFacade = new PostFacade();
	       System.out.println("modification de poste en cours");
	       int res = postFacade.modify(post.getId_postBB(),post.getId_user_publisher(),titlePost.getText(), textPost.getText(),post.getId_BDE_postBB());
		   super.goTo("BlackBoardUI");
		   if (res < 0 ) {
	    	   //ERROR MESSAGE 
	       }
	       else {
	    	   ConfirmMessageUI.setParams(Integer.toString(res));
	    	   super.openPopUp("Your post has been modified", "Your post has been added to list of waiting post, he will be visible when the administrator has valided it");
	    	   super.setCurrentPost(null);
	       }
	   }
	   
	   public void backBB(ActionEvent event) {
		   super.goTo("BlackBoardUI");
	   }
}
