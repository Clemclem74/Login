package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Poll;
import buisnessLogic.PollFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;
import buisnessLogic.UserFacade;
import buisnessLogic.Vote;
import buisnessLogic.VoteFacade;
import javafx.fxml.Initializable;

public class PollUI extends Routing implements Initializable {
	
	ArrayList<Poll> pollL = new ArrayList<>();
	
	ObservableList<Integer> choiceList = FXCollections.observableArrayList(1,2,3);
	
	@FXML
	 private Button homePage;
	 @FXML
	 private Button newPoll; 
	 @FXML
	 private Button logoutButton;
	 @FXML
	 private Button modifyPoll;
	 @FXML
	 private Button deletePoll; 
	 @FXML
	 private ListView<String> pollList;
	 @FXML
	 private Label titlePollSelected;
	 @FXML
	 private Label publisherName;
	 @FXML
	 private Label choices1;
	 @FXML
	 private Label choices2;
	 @FXML
	 private Label choices3;
	 @FXML
	 private ChoiceBox voteCheckbox;
	 @FXML
	 private Button voteButton;
	 @FXML
	 private Label counter1;
	 @FXML
	 private Label counter2;
	 @FXML
	 private Label counter3;
	 
	 
	
	
//--------------------------------------CONTROLEUR Create and Modifyy Poll BB----------------------------------------------
	 	@FXML
		private TextField pollTitle;
		@FXML
		private Button createPoll;
		@FXML
		private TextField field1;
		@FXML
		private TextField field2;
		@FXML
		private TextField field3;
		@FXML
		private Label errorMessage;
		@FXML
		private Button backButton;
		@FXML
		private Button ModifyButton;

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			System.out.println("Vue actuelle " + super.getVue());
			
