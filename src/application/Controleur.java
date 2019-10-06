package application;

import java.awt.Color;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Paint;

public class Controleur {

	@FXML
	private Label label_1;
	@FXML
	private Label label_2;
	@FXML
	private Label labelmsg;

	@FXML
	private TextArea textarea_1;
	@FXML
	private TextArea textarea_2;

	@FXML
	private ToggleButton bt_TexteMorse;
	@FXML
	private ToggleButton bt_MorseTexte;

	@FXML
	private CheckBox checkBoxAutoConv;

	@FXML
	public void afficheBT_Test() {
		System.out.println("Test");
		label_1.setText("Test1");
		label_2.setText("Test2");

		labelmsg.setTextFill(Paint.valueOf("#410009"));
		labelmsg.setText(">Testmsg");

		textarea_1.setText("test1");
		textarea_2.setText("test2");
	}

	@FXML
	public void afficheBT_Test2() {
		labelmsg.setText(">Testmsg");
	}

	/**
	 * change l'etat du bt TexteMorse
	 */
	@FXML
	public void bt_TexteMorse_Disable() {
		bt_TexteMorse.setDisable(true);
		bt_MorseTexte.setDisable(false);
		bt_MorseTexte.setSelected(false);
		label_1.setText("Texte");
		label_2.setText("Morse");

		//System.out.println("Texte > Morse");
	}

	/**
	 * change l'etat du bt MorseTexte
	 */
	@FXML
	public void bt_MorseTexte_Disable() {
		bt_MorseTexte.setDisable(true);
		bt_TexteMorse.setDisable(false);
		bt_TexteMorse.setSelected(false);
		label_1.setText("Morse");
		label_2.setText("Texte");

		//System.out.println("Morse > Texte");
	}

	/**
	 * Converti le Morse ou le Texte
	 */
	@FXML
	public void bt_Convertir() {

		if(bt_TexteMorse.isSelected()) {
			//System.out.println("Texte > Morse");

		}else if(bt_MorseTexte.isSelected()) {
			//System.out.println("Morse > Texte");
			textarea_2.setText(Convertisseur.convertirMorseTexte(textarea_1.getText()));
		}

	}

	@FXML
	public void Auto_Convertir() {
		if(checkBoxAutoConv.isSelected()) {
			if(bt_TexteMorse.isSelected()) {
				//System.out.println("Texte > Morse");

			}else if(bt_MorseTexte.isSelected()) {
				//System.out.println("Morse > Texte");
				textarea_2.setText(Convertisseur.convertirMorseTexte(textarea_1.getText()));
			}
		}

	}



}
