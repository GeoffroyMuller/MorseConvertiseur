package centre;

public class ArbreBinaire {

	private ArbreBinaire gauche;
	private ArbreBinaire droit;
	private char lettre;

	public ArbreBinaire() {
		// TODO Auto-generated constructor stub
	}

	public ArbreBinaire creerRetournerGauche() {
		if(gauche == null) {
			gauche = new ArbreBinaire();
		}
		return gauche;
	}

	public ArbreBinaire creerRetournerDroit() {
		if(droit == null) {
			droit = new ArbreBinaire();
		}
		return droit;
	}

	public ArbreBinaire getGauche() {
		return gauche;
	}

	public void setGauche(ArbreBinaire gauche) {
		this.gauche = gauche;
	}

	public ArbreBinaire getDroit() {
		return droit;
	}

	public void setDroit(ArbreBinaire droit) {
		this.droit = droit;
	}

	public char getLettre() {
		return lettre;
	}

	public void setLettre(char lettre) {
		this.lettre = lettre;
	}
	

	public void afficher() {
		try {
			System.out.println(">Arbre : "+this+" g:"+gauche+" d: "+droit+ " lettre: "+lettre);
			gauche.afficher();
			droit.afficher();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	

}
