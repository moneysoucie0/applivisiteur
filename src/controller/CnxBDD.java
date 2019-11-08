package controller;

import java.sql.Connection;
import model.Connecteur;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.omg.CORBA.Current;

import java.sql.SQLException;
import view.Fenetre;
import model.Config;
import model.User;

public class CnxBDD {
	public static Connection connecteurUserLab() {
		/* Connection � la base de donn�e BDUserLab */
		final int INITIAL_POOL_SIZE = 10;
		Statement statement = null;
		ResultSet resultat = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			

		    Config c = new Config();


		   // String url = "jdbc:mysql://"+c.getProp("DB_HOST")+"/"+c.getProp("DB_DATABASE")+"?useSSL=false";

		    String url = "jdbc:mysql://"+c.getProp("DB_HOST")+"/"+c.getProp("DB_DATABASE")+"?useSSL=false";

		    String user = c.getProp("DB_USER");
		    String passwd = c.getProp("DB_PASSWORD");
		    
		    
		    //utilisez ce connecteur partout ! sinon je vous frappe !!!!

		    Connecteur.connecteurUL = DriverManager.getConnection(url, user, passwd);

		    return Connecteur.connecteurUL; 

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("La connexion a eu un probl�me");
			return null;
		} finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) {
					/* ignored */ }
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
	}

	/* Connection � la base de donn�e BDMedocLab */
	public static Connection connecteurMedocLab() {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();


			Config c = new Config();

		    String url = "jdbc:mysql://"+c.getProp("DB_HOST")+"/"+c.getProp("DB_DATABASE_MEDOC")+"?useSSL=false";
		    String user = c.getProp("DB_USER");
		    String passwd = c.getProp("DB_PASSWORD");


		    Connecteur.connecteurML = DriverManager.getConnection(url, user, passwd);
		    return Connecteur.connecteurML; 

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("La connexion a eu un probl�me");
			return null;
		} finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
	}

	/* Connexion et r�cup�ration des informations du user connect� */
	public static Boolean connect(String login, String mdp, User User) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			Connection conn = connecteurUserLab();
			Connection connMedoc=connecteurMedocLab();
		    /* Cr�ation de l'objet g�rant les requ�tes */
		    statement = conn.createStatement();
		    
		    
		    /* Ex�cution d'une requ�te de lecture */
		    
		    //N'oubliez pas de mettre des ' ' sur vos variables comme ici pr�sent, j'ai mit 5min avant de comprendre xD
			resultat = statement.executeQuery("SELECT idUtilisateur, login, password, nom, prenom,role  FROM utilisateur WHERE login='" + login + "' AND password='" + mdp + "';");
			statement.setFetchSize(100);

			/* R�cup�ration des donn�es du r�sultat de la requ�te de lecture */
			if (resultat.next()) {
				User.id_utilisateur = resultat.getInt("idUtilisateur");
				User.nom = resultat.getString("nom");
				User.prenom = resultat.getString("prenom");
				User.role = resultat.getInt("role");
				User.connected = true;
				
				return true;
				// Pour faire ca, faut que les attributs de user soit en static, me demander par
				// pourquoi
			} else {
				System.out.println("login ou mot de passe incorrect");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("La connexion a eu un probl�me");
			return false;
		} finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();

				} catch (SQLException e) {
					/* ignored */}
			}
		}
	}
	
	
}