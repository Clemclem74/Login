package application;

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
import javafx.scene.control.TextField;

public class modifyPollUI extends Routing implements Initializable  {

	  	@FXML
	    private TextField pollTitle;
	
	    @FXML
	    private TextField field1;
	
	    @FXML
	    private TextField field2;
	
	    @FXML
	    private TextField field3;
	    
	    private ArrayList<String> arrayChoice = new ArrayList<>();
	    
	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
		PollFacade pollFacade = new PollFacade();
		Poll poll = new Poll();
		arrayChoice = poll.splitChoices(poll.getchoices_pollBB());
		this.pollTitle.setText(poll.getTitle_pollBB());
		this.field1.setText(arrayChoice.get(0));
		this.field2.setText(arrayChoice.get(1));
		this.field3.setText(arrayChoice.get(2));
		
	    }

	    @FXML
	    void modifyAction(ActionEvent event) {
	    	PollFacade pollFacade = new PollFacade();
			Poll poll = new Poll();
			BDEFacade bdeFacade = new BDEFacade();
			BDE bde = bdeFacade.findById(super.getCurrentUser().getCurrentBDE());
			int idBde = bde.getIdBDE();
			User user = super.getCurrentUser();
			int userId = user.getId_user();
			pollFacade.modify(userId, pollTitle.getText(), getText(), idBde);

	    	

	    }
	    
	    public ArrayList<String> getText() {
			 ArrayList<String> listchoice = new ArrayList<>();
			 listchoice.add(field1.getText());
			 listchoice.add(field2.getText());
			 listchoice.add(field3.getText());
			 return listchoice;
		}
	    
	    

}
