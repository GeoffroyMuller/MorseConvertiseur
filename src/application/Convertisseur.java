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

			while((sligne = bfr.readLine())!=null) {
				System.out.println("ligne : "+sligne);
				creerArbreBinaire(abp, sligne);
			}


		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void creerArbreBinaire(ArbreBinaire ap, String sligne) {
		if(!sligne.equals("")) {
			char lettre = sligne.charAt(0);

			ArbreBinaire a = new ArbreBinaire();
			a = ap;

			for(int i = 0;i<sligne.length();i++) {
				char c = sligne.charAt(i);
				if( c == '.' ) {
					System.out.println(">.");
					a = a.creerRetournerGauche();
				}else if ( c == '-' ) {
					System.out.println(">-");
					a = a.creerRetournerDroit();
				}
			}
			a.setLettre(lettre);
			//System.out.println("APPP::: "+ap.getGauche().getDroit()+"  lettre====="+ap.getGauche().getDroit().getLettre() );
			//System.out.println("A::: "+a+"  lettre====="+a.getLettre() );
		}
		//ap.afficher();

	}

	public String convertirMorseTexte(String s) {
		String res = "";

		ArbreBinaire a = new ArbreBinaire();

		for(int i = 0;i<s.length();i++) {
			System.out.println("length "+s.length()+"   i::: "+i);
			try {
			char c = s.charAt(i);
			if( c == '.' ) {
				System.out.println(">.");
				a = a.getGauche();
			}else if ( c == '-' ) {
				System.out.println(">-");
				a = a.getDroit();
			}else if ( c == ' ') {
				res = res + a.getLettre();
				if( s.charAt(i+1) == ' ' ) {
					res = res + " ";
				}
			}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzz  "+res);

		return null;
	}


	public static void txtsplit(String remplirconv) {
		String[] parts = remplirconv.split("/"); // split couple
		int i=0;
		while (estpasvide(parts[i]))
		{
			// split lettre - morse
			String[] separ = parts[i].split("#"); // split lettre et morse
			Listelettre listedelettre = new Listelettre();
			listedelettre.lettre = separ[0].charAt(0);
			listedelettre.morse = separ[1];
			i++;
		}
	}

	public static boolean estpasvide(String tabl) {
		if (tabl == "") return false;
		else return true;
	}

	public static void main(String[] args) {
		Convertisseur convertisseur = new Convertisseur();
		convertisseur.chargerFichierConvertisseur();

		convertisseur.convertirMorseTexte(".- .--. -- -. .-.  -.-- --.. -.--  --..");

	}

}
