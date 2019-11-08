package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;


public class Paragraphe extends JPanel {
	/*
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 	Classe pour créé un paragraphe
	 */
	public Paragraphe(String txt) {
		Font font = new Font("Open Sans", Font.PLAIN, 18);
		JLabel texte = new JLabel(txt);
		texte.setForeground(new Color(0,0,0));
		texte.setFont(font);
		setOpaque(false);
		setVisible(true);
		setSize(getMaximumSize());
		add(texte);
	}
}
