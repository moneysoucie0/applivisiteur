package view;

import view.TitrePrincipale;
import model.*;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.*;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.*;
import java.util.List;


public class ConsultationCompteRendu extends JPanel {
 

	private static final long serialVersionUID = 1L;

	public ConsultationCompteRendu() {
   
    	final  String[] months = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
  		      "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre" };
  
    	
    	DateFormat dateFormat = new SimpleDateFormat("MM");
    	List<List> List_CR= compteRenduControleur.consultationCompteRendu(Integer.parseInt(dateFormat.format(new Date())),0,User.id_utilisateur);
    	List<List> List_Visiteur= compteRenduControleur.selectVisiteur();
		List<String> ListNomVisiteur = new ArrayList<String>();
		List<Integer> ListIdVisiteur = new ArrayList<Integer>();

		for (int i = 0; i < List_Visiteur.size(); i++) {

			int idTemp = Integer.valueOf((String) List_Visiteur.get(i).get(0));
			ListIdVisiteur.add(idTemp);

			ListNomVisiteur.add((String) List_Visiteur.get(i).get(1));

		}
    	String[] nomVisiteur = ListNomVisiteur.toArray(new String[0]);
    	Integer[] idVisiteurList=ListIdVisiteur.toArray(new Integer[0]);
    	//String[] idVisiteurList= ListIdVisiteur.toArray(new String[0]);
    	JComboBox<?> BoxVisiteurChoice = new JComboBox<Object>(nomVisiteur);
		JComboBox<String> jcombo = new JComboBox<>(months);
		jcombo.setSelectedIndex(Integer.parseInt(dateFormat.format(new Date()))-1);
		
		TitrePrincipale mesSaisiesCompteRendu = new TitrePrincipale("Consultation des comptes rendus");
       
       int debut=0;
       JPanel espacement= new JPanel();
       JPanel cache = new JPanel(); 
       JPanel droite = new JPanel();
       droite.setPreferredSize(new Dimension(300, 100));

       jcombo.setLocation(10,200);
       
       JPanel panelOffset=new JPanel();
  

       JLabel JlabelOffset=new JLabel(debut+"-6");
       cache.setPreferredSize(new Dimension(300, 100));
       espacement.setPreferredSize(new Dimension(300, 30));
       
       panelOffset.setPreferredSize(new Dimension(500, 35));
       panelOffset.add(JlabelOffset, BorderLayout.CENTER);
       panelOffset.setBackground(Color.white);
       this.add(mesSaisiesCompteRendu);
       this.add(cache);
       if(User.role==2) {
    	   this.add(BoxVisiteurChoice, BorderLayout.CENTER);
       }
       this.add(jcombo, BorderLayout.CENTER);
       this.add(droite);

       this.add(panelOffset, BorderLayout.CENTER);
       
       JPanel carte[]= {new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};
       JPanel CardsPanel[]= {new JPanel()};
       
