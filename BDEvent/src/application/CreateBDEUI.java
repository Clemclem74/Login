package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDEFacade;
import buisnessLogic.Routing;
import buisnessLogic.User;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class CreateBDEUI extends Routing implements Initializable {
	//Declaration of component of the fxml file.
		@FXML
		private Button createButton;
		@FXML
		private TextField nameBDEField;
		@FXML
		private TextField schoolBDEField;
		@FXML
		private Label idBDELabel;
	  
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void createAction(ActionEvent event) {
		       BDEFacade bdeFacade = new BDEFacade();
		       UserFacade userFacade = new UserFacade();
		       User user = super.getCurrentUser();
		       System.out.println(user.getPassworduser());
		       int res = bdeFacade.create(user, nameBDEField.getText(),schoolBDEField.getText());
		       //System.out.println("L'ID du nouveau BDE est le : " + res);
		       userFacade.join(user, res);
		       super.getCurrentUser().setCurrentBDE(res);
		       if (res < 0 ) {
		    	   //ERROR MESSAGE 
		       }
		       else {
		    	   ConfirmMessageUI.setParams(Integer.toString(res));
		    	   super.goToLittleWindow("ConfirmMessageBDEUI");
		    	   //this.idBDELabel.setText("8");
		       }
		       
	   }
}
