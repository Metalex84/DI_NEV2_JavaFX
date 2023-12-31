import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    
		@Override
	    public void start(Stage primaryStage) throws Exception {

			Parent root = FXMLLoader.load(getClass().getResource("mainview.fxml"));
		    primaryStage.setTitle("Desarrollo de Interfaces - DAM2");
		    primaryStage.setScene(new Scene(root, 350, 600));
	        primaryStage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }

}
