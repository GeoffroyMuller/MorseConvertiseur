package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


import centre.ArbreBinaire;
import centre.Listelettre;
public class Convertisseur {


	public static ArbreBinaire arbrebinaire_tete ;
	public static Listelettre listedelettre_tete ;
	
	public static String message_ = "";			//msg d'etat 
	public static boolean erreur_ = false;		//true = il ya une erreur 

	/**
	 * Charge le fichier de convertion
	 */
	public static void chargerFichierConvertisseur(){
		try {
			InputStream in = Convertisseur.class.getClass().getResourceAsStream("/Convertisseur.txt");
			BufferedReader bfr = new BufferedReader(new InputStreamReader(in));

			System.out.println(">Lecture du dictionnaire \"Convertisseur.txt\"");
			String sligne = "";
			String chaineliste ="";

			arbrebinaire_tete = new ArbreBinaire();
			ArbreBinaire arbrecourant = new ArbreBinaire();

			while((sligne = bfr.readLine())!=null) {
				System.out.println("ligne : "+sligne);
				creerArbreBinaireRec(arbrecourant, sligne, 2, true);
				chaineliste = chaineliste + sligne+"/";
			}
			listedelettre_tete = creerListedelettre(chaineliste);
			bfr.close();
			message("Dictionnaire \"Convertisseur.txt\" charge (arbrebinaire & listedelettre initialises)", false);
			
		}catch (Exception e) {
			// TODO: handle exception
			message("erreur > chargement du dictionnaire (\"Convertisseur.txt\") impossible", true);
			e.printStackTrace();
		}

	}

	/**
	 * Creer l'arbre binaire
	 * @param arbrecourant
	 * @param sligne
	 * @param index
	 * @param init
	 */
	private static void creerArbreBinaireRec(ArbreBinaire arbrecourant, String sligne, int index, boolean init) {
		try {
			if(!sligne.equals("")) {
				if(init) {
					arbrebinaire_tete = arbrecourant;
					init = false;
				}
				char lettre = sligne.charAt(0);
				if(index >= sligne.length()) {
					arbrecourant.setLettre(lettre);
				}else {
					char c = sligne.charAt(index);
					if( c == '.' ) {
						//System.out.println(">.");
						creerArbreBinaireRec(arbrecourant.creerRetournerGauche(), sligne , index+1, false);
					}else if ( c == '-' ) {
						//System.out.println(">-");
						creerArbreBinaireRec(arbrecourant.creerRetournerDroit(), sligne , index+1, false);
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			message("erreur > creation de l'arbre binaire", true);
			e.printStackTrace();
			
		}
	}

	/**
	 * Convertie Morse vers Texte
	 * @param s morse
	 * @return texte
	 */
	public static String convertirMorseTexte(String s) {
		return arbrebinaire_tete.chercherTexteCorrespondant(s);
	}




	/**
	 * Creer la liste
	 * @param remplirconv string qui contient l'ensemble du dictionnaire
	 * @return
	 */
	public static Listelettre creerListedelettre(String remplirconv) {

		String[] parts = remplirconv.split("/"); // Tableau constitué des couples Lettre & morse 
		Listelettre precedent = null; //noeuds precedent de celui qu'on traitera a chaque tour du while
		Listelettre premier = null; // premier noeuds de la liste

		for (int i=0; i< parts.length; i++)
		{
			String[] separ = parts[i].split("#"); // split lettre et morse a l'interieur d'un couple
			Listelettre listedelettre = new Listelettre(); // crée nouvelle Lettre dans la liste
			if (premier == null) premier=listedelettre; //on met ce noeuds comme premier noeud de la liste a la creation de cette dernière
			listedelettre.lettre = separ[0].charAt(0); // rempli le noeud de la liste avec la lettre
			listedelettre.morse = separ[1]; // idem avec son equivalence en morse
			if (precedent != null) // attache le noeuds avec celui d'avant
				precedent.suiv = listedelettre; // suivant du noeuds d'avant = noeuds present
			precedent = listedelettre; //sinon on initialise precedent avec le premier noeud de la liste
		}
		return premier;
	}

	/**
	 * Convertie Texte vers Morse
	 * @param aconvertir
	 * @return
	 */
	public static String convertirtxtmorse(String aconvertir){
		String resultatmorse = "";

		char ltr;
		System.out.println(aconvertir);
		for(int i=0; i<aconvertir.length(); i++) {
			ltr = aconvertir.charAt(i);
			String tradchar = Listelettre.tradmorse(ltr, listedelettre_tete);
			if (tradchar == "espace") tradchar = " ";
			if (tradchar == "rt") tradchar = "\n";
			resultatmorse = resultatmorse + tradchar+" ";

		}	

		return resultatmorse;
	}

	/**
	 * Charge le contenu du fichier choisi
	 * @param fichier : String chemin du fichier
	 * @return String contenu dans fichier
	 */
	public static String chargerFichier(String fichier) {
		String sligne = "";
		String chaineliste ="";
		try {
			InputStream in = Convertisseur.class.getClass().getResourceAsStream(fichier);
			BufferedReader bfr = new BufferedReader(new InputStreamReader(in));

			System.out.println(">fichier charger ");


			while((sligne = bfr.readLine())!=null) {
				chaineliste = chaineliste + sligne+"\n";
			}
			bfr.close();
			message("fichier "+fichier+" charger", false);
		}catch (Exception e) {
			message("erreur > chargement du fichier "+fichier+" impossible", true);
			e.printStackTrace();
		}
		return chaineliste;
	}

	/**
	 * Enregistre le dansle fichier specifier le contenu
	 * @param fichier : chemin du fichier
	 * @param contenu : String
	 */
	public static void enregistrerFichier(String fichier,String contenu) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(fichier, "UTF-8");
			//writer.println(La première ligne);
			//writer.println(La deuxième ligne);
			char c;
			for(int i=0; i<contenu.length(); i++) {
				c = contenu.charAt(i);
				if(c == '\n') {
					writer.println("");
				}else {
					writer.print(c);
				}
			}	
			writer.close();

			message("fichier "+fichier+" enregistrer", false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message("erreur > enregistrement du fichier "+fichier+" impossible", true);
			e.printStackTrace();
		}
	}

	public static void message(String message, boolean erreur) {
		erreur_ = erreur;
		message_ = message;
		System.out.println(""+message_);	
	}


	public static void main(String[] args) throws IOException {

		Convertisseur.chargerFichierConvertisseur();

		Convertisseur.convertirMorseTexte(".- .--. -- -. .-\n--.. -.--  --.. ");

		Convertisseur.convertirtxtmorse("abbb babox");
	}

	/*public static String test() throws IOException {
	InputStream in = Convertisseur.class.getClass().getResourceAsStream("/Convertisseur.txt");
    BufferedReader bfr = new BufferedReader(new InputStreamReader(in));
	String sligne = "";
	String test ="";

	while((sligne = bfr.readLine())!=null) {
		test = test + sligne+"/";
	}
	bfr.close();
	//System.out.println("Voici la strings de traduction : "+test);
	return test;
}*/

	/*public static String convertirtxtmorse(String aconvertir){
	Listelettre debutliste;
	String resultatmorse = "";
	try {
		debutliste = creerListedelettre(Convertisseur.test());
		char ltr;
		System.out.println(aconvertir);
		for(int i=0; i<aconvertir.length(); i++) {
			ltr = aconvertir.charAt(i);
			String tradchar = Listelettre.tradmorse(ltr, debutliste);
			if (tradchar == "espace") tradchar = "  ";
			resultatmorse = resultatmorse + tradchar+" ";
		}	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return resultatmorse;
}*/


}
