package view;

import javax.swing.JPanel;


public class ConsultationPowerPoint extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public ConsultationPowerPoint() {
		TitrePrincipale bienvenue = new TitrePrincipale("Consultation Power Point");
		TitreTertiaire titre3 = new TitreTertiaire("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
