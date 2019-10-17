package application;

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
	private Label label_charger;
	@FXML
	private Label label_enregistrer;
	@FXML
	private static Label labelmsg;

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

	@FXML
	public static void affiche_Erreur(String s){
		labelmsg.setTextFill(Paint.valueOf("#A30000"));
		labelmsg.setText(">"+s);
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
		label_charger.setText("/Convertir/Texte.txt");
		label_2.setText("Morse");
		label_enregistrer.setText("/Convertir/Morse.txt");
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
		label_charger.setText("/Convertir/Morse.txt");
		label_2.setText("Texte");
		label_enregistrer.setText("/Convertir/Texte.txt");
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
	 * Converti Automatiquement le Morse ou le Texte textarea_1 vers textarea_2 (checkBoxAutoConv doit etre selectionner)
	 */
	@FXML
	public void Auto_Convertir() {
		if(checkBoxAutoConv.isSelected()) {
			bt_Convertir();
		}

	}

	/**
	 * Converti Automatiquement le Morse ou le Texte textarea_2 vers textarea_1 (checkBoxAutoConv doit etre selectionner)
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

	/**
	 * Echange le texte  textarea_1 vers textarea_2 et textarea_2 vers textarea_1
	 */
	private void changerTexte() {
		String s1 = textarea_1.getText();
		String s2 = textarea_2.getText();
		textarea_1.setText(s2);
		textarea_2.setText(s1);
		Auto_Convertir();
	}

	/**
	 * Charge le fichier Morse.txt ou le fichier Texte.txt (selon le bt selectionner)
	 * @throws InterruptedException
	 */
	@FXML
	public void chargerFichier() throws InterruptedException {
		if(bt_MorseTexte.isSelected()) {
			textarea_1.setText(Convertisseur.chargerFichier("/Convertir/Morse.txt"));
		}else if(bt_TexteMorse.isSelected()) {
			textarea_1.setText(Convertisseur.chargerFichier("/Convertir/Texte.txt"));
		}
		Auto_Convertir();
	}

	/**
	 * Charge le dictionnaire
	 * @throws InterruptedException
	 */
	@FXML
	public void chargerDictionnaire() throws InterruptedException {
		Convertisseur.chargerFichierConvertisseur();
		Auto_Convertir();
	}

	/**
	 * enregistrement fichier.txt en fonction du bt selectionner
	 * @throws InterruptedException
	 */
	@FXML
	public void enregistrerFichier() throws InterruptedException {
		
		if(bt_MorseTexte.isSelected()) {
			Convertisseur.enregistrerFichier("Convertir/Texte.txt", textarea_2.getText());
		}else if(bt_TexteMorse.isSelected()) {
			Convertisseur.enregistrerFichier("Convertir/Morse.txt", textarea_2.getText());
		}
		Auto_Convertir();
	}

}
