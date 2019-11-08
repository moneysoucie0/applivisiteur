package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ConsultationMedecin extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	
    private JScrollPane scrollPane;

	private static final long serialVersionUID = 1L;

	public ConsultationMedecin() {
		
		TitrePrincipale bienvenue = new TitrePrincipale("Consultation Medecins");
		Font font = new Font("Open Sans", Font.PLAIN, 18);
	
		this.add(bienvenue);

		List<List> List_Med = controller.Medecin.listMedecin();

		List<String> ListIDMed = new ArrayList<String>();
		List<String> ListMed = new ArrayList<String>();
		List<String> ListPrenom = new ArrayList<String>();

		for (int y = 0; y < List_Med.size(); y++) {

			ListIDMed.add((String) List_Med.get(y).get(0));
			ListMed.add((String) List_Med.get(y).get(2));
			ListPrenom.add((String) List_Med.get(y).get(3));


		}
		String nomMedStr[] = ListMed.toArray(new String[0]);
		String prenomMedStr[] = ListPrenom.toArray(new String[0]);
		JPanel choisir = new JPanel();
		choisir.setPreferredSize(new Dimension(2000,70));
		JComboBox<?> nomMedecinCombo = new JComboBox<Object>(nomMedStr);
		JLabel listMed = new JLabel("Choisir un médecin");
		choisir.add(nomMedecinCombo);
		choisir.add(listMed);
		this.add(choisir);
		
		TitreSecondaire titreSec = new TitreSecondaire("Dernier médecin ajouté :");
		this.add(titreSec);

		nomMedecinCombo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				Popup popup = new Popup("Consultation Médecin", 1500,800);
				String nomMedSelected = (String) nomMedecinCombo.getSelectedItem();
				TitrePrincipale titre = new TitrePrincipale("Consultation de la fiche du docteur" + " " +nomMedSelected); 
				popup.add(titre);
				List<List> List_This = controller.Medecin.listThisMedecin(nomMedSelected);
				String idMed = null;
				String idSpe = null;
				String nomMed = null;
				String prenom = null;
				String adresse = null;
				String ville = null;
				String cp = null;
				String tel = null;
				String idType = null;
				String nomSpe = null;
			
				for (int i =0; i<List_This.size(); i++) {
					idMed = (String) List_This.get(i).get(0);
					int idMedecinInt = Integer.parseInt(idMed);
					final int idMedU = Integer.parseInt(idMed);
					idSpe = (String) List_This.get(i).get(1);
					nomMed = (String) List_This.get(i).get(2);
					prenom = (String) List_This.get(i).get(3);
					adresse = (String) List_This.get(i).get(4);
					ville = (String) List_This.get(i).get(5);
					cp = (String) List_This.get(i).get(6);
					tel = (String) List_This.get(i).get(7);
				
				JPanel contenu = new JPanel();
				contenu.setBackground(Color.white);
				contenu.setPreferredSize(new Dimension(2000,215));

				JPanel nomMedecin = new JPanel();
				JLabel nomMedecinLabel = new JLabel("Nom du medecin :");
				JTextArea nomMedecinArea = new JTextArea(1,15);
				nomMedecinArea.setText(nomMed);
				nomMedecin.setPreferredSize(new Dimension(2000,50));
				nomMedecin.setFont(font);
				JLabel prenomMedecinLabel = new JLabel("Prénom du medecin :");
				JTextArea prenomMedecinArea = new JTextArea(1,15);
				prenomMedecinArea.setText(prenom);
				
				List<List> List_Spe = controller.Medecin.listTypeMedecin(idSpe);
				for(int y =0; y<List_Spe.size(); y++) {
					idType = (String) List_Spe.get(y).get(0);
					nomSpe = (String) List_Spe.get(y).get(1);

				}
				
				JLabel speMedecinLabel = new JLabel("Spécialité du medecin :");
				JLabel speMedecinArea = new JLabel(nomSpe);

				
				JPanel adresseMedecin = new JPanel();
				JLabel adresseMedecinLabel = new JLabel("Adresse du medecin :");
				JTextArea adresseMedecinArea = new JTextArea(2,15);	
				adresseMedecinArea.setText(adresse);
				adresseMedecin.setPreferredSize(new Dimension(2000,50));
				adresseMedecin.setFont(font);
				JLabel villeMedecinLabel = new JLabel("Ville du medecin :");
				JTextArea villeMedecinArea = new JTextArea(1,15);
				villeMedecinArea.setText(ville);


				JLabel cpMedecinLabel = new JLabel("Code postal du medecin :");
				JTextArea cpMedecinArea = new JTextArea(1,15);
				cpMedecinArea.setText(cp);


				
				JPanel telMedecin = new JPanel();
				JLabel telMedecinLabel = new JLabel("Téléphone du medecin :");
				JTextArea telMedecinArea = new JTextArea(1,15);
				telMedecinArea.setText(tel);

				telMedecin.setPreferredSize(new Dimension(2000,50));
				telMedecin.setFont(font);
				
				JPanel modifier = new JPanel();
				JButton modifierButton = new JButton("Modifier");
				JButton supButton = new JButton("Supprimer");
				supButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent ae) {
						controller.Medecin.suppressionMedecin(idMedecinInt);
						popup.dispose();


					}
				});
					
				
				modifierButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent ae) {
						String adresseU = adresseMedecinArea.getText();
						String cpU = cpMedecinArea.getText();
						String prenomU = prenomMedecinArea.getText();
						String nomMedecinU = nomMedecinArea.getText();
						String telU = telMedecinArea.getText();
						String villeU = villeMedecinArea.getText();
						
						
						controller.Medecin.updateMedecin(idMedU, nomMedecinU, prenomU, adresseU, villeU, cpU, telU);
						popup.dispose();

						
					}
				});
				popup.add(contenu);
				contenu.add(nomMedecin);
				nomMedecin.add(nomMedecinLabel);
				nomMedecin.add(nomMedecinArea);
				nomMedecin.add(prenomMedecinLabel);
				nomMedecin.add(prenomMedecinArea);
				nomMedecin.add(speMedecinLabel);
				nomMedecin.add(speMedecinArea);
				contenu.add(adresseMedecin);
				adresseMedecin.add(adresseMedecinLabel);
				adresseMedecin.add(adresseMedecinArea);
				adresseMedecin.add(villeMedecinLabel);
				adresseMedecin.add(villeMedecinArea);
				adresseMedecin.add(cpMedecinLabel);
				adresseMedecin.add(cpMedecinArea);
				contenu.add(telMedecin);
				telMedecin.add(telMedecinLabel);
				telMedecin.add(telMedecinArea);
				contenu.add(modifier);
				modifier.add(modifierButton);
				modifier.add(supButton);

				
				}

			}
		});		
		
		List<List> List_Last = controller.Medecin.listLastMedecin();
		String idMed = null;
		String idSpe = null;
		String nomMed = null;
		String prenom = null;
		String adresse = null;
		String ville = null;
		String cp = null;
		String tel = null;
		String idType = null;
		String nomSpe = null;
	
		for (int i =0; i<List_Last.size(); i++) {
			idMed = (String) List_Last.get(i).get(0);
			int idMedecinInt = Integer.parseInt(idMed);
			final int idMedU = Integer.parseInt(idMed);
			idSpe = (String) List_Last.get(i).get(1);
			nomMed = (String) List_Last.get(i).get(2);
			prenom = (String) List_Last.get(i).get(3);
			adresse = (String) List_Last.get(i).get(4);
			ville = (String) List_Last.get(i).get(5);
			cp = (String) List_Last.get(i).get(6);
			tel = (String) List_Last.get(i).get(7);
		
		JPanel contenu = new JPanel();
		contenu.setBackground(Color.white);
		contenu.setPreferredSize(new Dimension(2000,215));

		JPanel nomMedecin = new JPanel();
		JLabel nomMedecinLabel = new JLabel("Nom du medecin :");
		JTextArea nomMedecinArea = new JTextArea(1,15);
		nomMedecinArea.setText(nomMed);
		nomMedecin.setPreferredSize(new Dimension(2000,50));
		nomMedecin.setFont(font);
		JLabel prenomMedecinLabel = new JLabel("Prénom du medecin :");
		JTextArea prenomMedecinArea = new JTextArea(1,15);
		prenomMedecinArea.setText(prenom);
		
		List<List> List_Spe = controller.Medecin.listTypeMedecin(idSpe);
		for(int y =0; y<List_Spe.size(); y++) {
			idType = (String) List_Spe.get(y).get(0);
			nomSpe = (String) List_Spe.get(y).get(1);

		}
		
		JLabel speMedecinLabel = new JLabel("Spécialité du medecin :");
		JLabel speMedecinArea = new JLabel(nomSpe);

		
		JPanel adresseMedecin = new JPanel();
		JLabel adresseMedecinLabel = new JLabel("Adresse du medecin :");
		JTextArea adresseMedecinArea = new JTextArea(2,15);	
		adresseMedecinArea.setText(adresse);
		adresseMedecin.setPreferredSize(new Dimension(2000,50));
		adresseMedecin.setFont(font);
		JLabel villeMedecinLabel = new JLabel("Ville du medecin :");
		JTextArea villeMedecinArea = new JTextArea(1,15);
		villeMedecinArea.setText(ville);


		JLabel cpMedecinLabel = new JLabel("Code postal du medecin :");
		JTextArea cpMedecinArea = new JTextArea(1,15);
		cpMedecinArea.setText(cp);


		
		JPanel telMedecin = new JPanel();
		JLabel telMedecinLabel = new JLabel("Téléphone du medecin :");
		JTextArea telMedecinArea = new JTextArea(1,15);
		telMedecinArea.setText(tel);

		telMedecin.setPreferredSize(new Dimension(2000,50));
		telMedecin.setFont(font);
		
		JPanel modifier = new JPanel();
		JButton modifierButton = new JButton("Modifier");
		JButton supButton = new JButton("Supprimer");
		supButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				controller.Medecin.suppressionMedecin(idMedecinInt);


			}
		});
			
		
		modifierButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				String adresseU = adresseMedecinArea.getText();
				String cpU = cpMedecinArea.getText();
				String prenomU = prenomMedecinArea.getText();
				String nomMedecinU = nomMedecinArea.getText();
				String telU = telMedecinArea.getText();
				String villeU = villeMedecinArea.getText();
				
				
				controller.Medecin.updateMedecin(idMedU, nomMedecinU, prenomU, adresseU, villeU, cpU, telU);

				
			}
		});
		this.add(contenu);
		contenu.add(nomMedecin);
		nomMedecin.add(nomMedecinLabel);
		nomMedecin.add(nomMedecinArea);
		nomMedecin.add(prenomMedecinLabel);
		nomMedecin.add(prenomMedecinArea);
		nomMedecin.add(speMedecinLabel);
		nomMedecin.add(speMedecinArea);
		contenu.add(adresseMedecin);
		adresseMedecin.add(adresseMedecinLabel);
		adresseMedecin.add(adresseMedecinArea);
		adresseMedecin.add(villeMedecinLabel);
		adresseMedecin.add(villeMedecinArea);
		adresseMedecin.add(cpMedecinLabel);
		adresseMedecin.add(cpMedecinArea);
		contenu.add(telMedecin);
		telMedecin.add(telMedecinLabel);
		telMedecin.add(telMedecinArea);
		contenu.add(modifier);
		modifier.add(modifierButton);
		modifier.add(supButton);
		
		}
	}
}
