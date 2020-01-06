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
	
	ArrayList<BDEActivity> list1 = new ArrayList<BDEActivity>();
	ArrayList<StaffActivity> list2 = new ArrayList<StaffActivity>();
	
	ActivityList selected = new ActivityList();
	ActivityList selected2 = new ActivityList();
	
	@Override
	   public void initialize(URL location, ResourceBundle resources) {
			
			System.out.println("test");
		
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
		   super.goTo("LoginUI");
	   }
	
	
	@FXML
	private void createBDEActivity() {
		super.goTo("CreateBDEActivityUI");
	}

	@FXML
	public void createStaffActivity(ActionEvent event) {
		super.goTo("CreateStaffActivityUI");
	}
	
	@FXML
	public void home(ActionEvent event) {
		super.goTo("HomePageUI");
	}
	@FXML
	public void modify(ActionEvent event) {
		
		super.goTo("ModifyBDEActivityUI");
		BDE_Activity.setItems(loadData());
	}
	
	@FXML
	public void modify2(ActionEvent event) {
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
		super.goTo("EventUI");
	}
	
   
}

