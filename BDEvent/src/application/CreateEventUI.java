package application;

import buisnessLogic.UserFacade;
import buisnessLogic.Event;
import buisnessLogic.BDEFacade;
import buisnessLogic.EventFacade;
import buisnessLogic.Routing;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class CreateEventUI extends Routing implements Initializable {
		@FXML
		private Button saveButton;
		@FXML
		private TextField titleEventField;
		@FXML
		private TextField descriptionEventField;
		@FXML
		private DatePicker dateEventField;
	  
	   
	   
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
	       
	       eventFacade.create(event1);
	       super.goTo("EventUI");
	   }
	   
	   
}
