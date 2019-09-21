package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage windows) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../design_interface/_form.fxml"));
			//BorderPane root = new BorderPane();
			Parent root = loader.load();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("../design_interface/application.css").toExternalForm());
			windows.setTitle("TTT");
			windows.setScene(scene);
			windows.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
