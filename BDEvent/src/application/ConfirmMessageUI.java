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

public class ConfirmMessageUI extends Routing implements Initializable {
		@FXML
		private Label idBDELabel;
	  
		private static String params;
	   
		
		@Override
		   public void initialize(URL location, ResourceBundle resources) {
		       this.idBDELabel.setText(params);
		   }
		
	   
	   public static String getParams() {
			return params;
		}

		public static void setParams(String params) {
			ConfirmMessageUI.params = params;
		}

	
	 
	   // When user click on myButton
	   // this method will be called.
	  
	   
	   public void confirmCreation(ActionEvent event) {
		   super.hideConfirmMessage();
		   super.goTo("HomePageUI");
	   }
}
