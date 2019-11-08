package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.CnxBDD;
import model.Connecteur;

public class CreationPowerPointController {
	
	/* Création de la fonction qui va retourner une liste des médicaments */
	public static List<String> ListeMedicament(String LettreSelected){
	String nomMedoc;
	String effetMedoc;
	List<String> medicaments_list = new ArrayList<String>();
	
	
	try {
		Connection conn = (Connection) Connecteur.connecteurML;
	    
	    /* Création de l'objet gérant les requêtes */
	    Statement statement = conn.createStatement();
	    
		ResultSet resultat = statement.executeQuery("SELECT nom FROM medicament WHERE nom LIKE '"+LettreSelected+"%';");
	
	    /* Récupération de tous les médicaments */
	    while(resultat.next()) {
            
            nomMedoc = resultat.getString( "nom" );
            medicaments_list.add(nomMedoc);
            
	    }
	    return medicaments_list;
	}catch (Exception ErreurCnxMedocLab) {
			System.out.println("Problème de connexion à: 'bdmedocLab'");
		}
	return medicaments_list;

	}

	/* Récupère les effets d'un médicaments s'il en a */
	public static String EffetMedoc(String nomMedicament){
		String effet = null;
		try {
			Connection conn = (Connection) Connecteur.connecteurML;
		    
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
			
			/* Cette requête permet d'enlever les '\n' et les '\r' dans l'effet de la base de donnée */
			ResultSet resultat = statement.executeQuery("SELECT REPLACE(REPLACE(effet, CHAR(13), ' '), CHAR(10), ' ') AS effetPropre FROM medicament WHERE nom ='"+nomMedicament+"';");
			
		
		    /* Récupération des données du résultat de la requête de lecture si le médicament a un effet */
		    if(resultat.next()) {
	            
	            effet = resultat.getString( "effetPropre" );
	            return effet;
		    }
		    else {
		    	System.out.println("Effet non récupérer, nom inexistant ou effet non répertorié");
		    	return effet;
		    }
		    
		}catch (Exception ErreurCnxMedocLab) {
				System.out.println("Problème de connexion à: 'bdmedocLab'");
			}
		
		return effet;
	}
	/* Fonction qui récupère les contres indications d'un médicament s'il en a */
	public static String contreIndication(String nomMedicament){
		String contreIndication = null;
		try {
			Connection conn = (Connection) Connecteur.connecteurML;
		    
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
			
			/* Cette requête permet d'enlever les '\n' et les '\r' dans la contre indication de la base de donnée */			
			ResultSet resultat = statement.executeQuery("SELECT  REPLACE(REPLACE(contreIndication, CHAR(13), ' '), CHAR(10), ' ') AS contreIndicationPropre FROM medicament WHERE nom ='"+nomMedicament+"';");
		
		    /* Récupération des données du résultat de la requête de lecture si le médicament a une contreIndication */
		    if(resultat.next()) {
	            
		    	contreIndication = resultat.getString( "contreIndicationPropre" );
	            return contreIndication;
		    }
		    else {
		    	System.out.println("contreIndication non récupérer, nom inexistant ou contreIndication non répertorié");
		    	return contreIndication;
		    }
		    
		}catch (Exception ErreurCnxMedocLab) {
				System.out.println("Problème de connexion à: 'bdmedocLab'");
			}
		
		return contreIndication;
	}
}
