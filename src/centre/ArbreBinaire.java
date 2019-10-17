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
	
	/**
	 * Cherche le Texte correspondant au morse (String s)
	 * @param s morse
	 * @return texte
	 */
	public String chercherTexteCorrespondant(String s) {
		String res = "";
		s = s + " ";
		ArbreBinaire a = new ArbreBinaire();
		a=this;

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
				a=this;
			}else if( c == '\n' ) {
				try {
					System.out.print("RC|");
					res = res+a.getLettre();
					res = res + "\n";
				}catch (Exception e) {
					System.out.print("[]|");
				}
				a=this;
			}

		}
		System.out.println();
		System.out.println(res);
		return res;
	}
		
	/**
	 * Getter & Setter
	 * @return
	 */
	
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

}
