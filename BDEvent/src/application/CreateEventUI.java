package application;

import buisnessLogic.UserFacade;
import java.util.logging.Level;
import buisnessLogic.Event;
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
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateEventUI extends Routing implements Initializable {
		@FXML
		private Button saveButton;
		@FXML
		private TextField titleEventField;
		@FXML
		private TextField descriptionEventField;
		@FXML
		private DatePicker dateEventField;
		@FXML
		private Button image;

		
		File selectedFile = new File("");
		
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).
		   
		   
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void createAction(ActionEvent event) {
	       EventFacade eventFacade = new EventFacade();
	       Event event1 = new Event();
	       
	       String date = String.valueOf(dateEventField.getValue().getDayOfMonth()) + "/" +
	    		   String.valueOf(dateEventField.getValue().getMonthValue()) + "/" +
	    		   String.valueOf(dateEventField.getValue().getYear());
	       
	       event1.setTitle(this.titleEventField.getText());
	       event1.setDescription(this.descriptionEventField.getText());
	       event1.setEvent_date(date);
	       try {
			event1.setImage(selectedFile.toURI().toURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	       eventFacade.create(event1);
	       super.goTo("EventUI");
	   }
	   
	   public void selectImage(ActionEvent event) {
		   
		   FileChooser fileChooser = new FileChooser();

	       selectedFile = fileChooser.showOpenDialog(getStage());

	       System.out.println(selectedFile.getAbsolutePath());
		   
	   }
	   

	   
	   
}
