package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Event;
import buisnessLogic.EventFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	 private Button modify_button;
	 @FXML
	 private ImageView imageview;
	 @FXML
	 private Label event_join;
	 
	 

	@Override
	   public void initialize(URL location, ResourceBundle resources) {
			
	       loadData();
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
		
		if(eventList.getSelectionModel().getSelectedItem()!=null) {
			
			String event1 = eventList.getSelectionModel().getSelectedItem();
			theEvent = eventFacade.find(event1.split(" : ")[0]);
			if(event1 != null) {
				this.description.setText(theEvent.getDescription());
				this.event_title.setText(theEvent.getTitle());
				this.event_date.setText(theEvent.getEvent_date());	
			}
			
			super.setEventSelected(theEvent);
			
			Image image = new Image("/img/p1.jpg");
			
			if(theEvent.getImage()!=null && theEvent.getImage()!="") {
				image = new Image(theEvent.getImage());
			}
			
			
			imageview.setImage(image);

			imageview.setFitWidth(364);
			imageview.setPreserveRatio(true);
			imageview.setSmooth(true);
			imageview.setCache(true);
			
		}
		

		
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
	
	public void modify(ActionEvent event) {
		
		super.goTo("ModifyEventUI");
		
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
			event_join.setText("You succesfully join " + this.theEvent.getTitle());
			
		}
		
		if(res == -2) {
			event_join.setText("You already participate to this event");
		}
	}
	
   
}