			if(super.getVue().contentEquals("ModifyPoll")) {
		    	System.out.println("if modify");
				Poll poll = super.getCurrentPoll();
				pollTitle.setText(poll.getTitle_pollBB());
				String stringChoices = "";
				ArrayList<String> arrayChoices = new ArrayList<>();
				stringChoices = poll.getchoices_pollBB();
				arrayChoices = poll.splitChoices(stringChoices);
				field1.setText(arrayChoices.get(0));
				field2.setText(arrayChoices.get(1));
				field3.setText(arrayChoices.get(2));
			}
			else
				if(super.getVue().contentEquals("createPoll")) {
					
				}
				else {
					super.setCurrentPoll(null);
					displayPoll();
					modifyPoll.setVisible(false);
					deletePoll.setVisible(false);
					choices1.setVisible(false);
					choices2.setVisible(false);
					choices3.setVisible(false);
					voteCheckbox.setVisible(false);
					voteButton.setVisible(false);
					}
		}
		
		
		private void displayPoll() {
			pollL.removeAll(pollL);
			super.setCurrentPoll(null);
			User user = super.getCurrentUser();
			PollFacade pollFacade = new PollFacade();
			pollL = pollFacade.findAllPollBDE(user);
			ArrayList<String> titlePoll = new ArrayList<String>();
			pollL.forEach((n)-> titlePoll.add(n.getTitle_pollBB()));
			
			pollList.getItems().addAll(titlePoll);
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
		
		public void newPoll(ActionEvent event) {
			super.setVue("createPoll");
			super.goTo("CreatePollUI");
		}
		
		@FXML
		private void displaySelectedList(MouseEvent event) {
			choices1.setVisible(true);
			choices2.setVisible(true);
			choices3.setVisible(true);
			voteCheckbox.setVisible(true);
			voteButton.setVisible(true);
			
			voteCheckbox.setValue(1);
			voteCheckbox.setItems(choiceList);
			
			
			String state;
			String stringChoices = "";
			ArrayList<String> arrayChoices = new ArrayList<>();
			PollFacade pollFacade = new PollFacade();
			VoteFacade voteFacade = new VoteFacade();
			UserFacade userFacade = new UserFacade();
			String poll1 = pollList.getSelectionModel().getSelectedItem();
			Poll pollSelected = pollFacade.find(poll1);
			super.setCurrentPoll(pollSelected);			
			
			if(poll1 != null) {
				this.titlePollSelected.setText(pollSelected.getTitle_pollBB());
				stringChoices = pollSelected.getchoices_pollBB();
				arrayChoices = pollSelected.splitChoices(stringChoices);
				this.choices1.setText(arrayChoices.get(0));
				this.choices2.setText(arrayChoices.get(1));
				this.choices3.setText(arrayChoices.get(2));
				User publisher = userFacade.findById(pollSelected.getId_user_publisher());
				String namePublisher = publisher.getFirstname() + " "+publisher.getLastname();
				this.publisherName.setText(namePublisher);
				this.modifyPoll.setVisible(false);
				this.deletePoll.setVisible(false);
				if(super.getCurrentUser().isPublisherPoll(Routing.getCurrentPoll()) || super.getCurrentUser().isAdminOfHisBDE()){
					modifyPoll.setVisible(true);
					deletePoll.setVisible(true);
				if(voteFacade.alreadyvoted(super.getCurrentPoll().getId_pollBB(), super.getCurrentUser())) {
					voteButton.setVisible(false);
				}
				ArrayList<Integer> counter = new ArrayList<>();
				ArrayList<Vote> list = new ArrayList<>();
				list = voteFacade.findAllVoteBDE(super.getCurrentPoll().getId_pollBB(), super.getCurrentUser());
				counter = voteFacade.counterVotes(list, super.getCurrentPoll().getId_pollBB());
				System.out.println(counter.get(0).toString());
				this.counter1.setText(counter.get(0).toString());
				this.counter2.setText(counter.get(1).toString());
				this.counter3.setText(counter.get(2).toString());
					
				}
			}
		}
		
		@FXML 
		private void deleteSelected(ActionEvent event){
			PollFacade pollFacade = new PollFacade();
			pollFacade.delete(super.getCurrentPoll());
			pollList.getItems().clear();
			
			super.openPopUp("You're poll has been deleted", "");
			this.titlePollSelected.setText("");
			this.publisherName.setText("");
			this.choices1.setText("");
			this.choices2.setText("");
			this.choices3.setText("");
		}
		
		@FXML 
		void modifySelected(ActionEvent event) {
			super.setVue("ModifyPoll");
			super.goTo("modifyPollUI");
		}
		
		
		
		public ArrayList<String> getTextChoices() {
			 ArrayList<String> listchoice = new ArrayList<>();
			 listchoice.add(field1.getText());
			 listchoice.add(field2.getText());
			 listchoice.add(field3.getText());
			 return listchoice;
		}
		
		
//---------------------------------------------CONTROLEUR CREATE POLL BB--------------------

		//when user click on createPoll button
			//this method will be called.
		public void createPoll(ActionEvent event) {
			PollFacade pollFacade = new PollFacade();
			Poll poll = new Poll();
			BDEFacade bdeFacade = new BDEFacade();
			BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
			int idBde = bde.getIdBDE();
			User user = super.getCurrentUser();
			int userId = user.getId_user();
			System.out.println("crationd de poll en cours");
			int res = pollFacade.createPoll(userId, pollTitle.getText(), getTextChoices(), idBde);
			
			if(res < 0) {
				this.errorMessage.setText("Please make sure the fields are completed");
			}
			else {
				super.setVue("BasicPoll");
				super.goTo("PollUI");
				ConfirmMessageUI.setParams(Integer.toString(res));
				super.openPopUp("Your poll has been created", "");
			}
		}
		
		public void backPoll(ActionEvent event) {
			super.setVue("BasicPoll");
			super.goTo("PollUI");
		}
		
		
		
//---------------------------------------------CONTROLEUR MODIFY POLL BB--------------------

	    private ArrayList<String> arrayChoice = new ArrayList<>();
	    
	    //When user click on updatePoll
	    //this method will be called.
	    public void modifyPoll(ActionEvent event) {
	    	PollFacade pollFacade = new PollFacade();
			Poll poll = super.getCurrentPoll();
			int idPoll = poll.getId_pollBB();
			System.out.println("modification de poste en cours");
			BDEFacade bdeFacade = new BDEFacade();
			BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
			int idBde = bde.getIdBDE();
			User user = super.getCurrentUser();
			int userId = user.getId_user();
			int res = pollFacade.modify(idPoll,userId, pollTitle.getText(), getTextChoices(), idBde);
			
			if(res < 0) {
				//this.errorMessage.setText("Please make sure the fields are completed");
			}
			else {
				super.setVue("BasicPoll");
				super.goTo("PollUI");
				ConfirmMessageUI.setParams(Integer.toString(res));
				super.openPopUp("Poll modified with sucess", "");
				super.setCurrentPoll(null);
			}

	    	

	    }
	    
//---------------------------------------------CONTROLEUR VOTE--------------------
	    	
	    	public void createVote(ActionEvent event) {
	    		Poll poll = super.getCurrentPoll();
	    		int choice = (int) voteCheckbox.getValue();
	    		int idPoll = poll.getId_pollBB();
	    		VoteFacade voteFacade = new VoteFacade();
				User user = super.getCurrentUser();
				BDEFacade bdeFacade = new BDEFacade();
				BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
				int idBde = bde.getIdBDE();
				int userId = user.getId_user();
				
				int res = voteFacade.addVote(userId, choice, idBde, idPoll);
				if(res < 0) {
					//this.errorMessage.setText("Please make sure the fields are completed");
				}
				else {
					super.setVue("BasicPoll");
					super.goTo("PollUI");
					ConfirmMessageUI.setParams(Integer.toString(res));
					super.openPopUp("Voted with sucess", "");
					super.setCurrentPoll(null);
				}
				
	    	}
}
