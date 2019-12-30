package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Event;
import buisnessLogic.EventFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EventListUI extends Routing implements Initializable {
	 
	ArrayList<Event> list = new ArrayList<Event>();
	private Event theEvent = new Event();
	
	@FXML
	 private ListView<String> eventList;
	
	@FXML
	 private Label event_title;
	 @FXML
	 private Label description;
	 @FXML
	 private Label event_date;
	 @FXML
	 private ImageView imageview;
	 @FXML
	 private Button leave_button;
	 

	@Override
	   public void initialize(URL location, ResourceBundle resources) {
			
	       loadData();
	       System.out.println("eventList");
	   }

	private void loadData() {
		
		list.removeAll(list);
		
		EventFacade eventFacade = new EventFacade();
		
		list = eventFacade.getEventbyUser(super.getCurrentUser());
		
		ArrayList<String> eventName = new ArrayList<String>();
		
		list.forEach((n)-> eventName.add(n.getTitle() + " : " + n.getEvent_date())); 

		eventList.getItems().addAll(eventName);
	}
	
	public void leave(ActionEvent event) {
		
		EventFacade eventFacade = new EventFacade();
		eventFacade.leave(super.getCurrentUser().getId_user(),this.theEvent);
		eventList.getItems().removeAll(eventList.getItems()); 
		loadData();
		
	}
	
	public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.goTo("LoginUI");
	   }
	
	
	
	@FXML
	private void displaySelected(MouseEvent event) {
		EventFacade eventFacade = new EventFacade();
		
		String event1 = eventList.getSelectionModel().getSelectedItem();
		theEvent = eventFacade.find(event1.split(" : ")[0]);
		if(event1 != null) {
			this.description.setText(theEvent.getDescription());
			this.event_title.setText(theEvent.getTitle());
			this.event_date.setText(theEvent.getEvent_date());	
		}
		
		Image image = new Image("/img/p1.jpg");
		
		if(theEvent.getImage()!=null) {
			image = new Image(theEvent.getImage());
		}
		
		
		imageview.setImage(image);

		imageview.setFitWidth(364);
		imageview.setPreserveRatio(true);
		imageview.setSmooth(true);
		imageview.setCache(true);

		
		
	}
	
	
	public void home(ActionEvent event) {
		super.goTo("HomePageUI");
	}
	
	
	public void list(ActionEvent event) {
		super.goTo("EventUI");
	}
	
   
}
