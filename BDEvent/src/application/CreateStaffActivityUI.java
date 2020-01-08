package application;

import buisnessLogic.UserFacade;
import java.util.logging.Level;
import buisnessLogic.Event;
import buisnessLogic.ActivityFacade;
import buisnessLogic.BDEActivity;
import buisnessLogic.BDEFacade;
import buisnessLogic.EventFacade;
import buisnessLogic.Routing;
import buisnessLogic.StaffActivity;

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

public class CreateStaffActivityUI extends Routing implements Initializable {
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
		@FXML
		private TextField nb_users;
		@FXML
		private Label start_hour;
		@FXML
		private Label duration;
		@FXML
		private Slider start_hour_slider;
		
		
		String theHours="1";
		String theMinutes="0";
		String theStartHour="12h00m";
		
		
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).
		   start_hour.setText("12h00m");
		   duration.setText("0h00m");
		   
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void createAction(ActionEvent event) {
		   System.out.println(Hours.getAccessibleText());
		   
		   ActivityFacade actiFacade = new ActivityFacade();
	       StaffActivity acti1 = new StaffActivity();

	       String date = String.valueOf(dateActivityField.getValue().getDayOfMonth()) + "/" +
	    		   String.valueOf(dateActivityField.getValue().getMonthValue()) + "/" +
	    		   String.valueOf(dateActivityField.getValue().getYear());
	       
	       acti1.setName_activity(this.titleActivityField.getText());
	       acti1.setDate(date);
	       acti1.setDescription(this.descriptionActivity.getText());
	       acti1.setStart_hour(theStartHour);
	       acti1.setDuration(theHours+"h"+theMinutes+"m");
	       acti1.setNb_users(Integer.parseInt(this.nb_users.getText()));
	       System.out.println("Event:"+super.getEventSelected().getTitle());
	       actiFacade.createStaff(acti1,super.getEventSelected());
	       super.goTo("ActivityUI");
	       
	       
	   }
	   
	   public void handleHours(ActionEvent event) {
		    	   	
		   	MenuItem menu = new MenuItem();
		   	menu = (MenuItem) event.getSource();
		   	theHours = (String) menu.getUserData();
		   	
		   	String hours = "0";
			   String minutes = "0";
			   
			   if(theHours!="") {
				   hours=theHours;
			   }
			   if(theMinutes!="") {
				   minutes=theMinutes;
			   }
			   
			   duration.setText(hours+"h"+minutes+"m");
		   	
		    
		}
	   
	   public void handleMinutes(ActionEvent event) {
   	   	
		   	MenuItem menu = new MenuItem();
		   	menu = (MenuItem) event.getSource();
		   	theMinutes = (String) menu.getUserData();
		   	
		   	String hours = "0";
			   String minutes = "0";
			   
			   if(theHours!="") {
				   hours=theHours;
			   }
			   if(theMinutes!="") {
				   minutes=theMinutes;
			   }
			   
			   duration.setText(hours+"h"+minutes+"m");
		   	
		    
		}
	   
	   public void slider_change(MouseEvent event) {
		   int sliderval = (int) start_hour_slider.getValue();
		   double sliderValue = (double) sliderval/4;
		   
		   int i = new Double(sliderValue).intValue(); //recuperer la partie entiere
		   double decimale = sliderValue-(new Double(i).doubleValue());
		   String dec = String.valueOf(decimale);
		   switch(dec) {
		   case "0":
		     dec = "00";
		     break;
		   case "0.25":
			   dec = "15";
		     break;
		   case "0.5":
			   dec = "30";
			     break;
		   case "0.75":
			   dec = "45";
			     break;
		   default:
		     dec="00";
		 }
		   
		   start_hour.setText(String.valueOf(i)+"h"+String.valueOf(dec)+"m");
		   theStartHour = String.valueOf(i)+"h"+String.valueOf(dec)+"m";
	   }
	   
	  
	   
	   
}
