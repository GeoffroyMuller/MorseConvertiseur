package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import centre.ArbreBinaire;

public class Convertisseur {


	public static ArbreBinaire abp ;

	/**
	 * Charge le fichier de convertion
	 */
	public static void chargerFichierConvertisseur(){

		try {
			String fichier = "src/centre/Convertisseur.txt";
			BufferedReader bfr = new BufferedReader(new FileReader(fichier));
			System.out.println(">fichier "+fichier+" charger");
			String sligne = "";
			abp = new ArbreBinaire();
			ArbreBinaire abs = new ArbreBinaire();
			while((sligne = bfr.readLine())!=null) {
				System.out.println("ligne : "+sligne);
				creerArbreBinaireRec(abs, sligne, 2, true);
			}
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("erreur > chargement du dictionnaire (fichier \"Convertisseur.txt\")");
			e.printStackTrace();
		}

	}

	/**
	 * Creer l'arbre binaire
	 * @param ab
	 * @param sligne
	 * @param index
	 * @param init
	 */
	private static void creerArbreBinaireRec(ArbreBinaire ab, String sligne, int index, boolean init) {
		try {
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
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erreur > creation de l'arbre binaire");
			e.printStackTrace();
		}
	}

	/**
	 * Convertie Morse vers Texte
	 * @param s
	 * @return
	 */
	public static String convertirMorseTexte(String s) {
		System.out.println();
		String res = "";
		s = s + " ";
		ArbreBinaire a = new ArbreBinaire();
		a=abp;

		for(int i = 0;i<s.length();i++) {
			char c = s.charAt(i);
			if( c == '.' ) {
				System.out.print(".");
				try {
					a = a.getGauche();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else if ( c == '-' ) {
				System.out.print("-");
				try {
					a = a.getDroit();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else if ( c == ' ') {
				try {
					System.out.print(""+a.getLettre()+"|");
					res = res+a.getLettre();
					if(s.charAt(i+1) == ' ') {
						res = res + " ";
					}
				}catch (Exception e) {
					System.out.print("[]|");
				}
				a=abp;
			}else if( c == '\n' ) {
				try {
					System.out.print("RC|");
					res = res+a.getLettre();
					res = res + "\n";
				}catch (Exception e) {
					System.out.print("[]|");
				}
				a=abp;
			}

		}
		System.out.println();
		System.out.println(res);
		return res;

	}

	/**
	 * Creer la liste
	 * @param remplirconv string qui contient l'ensemble du dictionnaire
	 * @return
	 */
	public static Listelettre txtsplit(String remplirconv) {

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
	
	public static String convertirtxtmorse(String aconvertir) throws IOException {
		Listelettre debutliste = txtsplit(Convertisseur.test());
		char ltr;
		String resultatmorse = "";
		
		System.out.println(aconvertir);
		for(int i=0; i<aconvertir.length(); i++) {
			ltr = aconvertir.charAt(i);
			String tradchar = Listelettre.tradmorse(ltr, debutliste);
			if (tradchar == "espace") tradchar = "  ";
			resultatmorse = resultatmorse + tradchar+" ";
		}	
		return resultatmorse;
	}





	public static void main(String[] args) throws IOException {

		Convertisseur.chargerFichierConvertisseur();

		Convertisseur.convertirMorseTexte(".- .--. -- -. .-\n--.. -.--  --.. ");


	}


}
