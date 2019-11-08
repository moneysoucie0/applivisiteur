package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;

import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.AgendaC;
import controller.compteRenduControleur;
import model.Connecteur;
import model.User;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AgendaVisiteur extends JPanel {
	public AgendaVisiteur() {
		TitrePrincipale titre = new TitrePrincipale("Agenda des visiteurs");
		List<List> ListVisiteur = (List<List>) AgendaC.selectVisiteur();
        List<String> ListNomVisiteur=new ArrayList<String>();
        Calendar date = Calendar.getInstance();

		
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
		visiteurs.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				Popup choixVisiteur = new Popup("", 800,800);
				TitreSecondaire titre = new TitreSecondaire("Ajouter un rendez vous");
				  Object connecteur = Connecteur.connecteurUL;
			      List<List> ListMed= compteRenduControleur.selectMedecin();
			      List<String> ListNomMed=new ArrayList<String>();
			   
			    	  for(int m =0; m<ListMed.size(); m++) {
			  			  ListNomMed.add((String) ListMed.get(m).get(1));

			    	  }
			    	  
				 
				
				  UtilDateModel model = new UtilDateModel();
				  JDatePanelImpl datePanel = new JDatePanelImpl(model);
				  UtilDateModel model2 = new UtilDateModel();
				  JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
				  JDatePickerImpl datePickerDeb = new JDatePickerImpl(datePanel);
				  JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanel2);
				  
				

				  JPanel dateFin = new JPanel();
				  dateFin.setPreferredSize(new Dimension(600, 100));

				  JPanel heureDebut = new JPanel();
				  JPanel heureFin = new JPanel();
				  heureDebut.setPreferredSize(new Dimension(600,100));
				  JPanel evenement = new JPanel();
				  evenement.setPreferredSize(new Dimension(600, 100));
				  JPanel valider = new JPanel();
				  JLabel dateFinLabel = new JLabel("* Rendez-vous le :");

			      JLabel heureDebutLabel = new JLabel("* A :"); 
			      String[]  heureItems = {"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
			      JComboBox<?> heureDebutSelect = new JComboBox<Object>(heureItems);
				  String heureDeb = (String)heureDebutSelect.getSelectedItem();


			      String[]  minItems = {"00", "15", "30", "45"};
			      JComboBox<?> minDebutSelect = new JComboBox<Object>(minItems);
				  String minDeb = (String)minDebutSelect.getSelectedItem();

			      JLabel evenementLabel = new JLabel("* Avec : "); 
			      String[] nomMed=ListNomMed.toArray(new String[0]);


			      JComboBox<?> BoxMedChoice = new JComboBox<Object>(nomMed);
				  Object medecinSelected = BoxMedChoice.getSelectedItem();
				  String medecin = String.valueOf(medecinSelected);
				  
				     List<List> unMed= AgendaC.choixMedecin(medecinSelected);
				     String adresseMed =  new String();
				     String telMed =  new String();
				     String idMedecin = null;
				     int idMedecinInt;


					   
			    	  for(int u =0; u<unMed.size(); u++) {

			    		  idMedecin = (String) ListMed.get(u).get(0);
						  idMedecinInt = Integer.parseInt(idMedecin);

			    		  String nomMedecin = (String) ListMed.get(u).get(1);
			    		  String prenomMed = (String) ListMed.get(u).get(2);
			    		

			    		  
			    	  }
				    	  JPanel adresse = new JPanel();
					      JLabel adresseLabel = new JLabel("* Au  :");     
				    	  JLabel inputAdresse = new JLabel(adresseMed);
					      JPanel tel = new JPanel();
					      tel.setPreferredSize(new Dimension(600, 100));
					      JLabel telLabel = new JLabel("* Numéro à contacter : ");     
					      JLabel inputTel = new JLabel(telMed);
			    	  

			      JButton validerButton = new JButton("Valider");
			      JPanel obligatoirePanel = new JPanel();
			      JLabel obligatoire = new JLabel(" * Tous les champs sont obligatoires");
			      obligatoire.setForeground(Color.red);
			      Font font = new Font("Open Sans", Font.PLAIN, 24);
				  obligatoire.setFont(font);
			      valider.setPreferredSize(new Dimension(1000, 100));

			
				  dateFin.add(dateFinLabel);
				  dateFin.add(datePickerFin);
			      heureDebut.add(heureDebutLabel);
				  heureDebut.add(heureDebutSelect);
				  heureDebut.add(minDebutSelect);
				  evenement.add(evenementLabel);
				  evenement.add(BoxMedChoice);
		
				  obligatoirePanel.add(obligatoire);
				  valider.add(obligatoirePanel);
				  valider.add(validerButton);

			    	  
				  
				  validerButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String dateDebutEvent;
							/*DateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
							java.util.Date selectedDateDeb = (java.util.Date) datePickerDeb.getModel().getValue();
							dateDebutEvent = formatDate.format(selectedDateDeb);
							System.out.println("heure" +heureDebutEventTotal);*/
							String heureDebutEventTotal = heureDeb+":"+minDeb;

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
							try{

								controller.AgendaC.ajoutEvenementVisiteur(medecin, "2019-10-02", null ,idVisiteurInt, heureDebutEventTotal, null, 2,User.id_utilisateur);
							}catch(Exception e2) {}
						}
				  });
				
				  choixVisiteur.add(titre);
				  choixVisiteur.add(dateFin);
				  choixVisiteur.add(heureDebut);
				  choixVisiteur.add(heureFin);
				  choixVisiteur.add(evenement);
				  choixVisiteur.add(adresse);
				  choixVisiteur.add(tel);
				  choixVisiteur.add(valider);
			   
			  }
			
        });   

		
		JPanel top = new JPanel();
	    top.setPreferredSize(new Dimension(1800, 1200));
	    
	    JPanel tableau = new JPanel();
	    JPanel tableauII = new JPanel();
	    JPanel tableauIII = new JPanel();
	    JPanel tableauIV = new JPanel();
	    JPanel tableauV = new JPanel();
	    
	    tableau.setPreferredSize(new Dimension(300, 750));
	    tableauII.setPreferredSize(new Dimension(300, 750));
	    tableauIII.setPreferredSize(new Dimension(300, 750));
	    tableauIV.setPreferredSize(new Dimension(300, 750));
	    tableauV.setPreferredSize(new Dimension(300, 750));
	    tableau.setBackground(Color.white);
	    tableauII.setBackground(Color.gray);
	    tableauIII.setBackground(Color.white);
	    tableauIV.setBackground(Color.gray);
	    tableauV.setBackground(Color.white);
	    JPanel nom = new JPanel();


	    nom.setBackground(Color.cyan);
	    nom.setPreferredSize(new Dimension(300, 75));
	
	    JPanel lundi = new JPanel();
	    JPanel mardi = new JPanel();
	    JPanel mercredi = new JPanel();
	    JPanel jeudi = new JPanel();
	    JPanel vendredi = new JPanel();
	    JPanel samedi = new JPanel();
	    lundi.setPreferredSize(new Dimension(300, 108));
	    mardi.setPreferredSize(new Dimension(300, 108));
	    mercredi.setPreferredSize(new Dimension(300, 108));
	    jeudi.setPreferredSize(new Dimension(300, 108));
	    vendredi.setPreferredSize(new Dimension(300, 108));
	    samedi.setPreferredSize(new Dimension(300, 108));
	    lundi.setBackground(Color.white);
	    mardi.setBackground(Color.lightGray);
	    mercredi.setBackground(Color.white);
	    jeudi.setBackground(Color.lightGray);
	    vendredi.setBackground(Color.white);
	    samedi.setBackground(Color.lightGray);




	

		this.add(titre);
		this.add(visiteurs);
		this.add(top);
		top.add(tableau);
		top.add(tableauII);
		top.add(tableauIII);
		top.add(tableauIV);
		top.add(tableauV);
		tableau.add(nom);
		tableauII.add(nom);
		tableauIII.add(nom);
		tableauIV.add(nom);
		tableauV.add(nom);
		tableau.add(lundi);
	    tableau.add(mardi);
	    tableau.add(mercredi);
	    tableau.add(jeudi);
	    tableau.add(vendredi);
	    tableau.add(samedi);

	}

}
           
         
    
    
  