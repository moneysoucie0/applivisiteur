package view;

import java.awt.Color;
import javax.swing.JPanel;


public class AlertSuccess extends JPanel{
	/**
	 * 	Cette classe permet de générer un JPanel avec un fond vert contenant un message
	 * 	Inspiration : alert-success de Bootstrap
	 */
	private static final long serialVersionUID = 1L;

	public AlertSuccess(String texte) {
		JPanel messageSuccess = new JPanel();
		messageSuccess.setOpaque(true);
		messageSuccess.setBackground(new Color(85, 239, 196));
		
		Paragraphe textSuccess = new Paragraphe(texte);
		textSuccess.setForeground(new Color(96, 191, 96));
		
		messageSuccess.add(textSuccess);
		
		add(messageSuccess);
	}
}
