package application;
	
import buisnessLogic.Routing;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
  

public class Main extends Application {
	@Override
	
	
    public void start(Stage primaryStage) {
        try {
            // Read file fxml and draw interface.
        	
        	Routing root = new Routing(primaryStage);
        	root.goTo("CreateBDEActivityUI");
         
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
