package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.*;
import controller.AgendaC;
import model.Connecteur;
import model.User;

public  class ButtonRenderer extends JButton implements TableCellRenderer
{
	
	JButton button;     
	boolean enabled = true; 

	public ButtonRenderer() 
	{     
	    super();
		button = new JButton();  
		


	}
	 
	  public Component getTableCellRendererComponent(JTable table, Object value,
	                          boolean isSelected, boolean hasFocus, int row, int column) {
	    
	    
		    if (table.getValueAt(row, column) == "clicked") {
		        button.setEnabled(false);
		    } else {

		        button.setEnabled(true);
		    }

		    return button;
		    }
	  public void setEnabled(boolean enabled) 
	    {     
		    this.enabled = enabled;     
		}     
		}     

		class ButtonEditor extends AbstractCellEditor implements   
		TableCellEditor,ActionListener 
		{     
		JButton button;    
		private JTable cwwObjTblMainTable = null;
		boolean enabled = true;     
		int clickCountToStart = 1;  
		

		public ButtonEditor() 
		{     
		    button = new JButton();     
		    button.addActionListener(this);     
		}   
		
		public Component getTableCellEditorComponent(JTable table,     
		        Object value,     
		        boolean isSelected,     
		        int row, int column) {     

		    cwwObjTblMainTable=table;
		    button.setText("Voir"); 
		    button.setEnabled(enabled);     
		    return button;     
		}     

		public void setEnabled(boolean enabled) {     
		    this.enabled = enabled;     
		}     

		public Object getCellEditorValue() {     
		    return button.getText();     
		}     

		public boolean isCellEditable(EventObject anEvent) {     
		    if (anEvent instanceof MouseEvent) {     
		        return ((MouseEvent)anEvent).getClickCount() >= clickCountToStart;     
		    }     
		    return true;     
		}     
		
		
		
		public void actionPerformed(ActionEvent e) 
		{  
		    enabled=false;
		    button.setEnabled(false);
		    
		    Object connecteur = Connecteur.connecteurUL;
		    
		     List List_Event = (List) AgendaC.consultationThisEvenement(Agenda.IDCal);

   			for(int y=0; y<List_Event.size();y++) {
   				 String event= (String)((List) List_Event.get(y)).get(0);	
   				 String dateDebut= (String)((List) List_Event.get(y)).get(1);
   				 String dateFin= (String) ((List) List_Event.get(y)).get(2);
   				 String heureDebut = (String)((List) List_Event.get(y)).get(4);
   				 String heureFin = (String)((List) List_Event.get(y)).get(5);
   				 String idAgenda = (String)((List) List_Event.get(y)).get(6);
   				 String idMedecin = (String)((List) List_Event.get(y)).get(7);
   				 String idRole = (String)((List) List_Event.get(y)).get(8);

   				 int idAgendaInt = Integer.parseInt(idAgenda);
   				 int idMedecinInt = Integer.parseInt(idMedecin);
   				 int idRoleInt = Integer.parseInt(idRole);

	   			 Object connecteurDue = Connecteur.connecteurUL;
   			     List List_Med = (List) AgendaC.consultationThisMedecin(idMedecin);

   			     for(int i=0; i<1; i++) {

   			    	 
   	   				 String nom= (String)((List) List_Med.get(i)).get(0);	
   	   				 String prenom= (String)((List) List_Med.get(i)).get(1);	
   	   				 String adresse= (String)((List) List_Med.get(i)).get(2);	
   	   				 String ville = (String)((List) List_Med.get(i)).get(3);	
   	   				 String	cp= (String)((List) List_Med.get(i)).get(4);
   	   				 String	tel= (String)((List) List_Med.get(i)).get(5);	
   			     
				
   	   				 Popup opened = new Popup("Voir evenement", 800,800);
		             //ouverture de l evenement choisi
	
					  opened.setAlwaysOnTop(true);
					  Font font_low = new Font("open-sans", Font.PLAIN, 18);
			    	  Font bold = new Font ("open-sans", Font.BOLD, 20);
			    	  opened.setPreferredSize(new Dimension(800,800));
			    	  opened.setFont(font_low);
			    	  TitreSecondaire titreOpened = new TitreSecondaire("Evènement");
			    	  JPanel eventOpen= new JPanel();
			    	  eventOpen.setPreferredSize(new Dimension(500,100));
			    	  JLabel eventOpenLabel = new JLabel(event);
			    	  eventOpenLabel.setFont(bold);
			    	  JPanel heureEvent = new JPanel();
			    	  JPanel medecin = new JPanel();
			    	  JLabel medecinLabel = new JLabel("Nom/prénom du médecin");
			    	  medecinLabel.setFont(bold);

			    	  JLabel medecinSelected = new JLabel(nom+" " +prenom);
			    	  medecinSelected.setFont(font_low);
			    	  JPanel adresseMed = new JPanel();
			    	  JLabel adresseMedLabel = new JLabel("Adresse ");
			    	  adresseMedLabel.setFont(bold);

			    	  JLabel adresseMedSelected = new JLabel(adresse);
			    	  adresseMedSelected.setFont(font_low);
			    	  JLabel cpMed = new JLabel("Code postal");
			    	  cpMed.setFont(bold);

			    	  JLabel cpMedSelected = new JLabel(cp);
			    	  cpMedSelected.setFont(font_low);
			    	  JLabel villeMed = new JLabel("Ville");
			    	  villeMed.setFont(bold);

			    	  JLabel villeMedSelected = new JLabel(ville);
			    	  villeMedSelected.setFont(font_low);
			    	  JPanel telMed = new JPanel();
			    	  JLabel telLabel = new JLabel("Téléphone");
			    	  telLabel.setFont(bold);

			    	  JLabel telMedSelected = new JLabel (tel);
			    	  telMedSelected.setFont(font_low);
			    	
			    	  if(heureDebut == null) {
			    		  JLabel heureVide = new JLabel("Pas d'heure pour cet évènement");
			    		  heureEvent.add(heureVide);
			    		  heureVide.setFont(font_low);


			    	  }
			    	  else {
			    		  JLabel heureDebutEvent = new JLabel("de :" + heureDebut);
				    	  JLabel heureFinEvent = new JLabel("à :" + heureFin);
				    	  heureDebutEvent.setFont(font_low);
				    	  heureFinEvent.setFont(font_low);
				    	  heureEvent.add(heureDebutEvent);
				    	  heureEvent.add(heureFinEvent);

			    	  }
			    	  JPanel fin = new JPanel();
					  Font fontP = new Font("open-sans", Font.PLAIN, 18);

			    	  if(dateFin.equals(dateDebut)) {
			    		  JLabel finVide = new JLabel("Evènement de ce jour");

			    		  finVide.setFont(fontP);
			    		  fin.add(finVide);
			    		  finVide.setPreferredSize(new Dimension(500,70));
			    	  }
			    	  else {
				    	  JLabel finLabel = new JLabel("Fin le : "+dateFin);
				    	  fin.add(finLabel);
			    		  finLabel.setFont(fontP);
				    	  finLabel.setPreferredSize(new Dimension(500,70));

			    	  }
			    	
		    		 
			    	  JPanel modifierPanel = new JPanel();
		    		  JButton modifier = new JButton ("Modifier l'évenement");

			    	  opened.add(eventOpen);
			    	  eventOpen.add(eventOpenLabel);
			    	  eventOpen.add(heureEvent);
			    	  eventOpen.add(fin);
			    	  fin.setPreferredSize(new Dimension (800, 75));
			    	  opened.add(medecin);
			    	  opened.add(adresseMed);
			    	  adresseMed.add(adresseMedLabel);
			    	  medecin.setBackground(Color.white);
			    	  medecin.add(medecinLabel);
			    	  adresseMed.setBackground(Color.gray);
			    	  telMed.setBackground(Color.white);
			    	  medecin.setPreferredSize(new Dimension (800, 75));
			    	  adresseMed.setPreferredSize(new Dimension (800, 75));
			    	  telMed.setPreferredSize(new Dimension (800, 75));

				      medecin.add(medecinSelected);
				      opened.add(adresseMed);
				      adresseMed.add(adresseMedLabel);
				      adresseMed.add(adresseMedSelected);
				      adresseMed.add(cpMed);
				      adresseMed.add(cpMedSelected);
				      adresseMed.add(villeMed);
				      adresseMed.add(villeMedSelected);
				      opened.add(telMed);
				      telMed.add(telLabel);
				      telMed.add(telMedSelected);

				     if(idRoleInt == 0 || idRoleInt == 1) {
				    	 System.out.println("role"+idRoleInt );
			    	  opened.add(modifierPanel);
			    	  modifierPanel.setPreferredSize(new Dimension(800, 75));
			    	  modifierPanel.add(modifier);

			    	  //modification de l'evenement si créer par le délégué
			    	  modifier.addActionListener(new ActionListener() {

					      public void actionPerformed(ActionEvent ae) {
			
					    	  
					    		  Popup modifPopup = new Popup("Modifier l'évenement", 800, 500);
						    	  TitreSecondaire titreModif = new TitreSecondaire("Evènement");
						    	  JPanel eventOpenModif = new JPanel();
						    	  eventOpenModif.setPreferredSize(new Dimension(500,100));
						    	  JLabel eventOpenLabelModif = new JLabel("Evenement :");
						          JTextArea inputEvent = new JTextArea(event,5,25);
						          String TextEvent = inputEvent.getText();
						          
						          JPanel dateModif = new JPanel();
						          dateModif.setPreferredSize(new Dimension(500,100));
						          JLabel heureDModif = new JLabel("de :");
						          JTextArea inputHeureDeb = new JTextArea(heureDebut,1,10);
						          JTextArea inputHeureFin = new JTextArea(heureFin,1,10);
						          JLabel heureFinModif = new JLabel("à :");
						          
						          JPanel tempsModif = new JPanel();
						          tempsModif.setPreferredSize(new Dimension(500,100));
						          JLabel tempsLabel = new JLabel("Heure de début :");
						          JTextArea inputDateDeb = new JTextArea(dateDebut, 1, 10);
						          JLabel tempsFinLabel = new JLabel("Heure de fin :");
						          JTextArea inputFinDeb = new JTextArea(dateFin, 1, 10);
						          
						          JPanel buttonModif = new JPanel();
						          buttonModif.setPreferredSize(new Dimension(500,100));
						          JButton buttonValiderModif = new JButton("Valider");
						    
						    	  

						          
						          buttonValiderModif.addActionListener(new ActionListener() {

								      public void actionPerformed(ActionEvent ae) {
								    	  String TextEvent = inputEvent.getText();
								    	  String TextDateDebut = inputDateDeb.getText();
								    	  String TextDateFin = inputFinDeb.getText();
								    	  String TextHeureDeb = inputHeureDeb.getText();
								    	  String TextHeureFin = inputHeureFin.getText();


								    	 // controller.AgendaC.updateEvent(idAgendaInt, TextEvent, TextDateDebut, TextDateFin, User.id_utilisateur, TextHeureDeb, TextHeureFin);
								    	  modifPopup .dispose();
								   
								      }
						          });
						          
						     	  opened.add(titreModif);
						     	
						    	  eventOpenModif.add(eventOpenLabelModif);
						    	  eventOpenModif.add(inputEvent);

						    	  dateModif.setPreferredSize(new Dimension(800, 75));
						    	  dateModif.add(heureDModif);
						    	  dateModif.add(inputHeureDeb);
						    	  dateModif.add(heureFinModif);
						    	  dateModif.add(heureFinModif);
						    	  modifier.add(tempsModif);
						    	  tempsModif.add(tempsLabel);
						    	  tempsModif.add(inputDateDeb);
						    	  tempsModif.add(inputDateDeb);
						    	  tempsModif.add(tempsFinLabel);
						    	  tempsModif.add(inputFinDeb);
						    	  modifierPanel.add(modifier);
						    	  modifierPanel.add(buttonModif);
						    	  modifierPanel.add(eventOpenModif);
						    	  modifierPanel.add(dateModif);			    	 
						    	  buttonModif.add(buttonValiderModif);
						    	 

					      	}
					      
					      });
			    	  
				     }
			    	  
   			    }
   			     
   			}
		}
}
		