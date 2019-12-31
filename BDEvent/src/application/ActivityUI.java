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
import buisnessLogic.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

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
	private TableView<ActivityList> Staff_Activity;
	@FXML
	private TableColumn<ActivityList,String> Staff_Activity_title;
	@FXML
	private TableColumn<ActivityList,String> Staff_Activity_start;
	@FXML
	private TableColumn<ActivityList,String> Staff_Activity_end;
	
	
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
	
	
	@Override
	   public void initialize(URL location, ResourceBundle resources) {
			
			System.out.println("test");
		
	       //name_event.setText(super.getEventSelected().getTitle());
	       
	       BDE_Activity_title.setCellValueFactory(new PropertyValueFactory<ActivityList,String>("title"));
	       BDE_Activity_start.setCellValueFactory(new PropertyValueFactory<ActivityList,String>("start"));
	       BDE_Activity_end.setCellValueFactory(new PropertyValueFactory<ActivityList,String>("end"));
			
		   BDE_Activity.setItems(loadData(super.getEventSelected()));
	       
	      
	       
	   }

	private ObservableList<ActivityList> loadData(Event event) {
		
		ObservableList<ActivityList> acti = FXCollections.observableArrayList();
		
		
		list1.removeAll(list1);
		
		ActivityFacade activityFacade = new ActivityFacade();
		
		//list1 = activityFacade.getBDEActivity();
		
		//list1.forEach((n)-> acti.add(n.getTitle(),n.getStart(),n.getEnd())); 
		
		
		return acti;
		
	}

	
	public void logout(ActionEvent event) {
		   Routing.setCurrentUser(null);
		   super.goTo("LoginUI");
	   }
	
	
	@FXML
	private void createBDEActivity() {
		super.goTo("CreateBDEActivityUI");
	}

	
	public void activity(ActionEvent event) {
		super.goTo("HomePageUI");
	}
	
	
	public void home(ActionEvent event) {
		super.goTo("HomePageUI");
	}
	
	
	public void list(ActionEvent event) {
		super.goTo("EventUI");
	}
	
   
}

