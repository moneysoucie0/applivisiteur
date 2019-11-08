package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
	public static final DB_Connection connectionActive = new DB_Connection();
	public static Connection connectionbduser = null;
	public static Connection connectionbdmedoc = null;
	public static Connection connectionbdAV = null;
	public static void main(String[] args) {
		connectionActive.get_connection_bduser();  
	}
	
	
	//connection a la base de donn� bduser
	public Connection get_connection_bduser(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");              
			connectionbduser = DriverManager.getConnection("jdbc:mysql:http://bdlab.gsb.lan/phpmyadmin/sql.php?db=bduserLab&table=specialitepraticien&server=1&target=&token=75fd07ac1d3b861c642e9f46e3fd15c5#PMAURL-1:sql.php?db=bduserLab&table=message&server=1&target=&token=75fd07ac1d3b861c642e9f46e3fd15c5","rootuser", "Aristee.2018..//");
		} catch (Exception e) {
			System.out.println("Erreur : " + e);
		}
		
		return connectionbduser;
	}
	
	
	//connection a la base de donn�e bdmedoc 
	public Connection get_connection_bdmedoc(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");              
			connectionbdmedoc = DriverManager.getConnection("jdbc:mysql:http://bdlab.gsb.lan/phpmyadmin/index.php?db=bduserLab&table=message&target=sql.php&token=865360e8f5e9efd46199b406cd85b0ef#PMAURL-18:db_structure.php?db=bdmedocLab&table=&server=1&target=&token=865360e8f5e9efd46199b406cd85b0ef","rootmedoc", "Aristee.2018..//");
		} catch (Exception e) {
			System.out.println("Erreur : " + e);
		}
		
		return connectionbdmedoc;
	}
	public Connection get_connection_bdAV() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");              
			connectionbdAV = DriverManager.getConnection("jdbc:mysql:http://localhost/phpmyadmin/","root", "");
		} catch (Exception e) {
			System.out.println("Erreur : " + e);
		}
		
		return connectionbdAV;
		
	}
	
}