package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitreSecondaire extends JPanel {
	/*
	 * 	Classe pour créé un titre secondaire 
	 */
	private static final long serialVersionUID = 1L;

	public TitreSecondaire(String txt) {
		// Création des éléments
		Font font = new Font("Open Sans", Font.PLAIN, 36);
		JLabel texte = new JLabel(txt , JLabel.CENTER);
		// Définition du style
		texte.setFont(font);
		texte.setForeground(new Color(18,62,108));

		setOpaque(false);
		setVisible(true);
		setSize(getMaximumSize());
		add(texte);
	}
}
