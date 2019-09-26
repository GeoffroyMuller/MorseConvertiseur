package application;

import kenzer.Liste;

public class Listelettre {
	char lettre;
	String morse;
	Listelettre suiv;
	
	public boolean estvide() {
		return this==null;
	}
	
	public char valeurlettre()
	{
		return lettre;
	}
	
	public String valeurmorse()
	{
		return morse;
	}
	
	public Listelettre reste()
	{
		return suiv; 
	}
	
	public static boolean appartient(char ltr, Listelettre l) {
		while (!l.estvide())
		{
			if (l.valeurlettre()==ltr)
			{
				return true;
			}
			l=l.reste();
		}
		return false;
	}
	
	public String tradmorse (char lettre) {
		String conv = "";
		if(appartient(lettre, this))
		{
			conv = conv + "|" + 
		}
		return conv;
	}

	

}


