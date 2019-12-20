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

	public Routing(Stage primaryStage) {
		Routing.stage=primaryStage;
		// TODO Auto-generated constructor stub
	}

	public Routing() {
		// TODO Auto-generated constructor stub
	}

	public void loginForm() {
		// TODO - implement Routing.loginForm
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/LoginUi.fxml"));
			Routing.stage.setScene(new Scene(root));
	        Routing.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void homePage() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/HomePageUi.fxml"));
			Routing.stage.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO - implement Routing.login_action

	}
	
	
	public void modifyUser() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/ModifyUserUi.fxml"));
			Routing.stage.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteUser() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/DeleteUserUi.fxml"));
			Routing.stage.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void display_error(String msg) {
//		Parent root;
//		try {
//			root = FXMLLoader.load(getClass().getResource("/application/LoginUi.fxml"));
//			Routing.stage.setScene(new Scene(root));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void logout() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/LoginUi.fxml"));
			Routing.stage.setScene(new Scene(root));
	        Routing.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void goToRegister() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/RegisterUi.fxml"));
			Routing.stage.setScene(new Scene(root));
	        Routing.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void createBDE() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/CreateBDEUI.fxml"));
			Routing.stage.setScene(new Scene(root));
	        Routing.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void hideConfirmMessage() {
		Routing.confirmMessage.hide();
	}

}