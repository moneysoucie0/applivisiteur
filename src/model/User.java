package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.text.SimpleDateFormat;
//import java.sql.Date;

public class User {
	
	public static int id_utilisateur;
	public String nom;
	public String prenom;
	public String adresse;
	public String ville;
	public static int role;
	public String login;
	public String password;
	public boolean connected;
	//public String codePostal;
	public User currentUser;
	
	public User(String login1, String mdp) {
		//this.Read(login1, mdp);
	}
	
	public User(){
		// true = connection sans mdp et login
		this.connected = false;
	}
	
	/*
	*/
	
	/*public User Read(String inputLogin, String inputMdp){
		DB_Connection obj_DB_Connection = (DB_Connection) model.DB_Connection.connectionbduser;
		Connection connection = DB_Connection.getConnectionBduser();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			String query = String.format("select * from utilisateur WHERE login = %s AND password = %s", inputLogin, inputMdp);
			ps = connection.prepareStatement(query);
			//ps.setString(1, sl_no);
			//System.out.println(rs);
			rs=ps.executeQuery();
			//while(rs.next()){
				id_utilisateur = rs.getInt("idUtilisateur");		
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				adresse = rs.getString("adresse");			
				ville = rs.getString("ville");			
				role = rs.getInt("role");
				login = rs.getString("login");
				password = rs.getString("password");	
				System.out.println("je marche");
				System.out.println(rs);
			//}
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		return this;
	}
	
	/*
	public User Update(int Id, String Name, String Email, String Password, String RememberToken, int UserProfile, int SupplierId) {
		
		DB_Connection obj_DB_Connection = new DB_Connection();
		Connection connection = obj_DB_Connection.get_connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String query=String.format("UPDATE users SET name='%s', "
					+ "email='%s',"
					+ "password='%s',"
					+ "remember_token='%s',"
					+ "user_profile_id=%i,"
					+ "supplier_id=%i,"
					+ "updated_at='%d'"
					+ " WHERE id = %i", Id, Name, Email, Password, RememberToken, null, null, Date.from(null), Date.from(null));
			ps=connection.prepareStatement(query);
			//ps.setString(1, sl_no);
			System.out.println(ps);
			rs=ps.executeQuery();
		} catch (Exception e) {
			System.out.println("Erreur insert : " + e);
		}
		return this;
	}
	*/

	public Boolean isConnected() {
		return this.connected;
	}
	
	public void deconnexion() {
		
	 User currentUser;
		
	 System.gc();

	}
}
