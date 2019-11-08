package controller;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.Connection;

import model.Connecteur;
import view.Popup;

public class Medecin {
	public static  List<List> listSpeMedecin(){
		try {
			List<List> List_SM = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;
			Statement statement = conn.createStatement();
		    String requete = "SELECT * FROM specialitepraticien;";
			ResultSet resultat = statement.executeQuery(requete);
			
			while(resultat.next()) {
				List<String> speMed = new ArrayList<String>();
				String nomSpe = resultat.getString("nom");
				int idSpe = resultat.getInt("idSpe");
				
				speMed.add(nomSpe);
				speMed.add(Integer.toString(idSpe));
				
				List_SM.add(speMed);
			}
			return List_SM;
		}catch(Exception e){
			return null;
		}
	}
	
	public static  List<List> listMedecin(){
		try {
			List<List> List_Med = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;
			Statement statement = conn.createStatement();
		    String requete = "SELECT * FROM praticien order by nom;";
			ResultSet resultat = statement.executeQuery(requete);
			
			while(resultat.next()) {
				List<String> Med = new ArrayList<String>();
				
				int idMed = resultat.getInt("idPraticien");
				int idSpeMed = resultat.getInt("idTypePraticien");
				String nomMed = resultat.getString("nom");
				String prenomMed = resultat.getString("prenom");
				String adresseMed = resultat.getString("adresse");
				String villeMed = resultat.getString("ville");
				String cpMed = resultat.getString("codePostal");
				String telMed = resultat.getString("telephone");

				Med.add(Integer.toString(idMed));
				Med.add(Integer.toString(idSpeMed));
				Med.add(nomMed);
				Med.add(prenomMed);
				Med.add(adresseMed);
				Med.add(villeMed);
				Med.add(cpMed);
				Med.add(telMed);

				List_Med.add(Med);
			}
			return List_Med;
		}catch(Exception e){
			return null;
		}
	}
	
	public static  List<List> listTypeMedecin(String idSpe){
		try {
			List<List> List_TM = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;
			Statement statement = conn.createStatement();
		    String requete = "SELECT idSpe, specialitepraticien.nom FROM `specialitepraticien` left join praticien on specialitepraticien.idSpe = praticien.idTypePraticien where specialitepraticien.idSpe = '"+idSpe+"';";
			ResultSet resultat = statement.executeQuery(requete);
			
			while(resultat.next()) {
				List<String> Spe = new ArrayList<String>();
				
				int idSpeMed = resultat.getInt("idSpe");
				String nomSpe = resultat.getString("nom");
			
				Spe.add(Integer.toString(idSpeMed));
				Spe.add(nomSpe);
			
				List_TM.add(Spe);
			}
			return List_TM;
		}catch(Exception e){
			return null;
		}
	}
	
