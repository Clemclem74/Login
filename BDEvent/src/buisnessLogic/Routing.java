package buisnessLogic;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Routing {

	private static Stage stage;
	private static User currentUser;
	
	
	
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Routing.stage = stage;
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

		//change the scene with the LoginUI scene
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
	
	//change the scene with the homePageUI scene
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
	
	//change the scene with the ModifyUserUI scene
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
	
	//change the scene with the deleteUserUI scene
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

	//change the scene with the LoginUI scene
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
	
	//change the scene with the RegisterUI scene
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
	
	//change the scene with the CreateBDEUI scene
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

}