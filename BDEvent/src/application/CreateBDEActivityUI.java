package application;

import buisnessLogic.UserFacade;
import java.util.logging.Level;
import buisnessLogic.Event;
import buisnessLogic.ActivityFacade;
import buisnessLogic.BDEActivity;
import buisnessLogic.BDEFacade;
import buisnessLogic.EventFacade;
import buisnessLogic.Routing;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateBDEActivityUI extends Routing implements Initializable {
		@FXML
		private Button saveButton;
		@FXML
		private TextField titleActivityField;
		@FXML
		private DatePicker dateActivityField;
		@FXML
		private MenuButton Hours;
		@FXML
		private MenuButton Minutes;
		@FXML
		private Label Date_End;
		@FXML
		private TextField descriptionActivity;
		
		
		String theHours="";
		String theMinutes="";
		
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).
		   
		   
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void createAction(ActionEvent event) {
		   System.out.println(Hours.getAccessibleText());
		   
		   ActivityFacade actiFacade = new ActivityFacade();
	       BDEActivity acti1 = new BDEActivity();
	       
	       String date = String.valueOf(dateActivityField.getValue().getDayOfMonth()) + "/" +
	    		   String.valueOf(dateActivityField.getValue().getMonthValue()) + "/" +
	    		   String.valueOf(dateActivityField.getValue().getYear());
	       
	       acti1.setName_activity(this.titleActivityField.getText());
	       acti1.setDate(date);
	       acti1.setDescription(this.descriptionActivity.getText());
	       acti1.setDuration(theHours+"h"+theMinutes+"m");
	       
	       
	       actiFacade.create(acti1);
	       super.goTo("EventUI");
	       
	       
	   }
	   
	   public void handleHours(ActionEvent event) {
		    	   	
		   	MenuItem menu = new MenuItem();
		   	menu = (MenuItem) event.getSource();
		   	theHours = (String) menu.getUserData();
		   	
		    
		}
	   
	   public void handleMinutes(ActionEvent event) {
   	   	
		   	MenuItem menu = new MenuItem();
		   	menu = (MenuItem) event.getSource();
		   	theMinutes = (String) menu.getUserData();
		   	
		    
		}
	   
	  
	   

	   
	   
}
