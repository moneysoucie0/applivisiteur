package view;

import javax.swing.JPanel;


public class ConsultationMessagerie extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public ConsultationMessagerie() {
		TitrePrincipale bienvenue = new TitrePrincipale("Consultation Messagerie");
		TitreTertiaire titre3 = new TitreTertiaire("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
