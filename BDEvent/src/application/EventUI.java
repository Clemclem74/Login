package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Event;
import buisnessLogic.EventFacade;
import buisnessLogic.Post;
import buisnessLogic.Routing;
import buisnessLogic.TeamMemberFacade;
import buisnessLogic.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.stage.FileChooser;
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
	 @FXML
	 private Label event_responsible;
	 @FXML
	 private Button leave_button;
	 @FXML
	 private Button activity_button;
	 
	// ------------------------------- //
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
	 

	 
	private String myImage;
	 
	File selectedFile = new File("");
	 
	 

	@Override
	   public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Vue actuelle" + super.getVue());
		   
	       if (super.getVue().contentEquals("ModifyEvent")){
	    	   
	    	   System.out.println("if modify");
	    	   
	    	   setEvent(super.getEventSelected());
	       }
	       else 
	    	   if (super.getVue().contentEquals("CreateEvent")){
	    		   //NOTHING TO DO 
	    		   }
	    	   else {
	    		   if (super.getVue().contentEquals("EventList")){
	    			   loadDataUser();
		    		   }
	    		   else {
	    			   super.setEventSelected(null);
		    		   loadData();
		    		      
	    		   }
	    		   
	    	   }	
	   }

	
	
	public void setEvent(Event event) {
		this.titleEventField.setText(event.getTitle());
		this.descriptionEventField.setText(event.getDescription());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date = event.getEvent_date().replace("/","-");
		LocalDate localDate = LocalDate.parse(date, formatter);
		
		this.dateEventField.setValue(localDate);
		this.myImage = event.getImage();
    }
	
	public void leave(ActionEvent event) {
		
		EventFacade eventFacade = new EventFacade();
		eventFacade.leave(super.getCurrentUser().getId_user(),this.theEvent);
		eventList.getItems().removeAll(eventList.getItems()); 
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
	
	
	private void loadDataUser() {
		
		list.removeAll(list);
		
		EventFacade eventFacade = new EventFacade();
		
		list = eventFacade.getEventbyUser(super.getCurrentUser());
		
		ArrayList<String> eventName = new ArrayList<String>();
		
		list.forEach((n)-> eventName.add(n.getTitle() + " : " + n.getEvent_date())); 

		eventList.getItems().addAll(eventName);
	}
	
	
	public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.setVue("Login");
		   super.goTo("LoginUI");
	   }
	
	
	
	@FXML
	private void displaySelected(MouseEvent event) {
		EventFacade eventFacade = new EventFacade();
		UserFacade userFacade = new UserFacade();
		User responsible = new User();
		
		
		event_join.setText("");
		if(eventList.getSelectionModel().getSelectedItem()!=null) {
			
			String event1 = eventList.getSelectionModel().getSelectedItem();
			theEvent = eventFacade.find(event1.split(" : ")[0]);
			responsible = userFacade.findById(theEvent.getResponsible());
			if(event1 != null) {
				this.description.setText(theEvent.getDescription());
				this.event_title.setText(theEvent.getTitle());
				this.event_date.setText(theEvent.getEvent_date());
				this.event_responsible.setText(responsible.getFirstname() + " " + responsible.getUsername());	
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
	
	
	@FXML
	private void displayUserSelected(MouseEvent event) {
		EventFacade eventFacade = new EventFacade();
		if(eventList.getSelectionModel().getSelectedItem()!=null) {
		
			String event1 = eventList.getSelectionModel().getSelectedItem();
			theEvent = eventFacade.find(event1.split(" : ")[0]);
			super.setEventSelected(theEvent);
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

		
		
	}
	
	
	public void delete(ActionEvent event) {
		
		if(super.getEventSelected().getResponsible()==super.getCurrentUser().getId_user()) {
			EventFacade eventFacade = new EventFacade();
			eventFacade.delete(this.theEvent);
			eventList.getItems().removeAll(eventList.getItems()); 
			loadData();
		}
		else {
			event_join.setText("You are not responsible of the event");
		}
	
	}
	

	
	public void create(ActionEvent event) {
		
		TeamMemberFacade teamMemberFacade= new TeamMemberFacade();
		
		if(teamMemberFacade.isChief(super.getCurrentUser())){
			super.setVue("CreateEvent");
			super.goTo("CreateEventUI");
		}
		else {
			event_join.setText("You are not a team chief");
		}
		
	}
	
	public void modify(ActionEvent event) {
		if(super.getEventSelected().getResponsible()==super.getCurrentUser().getId_user()) {
			super.setVue("CreateEvent");
			super.goTo("ModifyEventUI");
		}
		else {
			event_join.setText("You are not responsible of the event");
		}
		
	}
	
	public void home(ActionEvent event) {
		super.setVue("HomePage");
		super.goTo("HomePageUI");
	}
	
	
	public void list(ActionEvent event) {
		super.setVue("EventList");
		super.goTo("EventListUI");
	}
	
	@FXML
	public void activity(ActionEvent event) {
		super.setVue("MyActivity");
		super.goTo("MyActivityUI");
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
	
	
	public void createAction(ActionEvent event) {
	       EventFacade eventFacade = new EventFacade();
	       Event event1 = new Event();
	       
	       String date = String.valueOf(dateEventField.getValue().getDayOfMonth()) + "/" +
	    		   String.valueOf(dateEventField.getValue().getMonthValue()) + "/" +
	    		   String.valueOf(dateEventField.getValue().getYear());
	       
	       event1.setTitle(this.titleEventField.getText());
	       event1.setDescription(this.descriptionEventField.getText());
	       event1.setEvent_date(date);
	       event1.setResponsible(super.getCurrentUser().getId_user());
	       try {
			event1.setImage(selectedFile.toURI().toURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	       eventFacade.create(event1);
	       super.setVue("Event");
	       super.goTo("EventUI");
	   }
	   
	   public void selectImage(ActionEvent event) {
		   
		   FileChooser fileChooser = new FileChooser();

	       selectedFile = fileChooser.showOpenDialog(getStage());

	       System.out.println(selectedFile.getAbsolutePath());
		   
	   }
	   
	   public void back() {
		   super.setVue("Event");
		   super.goTo("EventUI");   
	   }
	   
	   public void createAction2(ActionEvent event) {
	       EventFacade eventFacade = new EventFacade();
	       Event event1 = new Event();
	       
	       String date = String.valueOf(dateEventField.getValue().getDayOfMonth()) + "/" +
	    		   String.valueOf(dateEventField.getValue().getMonthValue()) + "/" +
	    		   String.valueOf(dateEventField.getValue().getYear());
	       
	       event1.setTitle(this.titleEventField.getText());
	       event1.setDescription(this.descriptionEventField.getText());
	       event1.setEvent_date(date);
	       event1.setImage(myImage);
	       try {
	    	if(selectedFile.toURI().toURL().toString().endsWith(".jpg") || selectedFile.toURI().toURL().toString().endsWith(".png")) {
	    		event1.setImage(selectedFile.toURI().toURL().toString());
	    		System.out.println("s="+selectedFile.toURI().toURL().toString());
	    	}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	       eventFacade.modify(super.getEventSelected().getId_event(),event1);
	       super.setVue("Event");
	       super.goTo("EventUI");
	   }
	
	   public void list2(ActionEvent event) {
		   	super.setVue("Event");
			super.goTo("EventUI");
		}
	   
	   public void activity2(ActionEvent event) {
		   super.setVue("Activity");
			super.goTo("ActivityUI");
		}
	   
   
}
