package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Acceuil {
	public Acceuil() {
		
		/*
		 * initialisation des conteneur 
		*/
		Fenetre fenetre = new Fenetre();
		
		Conteneur Conteneur = new Conteneur();
		
		TitrePrincipale bienvenue = new TitrePrincipale("Bienvenue Copain Suisse");
		//titreSecondaire important = new titreSecondaire("Important");
		//Paragraphe txtImportant = new Paragraphe("vous puez trop vous ete vire, perche' stai cercando il sole nell'abisso");
		//titreSecondaire equipe = new titreSecondaire("equipe");
		///Paragraphe txtEquipe = new Paragraphe("manger des petit suisse, voir Martine, Vendre mes enfant, pendre un NAZI, vir un lama.");
		//titreSecondaire activite = new titreSecondaire("activite");
		//Paragraphe txtactivite = new Paragraphe("manger des petit suisse, voir Martine, Vendre mes enfant, pendre un NAZI, vir un lama.");
		
		/*
		 * création du conteneur principal
		*/
		
		JPanel panel[]= {new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};
		
		/*
		 * insertion des contenue dans les conteneur
		*/
		panel[1].add(bienvenue);
		//panel[2].add(important);
		//panel[3].add(txtImportant);
		//panel[4].add(equipe);
		//panel[5].add(equipe);
		//panel[6].add(activite);
		//panel[7].add(txtactivite);
		
		
		/*
		 * construction de la page 
		 */
		panel[0].setOpaque(false);
		for (int i = 1; i < 2; i++) {
			panel[i].setOpaque(false);
			panel[0].add(panel[i]);
			BoxLayout box= new BoxLayout(panel[i], BoxLayout.Y_AXIS);
		}
		
		Conteneur.add(panel[0]);
		
		
		BoxLayout b= new BoxLayout(Conteneur, BoxLayout.Y_AXIS);
		BoxLayout box= new BoxLayout(panel[0], BoxLayout.Y_AXIS);
		BoxLayout boxes= new BoxLayout(fenetre, BoxLayout.Y_AXIS);
		fenetre.add(new Menu());
		fenetre.add(Conteneur);
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}