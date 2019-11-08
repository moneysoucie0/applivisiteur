package controller;

import java.sql.*;
import model.Connecteur;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import controller.*;
import model.User;
import model.Connecteur;


import com.mysql.jdbc.Connection;


public class compteRenduControleur {

	/* Fonction d'ajout du compte rendu saisi */
	public static boolean ajoutCompteRendu (int medecin, String Motif, String commentaire, String date, int echantillon, int Medicament) {
		try { 
			Connection conn = (Connection) Connecteur.connecteurUL;
			
			commentaire = commentaire.replaceAll("(\')", "\\\\'");
			
			/* Requête d'insertion en base du compte rendu */
			String requete = 
					"INSERT INTO" + 
					"`rapport`( `date`, `bilan`, `motif`, `idUtilisateur`, `idPraticien`, `idMedicament`, `echantillon`,`dateCreation`)" + 
					"VALUES ('"+date+"','"+commentaire+"','"+Motif+"','"+connectionControleur.id_utilisateur+"','"+medecin+"',"+Medicament+",'"+echantillon+"',NOW())";

			Statement statement =  conn.createStatement();	

			/* Exécution de la reqête */
			int rep = statement.executeUpdate(requete);
			
			return (rep > 0);
		}
		
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef ajout cr");
			return false;
		}
	}
	
	/* Fonction de sélection du médecin */
	public static List<List> selectMedecin() {
		try {
			List<List> List_Medecins = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;

			/* Requête de récupération des ids des médecins */
			String requete = "SELECT idPraticien,nom, prenom, adresse, ville, codePostal, telephone FROM praticien;";
			Statement statement =  conn.createStatement();
			ResultSet resultat = statement.executeQuery(requete);

			/* Récupère tous les id des médecins */
			while(resultat.next()) {
				List<String> unMedecin = new ArrayList<String>();
				int idMed= resultat.getInt( "idPraticien" );
				String nomMed= resultat.getString( "nom" );
				String prenomMed = resultat.getString("prenom");
				String adresseMed = resultat.getString("adresse");
				String villeMed = resultat.getString("ville");
				String cpMed = resultat.getString("codePostal");
				String telMed = resultat.getString("telephone");


				unMedecin.add(Integer.toString(idMed));
				unMedecin.add(nomMed);
				unMedecin.add(prenomMed);
				unMedecin.add(adresseMed);
				unMedecin.add(villeMed);
				unMedecin.add(cpMed);
				unMedecin.add(telMed);
				List_Medecins.add(unMedecin);
			}
			
			return List_Medecins;
			
			
		}
		
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef  select medecin ");
			return null;
		}
		
		
	}

	public static String selectNomMedoc(int IdMedoc) {
		String nomMedoc;
		try {
		Connection conn = (Connection) Connecteur.connecteurML;
		
	    /* Création de l'objet gérant les requêtes */
		Statement statement = conn.createStatement();
		
		/* Requête récupérat les comptes rendus du user connecté */
	    String requete = "SELECT nom from medicament where idMedicament='"+IdMedoc+"';";
		
		ResultSet resultat = statement.executeQuery(requete);
		if(resultat.next()) {
			nomMedoc=resultat.getString("nom");
			
			return nomMedoc;
		}else {
			nomMedoc=null;
			return nomMedoc;
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			nomMedoc=null;
			return nomMedoc;
		}
		
		
	}

	public static List<List> selectVisiteur(){
        List<List> List_visiteur = new ArrayList<List>();
        String req="SELECT idUtilisateur,nom from utilisateur where role='1'";
        try {
            Connection conn = (Connection) Connecteur.connecteurUL;
            Statement statement =  conn.createStatement();
            ResultSet resultat = statement.executeQuery(req);
            while(resultat.next()) {
                List<String> User=new ArrayList<String>();
                int idUser=resultat.getInt("idUtilisateur");
                String nom=resultat.getString( "nom" );
                User.add(Integer.toString(idUser));
                User.add(nom);
                List_visiteur.add(User);
                
            }


            return List_visiteur;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/* Fonction de récupération des comptes rendus du user connecté */
	public static List<List> consultationCompteRendu(int unMois,int debut,int IdUser)  {
		

	
		try {
			List<List> List_CR = new ArrayList<List>();
			Connection conn =(Connection) Connecteur.connecteurUL;
			String Mois=Integer.toString(unMois);
			if(Integer.toString(unMois).length()<2) {
				Mois="0"+Integer.toString(unMois);
			}

		    /* Création de l'objet gérant les requêtes */
			Statement statement = conn.createStatement();
			
			/* Requête récupérat les comptes rendus du user connecté */
		    String requete = "SELECT rapport.idRapport, rapport.date, rapport.bilan, rapport.motif, rapport.idUtilisateur,"
		    		+ " rapport.echantillon, praticien.nom, rapport.idMedicament from rapport,praticien"
		    		+ " where rapport.idPraticien=praticien.idPraticien AND rapport.idUtilisateur="+IdUser+""
		    				+ " AND rapport.date LIKE '%-"+Mois+"-%' LIMIT 6 OFFSET "+debut+";";
		    
			ResultSet resultat = statement.executeQuery(requete);
		    /* Exécution d'une requête de lecture */
			
		
		    /* Récupération des données du résultat de la requête de lecture */
		    while(resultat.next()) {
				List<String> cr = new ArrayList<String>();

	            int idRapport = resultat.getInt("rapport.idRapport");
	            String date = resultat.getString("rapport.date");
	            String bilan = resultat.getString("rapport.bilan");
	            String motif = resultat.getString("rapport.motif");
	            int echantillon = resultat.getInt("rapport.echantillon");
	            String medecin = resultat.getString("praticien.nom");
	            int medicament = resultat.getInt("rapport.idMedicament");
	            
	            int idUtilisateur = resultat.getInt("rapport.idUtilisateur");
	            cr.add(Integer.toString(idRapport));
	            cr.add(date);
	            cr.add(bilan);
	            cr.add(motif);
	            cr.add(Integer.toString(echantillon));
	            cr.add(medecin);
	            cr.add(Integer.toString(medicament));
	            cr.add(Integer.toString(idUtilisateur));
	            List_CR.add(cr);
	            


	           
	            /* Formatage des données pour affichage dans la JSP finale. */
				//System.out.println( "Données retournées par la requête :  Id="+idUtilisateur+".");
	            
	            User.id_utilisateur = idUtilisateur;
	           
				
	            // Pour faire ca, faut que les attributs de user soit en static, me demander par pourquoi
			}
		    if(List_CR.isEmpty()) {
				//System.out.println("empty");
			}
		    

			return List_CR;

		   
		}
		
		    
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef consult cr ");
			return null;
		}
		
		
	
	}	
	
	public static List<List> selectMedicament() {
		try {
			List<List> List_Medoc = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurML;
		
			/* Requête de récupération des ids des medicament */
			String requete = "SELECT `idMedicament`,`nom` FROM `medicament` WHERE 1;";
			Statement statement =  conn.createStatement();

			ResultSet resultat = statement.executeQuery(requete);
			/* Récupère tous les id des medicament */
			while(resultat.next()) {
				List<String> unMedoc = new ArrayList<String>();
				int idMed= resultat.getInt( "idMedicament" );
				String nomMed= resultat.getString( "nom" );
				unMedoc.add(Integer.toString(idMed));
				unMedoc.add(nomMed);
				List_Medoc.add(unMedoc);
			}

			return List_Medoc;
			
			
		}
		
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef medoc cr");
			return null;
		}
		
		
	}
	
}
