package view;

import java.awt.Font;
import javax.swing.JPasswordField;

public class JPasswordFieldModif extends JPasswordField{

	public JPasswordFieldModif(int taille) {
		/*
		 * descr
		 */
		Font font = new Font("Open Sans", Font.PLAIN, 18);
		this.setColumns(taille);
		this.setFont(font);
	}
	
	public JPasswordFieldModif() {
		/*
		 * descr
		 */
		Font font = new Font("Open Sans", Font.PLAIN, 18);
		this.setFont(font);
	}
	
}
