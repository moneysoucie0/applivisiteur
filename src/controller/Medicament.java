package controller;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.Connection;

import model.Connecteur;
import view.Popup;


public class Medicament {

	public static boolean ajoutMedicament(String nomMedicament, String familleMedicament, String depotLegal, String effetMedicament, String contreIndication, String composition, String notice, String unite, String quantite, String typeIndividu,/* String famille,*/ String prix) {
		Statement statement = null;
		int resultat = 0;
		
		try {
			Connection conn =(Connection) Connecteur.connecteurML;
			statement = conn.createStatement();
			String requete = "INSERT INTO medicament (depotLegal, nom, effet, contreIndication, composition, quantite, unite, typeIndividu, famille, notice, prixEchantillon) VALUES ('"+depotLegal+"', '"+nomMedicament+"', '"+effetMedicament+"', '"+contreIndication+"', '"+composition+"', "+quantite+", '"+unite+"', '"+typeIndividu+"', '"+familleMedicament+"', '"+notice+"', "+prix+")";

			System.out.println("req = " + requete);
			
			resultat = statement.executeUpdate(requete);
			System.out.println("requete"+resultat);

			Popup Succes = new Popup("Ajout", 800,200);
			
			JPanel panelSucces = new JPanel(); 
			JLabel labelSucces = new JLabel("Le medicament a été correctement rajouté à la base de donnée !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));

			return true;
		}
		catch (Exception e){
			
			Popup NotSucces = new Popup("Le medicament n'a pas été correctement rajouté à la base de donnée !", 800,100);
			
			JPanel panelNotSucces = new JPanel(); 
			JLabel labelNotSucces = new JLabel("Le medicament n'a pas été correctement rajouté à la base de donnée !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelNotSucces.setFont(font);
			NotSucces.add(panelNotSucces);
			panelNotSucces.add(labelNotSucces);

			panelNotSucces.setBackground(new Color(235, 77, 75));
			panelNotSucces.setForeground(new Color(191, 48, 48));
			return false;
		}
		
		finally {
			if (statement != null) {
				try {
					statement.close();
				} 
				catch (SQLException e) {
				/* ignored */
				}
			}
		}
	}
}
