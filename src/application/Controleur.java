package application;

import java.awt.Color;
import java.io.IOException;

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
	public void afficheBT_Test2() throws InterruptedException {
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
		changerTexte();
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
		changerTexte();
		//System.out.println("Morse > Texte");
	}

	/**
	 * Converti le Morse ou le Texte
	 */
	@FXML
	public void bt_Convertir() {

		if(bt_TexteMorse.isSelected()) {
			textarea_2.setText(Convertisseur.convertirtxtmorse(textarea_1.getText()));
		}else if(bt_MorseTexte.isSelected()) {
			textarea_2.setText(Convertisseur.convertirMorseTexte(textarea_1.getText()));
		}

	}

	/**
	 * Converti Automatiquement le Morse ou le Texte textarea_1 vers textarea_2
	 */
	@FXML
	public void Auto_Convertir() {
		if(checkBoxAutoConv.isSelected()) {
			bt_Convertir();
		}

	}

	/**
	 * Converti Automatiquement le Morse ou le Texte textarea_2 vers textarea_1
	 */
	@FXML
	public void Auto_Convertir_textarea_2() {
		if(checkBoxAutoConv.isSelected()) {
			if(bt_MorseTexte.isSelected()) {
				textarea_1.setText(Convertisseur.convertirtxtmorse(textarea_2.getText()));
			}else if(bt_TexteMorse.isSelected()) {
				textarea_1.setText(Convertisseur.convertirMorseTexte(textarea_2.getText()));
			}
		}

	}

	private void changerTexte() {
		String s1 = textarea_1.getText();
		String s2 = textarea_2.getText();
		textarea_1.setText(s2);
		textarea_2.setText(s1);
	}



}
