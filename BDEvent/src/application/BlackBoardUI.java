package application;

import buisnessLogic.BlackBoard;
import buisnessLogic.BlackBoardFacade;
import buisnessLogic.Event;
import buisnessLogic.EventFacade;
import buisnessLogic.Post;
import buisnessLogic.PostFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class BlackBoardUI extends Routing implements Initializable {
	
	ArrayList<Post> list = new ArrayList<Post>();
	
	 @FXML
	 private Button homePage;
	 
	 @FXML
	 private Button newPost; 
	 @FXML
	 private Button seePost;
	 @FXML
	 private Button logoutButton;
	 @FXML
	 private Button modifyPost;
	 @FXML
	 private Button deletePost; 
	 @FXML
	 private ListView<String> postList;
	 @FXML
	 private Text textPostSelected;
	 @FXML
	 private Label titrePostSelected;
	 
	 

	@Override
	   public void initialize(URL location, ResourceBundle resources) {
		  User user=super.getCurrentUser();
		  BlackBoardFacade blackboardFacade = new BlackBoardFacade();
		  BlackBoard bb = new BlackBoard();
		  displayPost();
		  
	   }

	private void displayPost() {
		list.removeAll(list);
		User user=super.getCurrentUser();
		PostFacade postFacade = new PostFacade();
		list = postFacade.findAllPostBDE(user);
		ArrayList<String> titlePost = new ArrayList<String>();
		list.forEach((n)-> titlePost.add(n.getTitle_postBB())); 
		
		postList.getItems().addAll(titlePost);
	}
	   
	   public void homePage(ActionEvent event) {
		   super.goTo("HomePageUI");
	   }
	   
	   
	   public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.goTo("LoginUI");
	   }
	   
	   public void newPost(ActionEvent event) {
		   super.goTo("CreatePostUI");
	   }
	   
	   @FXML
		private void displaySelected(MouseEvent event) {
			PostFacade postFacade = new PostFacade();
			String post1 = postList.getSelectionModel().getSelectedItem();
			Post postSelected = postFacade.find(post1.split(" : ")[0]);
			if(post1 != null) {
				this.titrePostSelected.setText(postSelected.getTitle_postBB());
				this.textPostSelected.setText(postSelected.getText_postBB());
			}
			
	   }
		@FXML	
		private void seeMyPost(ActionEvent event) {
			System.out.println("debut see my post");
			postList.getItems().clear();
			list.removeAll(list);
			User user=super.getCurrentUser();
			PostFacade postFacade = new PostFacade();
			System.out.println("avant fct findalluser");
			list = postFacade.findAllPostUser(user);
			System.out.println("apres fct findalluser");
			ArrayList<String> titlePost = new ArrayList<String>();
			list.forEach((n)-> titlePost.add(n.getTitle_postBB())); 
			postList.getItems().addAll(titlePost);
			
			
		}
	   
	  
	
	       
   
}