	public static boolean updateMedecin(int idMedU, String nomMedecin, String prenomMedecin, String adresse, String ville, String cp, String tel) {
		int resultat = 0;
		try {
			Connection conn = (Connection) Connecteur.connecteurUL;

			/* Création de l'objet gérant les requêtes */
			String requete = "UPDATE praticien SET idPraticien='" + idMedU + "', nom ='" + nomMedecin + "', prenom = '" + prenomMedecin+ "', adresse= '" + adresse +"', ville ='" + ville+"', codePostal ='" + cp+ "', telephone ='" + tel + "' where idPraticien = " + idMedU + ";";
			Statement statement =  conn.createStatement();	

			int rep = statement.executeUpdate(requete);
			
			Popup Succes = new Popup("Médecin mis à jour", 800, 200);

			JPanel panelSucces = new JPanel();
			JLabel labelSucces = new JLabel("Le médecin a été correctement mise à jour !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));

			return (rep > 0);
		} catch (Exception e) {
			Popup NotSucces = new Popup("Le medecin n'a pas été correctement mise à jour !", 800, 100);

			JPanel panelNotSucces = new JPanel();
			JLabel labelNotSucces = new JLabel("Le médecin n'a pas été correctement mise à jour !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelNotSucces.setFont(font);
			NotSucces.add(panelNotSucces);
			panelNotSucces.add(labelNotSucces);

			panelNotSucces.setBackground(new Color(235, 77, 75));
			panelNotSucces.setForeground(new Color(191, 48, 48));
			return false;

		} 
	}


	public static  List<List> listThisMedecin(String nomMedStr){
		try {
			List<List> List_TMed = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;
			Statement statement = conn.createStatement();
		    String requete = "SELECT * FROM praticien where nom = '" + nomMedStr + "';";
			ResultSet resultat = statement.executeQuery(requete);
			
			while(resultat.next()) {
				List<String> TMed = new ArrayList<String>();
				
				int idMed = resultat.getInt("idPraticien");
				int idSpeMed = resultat.getInt("idTypePraticien");
				String nomMed = resultat.getString("nom");
				String prenomMed = resultat.getString("prenom");
				String adresseMed = resultat.getString("adresse");
				String villeMed = resultat.getString("ville");
				String cpMed = resultat.getString("codePostal");
				String telMed = resultat.getString("telephone");

				TMed.add(Integer.toString(idMed));
				TMed.add(Integer.toString(idSpeMed));
				TMed.add(nomMed);
				TMed.add(prenomMed);
				TMed.add(adresseMed);
				TMed.add(villeMed);
				TMed.add(cpMed);
				TMed.add(telMed);

				List_TMed.add(TMed);
			}
			return List_TMed;
		}catch(Exception e){
			return null;
		}
	}
	
	public static  List<List> listLastMedecin(){
		try {
			List<List> List_LMed = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;
			Statement statement = conn.createStatement();
		    String requete = "SELECT * FROM praticien order by idPraticien desc limit 1 ;";
			ResultSet resultat = statement.executeQuery(requete);
			
			while(resultat.next()) {
				List<String> LMed = new ArrayList<String>();
				
				int idMed = resultat.getInt("idPraticien");
				int idSpeMed = resultat.getInt("idTypePraticien");
				String nomMed = resultat.getString("nom");
				String prenomMed = resultat.getString("prenom");
				String adresseMed = resultat.getString("adresse");
				String villeMed = resultat.getString("ville");
				String cpMed = resultat.getString("codePostal");
				String telMed = resultat.getString("telephone");

				LMed.add(Integer.toString(idMed));
				LMed.add(Integer.toString(idSpeMed));
				LMed.add(nomMed);
				LMed.add(prenomMed);
				LMed.add(adresseMed);
				LMed.add(villeMed);
				LMed.add(cpMed);
				LMed.add(telMed);

				List_LMed.add(LMed);
			}
			return List_LMed;
		}catch(Exception e){
			return null;
		}
	}
	
	public static boolean suppressionMedecin(int idMedecin) {
		Statement statement = null;
		try {
			Connection conn = (Connection) Connecteur.connecteurUL;

			/* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();
			String requete = "DELETE FROM praticien WHERE idPraticien = " + idMedecin + ";";

			int resultat = statement.executeUpdate(requete);
			Popup Succes = new Popup("Suppression :", 800, 200);

			JPanel panelSucces = new JPanel();
			JLabel labelSucces = new JLabel("Le médecin a été correctement supprimé !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));
			return true;
		} catch (Exception e) {

			Popup NotSucces = new Popup("Suppression : erreur ", 800, 100);

			JPanel panelNotSucces = new JPanel();
			JLabel labelNotSucces = new JLabel("Le médecin n'a pas été correctement supprimé !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelNotSucces.setFont(font);
			NotSucces.add(panelNotSucces);
			panelNotSucces.add(labelNotSucces);

			panelNotSucces.setBackground(new Color(235, 77, 75));
			panelNotSucces.setForeground(new Color(191, 48, 48));

			return false;

		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
	}
	
	public static boolean ajouterMedecin(int idTypePraticien, String nom, String prenom, String adresse,String ville, int codePostal, String tel  ) {
		 String req = "INSERT INTO praticien (`idTypePraticien`, `nom`, `prenom`, `adresse`, `ville`, `codePostal`, `telephone`)" + 
		 		" VALUE ("+idTypePraticien+",'"+nom+"','"+prenom+"','"+adresse+"','"+ville+"',"+codePostal+",'"+tel+"') ";
		 Statement statement = null;
		try {
			Connection conn = (Connection) Connecteur.connecteurUL;
			statement = conn.createStatement();
			int resultat = statement.executeUpdate(req);
			
			return  (resultat<0);

			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			System.out.println("marche pas chef ajout medecins");
			return false ;
		}
		 
		 
	}
}


