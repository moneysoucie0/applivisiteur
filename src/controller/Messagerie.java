package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Connecteur;

public class Messagerie {
	
	public void EnvoiMessage (int idExpediteur, int idDestinataire, String contenu) {
		
		String requete = "INSERT INTO  (expediteur, destinataire, contenu) VALUES (" + idExpediteur + ", " + idDestinataire + ", '" + contenu + "')";
		
	//	System.out.println(requete);
		
		try {
			Connection conn = Connecteur.connecteurUserLab();
		    
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
		    
			ResultSet resultat = statement.executeQuery(requete);
		}catch (Exception ErreurCnxMedocLab) {
				System.out.println("Problème de connexion à: 'bdmedocLab'");
		}
	}
	
	public void ObtenirDerniersMessages (int idExpediteur, int idDestinataire) {
		
		String requete_toutNonLu = "SELECT * FROM message WHERE ( destinataire = '" + idDestinataire + "' AND expediteur = '" + idExpediteur + "' ) OR ( destinataire = '" + idExpediteur + "' AND expediteur = '" + idDestinataire + "' ) ORDER BY date DESC";
		
		String requete_30Derniers= "SELECT * FROM message WHERE ( destinataire = '" + idDestinataire + "' AND expediteur = '" + idExpediteur + "' ) OR ( destinataire = '" + idExpediteur + "' AND expediteur = '" + idDestinataire + "' ) ORDER BY date DESC LIMIT 30";
		
		try {
			Connection conn = Connecteur.connecteurUserLab();
		    
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
		    
			ResultSet resultat = statement.executeQuery(requete);
		}catch (Exception ErreurCnxMedocLab) {
				System.out.println("Problème de connexion à: 'bdmedocLab'");
		}
	}
}
