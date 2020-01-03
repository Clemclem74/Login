package application;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import buisnessLogic.BDE;
import buisnessLogic.BDEFacade;
import buisnessLogic.Poll;
import buisnessLogic.PollFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class CreatePollUI extends Routing implements Initializable {
	
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO (don't really need to do anything here).
		}
	
	public void createAction(ActionEvent event) {
		PollFacade pollFacade = new PollFacade();
		Poll poll = new Poll();
		BDEFacade bdeFacade = new BDEFacade();
		BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
		int idBde = bde.getIdBDE();
		User user = super.getCurrentUser();
		int userId = user.getId_user();
		int res = pollFacade.createPoll(userId, pollTitle.getText(), getText(), idBde);
		
	};
	
	public ArrayList<String> getText() {
		 ArrayList<String> listchoice = new ArrayList<>();
		 listchoice.add(field1.getText());
		 listchoice.add(field2.getText());
		 listchoice.add(field3.getText());
		 return listchoice;
	}
	
	
		

	
	
	
	

}
