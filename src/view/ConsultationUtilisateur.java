package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.*;
import model.Connecteur;


public class ConsultationUtilisateur extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public ConsultationUtilisateur() {
		TitrePrincipale bienvenue = new TitrePrincipale("Consultation Utilisateur");
		
		JButton equipe = new JButton("Créer une équipe");
		equipe.addActionListener(new ActionListener() {

//popu pour ajouter des visiteur a une equipe
		      public void actionPerformed(ActionEvent ae) {
		    	  Popup equipe = new Popup("Créer une équipe", 1200,700);
		    	  TitreSecondaire titrePopup = new TitreSecondaire("Créer une équipe");
		    	  JPanel ajoutEquipe = new JPanel();
		    	  JLabel delegueLabel = new JLabel("Délégués :");
		    	  ajoutEquipe.setPreferredSize(new Dimension(1200,150));
//créons la listes des délégués
		    	  List<List> ListDelegue = (List<List>) AgendaC.selectDelegue();
		          List<String> ListNomDelegue=new ArrayList<String>();

		  		
		  		for(int i=0; i<ListDelegue.size(); i++) {
		  			ListNomDelegue.add((String) ListDelegue.get(i).get(0));
		  			 String nomOnly= (String) ListDelegue.get(i).get(0);
		  			 JButton nomLabel = new JButton();
		  	         nomLabel.setText(nomOnly);


		  		}
		  		
		        String[] nomDelegue=ListNomDelegue.toArray(new String[0]);
		  		JComboBox<?> delegues = new JComboBox<Object>(nomDelegue);
		  		Object nomDelegueSelected = delegues.getSelectedItem();
		  		String nomDelegueStr = String.valueOf(nomDelegueSelected);

		    	JLabel visiteurLabel = new JLabel("Visiteurs :");
// et maintenant celle des visiteurs
		  		List<List> ListVisiteur = (List<List>) AgendaC.selectVisiteur();
		        List<String> ListNomVisiteur=new ArrayList<String>();

		  		
		  		for(int i=0; i<ListVisiteur.size(); i++) {
		  			ListNomVisiteur.add((String) ListVisiteur.get(i).get(0));
		  			 String nomOnly= (String) ListVisiteur.get(i).get(0);
		  			 JButton nomLabel = new JButton();
		  	         nomLabel.setText(nomOnly);


		  		}
		  		
		      
		  	    String[] nomVisiteur=ListNomVisiteur.toArray(new String[0]);
		  		JComboBox<?> visiteurs = new JComboBox<Object>(nomVisiteur);
		  		Object nomVisiteurSelected = visiteurs.getSelectedItem();
		  		String nomVisiteurStr = String.valueOf(nomVisiteurSelected);
		  		
		        String[] parts = nomVisiteurStr.split("_");
				String nomV = parts[0];
				String prenomV = parts[1];
		        List<List> ListidVisiteur = AgendaC.idVisiteur(nomV, prenomV);
				String idVisiteur =null;
				int idVisiteurInt =0;
				for (int v=0; v<ListidVisiteur.size(); v++) {
						idVisiteur = (String) ListidVisiteur.get(v).get(0);
						idVisiteurInt = Integer.parseInt(idVisiteur);
					}

		  		JLabel nomEquipe = new JLabel("Nom/Numéro équipe");
		  		JTextArea nomEquipeArea = new JTextArea(2,30);
		  		JButton ajouter = new JButton("Ajouter un visiteur");
		  		JPanel equipeEntier = new JPanel();
		  		equipeEntier.setPreferredSize(new Dimension(1200,400));
		  		equipeEntier.setBackground(Color.white);
		  		JPanel visiteurEquipe = new JPanel();
		  		JLabel equipeEntierLabel = new JLabel("Visiteurs dans l'équipe :");
		  		//ajoutons un visiteur a notre quipe
		  		ajouter.addActionListener(new ActionListener() {


					@Override
					public void actionPerformed(ActionEvent arg0) {
				  		String nomEquipeChoisi = nomEquipeArea.getText();
						controller.Utilisateur.ajoutVisiteurEquipe(1, nomEquipeChoisi);
						Object connecteur = Connecteur.connecteurUL;
						/*List<List> ListEquipe = controller.Utilisateur.consultationEquipe(nomEquipeChoisi);
						for(int i=0; i<ListEquipe.size();i++) {
							String nomVisiteurEquipe =  (String) ListEquipe.get(i).get(1);
							String prenomVisiteurEquipe =  (String) ListEquipe.get(i).get(2);

							visiteurEquipe.removeAll(); 
							visiteurEquipe.updateUI();
							JLabel newVisiteur = new JLabel(nomVisiteurEquipe+prenomVisiteurEquipe);
					    	visiteurEquipe.add(newVisiteur);

						}*/
						
					}
		  		});
		  		
		  		
		    	  equipe.add(titrePopup);
		    	  equipe.add(ajoutEquipe);
		    	  ajoutEquipe.add(delegueLabel);
		    	  ajoutEquipe.add(delegues);
		    	  ajoutEquipe.add(nomEquipe);
		    	  ajoutEquipe.add(nomEquipeArea);
		    	  ajoutEquipe.add(visiteurLabel);
		    	  ajoutEquipe.add(visiteurs);
		    	  ajoutEquipe.add(ajouter);
		    	  equipe.add(equipeEntier);
		    	  equipeEntier.add(visiteurEquipe);
		    	  visiteurEquipe.add(equipeEntierLabel);
		    	
		      }
		});
		
		JButton consultationEquipe = new JButton("Voir équipe(s) formée(s)");
		consultationEquipe.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				Popup equipePopup = new Popup("Voir équipe",800,500);
				Object connecteur = Connecteur.connecteurUL;
				String nomVisiteurEquipe= null;
				List<List> ListEquipe = controller.Utilisateur.consultationEquipe(nomVisiteurEquipe);
				for(int i=0; i<ListEquipe.size();i++) {
					String nomVisiteurEquipeStr =  (String) ListEquipe.get(i).get(1);
					String prenomVisiteurEquipe =  (String) ListEquipe.get(i).get(2);
					String nomEquipe =  (String) ListEquipe.get(i).get(4);
					
					JPanel equipePanel = new JPanel();
					JLabel nomEquipeLabel = new JLabel(nomEquipe);
					JLabel equipeVide = new JLabel("Pas d'équipe");
					equipePanel.setBackground(Color.red);
					equipePanel.setPreferredSize(new Dimension(650,200));
					equipePopup.add(equipePanel);

					if(nomEquipe == nomEquipe) {
						equipePopup.add(nomEquipeLabel);
					}
					else {
						equipePopup.add(equipeVide);

					}
					equipePopup.add(equipePanel);
					consultationEquipe.add(equipePopup);

				}
				
			}
		});

		
		
		this.add(bienvenue);
		this.add(equipe);
		this.add(consultationEquipe);
	}
}
