package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import controller.Medecin;


public class CreationMedecin extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public CreationMedecin() {
		TitrePrincipale bienvenue = new TitrePrincipale("Creation de medecins");
		Font font = new Font("Open Sans", Font.PLAIN, 18);
		
		JPanel nomMedecin = new JPanel();
		JLabel nomMedecinLabel = new JLabel("Nom du medecin :");
		JTextArea nomMedecinArea = new JTextArea(1,15);
		nomMedecin.setPreferredSize(new Dimension(2000,50));
		nomMedecin.setFont(font);
		JLabel prenomMedecinLabel = new JLabel("Prénom du medecin :");
		JTextArea prenomMedecinArea = new JTextArea(1,15);
		List<List> List_Spe = controller.Medecin.listSpeMedecin();
		JLabel speMedecinLabel = new JLabel("Spécialité du medecin :");
		List<String> ListSpe = new ArrayList<String>();
		List<String> ListIdSpe = new ArrayList<String>();
		for (int i = 0; i < List_Spe.size(); i++) {
			ListIdSpe.add((String) List_Spe.get(i).get(1));
			ListSpe.add((String) List_Spe.get(i).get(0));

		}
		String idSpeMed[] = ListIdSpe.toArray(new String[0]);
		String speMed[] = ListSpe.toArray(new String[0]);
		JComboBox<?> speMedecinCombo = new JComboBox<Object>(speMed);

		
		JPanel adresseMedecin = new JPanel();
		JLabel adresseMedecinLabel = new JLabel("Adresse du medecin :");
		JTextArea adresseMedecinArea = new JTextArea(1,15);		
		adresseMedecin.setPreferredSize(new Dimension(2000,50));
		adresseMedecin.setFont(font);
		JLabel villeMedecinLabel = new JLabel("Ville du medecin :");
		JTextArea villeMedecinArea = new JTextArea(2,15);
		JLabel cpMedecinLabel = new JLabel("Code postal du medecin :");
		JTextArea cpMedecinArea = new JTextArea(1,15);

		JPanel telMedecin = new JPanel();
		JLabel telMedecinLabel = new JLabel("Téléphone du medecin :");
		JTextArea telMedecinArea = new JTextArea(1,15);
		telMedecin.setPreferredSize(new Dimension(2000,50));
		telMedecin.setFont(font);
		
		JPanel valider = new JPanel();
		JButton validerButton = new JButton("Valider");
		valider.setPreferredSize(new Dimension(2000,50));

		this.add(bienvenue);

		this.add(nomMedecin);
		nomMedecin.add(nomMedecinLabel);
		nomMedecin.add(nomMedecinArea);
		nomMedecin.add(prenomMedecinLabel);
		nomMedecin.add(prenomMedecinArea);
		nomMedecin.add(speMedecinLabel);
		nomMedecin.add(speMedecinCombo);
		this.add(adresseMedecin);
		adresseMedecin.add(adresseMedecinLabel);
		adresseMedecin.add(adresseMedecinArea);
		adresseMedecin.add(villeMedecinLabel);
		adresseMedecin.add(villeMedecinArea);
		adresseMedecin.add(cpMedecinLabel);
		adresseMedecin.add(cpMedecinArea);
		this.add(telMedecin);
		telMedecin.add(telMedecinLabel);
		telMedecin.add(telMedecinArea);
		this.add(valider);
		valider.add(validerButton);
		
		validerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int cp = Integer.parseInt(cpMedecinArea.getText());
				int spe = Integer.parseInt(idSpeMed[(int) speMedecinCombo.getSelectedIndex()]);
				
				Medecin.ajouterMedecin(spe,nomMedecinArea.getText(), prenomMedecinArea.getText(), adresseMedecinArea.getText(), villeMedecinArea.getText(),cp, telMedecinArea.getText());
			}
		});
	
	}
}
