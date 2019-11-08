package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitreTertiare extends JPanel {

	/*
	 * class  pour créé un titre tertiere
	 */

	private static final long serialVersionUID = 1L;

	public TitreTertiare(String txt) {
		Font font = new Font("Open Sans", Font.PLAIN, 24);
		JLabel texte = new JLabel();
		texte = new JLabel(txt);
		texte.setFont(font);
		texte.setForeground(new Color(102,163,211));
		this.setOpaque(false);
		this.setVisible(true);
		this.setSize(getMaximumSize());
		this.add(texte);
	}

}
