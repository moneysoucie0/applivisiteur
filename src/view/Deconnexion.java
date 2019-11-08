package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Deconnexion extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public Deconnexion() {


		TitrePrincipale bienvenue = new TitrePrincipale("Deconnexion");
		TitreTertiaire titre3 = new TitreTertiaire("A faire");
		
		this.add(titre3);	
		
		
	}
}
