package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Meeting;
import buisnessLogic.MeetingFacade;
import buisnessLogic.Participe;
import buisnessLogic.ParticipeFacade;
import buisnessLogic.Poll;
import buisnessLogic.PollFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;
import buisnessLogic.UserFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MeetingUI extends Routing implements Initializable {
	
	ArrayList<Meeting> meetingL = new ArrayList<>();
	
	ObservableList<String> choiceList = FXCollections.observableArrayList("oui","non");
	
	@FXML
	private Button newMeeting;
	@FXML
	private Button HomePage;
	@FXML
	private Button logoutButton;
	@FXML
	private Button modifyMeeting;
	@FXML
	private Button deleteMeeting;
	@FXML
	private ListView<String> MeetingList;
	@FXML
	private Label titleMeetingSelected;
	@FXML
	private Text textName;
	@FXML
	private Label publisherName;
	@FXML
	private Label selectedDate;
	@FXML 
	private Text textDate;
	@FXML
	private ChoiceBox participeCheckbox;
	@FXML
	private Button participe_button;
	@FXML
	private Label nboui;
	@FXML
	private Label nbnon;
	
	
	
	
//--------------------------------------CONTROLEUR Create and Modify MEETING----------------------------------------------

	@FXML
	private TextField titleMeeting;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Button createMeeting;
	@FXML
	private Button ModifyMeeting;
	@FXML
	private Button backButton;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Vue actuelle " + super.getVue());
		if(super.getVue().contains("ModifyMeeting")) {
			System.out.println("if modify");
			Meeting meeting = super.getCurrentMeeting();
			titleMeeting.setText(meeting.getTitle());
			//how to convert string to localdate
			
			
		}
		else {
			if(super.getVue().contentEquals("createMeeting")) {
				
			}
			else {
				super.setCurrentMeeting(null);
				displayMeeting();
				modifyMeeting.setVisible(false);
				deleteMeeting.setVisible(false);
				selectedDate.setVisible(false);
				participeCheckbox.setVisible(false);
				participe_button.setVisible(false);
			}
		}
	}
	
	public void HomePage(ActionEvent event) {
		super.setVue("Homepage");
		super.goTo("HomePageUI");
	}
	
	public void logout(ActionEvent event) {
		Routing.setCurrentUser(null);
		super.setVue("login");
		super.goTo("LoginUI");
	}
	
	public void newMeeting(ActionEvent event) {
		super.setVue("createMeeting");
		super.goTo("CreateMeetingUI");
	}
	
	public void backMeeting(ActionEvent event) {
		super.setVue("BasicMeeting");
		super.goTo("MeetingUI");
	}




	private void displayMeeting() {
		meetingL.removeAll(meetingL);
		super.setCurrentMeeting(null);
		User user = super.getCurrentUser();
		MeetingFacade meetingFacade = new MeetingFacade();
		meetingL = meetingFacade.findAllMeetingBDE(user);
		ArrayList<String> titleMeeting = new ArrayList<>();
		meetingL.forEach((n)-> titleMeeting.add(n.getTitle()));
		System.out.println(titleMeeting);
		
		MeetingList.getItems().addAll(titleMeeting);
		
	}
	
	@FXML
	private void displaySelectedMeeting(MouseEvent event) {
		selectedDate.setVisible(true);
		nboui.setVisible(true);
		nbnon.setVisible(true);
		participe_button.setVisible(true);
		participeCheckbox.setVisible(true);
		
		participeCheckbox.setValue("oui");
		participeCheckbox.setItems(choiceList);
		
		
		MeetingFacade meetingFacade = new MeetingFacade();
		UserFacade userFacade = new UserFacade();
		String meeting1 = MeetingList.getSelectionModel().getSelectedItem();
		Meeting meetingSelected = meetingFacade.find(meeting1);
		ParticipeFacade participeFacade = new ParticipeFacade();
		super.setCurrentMeeting(meetingSelected);
		
		if(meeting1 != null) {
			this.titleMeetingSelected.setText(meetingSelected.getTitle());
			this.selectedDate.setText(meetingSelected.getMeeting_date());
			User publisher = userFacade.findById(meetingSelected.getPublisher_meeting());
			String namePublisher = publisher.getFirstname() + " "+publisher.getLastname();
			this.publisherName.setText(namePublisher);
			System.out.println(super.getCurrentUser().getId_user() == meetingSelected.getPublisher_meeting());
			System.out.println(super.getCurrentUser().isAdminOfHisBDE() == false);
			System.out.println(meetingSelected.getPublisher_meeting());
			if(super.getCurrentUser().isPublisherMeeting(Routing.getCurrentMeeting()) || super.getCurrentUser().isAdminOfHisBDE()) {
				this.modifyMeeting.setVisible(true);
				this.deleteMeeting.setVisible(true);
			}
			if(participeFacade.alreadyParticiped(super.getCurrentMeeting().getId_meeting(), super.getCurrentUser())) {
				participe_button.setVisible(false);
			}
			}
			ArrayList<Integer> counter = new ArrayList<>();
			ArrayList<Participe> list = new ArrayList<>();
			list = participeFacade.findAllParticipeBDE(super.getCurrentMeeting().getId_meeting(), super.getCurrentUser());
			counter = participeFacade.CounterParticipes(list, super.getCurrentMeeting().getId_meeting());
			this.nboui.setText(counter.get(0).toString());
			this.nbnon.setText(counter.get(1).toString());
			
			
			
		}

	
	@FXML void deleteSelected(ActionEvent event) {
		MeetingFacade meetingFacade = new MeetingFacade();
		meetingFacade.delete(super.getCurrentMeeting());
		MeetingList.getItems().clear();
		MeetingList.getItems();
		super.openPopUp("You're Meeting has been deleted", "");
		this.titleMeetingSelected.setText("");
		this.selectedDate.setText("");
	}
	
	@FXML 
	void modifySelected(ActionEvent event) {
		super.setVue("ModifyMeeting");
		super.goTo("modifyMeetingUI");
	}
	
