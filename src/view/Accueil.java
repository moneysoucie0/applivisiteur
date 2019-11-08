package view;

import javax.swing.JPanel;


public class Accueil extends JPanel {
	/**
	 *  Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public Accueil() {		
		TitrePrincipale bienvenue = new TitrePrincipale("Bienvenue");
		this.add(bienvenue);
	}

	public Accueil(String nom) {
		TitrePrincipale bienvenue = new TitrePrincipale("Bienvenue " + nom);
		this.add(bienvenue);
	}
}