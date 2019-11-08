 package view;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class JTextFieldModif extends JTextField {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public JTextFieldModif(int taille, int limite) {
		/*
		 * param taille : nbr de colonne de largeur
		 * param limite : nbr de caractere max
		 */
		Font font = new Font("Open Sans", Font.PLAIN, 18);
		
		this.setColumns(taille);
		this.setFont(font);
		this.setDocument(new JTextFieldLimit(limite));
		
	}
	
	class JTextFieldLimit extends PlainDocument {
        /*
        *    Classe permettant de limiter le nombre de caract�re dans un JtextField
        */
        private static final long serialVersionUID = 1L;
        private int limit;
        JTextFieldLimit(int limit) {
        	super();
        	this.limit = limit;
        }
        JTextFieldLimit(int limit, boolean upper) {
        	super();
        	this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        	if(str == null) {
        		return;        		
        	}
            if((getLength() + str.length()) <= limit) {
            	super.insertString(offset, str, attr);
            }
    	}
    }	
}
