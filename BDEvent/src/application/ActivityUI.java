package application;

import buisnessLogic.UserFacade;
import buisnessLogic.ActivityFacade;
import buisnessLogic.ActivityList;
import buisnessLogic.BDE;
import buisnessLogic.BDEActivity;
import buisnessLogic.BDEFacade;
import buisnessLogic.Event;
import buisnessLogic.EventFacade;
import buisnessLogic.Routing;
import buisnessLogic.StaffActivity;
import buisnessLogic.User;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ActivityUI extends Routing implements Initializable {

	
	@FXML
	private TableView<ActivityList> BDE_Activity;
	@FXML
	private TableColumn<ActivityList,String> BDE_Activity_title;
	@FXML
	private TableColumn<ActivityList,String> BDE_Activity_start;
	@FXML
	private TableColumn<ActivityList,String> BDE_Activity_end;
	@FXML
	private TableColumn<ActivityList,String> BDE_Activity_nb;
	
	
	
	@FXML
	private TableView<ActivityList> Staff_Activity;
	@FXML
	private TableColumn<ActivityList,String> Staff_Activity_title;
	@FXML
	private TableColumn<ActivityList,String> Staff_Activity_start;
	@FXML
	private TableColumn<ActivityList,String> Staff_Activity_end;
	@FXML
	private TableColumn<ActivityList,String> Staff_Activity_nb;
	
	
	@FXML
	private Label name_event;
	@FXML
	private MenuItem Staff_Activity_Menu;
	@FXML
	private MenuItem BDE_Activity_Menu;
	@FXML
	private SplitMenuButton Menu_Button; 
	
	
	@FXML
	private Button logoutButton; 
	
	
	
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
	
	private BDEActivity theBDEActi = new BDEActivity();

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
	 private Label end_hour;
	 @FXML
	 private Label users;
	 @FXML
	 private Button leave_button;
	 @FXML
	 private Button activity_button;
	 
	
	
	String theHours="1";
	String theMinutes="0";
	String theStartHour="12h00m";
	
	
	ArrayList<BDEActivity> list1 = new ArrayList<BDEActivity>();
	ArrayList<StaffActivity> list2 = new ArrayList<StaffActivity>();
	
	ActivityList selected = new ActivityList();
	ActivityList selected2 = new ActivityList();
	
	@Override
	   public void initialize(URL location, ResourceBundle resources) {
			
		 if (super.getVue().contentEquals("Activity")){
			//name_event.setText(super.getEventSelected().getTitle());
		       
		       BDE_Activity_title.setCellValueFactory(new PropertyValueFactory<ActivityList,String>("title"));
		       BDE_Activity_start.setCellValueFactory(new PropertyValueFactory<ActivityList,String>("start"));
		       BDE_Activity_end.setCellValueFactory(new PropertyValueFactory<ActivityList,String>("end"));
		       BDE_Activity_nb.setCellValueFactory(new PropertyValueFactory<ActivityList,String>("nb_user"));
				
			   BDE_Activity.setItems(loadData());
			   
			   Staff_Activity_title.setCellValueFactory(new PropertyValueFactory<ActivityList,String>("title"));
		       Staff_Activity_start.setCellValueFactory(new PropertyValueFactory<ActivityList,String>("start"));
		       Staff_Activity_end.setCellValueFactory(new PropertyValueFactory<ActivityList,String>("end"));
		       Staff_Activity_nb.setCellValueFactory(new PropertyValueFactory<ActivityList,String>("nb_user"));
				
		       Staff_Activity.setItems(loadData2(super.getEventSelected())); 
		 } else {
			 if (super.getVue().contentEquals("CreateBDEActivity")||super.getVue().contentEquals("CreateStaffActivity")){
				 start_hour.setText("12h00m");
				 duration.setText("0h00m");
			 }
			 else {
				 if (super.getVue().contentEquals("ModifyBDEActivity")){
					 setActivity(super.getBdeActivitySelected());
				 }
				 else { 
					 if (super.getVue().contentEquals("ModifyStaffActivity")){
						 setActivityStaff(super.getStaffActivitySelected());
					 }
					 else {
						 if (super.getVue().contentEquals("MyActivity")){
							 loadMyData();
						 }
					 }
				 }
			 }
		 }
	   }

	
private ObservableList<ActivityList> loadData() {
		
		ObservableList<ActivityList> acti = FXCollections.observableArrayList();
		
		
		list1.removeAll(list1);
		
		ActivityFacade activityFacade = new ActivityFacade();
		
		list1 = activityFacade.findAll();
		
		
		
		list1.forEach( (n) -> { 
			ActivityList aActi = new ActivityList();
			aActi.setId(n.getId_activity());
			aActi.setTitle(n.getName_activity());
			aActi.setStart(n.getStart_hour());
			aActi.setEnd(calculate_end(n.getStart_hour(),n.getDuration()));
			aActi.setNb_user(String.valueOf(activityFacade.count_users_BDEacti(n))+"/"+n.getNb_users());
			acti.add(aActi);
		}
		); 
		
		return acti;
		
	}

	private void loadMyData() {
	
		list1.removeAll(list1);
		list2.removeAll(list2);
		
		ActivityFacade eventFacade = new ActivityFacade();
		
		list1 = eventFacade.getBDEActivitybyUser(super.getCurrentUser());
		list2 = eventFacade.getStaffActivitybyUser(super.getCurrentUser());
		
		ArrayList<String> actiName = new ArrayList<String>();
		
		list1.forEach((n)-> actiName.add(n.getId_activity() + " - " + n.getName_activity() + " : BDE")); 
		list2.forEach((n)-> actiName.add(n.getId_activity() + " - " + n.getName_activity()+ " : Staff")) ;
		
		System.out.println("list 1:"+actiName.size());
	
		actiList.getItems().addAll(actiName);
	}

	
	public void leave(ActionEvent event) {
		
		actiList.getItems().removeAll(actiList.getItems()); 
		loadData();
		
	}
	
	
	private ObservableList<ActivityList> loadData2(Event event) {
		
		ObservableList<ActivityList> acti = FXCollections.observableArrayList();
		
		
		list2.removeAll(list2);
		
		ActivityFacade activityFacade = new ActivityFacade();
		
		list2 = activityFacade.findAllStaff(event);
		
		
		
		list2.forEach( (n) -> { 
			ActivityList aActi = new ActivityList();
			aActi.setId(n.getId_activity());
			aActi.setTitle(n.getName_activity());
			aActi.setStart(n.getStart_hour());
			aActi.setEnd(calculate_end(n.getStart_hour(),n.getDuration()));
			aActi.setNb_user(String.valueOf(activityFacade.count_users_Staffacti(n))+"/"+n.getNb_users());
			acti.add(aActi);
		}
		); 
		
		return acti;
		
	}
	
	private String calculate_end(String start,String duration) {
		
		String ret ="";
		
		System.out.println(start+" "+duration);
		
		String[] tab1 = start.split("h");
		String[] tab2 = duration.split("h");
		
	
		tab1[1]=tab1[1].substring(0,tab1[1].length()-1);
		tab2[1]=tab2[1].substring(0,tab2[1].length()-1);		
		
		int mins = Integer.parseInt(tab1[1])+Integer.parseInt(tab2[1]);
		int ahour = 0;
		
		switch(mins) {
		   case 90:
		      ahour = 1;
		      mins = 30; 
		     break;
		   case 75:
			   ahour = 1;
			   mins = 15;
		     break;
		   case 60:
			   ahour = 1;
			   mins = 0;
			     break;
		   default:
			   ahour = 0;
		 }
		
		String m = String.valueOf(mins);
		if(m.startsWith("0")) {
			m="00";
		}
		
		ret = ret + String.valueOf(Integer.parseInt(tab1[0]) + Integer.parseInt(tab2[0]) + ahour)+"h"+String.valueOf(m)+"m";
		return ret;
	}
	
	@FXML
	private void getSelected(MouseEvent event) {
		
		ActivityFacade activityFacade = new ActivityFacade();
		
		if((BDE_Activity.getSelectionModel().getSelectedItem()!=null)) {
			this.selected = BDE_Activity.getSelectionModel().getSelectedItem();
			
			super.setBdeActivitySelected(activityFacade.find(this.selected.getId()));
		}
	}
	
	@FXML
	private void getSelected2(MouseEvent event) {
		
		ActivityFacade activityFacade = new ActivityFacade();
		
		if((Staff_Activity.getSelectionModel().getSelectedItem()!=null)) {
			this.selected2 = Staff_Activity.getSelectionModel().getSelectedItem();
			
			super.setStaffActivitySelected(activityFacade.findStaff(this.selected2.getId()));
			
		}
	}
	
	@FXML
	private void join() {
		ActivityFacade acti = new ActivityFacade();
		acti.join(this.selected.getId(), super.getCurrentUser());
		BDE_Activity.setItems(loadData());
	}
	
	@FXML
	private void join2() {
		ActivityFacade acti = new ActivityFacade();
		acti.joinStaff(this.selected2.getId(),super.getEventSelected() ,super.getCurrentUser());
		Staff_Activity.setItems(loadData2(super.getEventSelected()));
	}
	
	public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.setVue("Login");
		   super.goTo("LoginUI");
	   }
	
	
	@FXML
	private void createBDEActivity() {
		super.setVue("CreateBDEActivity");
		super.goTo("CreateBDEActivityUI");
	}

	@FXML
	public void createStaffActivity(ActionEvent event) {
		super.setVue("CreateStaffActivity");
		super.goTo("CreateStaffActivityUI");
	}
	
	@FXML
	public void home(ActionEvent event) {
		super.setVue("HomePage");
		super.goTo("HomePageUI");
	}
	@FXML
	public void modify(ActionEvent event) {
		super.setVue("ModifyBDEActivity");
		super.goTo("ModifyBDEActivityUI");
		BDE_Activity.setItems(loadData());
	}
	
	@FXML
	public void modify2(ActionEvent event) {
		super.setVue("ModifyStaffActivity");
		super.goTo("ModifyStaffActivityUI");
		Staff_Activity.setItems(loadData2(super.getEventSelected()));
	}
	
	@FXML
	public void delete(ActionEvent event) {
		
		ActivityFacade activityFacade = new ActivityFacade();
		
		activityFacade.delete(activityFacade.find(selected.getId()));
		
		BDE_Activity.setItems(loadData());
	}
	
	@FXML
	public void delete2(ActionEvent event) {
		
		ActivityFacade activityFacade = new ActivityFacade();
		
		activityFacade.deleteStaff(activityFacade.findStaff(selected2.getId()));
		
		Staff_Activity.setItems(loadData2(super.getEventSelected()));
	}
	
	
	public void list(ActionEvent event) {
		super.setVue("Event");
		super.goTo("EventUI");
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
       acti1.setStart_hour(theStartHour);
       acti1.setDuration(theHours+"h"+theMinutes+"m");
       acti1.setNb_users(Integer.parseInt(this.nb_users.getText()));
       
       actiFacade.create(acti1);
       super.setVue("Activity");
       super.goTo("ActivityUI");
       
       
   }
   
   public void createActionStaff(ActionEvent event) {
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
       super.setVue("Activity");
       super.goTo("ActivityUI");
       
       
   }
   
   
   public void setActivity(BDEActivity acti) {
	   
	   start_hour.setText(acti.getStart_hour());
	   duration.setText(acti.getDuration());
	   
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	   String date = acti.getDate().replace("/","-");
	   

	   if(date.charAt(1)=='-') {
		   date = "0" + date;
		   if(date.charAt(4)=='-') {
			   date = date.substring(0,3) + "0" + date.substring(3);
		   }
	   }else {
		   System.out.println(date.charAt(4));
		   if((date.charAt(4)=='-')) {
			   date = date.substring(0,3) + "0" + date.substring(3);
		   }
		   
	   }
	   
	   System.out.println(date);
	   
	   LocalDate localDate = LocalDate.parse(date, formatter);
	   
	   
	   this.dateActivityField.setValue(localDate);
	   this.titleActivityField.setText(acti.getName_activity());
	   this.descriptionActivity.setText(acti.getDescription());
	   this.nb_users.setText(String.valueOf(acti.getNb_users()));

	   String[] tab1 = acti.getDuration().split("h");
		
	   theHours = tab1[0];
	   theMinutes = tab1[1].substring(0,tab1[1].length()-1);
	   theStartHour=acti.getStart_hour();
	   
   }
   
   public void setActivityStaff(StaffActivity acti) {
	   
	   start_hour.setText(acti.getStart_hour());
	   duration.setText(acti.getDuration());
	   
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	   String date = acti.getDate().replace("/","-");
	   

	   if(date.charAt(1)=='-') {
		   date = "0" + date;
		   if(date.charAt(4)=='-') {
			   date = date.substring(0,3) + "0" + date.substring(3);
		   }
	   }else {
		   System.out.println(date.charAt(4));
		   if((date.charAt(4)=='-')) {
			   date = date.substring(0,3) + "0" + date.substring(3);
		   }
		   
	   }
	   
	   System.out.println(date);
	   
	   LocalDate localDate = LocalDate.parse(date, formatter);
	   
	   
	   this.dateActivityField.setValue(localDate);
	   this.titleActivityField.setText(acti.getName_activity());
	   this.descriptionActivity.setText(acti.getDescription());
	   this.nb_users.setText(String.valueOf(acti.getNb_users()));

	   String[] tab1 = acti.getDuration().split("h");
		
	   theHours = tab1[0];
	   theMinutes = tab1[1].substring(0,tab1[1].length()-1);
	   theStartHour=acti.getStart_hour();
	   
   }
   
   
   public void modifyAction(ActionEvent event) {
	   System.out.println(Hours.getAccessibleText());
	   
	   ActivityFacade actiFacade = new ActivityFacade();
       BDEActivity acti1 = new BDEActivity();
       
       String date = String.valueOf(dateActivityField.getValue().getDayOfMonth()) + "/" +
    		   String.valueOf(dateActivityField.getValue().getMonthValue()) + "/" +
    		   String.valueOf(dateActivityField.getValue().getYear());
       
       acti1.setName_activity(this.titleActivityField.getText());
       acti1.setDate(date);
       acti1.setDescription(this.descriptionActivity.getText());
       acti1.setStart_hour(theStartHour);
       acti1.setDuration(theHours+"h"+theMinutes+"m");
       acti1.setNb_users(Integer.parseInt(this.nb_users.getText()));
       
       actiFacade.modify(acti1,super.getBdeActivitySelected().getId_activity());
       super.setVue("Activity");
       super.goTo("ActivityUI");
       
       
   }
   
   
   public void modifyActionStaff(ActionEvent event) {
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
       
       System.out.println("title "+ acti1.getName_activity());
       actiFacade.modifyStaff(acti1,super.getStaffActivitySelected().getId_activity());
       super.setVue("Activity");
       super.goTo("ActivityUI");
       
       
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
	   super.setVue("Activity");
		super.goTo("ActivityUI");
	}
	
	
}

