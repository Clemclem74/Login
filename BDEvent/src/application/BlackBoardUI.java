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
	 private Button manageButton;
	 @FXML
	 private Button newPost; 
	 @FXML
	 private Button seePost;
	 @FXML
	 private Button buttonSeeBB;
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
		super.setCurrentPost(null);
		displayPost();
		buttonSeeBB.setVisible(false);
		modifyPost.setVisible(false);
		deletePost.setVisible(false);
		manageButton.setVisible(false);
		if (super.getCurrentUser().isAdminOfHisBDE()){
			manageButton.setVisible(true);
		}
		
	   }

	private void displayPost() {
		list.removeAll(list);
		super.setCurrentPost(null);
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
			super.setCurrentPost(postSelected);
			if(post1 != null) {
				this.titrePostSelected.setText(postSelected.getTitle_postBB());
				this.textPostSelected.setText(postSelected.getText_postBB());
				if (super.getCurrentUser().isPublisherPost(Routing.getCurrentPost())){
					modifyPost.setVisible(true);
					deletePost.setVisible(true);
				}
				
			}
			
	   }
		@FXML	
		private void seeMyPost(ActionEvent event) {
			super.setCurrentPost(null);
			this.titrePostSelected.setText("");
			this.textPostSelected.setText("");
			postList.getItems().clear();
			list.removeAll(list);
			User user=super.getCurrentUser();
			PostFacade postFacade = new PostFacade();
			list = postFacade.findAllPostUser(user);
			ArrayList<String> titlePost = new ArrayList<String>();
			list.forEach((n)-> titlePost.add(n.getTitle_postBB())); 
			postList.getItems().addAll(titlePost);
			buttonSeeBB.setVisible(true);
			seePost.setVisible(false);
			
			
		}
	   
		@FXML	
		private void seeBB(ActionEvent event) {
			super.goTo("BlackBoardUI");
		}
		
		@FXML	
		private void deleteSelected(ActionEvent event) {
			PostFacade postFacade = new PostFacade();
			postFacade.delete(super.getCurrentPost());
			postList.getItems().clear();
			list.removeAll(list);
			super.openPopUp("You're post has been deleted", "congratulations, you can continue to the application");
			 displayPost();
			 this.titrePostSelected.setText("");
			this.textPostSelected.setText("");
			
			 
		}
		
		@FXML	
		private void modifySelected(ActionEvent event) {
			super.goTo("ModifyPostUI");
			 
		}
		
		@FXML	
		private void manageBB(ActionEvent event) {
			super.goTo("ManageBlackBoardUI");
		}
		
		//---------------------------------------------CONTROLEUR MANAGE BB--------------------
		
		 @FXML
		 private ListView<String> appliancePostList;
		 @FXML
		 private Button acceptButton;
		 @FXML
		 private Button refusedButton;
		 
		 
		 
		 
	       
   
}
