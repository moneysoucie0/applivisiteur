package view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.CnxBDD;
import model.User;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;


public class Connexion extends JPanel {
	/**
	 * 	Explication de cette vue
	 */
	private static final long serialVersionUID = 1L;
	public Connexion(User User, Fenetre f, Boolean error) {
		int largeurConteneur = 500;
		
		// Création des differents éléments de la vue
		TitrePrincipale titrePrincipale = new TitrePrincipale("Connexion");
		titrePrincipale.setPreferredSize(new Dimension(1500, 100));
		Paragraphe paragrapheId = new Paragraphe("Identifiant : ");
		JTextFieldModif textFieldId = new JTextFieldModif(12, 12);
		Paragraphe paragrapheMdp = new Paragraphe("Mot de passe : ");
		JPasswordFieldModif textFieldMdp = new JPasswordFieldModif(12);
		JPanel messageErreur = new AlertError("Nom de compte ou mot de passe incorrect !");

//		JPanel videHaut = new JPanel();
//		videHaut.setSize(largeurConteneur, 1000);
		
		Conteneur conteneur = new Conteneur();
		conteneur.setSize(largeurConteneur, 1000);
		conteneur.setLayout(new BoxLayout(conteneur, BoxLayout.PAGE_AXIS));

		// Création du bouton "Valider" ainsi que des EventListener permettant de detecter la touche Entrer pour simuler le clique sur le bouton "Valider"
		JButton boutonValider = new JButton("Connexion");
		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CnxBDD.connect(textFieldId.getText(), textFieldMdp.getText(), User);
					f.refreshConnexion(User.isConnected());
				}
				catch(Exception exception) {
					System.out.println(exception);
				}
			}
		});
		textFieldId.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						CnxBDD.connect(textFieldId.getText(), textFieldMdp.getText(), User);
						f.refreshConnexion(User.isConnected());
					}
					catch(Exception exception) {
						System.out.println(exception);
					}
				}
			}
		});
		textFieldMdp.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						CnxBDD.connect(textFieldId.getText(), textFieldMdp.getText(), User);
						f.refreshConnexion(User.isConnected());
					}
					catch(Exception exception) {
						System.out.println(exception);
					}
				}
			}
		});
		
		JPanel ligneUne =  new JPanel();
		ligneUne.setLayout(new BoxLayout(ligneUne, BoxLayout.LINE_AXIS));
		ligneUne.setOpaque(false);
		ligneUne.setPreferredSize(new Dimension(largeurConteneur, 150));
		
		ligneUne.add(titrePrincipale);
		
		
		JPanel ligneDeux = new JPanel();
		ligneDeux.setLayout(new BoxLayout(ligneDeux, BoxLayout.LINE_AXIS));
		ligneDeux.setOpaque(false);
		ligneDeux.setPreferredSize(new Dimension(largeurConteneur, 150));
		
		ligneDeux.add(messageErreur);
		
			
		JPanel ligneTrois = new JPanel();
	//	ligneTrois.setLayout(new BoxLayout(ligneTrois, BoxLayout.LINE_AXIS));
		ligneTrois.setOpaque(false);
		ligneTrois.setPreferredSize(new Dimension(largeurConteneur, 40));
		
		ligneTrois.add(paragrapheId);
		ligneTrois.add(textFieldId);

		
		JPanel ligneQuatre = new JPanel();
	//	ligneQuatre.setLayout(new BoxLayout(ligneQuatre, BoxLayout.LINE_AXIS));
		ligneQuatre.setOpaque(false);
		ligneQuatre.setPreferredSize(new Dimension(largeurConteneur, 40));
		
		ligneQuatre.add(paragrapheMdp);
		ligneQuatre.add(textFieldMdp);
		
		
		JPanel ligneCinq = new JPanel();
		ligneCinq.setLayout(new BoxLayout(ligneCinq, BoxLayout.LINE_AXIS));
		ligneCinq.setOpaque(false);
		ligneCinq.setPreferredSize(new Dimension(largeurConteneur, 75));
		
		ligneCinq.add(boutonValider);

		
//		conteneur.add(videHaut);
		conteneur.add(ligneUne);
		conteneur.add(ligneTrois);
		conteneur.add(ligneQuatre);
		conteneur.add(ligneCinq);
		
		// Ajout de tout les éléments à la vue
	/*	this.add(ligneUne);
		this.add(espacement[0]);
		if(error == true) {
			this.add(messageError);
		}
		this.add(titrePrincipale);
		this.add(ligneDeux);
		//this.add(espacement[1]);
		this.add(ligneTrois);
		//this.add(espacement[2]);
		this.add(ligneQuatre);*/
		
		this.add(conteneur);
	}

	// public boolean isConnected(String login, String mdp) {
	// 	if((login instanceof String) && (mdp instanceof String)) {
	// 		try {
	// 			User u = new User(login, mdp);
	// 			return true;
	// 		}
	// 		catch(Exception excep) {
	// 			System.out.println(excep);
	// 			return false;
	// 		}
	// 	}
	// 	else {
	// 		return false;
	// 	}
	// }
}
