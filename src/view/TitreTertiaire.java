package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitreTertiaire extends JPanel {
	/*
	 * 	Classe pour créé un titre tertiaire
	 */
	private static final long serialVersionUID = 1L;

	public TitreTertiaire(String txt) {
		// Création des éléments
		Font font = new Font("Open Sans", Font.PLAIN, 24);
		JLabel texte = new JLabel(txt);
		// Définition du style
		texte.setFont(font);
		texte.setForeground(new Color(102,163,211));
		
		setOpaque(false);
		setVisible(true);
		setSize(getMaximumSize());
		add(texte);
	}
}
