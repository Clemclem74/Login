package application;

import buisnessLogic.UserFacade;
import buisnessLogic.BDEFacade;
import buisnessLogic.Routing;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class ModifyBDEUI extends Routing implements Initializable {
		@FXML
		private Button createButton;
		@FXML
		private TextField nameBDEField;
		@FXML
		private TextField schoolBDEField;

	  
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   this.nameBDEField.setText("Nom du BDE");
		   this.schoolBDEField.setText("Nom de l'école");
	       // TODO (don't really need to do anything here).
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   public void modifyAction(ActionEvent event) {
		       BDEFacade bdeFacade = new BDEFacade();
		       bdeFacade.create(super.getCurrentUser(), nameBDEField.getText(),schoolBDEField.getText());
		       super.goTo("HomePageUI");
	   }
}
