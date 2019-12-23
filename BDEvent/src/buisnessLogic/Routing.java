package buisnessLogic;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Routing {

	private static Stage stage;
	private static Stage confirmMessage;
	private static User currentUser;
	
	
	
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Routing.stage = stage;
	}

	public static Stage getConfirmMessage() {
		return confirmMessage;
	}

	public static void setConfirmMessage(Stage stage) {
		Routing.confirmMessage = stage;
	}
	
	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		Routing.currentUser = currentUser;
	}

	//Function which change the current stage with the stage enter in parameters
	public Routing(Stage primaryStage) {
		Routing.stage=primaryStage;
		// TODO Auto-generated constructor stub
	}

	public Routing() {
		// TODO Auto-generated constructor stub
	}

		
	//change the scene with the UiScene in parameter 
	
	public void goTo(String UiScene) {
		// TODO - implement Routing.loginForm
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/"+UiScene+".fxml"));
			Routing.stage.setScene(new Scene(root));
	        Routing.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	//change the scene with the homePageUI scene
	public void homePage() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/HomePageUI.fxml"));
			Routing.stage.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO - implement Routing.login_action

	}
	

	public void Event() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/EventUI.fxml"));
			Routing.stage.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO - implement Routing.login_action

	}

	
	public void confirmBDEMessage() {
		Parent root;
		
		try {
			Routing.confirmMessage=new Stage();
			root = FXMLLoader.load(getClass().getResource("/application/ConfirmMessageBDE.fxml"));
			Routing.confirmMessage.setScene(new Scene(root));
	        Routing.confirmMessage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void joinBDE() {
		Parent root;
		
		try {
			Routing.confirmMessage=new Stage();
			root = FXMLLoader.load(getClass().getResource("/application/JoinBDE.fxml"));
			Routing.confirmMessage.setScene(new Scene(root));
	        Routing.confirmMessage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void hideConfirmMessage() {
		Routing.confirmMessage.hide();
	}

}