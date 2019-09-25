package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class Controleur {
	
	@FXML
	private Label label_1;
	@FXML
	private Label label_2;
	@FXML
	private ToggleButton bt_TexteMorse;
	@FXML
	private ToggleButton bt_MorseTexte;
	
    @FXML
    public void afficheBT_Test() {
        System.out.println("Test");
        label_1.setText("Test1");
        label_2.setText("Test2");
        
    }
    
    @FXML
    public void bt_TexteMorse_Disable() {
    	bt_TexteMorse.setDisable(true);
    	bt_MorseTexte.setDisable(false);
    	bt_MorseTexte.setSelected(false);
        label_1.setText("Texte");
        label_2.setText("Morse");
    }
    @FXML
    public void bt_MorseTexte_Disable() {
    	bt_MorseTexte.setDisable(true);
    	bt_TexteMorse.setDisable(false);
    	bt_TexteMorse.setSelected(false);
        label_1.setText("Morse");
        label_2.setText("Texte");
    }
    

}
