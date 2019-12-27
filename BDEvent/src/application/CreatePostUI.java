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

public class CreatePostUI extends Routing implements Initializable {
	//Declaration of component of the fxml file.
		@FXML
		private Button createPostButton;
		@FXML
		private Button backButton;
		@FXML
		private TextField titlePost;
		@FXML
		private TextArea textPost;
		@FXML
		private Label errorMessage;
		
	  
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void createPost(ActionEvent event) {
		   User user=super.getCurrentUser();
		   PostFacade postFacade = new PostFacade();
	       System.out.println("crationd de post en cours");
	       int res = postFacade.create( user.getId_user() ,titlePost.getText(),textPost.getText(),user.getCurrentBDE());
	       System.out.println("L'ID du nouveau post est le : " + res);
	       //userFacade.join(user, res);
		   if (res < 0 ) {
	    	   this.errorMessage.setText("Please make sure the post have a title and a text non null");
	       }
	       else {
	    	   super.goTo("BlackBoardUI");
	    	   ConfirmMessageUI.setParams(Integer.toString(res));
	    	   super.openPopUp("Your post has been created", "Your post has been added to list of waiting post, he will be visible when the administrator has valided it");
	    	   //this.idBDELabel.setText("8");
	       }
	   }
	   
	   public void backBB(ActionEvent event) {
		   super.goTo("BlackBoardUI");
	   }
}
