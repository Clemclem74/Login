package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDEFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class PopUp extends Routing implements Initializable {
		@FXML
		private Label titlePopUp;
		@FXML
		private Label labelPopUp;
		@FXML
		private Button okButton;
		
		private static String title;
		private static String text;
		
	   
		//Getters and Setters
		public static String getTitle() {
			return title;
		}

		public static void setTitle(String title) {
			PopUp.title = title;
		}

		public static String getText() {
			return text;
		}

		public static void setText(String text) {
			PopUp.text = text;
		}

		@Override
		   public void initialize(URL location, ResourceBundle resources) {
		       this.titlePopUp.setText(title);
		       this.labelPopUp.setText(text);
		   }
		
	 
	   // When user click on myButton
	   // this method will be called.
	  
	   
	   public void okContinue(ActionEvent event) {
		   super.hidePopUp();
	   }
}
