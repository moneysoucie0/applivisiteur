package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import controller.GenerateurPPTX;
import controller.CreationPowerPointController;


public class CreationPowerPoint extends JPanel {
	/*
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public CreationPowerPoint() {
		Conteneur conteneurPowerpointUI = new Conteneur();
		conteneurPowerpointUI.setPreferredSize(new Dimension(1000, 500));
		
		//JPanel title_panel =  new JPanel();
		JPanel panel2 =  new JPanel();

		// Liste des dispositions
		JPanel dispositions = new JPanel();
		dispositions.setBackground(Color.white);
		dispositions.setPreferredSize(new Dimension(1000, 50));

		// Liste des médicaments
		JPanel liste_medicaments = new JPanel();
		JLabel medicaments_label = new JLabel("Choisissez votre médicament ");
		JLabel suite_medicaments_label= new JLabel("par ordre alphabétique: ");
		liste_medicaments.add(medicaments_label);
		liste_medicaments.add(suite_medicaments_label);
		liste_medicaments.setBackground(Color.white);
		liste_medicaments.setPreferredSize(new Dimension(260, 300));

		// Powerpoint builder
		JPanel powerpoint_builder = new JPanel();
		ImageIcon imgDisposition = new ImageIcon("img/dispo1.png");
		int imgWidth=imgDisposition.getIconWidth();
		int imgHeight =imgDisposition.getIconHeight();
		imgDisposition= new ImageIcon(imgDisposition.getImage().getScaledInstance(imgWidth/2, imgHeight/2, Image.SCALE_DEFAULT));
		JLabel label_imgDisposition = new JLabel(imgDisposition);
		
		powerpoint_builder.setBackground(Color.white);
		
		
		powerpoint_builder.setPreferredSize(new Dimension(650, 300));

		JLabel panel3 = new JLabel();

		JButton buttonGenerate = new JButton("Générer une Présentation");


		
		TitrePrincipale titre = new TitrePrincipale("Générateur de Présentation");

		JLabel disposition_label = new JLabel("Choisissez votre disposition");
		disposition_label.setOpaque(false);
		dispositions.add(disposition_label);
		
		
		String[] dispo= {"1","2","3","4"};
		JComboBox<?> ListeDisposition = new JComboBox<String>(dispo);
		
		ListeDisposition.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String DispositionChoisie = String.valueOf(ListeDisposition.getSelectedItem());
				ImageIcon imgDisposition = new ImageIcon("img/dispo"+DispositionChoisie+".png");
				int imgWidth=imgDisposition.getIconWidth();
				int imgHeight =imgDisposition.getIconHeight();
				imgDisposition= new ImageIcon(imgDisposition.getImage().getScaledInstance(imgWidth/2, imgHeight/2, Image.SCALE_DEFAULT));
				
				label_imgDisposition.setIcon(imgDisposition);
				
			}
		});
		
		dispositions.add(ListeDisposition);
		
		
		Character[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		JComboBox<?> liste = new JComboBox<Character>(alphabet);

		JLabel label_medicament_selectionne = new JLabel();
		
		liste.addActionListener(new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent e) {
				String LettreSelected = String.valueOf(liste.getSelectedItem());
				Popup popup = new Popup("popup", 700, 300);
				Conteneur conteneur_popup = new Conteneur();
				JPanel panelMedocSelect= new JPanel();
				panelMedocSelect.setPreferredSize(new Dimension(700,150));
				panelMedocSelect.setBackground(Color.white);
				
				//panelMedocSelect
				JLabel label_TextMedocSelect=new JLabel("");
				List<String> medicaments_list = new ArrayList<String>();
				
				JComboBox<?> liste_medicaments = new JComboBox<String>();
				liste_medicaments.setModel(new DefaultComboBoxModel(CreationPowerPointController.ListeMedicament(LettreSelected).toArray()));

				liste_medicaments.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						String medicament_selectionne = String.valueOf(liste_medicaments.getSelectedItem());
						label_medicament_selectionne.setText(medicament_selectionne);
						label_TextMedocSelect.setText("Vous avez séléctionné le médicament suivant: "+medicament_selectionne+".");
						
						
					}
				});
				conteneur_popup.add(liste_medicaments);
				popup.add(conteneur_popup);
				popup.add(panelMedocSelect);
				panelMedocSelect.add(label_TextMedocSelect);
			}
		});
		buttonGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Debut generation PowerPoint");
				String nomMedicament=label_medicament_selectionne.getText();
				if(nomMedicament!="") {
					JFileChooser f = new JFileChooser();
				    f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				    f.showSaveDialog(null);
				    File Path=f.getSelectedFile();
				    System.out.println(f.getSelectedFile());
					try {
						System.out.println("La disposition "+ListeDisposition.getSelectedItem().toString()+" a été sélectionnée");
						String effet=CreationPowerPointController.EffetMedoc(nomMedicament);
						String contreIndication=CreationPowerPointController.contreIndication(nomMedicament);
						
						if(Path!=null){
							System.out.println("Le fichier sera enregistrer dans le dossier suivant: "+Path);
							new GenerateurPPTX(ListeDisposition.getSelectedItem().toString(),nomMedicament,effet,contreIndication,Path);
							System.out.println("Fichier enregisté");
						} else {
							System.out.println("Création annulée");
						}
						
					} catch (IOException e) {
						System.out.println("Erreur lors de la création de la présentation");
						e.printStackTrace();
					}
				
				} else {
					System.out.println("Aucun médicament séléctionné");
					
				}
			}
			
		}); 
		//JLabel nom = new JLabel("Inspecteur Gadget");
		panel2.setOpaque(false);
		//panel2.add(nom);
		panel2.add(buttonGenerate);
		
		panel3.setOpaque(false);
		panel3.add(liste);
		
		/* Ajout titre */  
		liste_medicaments.add(liste);
		liste_medicaments.add(label_medicament_selectionne);
		powerpoint_builder.add(label_imgDisposition);
		conteneurPowerpointUI.add(dispositions);
		conteneurPowerpointUI.add(liste_medicaments);
		conteneurPowerpointUI.add(powerpoint_builder);
		conteneurPowerpointUI.add(panel2);
		conteneurPowerpointUI.add(panel3);
		this.add(titre);
		this.add(conteneurPowerpointUI);
	}
}
