package view;

import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PowerpointUI extends JFrame {
	
	public PowerpointUI() {
		Fenetre fenetrePowerpointUI = new Fenetre();
		Conteneur conteneurTitre = new Conteneur();
		Conteneur conteneurPowerpointUI = new Conteneur();
		
		JPanel panel1 =  new JPanel();
		JPanel panel2 =  new JPanel();
		JPanel panel3 =  new JPanel(new GridLayout());

		JButton buttonGenerate = new JButton("Générer un PowerPoint");
		buttonGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Début génération PowerPoint");
			}
		});
		
		/* Ajout menu */
		Menu menu = new Menu();
		JPanel panel_menu = new JPanel();
		panel_menu.add(menu);
		
		
		TitrePrincipale title = new TitrePrincipale("PowerPoint Generator");
		panel1.setOpaque(false);
		panel1.add(title);
		
		JLabel nom = new JLabel("Inspecteur Gadget");
		panel2.setOpaque(false);
		panel2.add(nom); panel2.add(buttonGenerate);
		
		// Créer un fichier UIComponents dans lequel on mettre TitrePrincipal, Secondaire, Fenetre etc...
		
		
		// Menu déroulant
		String[] medicament_list = {"Doliprane", "Spasfon", "Ibuprofène", "Xanax", "Smecta"};
		JComboBox<?> liste = new JComboBox<Object>(medicament_list);

		Checkbox test = new Checkbox("Generer page Intro / Outro", null, true);
		panel2.add(test);
		
		panel3.setOpaque(false);
		panel3.add(liste);
		
		
		
		
		/* Ajout titre */
		conteneurTitre.add(panel1);
		conteneurPowerpointUI.add(panel2);
		conteneurPowerpointUI.add(panel3);
		fenetrePowerpointUI.add(panel_menu);
		fenetrePowerpointUI.add(conteneurTitre);
		fenetrePowerpointUI.add(conteneurPowerpointUI);
		
	}
}
