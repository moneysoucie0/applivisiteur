package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CompteRendu {
	public static String medecin;
	public String motif;
	public String bilan;
	public String echantillon;
	public int id_utilisateur;
	public int id_rapport;
	public String medicament;
	
	public CompteRendu() { 
	this.medecin = medecin;
	this.motif = motif;
	this.bilan = bilan;
	this.echantillon = echantillon;
	this.id_utilisateur = id_utilisateur;
	this.id_rapport = id_rapport;
	this.medicament= medicament;
	
	}
	
	public static String getMedecin() {
		return medecin;
	}
	
	public void setMedecin() {
		this.medecin = medecin;
	}
	
	
}