       for(int i = 1;i<40;i++) {
    	 CardsPanel = ajoutemoi(CardsPanel, new JPanel());
       	
       }
       JLabel date[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel medoc[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel labelIdMedoc[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel Medecin[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel bilan[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel motif[] = {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       
       JButton boutonVoir[]= {new JButton("Voir"),new JButton("Voir"),new JButton("Voir"),
    		   new JButton("Voir"),new JButton("Voir"),new JButton("Voir")};
       
       CRChangeLabel(Integer.parseInt(dateFormat.format(new Date())),0,date,medoc,labelIdMedoc,Medecin,bilan,motif,boutonVoir,User.id_utilisateur);
       for (int i = 0; i < carte.length; i++) {
    	   carte[i].setBackground(Color.white);
    	   carte[i].setPreferredSize(new Dimension(1820,50));
    	   
    	   final int iTmp = i;
    	   //Panel date
    	   CardsPanel[i].add(date[i]);
    	   CardsPanel[i].setPreferredSize(new Dimension(1200/3,50));
    	   CardsPanel[i].setBackground(Color.white);
    	   carte[i].add(CardsPanel[i]);
    	   
    	   
    	   //Panel medoc
    	   CardsPanel[i+7].add(labelIdMedoc[i]);
    	   labelIdMedoc[i].setVisible(false);
    	   CardsPanel[i+7].add(medoc[i]);
    	   CardsPanel[i+7].setPreferredSize(new Dimension(1200/3,50));
    	   CardsPanel[i+7].setBackground(Color.white);
    	   carte[i].add(CardsPanel[i+7]);

    	   //Panel medecin
    	   CardsPanel[i+14].add(Medecin[i]);
    	   CardsPanel[i+14].setPreferredSize(new Dimension(1200/3,50));
    	   CardsPanel[i+14].setBackground(Color.white);    	   
    	   carte[i].add(CardsPanel[i+14]);
    	   
    	   //Panel invisible bilan
    	   CardsPanel[i+20].add(bilan[i]);
    	   CardsPanel[i+20].setVisible(false);
    	   carte[i].add(CardsPanel[i+20]);
    	   if(Medecin[i].getText()!=null) {
    		  
	    	   carte[i].add(boutonVoir[i]);
	    	   boutonVoir[i].addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	int widthPopup=900;
				    	int HeightPopup=500;
				    	Popup popup = new Popup("Compte Rendu", widthPopup, HeightPopup);
					
						JPanel panelCompteRendu= new JPanel();
						
						
						JPanel[] espacement= {new JPanel(),new JPanel(),new JPanel(),new JPanel()};
						for (int j = 0; j < espacement.length; j++) {
							espacement[j].setPreferredSize(new Dimension(550,50));
							espacement[j].setBackground(Color.WHITE);
						}
						
						
						JLabel labelBilan=new JLabel("Bilan : "+bilan[iTmp].getText());
						JLabel labelDate=new JLabel(date[iTmp].getText());
						JLabel labelPraticien=new JLabel("Praticien : "+Medecin[iTmp].getText());
						int idMedoc=Integer.parseInt(labelIdMedoc[iTmp].getText());
						String nomMedoc=compteRenduControleur.selectNomMedoc(idMedoc);
						JLabel labelMedicament=new JLabel("Medicament : "+nomMedoc);
						JLabel labelMotif =new JLabel("Motif de la visite : "+motif[iTmp].getText());
						
						panelCompteRendu.setPreferredSize(new Dimension(700,400));
						panelCompteRendu.setBackground(Color.white);
	
						panelCompteRendu.add(labelDate);
						panelCompteRendu.add(espacement[0]);
						panelCompteRendu.add(labelMotif);
						panelCompteRendu.add(espacement[1]);
						panelCompteRendu.add(labelPraticien);
						panelCompteRendu.add(espacement[2]);
						panelCompteRendu.add(labelMedicament);
						panelCompteRendu.add(espacement[3]);
						panelCompteRendu.add(labelBilan);
						
						
						popup.add(panelCompteRendu);
				    }
	    	   });
    	   } 
    	   
    	   this.add(carte[i]);
	}
   	
       
       espacement.setPreferredSize(new Dimension(1000,100));
       

// select des dates
       		
		 jcombo.addActionListener(e -> {
			 
			 if(User.role==2) {
				 
				 CRChangeLabel((int) jcombo.getSelectedIndex()+1,0,date,medoc,labelIdMedoc,Medecin,bilan,motif,boutonVoir,idVisiteurList[(int) BoxVisiteurChoice.getSelectedIndex()]);
			 }else {
				 CRChangeLabel((int) jcombo.getSelectedIndex()+1,0,date,medoc,labelIdMedoc,Medecin,bilan,motif,boutonVoir,User.id_utilisateur);
			 }
			 JlabelOffset.setText("0-6");
		    });
		 JButton Suivant=new JButton("Suivant");
		 JButton Precedent=new JButton("Précédent");
		 
		 Suivant.addActionListener(new ActionListener() { 
			    public void actionPerformed(ActionEvent e) {
			    	int indexOffset=JlabelOffset.getText().indexOf("-");
			    	int offset=Integer.parseInt(JlabelOffset.getText().substring(0,indexOffset));
			    	offset+=6;
			    	int finOffset=offset+6;
			    	
			    	if(User.role==2) {
			    		CRChangeLabel((int) jcombo.getSelectedIndex()+1,offset,date,medoc,labelIdMedoc,Medecin,bilan,motif,boutonVoir,idVisiteurList[(int) BoxVisiteurChoice.getSelectedIndex()]);
					 }else {
						 CRChangeLabel((int) jcombo.getSelectedIndex()+1,offset,date,medoc,labelIdMedoc,Medecin,bilan,motif,boutonVoir,User.id_utilisateur);
					 }
			        JlabelOffset.setText(Integer.toString(offset)+"-"+finOffset);
			       
			    }
			});
		 BoxVisiteurChoice.addActionListener(e -> {
			 //System.out.println(idVisiteurList[(int) BoxVisiteurChoice.getSelectedIndex()]);
			 CRChangeLabel((int) jcombo.getSelectedIndex()+1,0,date,medoc,labelIdMedoc,Medecin,bilan,motif,boutonVoir,idVisiteurList[(int) BoxVisiteurChoice.getSelectedIndex()]);
			 JlabelOffset.setText("0-6");
		    });
		 
		 Precedent.addActionListener(new ActionListener() {
			 
			    public void actionPerformed(ActionEvent e) {
			    	int indexOffset=JlabelOffset.getText().indexOf("-");
			    	int offset=Integer.parseInt(JlabelOffset.getText().substring(0,indexOffset));
			    	if(offset!=0) {
			    	offset-=6;
				    int finOffset=offset+6;
				    if(User.role==2) {
						 CRChangeLabel((int) jcombo.getSelectedIndex()+1,offset,date,medoc,labelIdMedoc,Medecin,bilan,motif,boutonVoir,idVisiteurList[(int) BoxVisiteurChoice.getSelectedIndex()]);
					 }else {
						 CRChangeLabel((int) jcombo.getSelectedIndex()+1,offset,date,medoc,labelIdMedoc,Medecin,bilan,motif,boutonVoir,User.id_utilisateur);
					 }
			        JlabelOffset.setText(Integer.toString(offset)+"-"+finOffset);
			        
			    	}
			    }
			});
		 this.add(Precedent);
		 this.add(Suivant);
     
    }
	public void CRChangeLabel(int Mois,int debut,JLabel[] labelDate,JLabel[] labelMedoc,JLabel[] labelIdMedoc,JLabel[] labelMedecin,JLabel[] labelBilan,JLabel[] labelMotif,JButton[] bouton,int idUser){
		List<List> List_CR= compteRenduControleur.consultationCompteRendu(Mois,debut,idUser);
		if(List_CR.size()<6) {
			for (int i = 0; i < 6; i++) {
				
				
				if(i>List_CR.size()-1) {
					labelDate[i].setText("");
					labelMedoc[i].setText("");
					labelMedecin[i].setText("");
					labelBilan[i].setText("");
					labelIdMedoc[i].setText("");
					labelMotif[i].setText("");
					bouton[i].setVisible(false);
					
				}else {
					String dateBrute= (String) List_CR.get(i).get(1);
		        	String DdMmAaaa=dateBrute.substring(8,10)+"/"+dateBrute.substring(5,7)+"/"+dateBrute.substring(0, 4);
		        	
		        	int idMedoc=Integer.parseInt((String) List_CR.get(i).get(6));
					String nomMedoc=compteRenduControleur.selectNomMedoc(idMedoc);
		        	String Medecin=(String) List_CR.get(i).get(5);
		        	String Bilan=(String) List_CR.get(i).get(2);
		        	String Motif=(String) List_CR.get(i).get(3);
					labelDate[i].setText("Date: "+DdMmAaaa);
					labelIdMedoc[i].setText(Integer.toString(idMedoc));
					labelMedoc[i].setText(nomMedoc);
					labelMotif[i].setText(Motif);
					labelMotif[i].setVisible(false);
					labelMedecin[i].setText(Medecin);
					labelBilan[i].setText(Bilan);
					bouton[i].setVisible(true);
					
				}
				labelDate[List_CR.size()].setText("Il n'y a pas d'autre compte rendu.");
			}
			
		} else {
			for (int i = 0; i < List_CR.size(); i++) {
				int idMedoc=Integer.parseInt((String) List_CR.get(i).get(6));
				String dateBrute= (String) List_CR.get(i).get(1);
	        	String DdMmAaaa=dateBrute.substring(8,10)+"/"+dateBrute.substring(5,7)+"/"+dateBrute.substring(0, 4);
	        	String nomMedoc=compteRenduControleur.selectNomMedoc(idMedoc);
	        	String Medecin=(String) List_CR.get(i).get(5);
	        	String Bilan=(String) List_CR.get(i).get(2);
	        	String Motif=(String) List_CR.get(i).get(3);
	        	labelMotif[i].setText(Motif);
				labelMotif[i].setVisible(false);
				labelDate[i].setText("Date: "+DdMmAaaa);
				labelIdMedoc[i].setText(Integer.toString(idMedoc));
				labelMedoc[i].setText(nomMedoc);
				labelMedecin[i].setText(Medecin);
				labelBilan[i].setText(Bilan);
				bouton[i].setVisible(true);
			}
		}
	}
	private JPanel[] ajoutemoi(JPanel[] MonArray, JPanel NouveauPanel) {
		/*
		 * 
		 * Fonction qui sert � rajouter des �l�ments dans un tableau. (ici nous ajoutons des JPanels) 
		 * 
		 */
		int newSize = MonArray.length + 1;
		JPanel[] tempArray = new JPanel[ newSize ];
		//Nous cr��ons un array temporaire avec la taille de l'array actuelle
		
		for (int i=0; i < MonArray.length; i++)
		{
			tempArray[i] = MonArray[i];
			//Pour chaque �l�ment dans mon array, on ajoute l'�l�ment dans la nouvelle array temporaire
		}
		tempArray[newSize- 1] = NouveauPanel; //Ici on ajoute le nouveau Jpanel dans l'array
		return tempArray;   // on retourne notre nouvelle array
	}
}