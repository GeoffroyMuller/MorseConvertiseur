package application;


public class Listelettre {
	char lettre;
	String morse;
	Listelettre suiv;
	
	public static boolean estvide(Listelettre l) {
		return l==null;
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
	
//	public Listelettre resmorse(String txt) {
//		String convmorse = "";
//		for (char letter: txt.toCharArray()) {
//			convmorse = convmorse.concact(Listelettre.searchCode(letter)+'/');
//		}
//		return convmorse;
//	}
	
	public static String tradmorse (char lettre, Listelettre l) {
		String conv ="";
		if (lettre == ' ') return "espace";
		while (! estvide(l))
		{
			if (Character.toLowerCase(l.valeurlettre())==Character.toLowerCase(lettre))
			{
				conv = l.valeurmorse();
				return l.valeurmorse();
			}
			l=l.reste();
		}
		return conv;
	}
	
	public static void afficher(Listelettre l)
	{ 
		while (l != null)
		{
			System.out.println(l.valeurlettre());
			System.out.println(l.valeurmorse());
			l = l.reste();
		}
	}


	

}


