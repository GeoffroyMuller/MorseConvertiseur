package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import centre.ArbreBinaire;

public class Convertisseur {


	ArbreBinaire abp ;

	public void chargerFichierConvertisseur(){

		try {
			String fichier = "src/centre/Convertisseur.txt";
			BufferedReader bfr = new BufferedReader(new FileReader(fichier));
			System.out.println(">fichier "+fichier+" charger");
			String res = "";
			String sligne = "";
			String[] smots;
			abp = new ArbreBinaire();
			ArbreBinaire abs = new ArbreBinaire();
			while((sligne = bfr.readLine())!=null) {
				System.out.println("ligne : "+sligne);
				creerArbreBinaireRec(abs, sligne, 2, true);
			}


		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void creerArbreBinaireRec(ArbreBinaire ab, String sligne, int index, boolean init) {
		if(!sligne.equals("")) {
			if(init) {
				abp = ab;
				init = false;
			}

			char lettre = sligne.charAt(0);
			if(index >= sligne.length()) {
				ab.setLettre(lettre);
			}else {
				char c = sligne.charAt(index);
				if( c == '.' ) {
					System.out.println(">.");
					creerArbreBinaireRec(ab.creerRetournerGauche(), sligne , index+1, false);
				}else if ( c == '-' ) {
					System.out.println(">-");
					creerArbreBinaireRec(ab.creerRetournerDroit(), sligne , index+1, false);
				}
			}
		}
	}

	public String convertirMorseTexte(String s) {
		String res = "";

		ArbreBinaire a = new ArbreBinaire();
		a=abp;

		for(int i = 0;i<s.length();i++) {
			System.out.println("length "+s.length()+"   i::: "+i);
			//try {
			char c = s.charAt(i);
			if( c == '.' ) {
				System.out.println(">.");
				a = a.getGauche();
			}else if ( c == '-' ) {
				System.out.println(">-");
				a = a.getDroit();
			}else if ( c == ' ') {
				System.out.println("lettre :: "+a.getLettre());
				res = res + a.getLettre();
				if( s.charAt(i+1) == ' ' ) {
					res = res + " ";
				}
			}
			//System.out.println("zte "+res);
			/*}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}*/
		}

		System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzz  "+res);

		return null;
	}






	public static Listelettre txtsplit(String remplirconv) {

		String[] parts = remplirconv.split("/"); // split couple
		Listelettre precedent = null; //noeuds precedent de celui qu'on traitera a chaque tour du while
		Listelettre premier = null; // premier noeuds de la liste

		//System.out.println("Voici la liste qui en decoule : ");
		for (int i=0; i< parts.length; i++)
		{
			// split lettre - morse
			String[] separ = parts[i].split("#"); // split lettre et morse
			Listelettre listedelettre = new Listelettre();
			if (premier == null) premier=listedelettre; //premiere valeur de la liste
			listedelettre.lettre = separ[0].charAt(0); // rempli le noeud de la liste 
			listedelettre.morse = separ[1];
			if (precedent != null) // attache le noeuds avec celui d'avant
				precedent.suiv = listedelettre; // suivant du noeuds d'avant = noeuds present
			precedent = listedelettre;
			//Listelettre.afficher(listedelettre);
		}
		return premier;
	}




	public static boolean estpasvide(String tabl) {
		if (tabl == null) return false;
		if (tabl == "") return false;
		else return true;
	}




	public static String test() throws IOException {
		String fichier = "src/centre/Convertisseur.txt";
		BufferedReader bfr = new BufferedReader(new FileReader(fichier));
		System.out.println(">fichier "+fichier+" charger");
		String res = "";
		String sligne = "";
		String[] smots;
		String test ="";

		while((sligne = bfr.readLine())!=null) {
			test = test + sligne+"/";
		}
		//System.out.println("Voici la strings de traduction : "+test);
		return test;
	}





	public static void main(String[] args) throws IOException {

		Convertisseur convertisseur = new Convertisseur();
		convertisseur.chargerFichierConvertisseur();

		convertisseur.convertirMorseTexte(".- .--. -- -. .-.  -.-- --.. -.--  --..");

		Listelettre debutliste = txtsplit(Convertisseur.test());
		//System.out.println("Equivalent de 'a' en morse depuis le liste : "+Listelettre.tradmorse('a', debutliste));

		char ltr;
		String resultatmorse = "";
		String stringdetest="Je ttttt eqdsf sdgf";
		System.out.println("Voici la traduction en morse de : "+stringdetest);
		for(int i=0; i<stringdetest.length(); i++) {
			ltr = stringdetest.charAt(i);
			String tradchar = Listelettre.tradmorse(ltr, debutliste);
			if (tradchar == "espace") tradchar = " ";
			resultatmorse = resultatmorse + tradchar;
		}	
		System.out.println(resultatmorse);



		// faire menage methodes inutiles
	}


}
