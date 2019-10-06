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
			Parent root = loader.load();
			Scene scene = new Scene(root,929,550);
			scene.getStylesheets().add(getClass().getResource("../design_interface/css_form.css").toExternalForm());
			windows.setMinWidth(945);
			windows.setMinHeight(580);
			windows.setMaxWidth(945);
			windows.setTitle("Morse Convertisseur");
			windows.setScene(scene);
			windows.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Convertisseur.chargerFichierConvertisseur();
		launch(args);
	}
}
