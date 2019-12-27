package application;

import buisnessLogic.BlackBoard;
import buisnessLogic.BlackBoardFacade;
import buisnessLogic.Event;
import buisnessLogic.EventFacade;
import buisnessLogic.Post;
import buisnessLogic.PostFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;
import buisnessLogic.UserFacade;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BlackBoardUI extends Routing implements Initializable {
	
	ArrayList<Post> valideList = new ArrayList<Post>();
	
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
	 @FXML
	 private Label publisherName;
	 @FXML
	 private Text textName;
	 @FXML
	 private Text stateText;
	 @FXML
	 private Label statePost;
	 
	//---------------------------------------------CONTROLEUR MANAGE BB--------------------
		
	 @FXML
	 private ListView<String> appliancePostList;
	 @FXML
	 private Button acceptButton;
	
	 
	 ArrayList<Post> waitingList = new ArrayList<Post>();
	 private static String mode = "Normal";
	 
	 //-----------------------------------------------------------------------------------
	 
	 

	@Override
	   public void initialize(URL location, ResourceBundle resources) {
		super.setCurrentPost(null);
		displayPost();
		buttonSeeBB.setVisible(false);
		modifyPost.setVisible(false);
		deletePost.setVisible(false);
		manageButton.setVisible(false);
		textName.setVisible(false);
		stateText.setVisible(false);
		
		if (super.getCurrentUser().isAdminOfHisBDE()){
			manageButton.setVisible(true);
			if (mode.contentEquals("Manage")) {
				System.out.println(mode +"ds if");
				displayWaitingPost();
				acceptButton.setVisible(false);
				seePost.setVisible(false);
				buttonSeeBB.setVisible(true);
				manageButton.setVisible(false);
				stateText.setVisible(false);
				
			}
		}
		
		
	   }

	private void displayPost() {
		valideList.removeAll(valideList);
		super.setCurrentPost(null);
		User user=super.getCurrentUser();
		PostFacade postFacade = new PostFacade();
		valideList = postFacade.findAllValidatePostBDE(user);
		ArrayList<String> titlePost = new ArrayList<String>();
		valideList.forEach((n)-> titlePost.add(n.getTitle_postBB())); 
		
		postList.getItems().addAll(titlePost);
	}
	   
	   public void homePage(ActionEvent event) {
		   this.mode= "Normal";
		   super.goTo("HomePageUI");
	   }
	   
	   
	   public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   this.mode= "Normal";
		   super.goTo("LoginUI");
	   }
	   
	   public void newPost(ActionEvent event) {
		   this.mode= "Normal";
		   super.goTo("CreatePostUI");
	   }
	   
	   @FXML
		private void displaySelectedList(MouseEvent event) {
		   	String state;
			PostFacade postFacade = new PostFacade();
			UserFacade userFacade = new UserFacade();
			String post1 = postList.getSelectionModel().getSelectedItem();
			Post postSelected = postFacade.find(post1.split(" : ")[0]);
			super.setCurrentPost(postSelected);
			if(post1 != null) {
				if (mode.contentEquals("Manage")|| mode.contentEquals("SeePost") ) {
					this.stateText.setVisible(true);
					if (postSelected.getState() == 0) {
						state = "Waiting state";
						this.statePost.setTextFill(Color.RED);}
					else {
						state = "Validate state";
						this.statePost.setTextFill(Color.DARKGREEN);}
					this.statePost.setText(state);
				}
				this.titrePostSelected.setText(postSelected.getTitle_postBB());
				this.textPostSelected.setText(postSelected.getText_postBB());
				textName.setVisible(true);
				User publisher = userFacade.findById(postSelected.getId_user_publisher());
				String namePublisher = publisher.getFirstname() +" " + publisher.getLastname();
				this.publisherName.setText(namePublisher);
				modifyPost.setVisible(false);
				deletePost.setVisible(false);
				if (super.getCurrentUser().isPublisherPost(Routing.getCurrentPost()) || super.getCurrentUser().isAdminOfHisBDE()){
					modifyPost.setVisible(true);
					deletePost.setVisible(true);
					if (mode.contentEquals("Manage")) {
						acceptButton.setVisible(false);
					}
				}
				
			}
			
	   }
	   
	   
	   
	   
		@FXML	
		private void seeMyPost(ActionEvent event) {
			BlackBoardUI.mode= "SeePost";
			super.setCurrentPost(null);
			this.titrePostSelected.setText("");
			this.textPostSelected.setText("");
			this.publisherName.setText("");
			this.textName.setVisible(false);
			postList.getItems().clear();
			valideList.removeAll(valideList);
			User user=super.getCurrentUser();
			PostFacade postFacade = new PostFacade();
			valideList = postFacade.findAllPostUser(user);
			ArrayList<String> titlePost = new ArrayList<String>();
			valideList.forEach((n)-> titlePost.add(n.getTitle_postBB())); 
			postList.getItems().addAll(titlePost);
			buttonSeeBB.setVisible(true);
			seePost.setVisible(false);
			
			
		}
	   
		@FXML	
		private void seeBB(ActionEvent event) {
			BlackBoardUI.mode= "Normal";

			super.goTo("BlackBoardUI");
		}
		
		@FXML	
		private void deleteSelected(ActionEvent event) {
			PostFacade postFacade = new PostFacade();
			postFacade.delete(super.getCurrentPost());
			postList.getItems().clear();
			valideList.removeAll(valideList);
			
			if (mode.equals("Manage")) {
				appliancePostList.getItems().clear();
				waitingList.removeAll(waitingList);
				
			}
			
			super.openPopUp("You're post has been deleted", "congratulations, you can continue to the application");
			displayPost();
			if (mode.contentEquals("Manage")) {
				displayWaitingPost();
			}
			this.titrePostSelected.setText("");
			this.textPostSelected.setText("");
			this.textName.setVisible(false);
			this.publisherName.setText("");
			
			
			 
		}
		
		@FXML	
		private void modifySelected(ActionEvent event) {
			BlackBoardUI.mode= "Normal";
			super.goTo("ModifyPostUI");
			 
		}
		
		 @FXML	
			private void manageBB(ActionEvent event) {
				BlackBoardUI.mode= "Manage";
				super.goTo("ManageBlackBoardUI");
				BlackBoardUI.mode= "Manage";

			}
		 
		
		//---------------------------------------------CONTROLEUR MANAGE BB--------------------
		 
		 private void displayWaitingPost() {
			 waitingList.removeAll(waitingList);
				super.setCurrentPost(null);
				User user=super.getCurrentUser();
				PostFacade postFacade = new PostFacade();
				waitingList = postFacade.findAllWaitingPostBDE(user);
				ArrayList<String> titlePost = new ArrayList<String>();
				waitingList.forEach((n)-> titlePost.add(n.getTitle_postBB())); 
				
				appliancePostList.getItems().addAll(titlePost);
			}
		 
		 
		 @FXML
			private void displaySelectedWaitingList(MouseEvent event) {
			 	String state;
			 	String color ="Color.";
				PostFacade postFacade = new PostFacade();
				UserFacade userFacade = new UserFacade();
				String post1 = appliancePostList.getSelectionModel().getSelectedItem();
				Post postSelected = postFacade.find(post1.split(" : ")[0]);
				super.setCurrentPost(postSelected);
				User publisher = userFacade.findById(postSelected.getId_user_publisher());
				String namePublisher = publisher.getFirstname() +" " + publisher.getLastname();
				System.out.println("state:"+ postSelected.getState());
				if(post1 != null) {
					if (postSelected.getState() == 0) {
						state = "Waiting state";
						this.statePost.setTextFill(Color.RED);}
					else {
						state = "Validate state";
						this.statePost.setTextFill(Color.DARKGREEN);}
					this.statePost.setText(state);
					
					this.publisherName.setText(namePublisher);
					this.titrePostSelected.setText(postSelected.getTitle_postBB());
					this.textPostSelected.setText(postSelected.getText_postBB());
					this.textName.setVisible(true);
					this.stateText.setVisible(true);
					if (super.getCurrentUser().isPublisherPost(Routing.getCurrentPost())){
						modifyPost.setVisible(true);
						deletePost.setVisible(true);
						acceptButton.setVisible(true);
					}
					
				}
				
		   }
		
		 public void acceptedPost(ActionEvent event) {
			   Post post=super.getCurrentPost();
			   PostFacade postFacade = new PostFacade();
		       System.out.println("accepatation de poste en cours");
		       int res = postFacade.accept(post.getId_postBB());
		       this.mode= "Manage";
			   super.goTo("ManageBlackBoardUI");
			   if (res < 0 ) {
		    	   //ERROR MESSAGE 
		       }
		       else {
		    	   ConfirmMessageUI.setParams(Integer.toString(res));
		    	   super.openPopUp("This post has been accepted", "you have accepted this post.");
		    	   super.setCurrentPost(null);
		       }
		   }
		 
		
		 
		 
		 
	       
   
}
