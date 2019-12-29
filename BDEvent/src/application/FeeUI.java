package application;

import buisnessLogic.Event;
import buisnessLogic.EventFacade;
import buisnessLogic.Fee;
import buisnessLogic.FeeFacade;
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

public class FeeUI extends Routing implements Initializable {
	
	ArrayList<Fee> globalFeeList = new ArrayList<Fee>();
	
	 @FXML
	 private Button homePage;
	 @FXML
	 private Button trezButton;
	 @FXML
	 private Button newFee; 
	 @FXML
	 private Button logoutButton;
	 @FXML
	 private Button modifyFee;
	 @FXML
	 private Button deleteFee; 
	 @FXML
	 private ListView<String> feeList;
	 @FXML
	 private Text commentFeeSelected;
	 @FXML
	 private Label titleFeeSelected;
	 @FXML
	 private Label stateFee;
	 @FXML
	 private Text stateText;
	 @FXML
	 private Text amountText;
	 @FXML
	 private Label amountFee;

	 
	 //--------------------------------Controlleur pour create Fee
	 
	 	@FXML
		private Button createFeeButton;
		@FXML
		private Button backButton;
		@FXML
		private TextField titleFee;
		@FXML
		private TextField amountFeeField;
		@FXML
		private TextArea textFee;
		@FXML
		private Label errorMessage;
		
	 
	
	   public void initialize(URL location, ResourceBundle resources) {
		   
		  if ( super.getVue().contentEquals("CreateFee")) {
			  //NOTHING TO DO
		  } else 
			  
			  if( super.getVue().contentEquals("ModifyFee")) {
			 
			   Fee fee = super.getCurrentFee();
			   titleFee.setText(fee.getTitle_fee());
			   textFee.setText(fee.getComment_fee());
			   amountFeeField.setText(Integer.toString(fee.getAmount_fee()));
		  	}
		  
				  else /*si basic fee */ {
			   displayFee();
			    modifyFee.setVisible(false);
				deleteFee.setVisible(false);
				stateText.setVisible(false);
				amountText.setVisible(false);
			  }
	   }
			
		  
	   private void displayFee() {
		   	globalFeeList.removeAll(globalFeeList);
			super.setCurrentFee(null);
			User user=super.getCurrentUser();
			FeeFacade feeFacade = new FeeFacade();
			globalFeeList = feeFacade.findAllMyFee(user);
			ArrayList<String> titleFee = new ArrayList<String>();
			globalFeeList.forEach((n)-> titleFee.add(n.getTitle_fee())); 
			
			feeList.getItems().addAll(titleFee);
		}
	   
	   @FXML
	 		private void displaySelectedList(MouseEvent event) {
	 		   	String state;
	 			FeeFacade feeFacade = new FeeFacade();
	 			UserFacade userFacade = new UserFacade();
	 			String fee1 = feeList.getSelectionModel().getSelectedItem();
	 			Fee feeSelected = feeFacade.find(fee1.split(" : ")[0]);
	 			super.setCurrentFee(feeSelected);
	 			if(fee1 != null) {
	 				if (super.getVue().contentEquals("BasicFee")) {
	 					this.stateText.setVisible(true);
	 					this.amountText.setVisible(true);
	 					if (feeSelected.getState_fee() == 0) {
	 						state = "Not refund";
	 						this.stateFee.setTextFill(Color.RED);}
	 					else {
	 						state = "Already refund";
	 						this.stateFee.setTextFill(Color.DARKGREEN);}
	 					this.stateFee.setText(state);
	 				}
	 				this.titleFeeSelected.setText(feeSelected.getTitle_fee());
	 				this.commentFeeSelected.setText(feeSelected.getComment_fee());
	 				this.amountFee.setText(Integer.toString(feeSelected.getAmount_fee()));
	 				
	 				modifyFee.setVisible(true);
	 				deleteFee.setVisible(true);
	 			
	 				}
	 				
	 			}
	   
	   @FXML	
		private void deleteSelected(ActionEvent event) {
			FeeFacade feeFacade = new FeeFacade();
			feeFacade.delete(super.getCurrentFee());
			feeList.getItems().clear();
			globalFeeList.removeAll(globalFeeList);
			
			
			super.openPopUp("You're post has been deleted", "congratulations, you can continue to the application");
			displayFee();
			
			this.titleFeeSelected.setText("");
			this.commentFeeSelected.setText("");
			this.stateText.setVisible(false);
			this.amountText.setVisible(false);
			this.amountFee.setText("");
			this.stateFee.setText("");
		}
	 			
	// --------------ROUTING FUNCTION
	   
	   public void newFee(ActionEvent event) {
			super.setVue("CreateFee");
		   super.goTo("CreateFeeUI");
	   }
	   
	   
	   public void homePage(ActionEvent event) {
		   super.setVue("HomePage");
		   super.goTo("HomePageUI");
	   }
	   
	   
	   public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.setVue("Login");
		   super.goTo("LoginUI");
	   }
	   
	   @FXML	
		private void modifySelected(ActionEvent event) {
			super.setVue("ModifyFee");
			super.goTo("ModifyFeeUI");
			 
		}
	   
	   
	   
	   //Creation Fee 
	   
	   public void createFee(ActionEvent event) {
		   User user=super.getCurrentUser();
		   FeeFacade feeFacade = new FeeFacade();
	       System.out.println("crationd de fee en cours");
	       try {
	    	    int amount = Integer.parseInt(amountFeeField.getText());
	    	    int res = feeFacade.create( user.getId_user() ,titleFee.getText(),textFee.getText(), amount);
	 	       System.out.println("L'ID du nouveau post est le : " + res);
	 	       
	 		   if (res < 0 ) {
	 	    	   this.errorMessage.setText("Please make sure the post have a title, a text and amount > 0");
	 	       }
	 	       else {
	 			   super.setVue("BasicFee");
	 	    	   super.goTo("FeeUI");
	 	    	   ConfirmMessageUI.setParams(Integer.toString(res));
	 	    	   super.openPopUp("Your Fee has been created", "Your Fee has been transfer to the Trez Team ");
	 	    	  
	 	       }
	    	  
	    	} catch (NumberFormatException nfe) {
	    		this.errorMessage.setText("Please make sure the amount is a number and not a string");
	    	}
	      
	   }
	   
	   public void modifyFee(ActionEvent event) {
		   Fee fee=super.getCurrentFee();
		   FeeFacade feeFacade = new FeeFacade();
	       System.out.println("modification de fee en cours");
	       try {
		       int amount = Integer.parseInt(amountFeeField.getText());
		       int res = feeFacade.modify( fee.getId_fee(), titleFee.getText(),textFee.getText(), amount);
			   
			   if (res < 0 ) {
				   this.errorMessage.setText("Please make sure the post have a title and a text non null");
		       }
		       else {
				   super.setVue("BasicFee");
		    	   super.goTo("FeeUI");
		    	   ConfirmMessageUI.setParams(Integer.toString(res));
		    	   super.openPopUp("Your fee has been modified", "Your Fee has been transfer to the Trez Team");
		    	   super.setCurrentPost(null);
		       }
	       } catch (NumberFormatException nfe) {
	    		this.errorMessage.setText("Please make sure the amount is a number and not a string");
	    	}
	   }
	   
	   //-------------ROUTING FUNCTION
	   
	   public void backFee(ActionEvent event) {
			super.setVue("BasicFee");
		   super.goTo("FeeUI");
	   }
	  
	   
	   
	       
   
}
