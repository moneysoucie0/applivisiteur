package view;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Popup extends JFrame {
    /**
	 * 	Classe permettant d'ouvrir une nouvelle fenetre
	 */
	private static final long serialVersionUID = 1L;

	public Popup(String title, int width, int height){

		setBounds(100,50,2000,1000/16*9);
		setSize(width, height);
		setTitle(title);
		
		JPanel fenetre = new JPanel();
		fenetre.setBackground(new java.awt.Color(102, 163, 211));
		setContentPane(fenetre);
		
		setVisible(true);
    }
}
