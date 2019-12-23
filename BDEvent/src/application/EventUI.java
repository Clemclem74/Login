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

public class EventUI extends Routing implements Initializable {
	 
	ArrayList<Event> list = new ArrayList<Event>();
	
	@FXML
	 private ListView<String> eventList;


	@Override
	   public void initialize(URL location, ResourceBundle resources) {
			
	       loadData();
	       System.out.println("test");
	   }

	private void loadData() {
		list.removeAll(list);
		EventFacade eventFacade = new EventFacade();
		
		list = eventFacade.findAll();
		
		ArrayList<String> eventName = new ArrayList<String>();
		
		list.forEach((n)-> eventName.add(n.getTitle() + " : " + n.getEvent_date())); 
		
		
		
		eventList.getItems().addAll(eventName);
	}
   
}
