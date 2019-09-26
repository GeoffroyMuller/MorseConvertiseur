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

		Convertisseur.chargerFichierConvertisseur();
	}

}