//---------------------------------------------CONTROLEUR CREATE MEETING--------------------

	
	public void createMeeting(ActionEvent event) {
		MeetingFacade meetingFacade = new MeetingFacade();
		Meeting meeting = new Meeting();BDEFacade bdeFacade = new BDEFacade();
		BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
		int idBde = bde.getIdBDE();
		User user = super.getCurrentUser();
		int userId = user.getId_user();
		System.out.println("crationd de poll en cours");
		int res = meetingFacade.create(userId, titleMeeting.getText(), datePicker.getValue().toString(), idBde);
		
		if(res < 0) {
			//this.errorMessage.setText("Please make sure the fields are completed");
		}
		else {
			super.setVue("BasicMeeting");
			super.goTo("MeetingUI");
			ConfirmMessageUI.setParams(Integer.toString(res));
			super.openPopUp("Your meeting has been created", "");
		}
	}
	
	
	//---------------------------------------------CONTROLEUR MODIFY MEETING--------------------

   
    
    //When user click on updateMeeting
    //this method will be called.
    public void modifyMeeting(ActionEvent event) {
  
    	MeetingFacade meetingFacade = new MeetingFacade();
		Meeting meeting = super.getCurrentMeeting();
		int idMeeting = meeting.getId_meeting();
		System.out.println("modification de Meeting en cours");
		BDEFacade bdeFacade = new BDEFacade();
		BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
		int idBde = bde.getIdBDE();
		User user = super.getCurrentUser();
		int userId = user.getId_user();
		int res = meetingFacade.modify(idMeeting, userId, titleMeeting.getText(), datePicker.getEditor().getText(), idBde);
		if(res < 0) {
			//this.errorMessage.setText("Please make sure the fields are completed");
		}
		else {
			super.setVue("BasicMeeting");
			super.goTo("MeetingUI");
			ConfirmMessageUI.setParams(Integer.toString(res));
			super.openPopUp("Meeting modified with sucess", "");
			super.setCurrentMeeting(null);
		}

    	

    }
    
    
  //---------------------------------------------CONTROLEUR PARTICIPE--------------------

	public void createParticipe(ActionEvent event) {
		Meeting meeting = super.getCurrentMeeting();
		String choice = (String) participeCheckbox.getValue();
		ParticipeFacade participeFacade = new ParticipeFacade();
		User user = super.getCurrentUser();
		int userId = user.getId_user();
		BDEFacade bdeFacade = new BDEFacade();
		BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
		int idBde = bde.getIdBDE();
		
		
		int res = participeFacade.addParticipe(userId, meeting.getId_meeting(), choice, idBde);
		if(res < 0) {
			//this.errorMessage.setText("Please make sure the fields are completed");
		}
		else {
			super.setVue("BasicMeeting");
			super.goTo("MeetingUI");
			ConfirmMessageUI.setParams(Integer.toString(res));
			super.openPopUp("Participed with sucess", "");
			super.setCurrentPoll(null);
		}
	}

}
