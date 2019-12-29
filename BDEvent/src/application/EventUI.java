package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Event;
import buisnessLogic.EventFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;

import java.io.InputStream;
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

public class EventUI extends Routing implements Initializable {
	 
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
	 private Button join_button;
	 @FXML
	 private ImageView imageview;
	 

	@Override
	   public void initialize(URL location, ResourceBundle resources) {
			
	       loadData();
	       System.out.println("eventList");
	   }

	private void loadData() {
		list.removeAll(list);
		EventFacade eventFacade = new EventFacade();
		
		list = eventFacade.findAll();
		
		ArrayList<String> eventName = new ArrayList<String>();
		
		list.forEach((n)-> eventName.add(n.getTitle() + " : " + n.getEvent_date())); 
		
		
		
		eventList.getItems().addAll(eventName);
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
		
		System.out.println("test");
		
		imageview.setImage(image);
		
		System.out.println("test2");
		imageview.setFitWidth(364);
		imageview.setPreserveRatio(true);
		imageview.setSmooth(true);
		imageview.setCache(true);

		
	}
	
	public void delete(ActionEvent event) {
		EventFacade eventFacade = new EventFacade();
		eventFacade.delete(this.theEvent);
		eventList.getItems().removeAll(eventList.getItems()); 
		loadData();
	}
	
	public void create(ActionEvent event) {
		super.goTo("CreateEventUI");
	}
	
	public void home(ActionEvent event) {
		super.goTo("HomePageUI");
	}
	
	
	public void list(ActionEvent event) {
		super.goTo("EventListUI");
	}
	
	
	public void join(ActionEvent event) {
		EventFacade eventFacade = new EventFacade();
		int res=eventFacade.join(this.theEvent,super.getCurrentUser());
		if(res == 0) {
			System.out.println("You succesfully join " + this.theEvent.getTitle());
			
		}
		
		if(res == -2) {
			System.out.println("You already participate to this event");
		}
	}
	
   
}
