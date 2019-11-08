package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Medicament;

public class CreationMedicament extends JPanel {

	/**
	 * 	Explication de cette classe
	 */
	
	
	
	private static final long serialVersionUID = 1L;

	public CreationMedicament(){
		TitrePrincipale bienvenue = new TitrePrincipale("Creation de médicament");
		Font font = new Font("Open Sans", Font.PLAIN, 18);
		
		
		
		JPanel nomMedicament = new JPanel();
		nomMedicament.setPreferredSize(new Dimension(2000,50));
    	nomMedicament.setFont(font);
		
		JLabel nomMedicamentLabel = new JLabel("Nom du médicament :");
		JTextArea nomMedicamentArea = new JTextArea(1,15);
		
    	JLabel familleMedicamentLabel = new JLabel("Famille");
		JTextArea familleMedicamentArea = new JTextArea(1,15);
    	
		JLabel depotLegalLabel = new JLabel("Dépôt légal :");
		JTextArea depotLegalArea = new JTextArea(1,15);
		
		
		
		JPanel effetMedicament = new JPanel();
		effetMedicament.setPreferredSize(new Dimension(2000,140));
		effetMedicament.setFont(font);
		
		JLabel effetMedicamentLabel = new JLabel("Effet medicament :");
		JTextArea effetMedicamentArea = new JTextArea(6,45);
		
		JLabel contreIndicationLabel = new JLabel("Contre indication :");
		JTextArea contreIndicationArea = new JTextArea(6,45);
		
		
		
		JPanel contreIndicationCompositionPanel = new JPanel();
		contreIndicationCompositionPanel.setPreferredSize(new Dimension(2000,140));
		contreIndicationCompositionPanel.setFont(font);
		
		JLabel compositionLabel = new JLabel("Composition : ");
		JTextArea compositionArea = new JTextArea(6,45);
		
		JLabel noticeLabel = new JLabel("Notice médicament : ");
		JTextArea noticeArea = new JTextArea(6,45);
		
		
		JPanel unite = new JPanel();
		unite.setPreferredSize(new Dimension(2000,50));
		unite.setFont(font);
		
		JLabel uniteLabel = new JLabel("Poid unitaire :");
		JTextArea uniteArea = new JTextArea(1,15);
		
		JLabel quantiteLabel = new JLabel("quantite :");
		JTextArea quantiteArea = new JTextArea(1,15);
		
		JLabel typeIndividuLabel = new JLabel("Type individu : ");
		JTextArea typeIndividuArea = new JTextArea(1,15);
		
/*		JLabel familleLabel = new JLabel("Famille de médicament : ");
		JTextArea familleArea = new JTextArea(1,15);
*/		
		JLabel prixLabel = new JLabel("Prix : ");
		JTextArea prixArea = new JTextArea(1,15);
		
		
		
		JPanel valider = new JPanel();
		JButton validerButton = new JButton("Valider");
		valider.setPreferredSize(new Dimension(2000,50));

		this.add(bienvenue);
		
		this.add(nomMedicament);
		nomMedicament.add(nomMedicamentLabel);
		nomMedicament.add(nomMedicamentArea);
		nomMedicament.add(familleMedicamentLabel);
		nomMedicament.add(familleMedicamentArea);
		nomMedicament.add(depotLegalLabel);
		nomMedicament.add(depotLegalArea);
		
		this.add(effetMedicament);
		effetMedicament.add(effetMedicamentLabel);
		effetMedicament.add(effetMedicamentArea);
		effetMedicament.add(contreIndicationLabel);
		effetMedicament.add(contreIndicationArea);
		
		this.add(contreIndicationCompositionPanel);
		contreIndicationCompositionPanel.add(compositionLabel);
		contreIndicationCompositionPanel.add(compositionArea);
		contreIndicationCompositionPanel.add(noticeLabel);
		contreIndicationCompositionPanel.add(noticeArea);
	
		this.add(unite);
		unite.add(uniteLabel);
		unite.add(uniteArea);
		unite.add(quantiteLabel);
		unite.add(quantiteArea);
		unite.add(typeIndividuLabel);
		unite.add(typeIndividuArea);
//		unite.add(familleLabel);
//		unite.add(familleArea);
		unite.add(prixLabel);
		unite.add(prixArea);
		
		this.add(valider);
		valider.add(validerButton);
		
		validerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Medicament.ajoutMedicament(nomMedicamentArea.getText(),familleMedicamentArea.getText(), depotLegalArea.getText(), effetMedicamentArea.getText(), contreIndicationArea.getText(),compositionArea.getText(), noticeArea.getText(), uniteArea.getText(), quantiteArea.getText(), typeIndividuArea.getText(),/* familleArea.getText(),*/ prixArea.getText());
			}
		});
		
	}
}
