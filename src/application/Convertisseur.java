package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import centre.ArbreBinaire;

public class Convertisseur {

	public static void chargerFichierConvertisseur(){
		try {
			String fichier = "src/centre/Convertisseur.txt";
			BufferedReader bfr = new BufferedReader(new FileReader(fichier));
			System.out.println(">fichier "+fichier+" charger");
			String res = "";
			String sligne = "";
			String[] smots;
			
			while((sligne = bfr.readLine())!=null) {
				//smots = sligne.split(" ");
				ArbreBinaire arbreBinaire = new ArbreBinaire();
				for(int i=sligne.length();i>0;i--) {
					System.out.println(">"+i+" "+sligne.length()+sligne.charAt(i-1));
					char c = sligne.charAt(i-1);
					
					if( c == '.') {
						System.out.println("point");
					}else if ( c == '-' ) {
						System.out.println("trait");
					}else if ( c == '#') {
						
					}
					
				}
				System.out.println("//");
			}
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

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

		Convertisseur.chargerFichierConvertisseur();
		
		Listelettre debutliste = txtsplit(Convertisseur.test());
		//System.out.println("Equivalent de 'a' en morse depuis le liste : "+Listelettre.tradmorse('a', debutliste));
		
		char ltr;
		String resultatmorse = "";
		String stringdetest="Je suce des bites";
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
