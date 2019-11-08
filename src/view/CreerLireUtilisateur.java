package view;

import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import controller.Medecin;
import controller.Utilisateur;


public class CreerLireUtilisateur extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public CreerLireUtilisateur() {
		
		TitrePrincipale bienvenue = new TitrePrincipale("Liste utilisateurs");
		this.add(bienvenue);
		
		List<List> data = controller.Utilisateur.getFragmentUtilisateur();
		
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(0, 5));
		
		System.out.println("Ca c'est data : " + data);
		
		Integer length = data.get(0).size();
		System.out.println("LENGTH : " + length);
		
		for (int j = 1; j < length; j++) {
			if(j % 5 == 0) {
				int tampon = j;
				JButton myButton = new JButton("Modifier");
				myButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Popup popup = new Popup("Détails utilisateur", 700, 300);
						List<List> utilisateur = controller.Utilisateur.getUtilisateur((String) data.get(0).get(tampon-5));
						
						Font font = new Font("Arial", Font.BOLD, 20);
						
						JTextArea nom = new JTextArea();
						nom.setFont(font);
						nom.setText((String) utilisateur.get(0).get(1));
						popup.add(nom);
						
						JTextArea prenom = new JTextArea();
						prenom.setFont(font);
						prenom.setText((String) utilisateur.get(0).get(2));
						popup.add(prenom);
						
						JTextArea adresse = new JTextArea();
						adresse.setFont(font);
						adresse.setText((String) utilisateur.get(0).get(3));
						popup.add(adresse);
						
						JTextArea ville = new JTextArea();
						ville.setFont(font);
						ville.setText((String) utilisateur.get(0).get(4));
						popup.add(ville);
						
						JTextArea codePostal = new JTextArea();
						codePostal.setFont(font);
						codePostal.setText((String) utilisateur.get(0).get(5));
						popup.add(codePostal);
						
						JTextArea role = new JTextArea();
						role.setFont(font);
						role.setText((String) utilisateur.get(0).get(6));
						popup.add(role);
						
						JButton save = new JButton("Sauvegarder");
						save.setFont(font);
						popup.add(save);
						
						save.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String idUtilisateur = (String) utilisateur.get(0).get(0);
								String final_nom = nom.getText();
								String final_prenom = prenom.getText();
								String final_adresse = adresse.getText();
								String final_ville = ville.getText();
								String final_codePostal = codePostal.getText();
								String final_role = role.getText();
								
								controller.Utilisateur.saveData(idUtilisateur, final_nom, final_prenom, final_adresse, final_ville, final_codePostal, final_role);
							}
						});
					}
				});
				grid.add(new JLabel((String) data.get(0).get(j)));
				grid.add(myButton);
			}
			else if (j == 1 || (j - 1) % 5 == 0) {
				
			}
			else {
				grid.add(new JLabel((String) data.get(0).get(j)));
			}
			// infos_utilisateur = ajoutemoiLabel(infos_utilisateur, new JLabel("" + data.get(0).get(j) + ""));
			
		}
		
		
		this.add(grid);
		
	}

}
