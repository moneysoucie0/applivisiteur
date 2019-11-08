package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.Date;
import java.util.List;
import java.awt.Component;
import java.awt.Dimension;


public class CarteCompteRendu extends JPanel {
    public CarteCompteRendu(String someFrame, String medicament) {


        
        
        Conteneur conteneur = new Conteneur(); 
        JLabel JLabelDate = new JLabel(String.valueOf(someFrame));
        JLabelDate.setPreferredSize(new Dimension(1000, 150));
       // JLabel JLabelMedecin = new JLabel(medecin);
       // JLabel JLabelDate = new JLabel(someFrame);
	    JLabel labelMedicament = new JLabel(medicament);	


        
        
       // conteneur.add(JLabelMedecin);
        conteneur.add(JLabelDate);
        conteneur.add(labelMedicament);

    }

	
}