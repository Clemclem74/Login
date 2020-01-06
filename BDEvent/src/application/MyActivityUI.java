package application;

import buisnessLogic.UserFacade;
import buisnessLogic.ActivityFacade;
import buisnessLogic.BDE;
import buisnessLogic.BDEActivity;
import buisnessLogic.BDEFacade;
import buisnessLogic.Event;
import buisnessLogic.EventFacade;
import buisnessLogic.Routing;
import buisnessLogic.StaffActivity;
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

public class MyActivityUI extends Routing implements Initializable {
	 
	ArrayList<BDEActivity> list = new ArrayList<BDEActivity>();
	private BDEActivity theBDEActi = new BDEActivity();
	
	ArrayList<StaffActivity> list2 = new ArrayList<StaffActivity>();
	private StaffActivity theStaffActi = new StaffActivity();
	
	@FXML
	 private ListView<String> actiList;
	
	@FXML
	 private Label title;
	 @FXML
	 private Label description;
	 @FXML
	 private Label date;
	 @FXML
	 private Label start_hour;
	 @FXML
	 private Label end_hour;
	 @FXML
	 private Label users;
	 @FXML
	 private Button leave_button;
	 @FXML
	 private Button activity_button;
	 

	@Override
	   public void initialize(URL location, ResourceBundle resources) {
			
	       loadData();
	   }

	private void loadData() {
		
		list.removeAll(list);
		list2.removeAll(list2);
		
		ActivityFacade eventFacade = new ActivityFacade();
		
		list = eventFacade.getBDEActivitybyUser(super.getCurrentUser());
		list2 = eventFacade.getStaffActivitybyUser(super.getCurrentUser());
		
		ArrayList<String> actiName = new ArrayList<String>();
		
		list.forEach((n)-> actiName.add(n.getId_activity() + " - " + n.getName_activity() + " : BDE")); 
		list2.forEach((n)-> actiName.add(n.getId_activity() + " - " + n.getName_activity()+ " : Staff")) ;
		
		System.out.println("list 1:"+actiName.size());
		
		actiList.getItems().addAll(actiName);
	}
	
	public void leave(ActionEvent event) {
		
		actiList.getItems().removeAll(actiList.getItems()); 
		loadData();
		
	}
	
	public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.goTo("LoginUI");
	   }
	
	
	
	@FXML
	private void displaySelected(MouseEvent event) {
		ActivityFacade actiFacade = new ActivityFacade();
		if(actiList.getSelectionModel().getSelectedItem()!=null) {
		
			String event1 = actiList.getSelectionModel().getSelectedItem();
			
			if(event1.split(" : ")[1].endsWith("BDE")) {
				
				theBDEActi = actiFacade.find(Integer.parseInt(event1.split(" - ")[0]));

				String alluser = actiFacade.findCollegue(theBDEActi);
				
				
				if(event1 != null) {
					this.description.setText(theBDEActi.getDescription());
					this.title.setText(theBDEActi.getName_activity());
					this.date.setText(theBDEActi.getDate());
					this.start_hour.setText(theBDEActi.getStart_hour());
					this.end_hour.setText(theBDEActi.getDuration());
					this.users.setText(alluser);
				}
				
			}else {
				
				theStaffActi = actiFacade.findStaff(Integer.parseInt(event1.split(" - ")[0]));

				String alluser = actiFacade.findCollegueStaff(theStaffActi);
				
				if(event1 != null) {
					this.description.setText(theStaffActi.getDescription());
					this.title.setText(theStaffActi.getName_activity());
					this.date.setText(theStaffActi.getDate());
					this.start_hour.setText(theStaffActi.getStart_hour());
					this.end_hour.setText(theStaffActi.getDuration());
					this.users.setText(alluser);
				
				}
			
			}
		
		}

		
		
	}
	
	public void activity(ActionEvent event) {
		super.goTo("ActivityUI");
	}
	
	
	public void home(ActionEvent event) {
		super.goTo("HomePageUI");
	}
	
	
	public void list(ActionEvent event) {
		super.goTo("EventUI");
	}
	
   
}
