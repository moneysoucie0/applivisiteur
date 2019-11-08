package view;

import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;

import controller.AgendaC;
import controller.compteRenduControleur;
import model.User;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

 
public class Agenda<Spanned> extends JPanel  {

	private static final long serialVersionUID = 1L;
	public static String IDCal;
	private JPanel[] PanelJours;
	private JLabel[] numDujourLabel;
	private JLabel[] nbrEventInJourLabel;
	private JLabel[] nbrFinEventInJourLabel;
	private String[] idArrayVisiteur;
	private String[] nomArrayVisiteur;
	private JComboBox VisiteurSelect;
	private static Button MoisPrecedent= new Button("Mois précedent");
	private static Button MoisSuivant= new Button("Mois suivant");
	private static Boolean Rightclick=false;

	@SuppressWarnings("deprecation")
	public Agenda() {
		TitrePrincipale bienvenue = new TitrePrincipale("Agenda");
		
		
		
		
		this.add(bienvenue);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month= Calendar.getInstance().get(Calendar.MONTH);
        
        
        JPanel main=new JPanel();
        
		Dimension sizeEcran= Toolkit.getDefaultToolkit().getScreenSize();
		int height=sizeEcran.height-150;
        int width=sizeEcran.width;
        main.setPreferredSize(new Dimension(width, height));
        Button AjoutEventButton=new Button("Ajouter un évènement");
       
        JPanel[] Temp= {new JPanel()};
        JPanel[] JourSemaine={new JPanel()};
        String[] ListeNomJour= {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
        List<String> YearList=new ArrayList<>();
        for(int i=year-3;i<year+2;i++) {
        	YearList.add(Integer.toString(i));
        }
        String [] ListeAnnee = new String[YearList.size()];
        ListeAnnee = YearList.toArray(ListeAnnee);

        JComboBox<String> MonthSelect = new JComboBox<>(months);
        JComboBox<String> YearSelect= new JComboBox<>(ListeAnnee);
        MonthSelect.setSelectedIndex(month);
        YearSelect.setSelectedIndex(3);
        List<List> VisiteurList= AgendaC.selectVisiteur();
        List<String> idVisiteurList = new ArrayList<>();
        List<String> NomVisiteurList= new ArrayList<>();
        
        if(User.role!=1) {
        	NomVisiteurList.add("Mon agenda");
        	idVisiteurList.add(Integer.toString(User.id_utilisateur));
        	for (int i = 0; i < VisiteurList.size(); i++) {
        		idVisiteurList.add((String) VisiteurList.get(i).get(0));
        		NomVisiteurList.add((String) VisiteurList.get(i).get(1));
        	}
        }
       
        this.nomArrayVisiteur = NomVisiteurList.toArray(new String[NomVisiteurList.size()]);
        this.idArrayVisiteur= idVisiteurList.toArray(new String[idVisiteurList.size()]);

        this.VisiteurSelect= new JComboBox<>(nomArrayVisiteur);
        
        
        JPanel Selection=new JPanel();
        Selection.setPreferredSize(new Dimension(width-50, 100));
        Selection.add(Agenda.MoisPrecedent);
        if(User.role!=1) {	
        	Selection.add(this.VisiteurSelect);
        }
        Selection.add(MonthSelect);
        Selection.add(YearSelect);
        Selection.add(Agenda.MoisSuivant);
        Selection.add(AjoutEventButton);
        Selection.setBackground(Color.WHITE);
        main.add(Selection);
        
        
        for(int i = 1;i<7;i++) {
        	JourSemaine = ajoutemoi(JourSemaine, new JPanel());
        }
        for(int i=0;i<JourSemaine.length;i++) {
        	JLabel NomJour=new JLabel(ListeNomJour[i]);
        	NomJour.setForeground(Color.white);
        	JourSemaine[i].add(NomJour);
        	JourSemaine[i].setPreferredSize(new Dimension(width/8, 50));
        	JourSemaine[i].setBorder(BorderFactory.createLineBorder(Color.black));
        	JourSemaine[i].setBackground(new Color(0,63,128));
        	
        	main.add(JourSemaine[i]);
        }
        for(int i = 1;i<50;i++) {
        	Temp = ajoutemoi(Temp, new JPanel());
        }
        this.PanelJours=Temp;
        
        for (int i=0; i<getFirstDateOfCurrentMonth(year,month).getDay()-1;i++) {
        	this.PanelJours[i].setPreferredSize(new Dimension(width/8, 50));
        	this.PanelJours[i].setBorder(BorderFactory.createLineBorder(Color.black));
        	main.add(this.PanelJours[i]);
        	
        }
        JLabel[] TempLabel= {new JLabel()};
        JLabel[] nbrEventTemp= {new JLabel()};
        JLabel[] nbrFinEventTemp= {new JLabel()};
        for(int i = 1;i<this.PanelJours.length+1;i++) {
        	TempLabel = ajoutemoiLabel(TempLabel, new JLabel());
        	nbrEventTemp=ajoutemoiLabel(nbrEventTemp, new JLabel());
        	nbrFinEventTemp=ajoutemoiLabel(nbrFinEventTemp, new JLabel());
        }
        this.numDujourLabel= TempLabel;
        this.nbrEventInJourLabel=nbrEventTemp;
        this.nbrFinEventInJourLabel=nbrFinEventTemp;
        for (int i = 0; i < this.PanelJours.length; i++) {
        	this.PanelJours[i].setPreferredSize(new Dimension(width/8, 50));
        	this.numDujourLabel[i].setPreferredSize(new Dimension(width/8, 20));
        	this.nbrEventInJourLabel[i].setPreferredSize(new Dimension(width/20, 20));
        	this.nbrFinEventInJourLabel[i].setPreferredSize(new Dimension(width/16, 20));
			this.PanelJours[i].add(this.numDujourLabel[i]);
			this.PanelJours[i].add(this.nbrEventInJourLabel[i]);
			this.PanelJours[i].add(this.nbrFinEventInJourLabel[i]);
			this.PanelJours[i].setBackground(Color.WHITE);
			main.add(this.PanelJours[i]);
        }
        AjoutEventButton.addMouseListener(FormulairePopup(3,MonthSelect.getSelectedIndex(),Integer.parseInt((String) YearSelect.getSelectedItem()),0));
        
        ChangeMonth(month,year,User.id_utilisateur);
        YearSelect.addActionListener(LimitDate(MonthSelect,YearSelect,year));
        MonthSelect.addActionListener(LimitDate(MonthSelect,YearSelect,year));
        if(User.role!=1) {
            VisiteurSelect.addActionListener(e->{
            	int MoisChoisie=MonthSelect.getSelectedIndex();
            	int YearChoisie=Integer.parseInt((String) YearSelect.getSelectedItem());
            	ChangeMonth(MoisChoisie,YearChoisie,0);
            	
            	
            });
        }
    	
        Agenda.MoisPrecedent.addActionListener(e -> {
        	int MoisChoisie=MonthSelect.getSelectedIndex()-1;
        	int YearChoisie=Integer.parseInt((String) YearSelect.getSelectedItem());
        	Agenda.MoisSuivant.setEnabled(true);
        	if(MoisChoisie==0 && YearChoisie==year-3) {
        		Agenda.MoisPrecedent.setEnabled(false);
        	}else {
        		Agenda.MoisPrecedent.setEnabled(true);
        	}
        	if(MoisChoisie==-1) {
        		MoisChoisie=11;
        		YearChoisie=YearChoisie-1;
        		YearSelect.setSelectedItem(Integer.toString(YearChoisie));
        	}
        	MonthSelect.setSelectedIndex(MoisChoisie);
        	if(User.role!=1) {	
        		
        		ChangeMonth(MoisChoisie,YearChoisie,0);
            }
        	if(User.role==1) {
        		ChangeMonth(MoisChoisie,YearChoisie,User.id_utilisateur);	
        	}
        });
        Agenda.MoisSuivant.addActionListener(e -> {
        	int MoisChoisie=MonthSelect.getSelectedIndex()+1;
        	int YearChoisie=Integer.parseInt((String) YearSelect.getSelectedItem());
        
        	Agenda.MoisPrecedent.setEnabled(true);
        	if(MoisChoisie==11 && YearChoisie==year+1) {
        		Agenda.MoisSuivant.setEnabled(false);
        	}else {
        		Agenda.MoisSuivant.setEnabled(true);
        	}
        	if(MoisChoisie==12) {
        		MoisChoisie=0;
        		YearChoisie=YearChoisie+1;
        		YearSelect.setSelectedItem(Integer.toString(YearChoisie));
        	}
        	MonthSelect.setSelectedIndex(MoisChoisie);
        	if(User.role!=1) {
        		ChangeMonth(MoisChoisie,YearChoisie,0);
            }
        	if(User.role==1) {
        		ChangeMonth(MoisChoisie,YearChoisie,User.id_utilisateur);
        	}
        });
        
        

        main.setBackground(Color.WHITE);
		this.add(main);
		
		
	}
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void ChangeMonth(int month,int year,int VisiteurChoisi) {
		//On récupère tous les évènements du mois
		int IdUser=VisiteurChoisi;
		if(VisiteurChoisi==0 && User.role!=1 && VisiteurSelect.getSelectedIndex()!=-1) {			
			IdUser=Integer.parseInt(idArrayVisiteur[VisiteurSelect.getSelectedIndex()]);
		}
		List<List> EventDuMois=AgendaC.consultationEvenementMois(IdUser,month+1,year);
		//System.out.println(EventDuMois);
		YearMonth yearMonthObject = YearMonth.of(year, month+1);
        int NumberDaysInMonth = yearMonthObject.lengthOfMonth();
        int numJour=getFirstDateOfCurrentMonth(year,month).getDay();
        if(numJour==0) {
        	numJour=7;
        }
        for (int i = 0; i < this.PanelJours.length; i++) {
        	this.nbrEventInJourLabel[i].setText("");
        	this.nbrFinEventInJourLabel[i].setText("");
        	this.PanelJours[i].setBackground(Color.WHITE);
        	this.PanelJours[i].removeMouseListener(eventDuJourPopup(Integer.toString(i-numJour+2),month,year,IdUser));
        	
        	//On enlève tout les MouseListener à chaque panel, cela évite d'ouvrir deux popup lors d'un changement de mois
        	for( MouseListener al : this.PanelJours[i].getMouseListeners() ) {
        		this.PanelJours[i].removeMouseListener( al );
        	}
        	
        	if(i>numJour-2 && i<NumberDaysInMonth+numJour-1) {
        		this.numDujourLabel[i].setText(Integer.toString(i-numJour+2));
        		this.numDujourLabel[i].setHorizontalAlignment(JLabel.CENTER);
        		
        	}else {
        		this.numDujourLabel[i].setText("");
        	}

        	if(i<NumberDaysInMonth+numJour-1) {
        		this.PanelJours[i].setBorder(BorderFactory.createLineBorder(Color.black));
        	}else {
        		this.PanelJours[i].setBorder(null);
        	}
        	
		}
        if(EventDuMois.size()>0) {
        	List<String> ListJourAvecEvent=new ArrayList<>();
        	List<String> ListJourFinEvent=new ArrayList<>();
        	 for(int j=0;j<EventDuMois.size();j++) {
         		String DateDebut=(String) EventDuMois.get(j).get(1);
         		String DateFin=(String) EventDuMois.get(j).get(2);
         		Date dateDebut=null;
         		Date dateFin=null;
         		try {
         			dateDebut=new SimpleDateFormat("yyyy-MM-dd").parse(DateDebut);
         			dateFin=new SimpleDateFormat("yyyy-MM-dd").parse(DateFin);
         		} catch (ParseException e) {
         			e.printStackTrace();
         			System.out.println("problème à la ligne : "+ Integer.toString(getLineNumber()));
         		}
         		SimpleDateFormat formaterJDebut = new SimpleDateFormat("dd");
         		SimpleDateFormat formaterJFin = new SimpleDateFormat("dd");
         		String JourEvent=formaterJDebut.format(dateDebut);
         		String JourFinEvent=formaterJFin.format(dateFin);
         		if (!ListJourAvecEvent.contains(JourEvent)) {
         			//On évite d'ajouter plusieurs MouseListener à une seule journée, évitant donc plusieurs popup pour le jour 
         			ListJourAvecEvent.add(JourEvent);
         		}
         		if(!ListJourFinEvent.contains(JourFinEvent) && !ListJourAvecEvent.contains(JourFinEvent)) {
         			ListJourFinEvent.add(JourFinEvent);
         		}
         		
         	}for(int j=0;j<ListJourAvecEvent.size();j++) {
         		String JourEvent=ListJourAvecEvent.get(j);
         		
         		int nbrEventInDay=0;
         		if(User.role==1) {
         			
         			nbrEventInDay=AgendaC.consultationEvenementJour(User.id_utilisateur,JourEvent,month+1,year,true).size();
         		} else {
         			nbrEventInDay=AgendaC.consultationEvenementJour(IdUser,JourEvent,month+1,year,true).size();
         		}
         		if(nbrEventInDay!=0) {         			
         			this.nbrEventInJourLabel[Integer.parseInt(JourEvent)+numJour-2].setText(Integer.toString(nbrEventInDay));
         			this.nbrEventInJourLabel[Integer.parseInt(JourEvent)+numJour-2].setHorizontalAlignment(JLabel.LEFT);
         			this.nbrEventInJourLabel[Integer.parseInt(JourEvent)+numJour-2].setForeground(Color.blue);
         		}
         		this.PanelJours[Integer.parseInt(JourEvent)+numJour-2].addMouseListener(eventDuJourPopup(JourEvent,month,year,IdUser));
         	}
         	for(int j=0;j<ListJourFinEvent.size();j++) {
         		String JourFinEvent=ListJourFinEvent.get(j);
         		int nbrEventInDay=0;
         		if(User.role==1) {
         			
         			nbrEventInDay=AgendaC.consultationEvenementJour(User.id_utilisateur,JourFinEvent,month+1,year,false).size();
         		}else {
         			nbrEventInDay=AgendaC.consultationEvenementJour(IdUser,JourFinEvent,month+1,year,false).size();
         		}
         		if(nbrEventInDay!=0) {         			
         			this.nbrFinEventInJourLabel[Integer.parseInt(JourFinEvent)+numJour-2].setText(Integer.toString(nbrEventInDay));
         			this.nbrFinEventInJourLabel[Integer.parseInt(JourFinEvent)+numJour-2].setHorizontalAlignment(JLabel.RIGHT);
         			this.nbrFinEventInJourLabel[Integer.parseInt(JourFinEvent)+numJour-2].setForeground(new Color(98, 0, 0));
         		}
         		//this.PanelJours[Integer.parseInt(JourFinEvent)+numJour-2].addMouseListener(eventDuJourPopup(JourFinEvent,month,year));
         	}
        }
       
    		
	}
	
	String[] months = {"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Decembre"};
	String[] quartDheur= {"00","15","30","45"};
	
	
	public ActionListener LimitDate(JComboBox<String> MonthSelect,JComboBox<String> YearSelect,int year) {
		ActionListener DateLimited=new ActionListener() {
			/*
			 * @see on ne veut pas que l'utilisateur change d'année à l'infine,
			 * 		on ajoute donc une limite d'année, et on désactive les boutons mois suivant et mois précédent si limite atteinte.
			 * 
			 * @return ActionListener
			 * 
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int MoisChoisie=MonthSelect.getSelectedIndex();
				int YearChoisie=Integer.parseInt((String) YearSelect.getSelectedItem());
				int IdUser = 0;
				if(User.role==1) {
					IdUser=User.id_utilisateur;
				}else {
					if(VisiteurSelect.getSelectedIndex()!=-1) {						
						IdUser=Integer.parseInt(idArrayVisiteur[VisiteurSelect.getSelectedIndex()]);
					}
				}
	        	ChangeMonth(MoisChoisie,YearChoisie,IdUser);
	        	if(MoisChoisie==11 && YearChoisie==year+1) {
	        		Agenda.MoisSuivant.setEnabled(false);
	        	}else {
	        		Agenda.MoisSuivant.setEnabled(true);
	        	}
	        	if(MoisChoisie==0 && YearChoisie==year-3) {
	        		Agenda.MoisPrecedent.setEnabled(false);
	        	}else {
	        		Agenda.MoisPrecedent.setEnabled(true);
	        	}	
			};
		};
		return DateLimited;
	}
	
	public MouseListener FormulairePopup(int day,int month, int year,int idEventModif) {
		MouseListener Form=new MouseListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void mousePressed(MouseEvent e) {
				int widthPopup=900;
		    	int HeightPopup=500;
				Popup popup=new Popup("Ajouts d'évènements",widthPopup,HeightPopup);
				List<String> HorlogeList=new ArrayList<>();
				for (int i=6;i<18;i++) {
					for(int j=0;j<quartDheur.length;j++) {
						if(Integer.toString(i).length()==1) {
							HorlogeList.add("0"+Integer.toString(i)+":"+quartDheur[j]);
						}else {
							HorlogeList.add(Integer.toString(i)+":"+quartDheur[j]);
						}
					}
				}
				List<List> VisiteurListPopup= AgendaC.selectVisiteur();
		        List<String> idVisiteurListPopup = new ArrayList<>();
		        List<String> NomVisiteurListPopup= new ArrayList<>();
		        
		        if(User.role!=1) {
		        	NomVisiteurListPopup.add("Mon agenda");
		        	idVisiteurListPopup.add(Integer.toString(User.id_utilisateur));
		        	for (int i = 0; i < VisiteurListPopup.size(); i++) {
		        		idVisiteurListPopup.add((String) VisiteurListPopup.get(i).get(0));
		        		NomVisiteurListPopup.add((String) VisiteurListPopup.get(i).get(1));
		        	}
		        }
		        String[] nomArrayVisiteurPopup = NomVisiteurListPopup.toArray(new String[NomVisiteurListPopup.size()]);
		        String[] idArrayVisiteurPopup= idVisiteurListPopup.toArray(new String[idVisiteurListPopup.size()]);

		        JComboBox VisiteurSelectPopup= new JComboBox<>(nomArrayVisiteurPopup);
		        
		        
				JButton modifButton=new JButton("Modifier l'évènement");
				
				String[] HorlogeArray=new String[HorlogeList.size()];
				HorlogeArray=HorlogeList.toArray(HorlogeArray);
				JComboBox<String> HorlogeSelectDebut = new JComboBox<>(HorlogeArray);
				JComboBox<String> HorlogeSelectFin = new JComboBox<>(HorlogeArray);
				
				JPanel panelPopup= new JPanel();
				panelPopup.setPreferredSize(new Dimension(widthPopup, HeightPopup));
		    	panelPopup.setBackground(Color.white);
		    	
				JPanel DateDebutPanel=new JPanel();
				DateDebutPanel.setPreferredSize(new Dimension(widthPopup, 50));
				DateDebutPanel.setBackground(Color.white);
				JPanel DateFinPanel=new JPanel();
				DateFinPanel.setPreferredSize(new Dimension(widthPopup, 50));
				DateFinPanel.setBackground(Color.white);
		    	
		    	JLabel DateDebut=new JLabel("Date du début de l'évènement");
		    	JLabel DateFin=new JLabel("Date de la fin de l'évènement");
		    	UtilDateModel model1 = new UtilDateModel();
		    	UtilDateModel model2 = new UtilDateModel();
		    	// TODO : set value à un jDatePicker, mais je crois que c'est pas possible =/
		    	if(day!=0) {
		    		model1.setDate(year, month, day);
		    		
		    		model2.setDate(year, month, day);
		    	}
				JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
				JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
				
				JDatePickerImpl datePickerDebut = new JDatePickerImpl(datePanel1);
				JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanel2);
				JPanel espacement[]= {new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};

				for(int i=0;i<espacement.length;i++) {
					espacement[i].setPreferredSize(new Dimension(widthPopup/8, 50));
					espacement[i].setBackground(Color.white);
				}
				
				if(User.role!=1) {
		        	panelPopup.add(VisiteurSelectPopup);
		        }
				
				DateDebutPanel.add(DateDebut);
				DateDebutPanel.add(datePickerDebut);
				DateDebutPanel.add(espacement[0]);
				DateDebutPanel.add(HorlogeSelectDebut);
				panelPopup.add(DateDebutPanel);
				DateDebutPanel.add(espacement[1]);
				
				
				DateFinPanel.add(DateFin);
				DateFinPanel.add(datePickerFin);
				
				
				DateFinPanel.add(espacement[2]);
				
				DateFinPanel.add(HorlogeSelectFin);
				DateFinPanel.add(espacement[3]);

				panelPopup.add(DateFinPanel);
				
				JLabel NomEventLabel=new JLabel("Nom de l'évènement : ");
				JFormattedTextField NomEventJTF=new JFormattedTextField();
				NomEventJTF.setColumns(15);
				JPanel NomEventPanel=new JPanel();
				NomEventPanel.add(NomEventLabel);
				NomEventPanel.add(NomEventJTF);
				NomEventPanel.setPreferredSize(new Dimension(widthPopup, 50));
				NomEventPanel.setBackground(Color.white);
				NomEventPanel.add(espacement[4]);
				espacement[4].setPreferredSize(new Dimension((int) (widthPopup/2.5), 50));
				panelPopup.add(NomEventPanel);
				
				JLabel Praticien=new JLabel("Visite de medecin ?");
				List<String> ListNomMed=new ArrayList<>();
				ListNomMed.add("Aucune Visite");
				List<List> ListMedTemp = compteRenduControleur.selectMedecin();
				List<Integer> ListIdMed=new ArrayList<>();
				for (int i = 0; i < ListMedTemp.size(); i++) {

					int idTemp = Integer.valueOf((String) ListMedTemp.get(i).get(0));
					ListIdMed.add(idTemp);
					ListNomMed.add((String) ListMedTemp.get(i).get(1));


				}
				String [] ArrayMedecin = new String[ListNomMed.size()];
				ArrayMedecin = ListNomMed.toArray(ArrayMedecin);
			
				JComboBox<String> SelectMedecin = new JComboBox<>(ArrayMedecin);
				
				JPanel Visite=new JPanel();
				Visite.setPreferredSize(new Dimension(widthPopup, 50));
				Visite.setBackground(Color.white);
				Visite.add(Praticien);
				Visite.add(SelectMedecin);
				Visite.add(espacement[5]);
				espacement[5].setPreferredSize(new Dimension(widthPopup/2, 50));
				if(User.role!=1) {					
					panelPopup.add(Visite);
				}
				
				JButton boutton=new JButton("Ajouter l'évènement");
				JPanel validation=new JPanel();
				validation.add(boutton);
				validation.setPreferredSize(new Dimension(widthPopup, 200));
				validation.setBackground(Color.white);
				
				if(idEventModif!=0) {
					List<String> MonEvent= new ArrayList<>();
					MonEvent=AgendaC.consultationThisEvenement(Integer.toString(idEventModif)).get(0);
					//System.out.println(MonEvent);
					panelPopup.add(modifButton);
					HorlogeSelectDebut.setSelectedItem(MonEvent.get(4));
					HorlogeSelectFin.setSelectedItem(MonEvent.get(5));
					NomEventJTF.setText(MonEvent.get(0));
					String idOfEvent=MonEvent.get(6);
					int indexOfMed = ListIdMed.indexOf(Integer.parseInt(MonEvent.get(7)));
					SelectMedecin.setSelectedIndex(indexOfMed+1);
					modifButton.addActionListener(click ->{
						String DateDebutChoisie;
						String DateFinChoisie;
						String EventName=NomEventJTF.getText();
						String HeurDebut=(String) HorlogeSelectDebut.getSelectedItem();
						String HeurFin=(String) HorlogeSelectFin.getSelectedItem();
						if(EventName.length()<2) {
							EventName=null;
						}
						try {
							Date selectedDateDebut = (Date) datePickerDebut.getModel().getValue();
							DateFormat formatDateDebut = new SimpleDateFormat("yyyy/MM/dd");
							DateDebutChoisie = formatDateDebut.format(selectedDateDebut);

							Date selectedDateFin = (Date) datePickerFin.getModel().getValue();
							DateFormat formatDateFin = new SimpleDateFormat("yyyy/MM/dd");
							DateFinChoisie = formatDateFin.format(selectedDateFin);
							if(selectedDateDebut.compareTo(selectedDateFin)>0) {
								DateDebutChoisie=null;
								DateFinChoisie=null;
								System.out.println("Date de début supérieur à la date de fin de l'évènement");
							}

						} catch (Exception e2) {
							System.out.println(e2);
							System.out.println("une date n'a pas été sélectionnée");
							System.out.println("problème à la ligne : "+ Integer.toString(getLineNumber()));
							DateDebutChoisie = null;
							DateFinChoisie = null;
						}
						if(DateDebutChoisie!=null && DateFinChoisie!=null) {
							if(DateDebutChoisie.equals(DateFinChoisie)) {
								if(!verifHeur(HeurDebut,HeurFin)) {
									HeurDebut=null;
									HeurFin=null;
									System.out.println("problème de logique : heure de début après l'heure de fin pour la meme date.");
								}
							}
							if(EventName!=null && HeurDebut!=null && HeurFin!=null) {
								System.out.println("à prioris tout est bon");
								Integer idMedSelect;
								String nomMedecin;
								if(SelectMedecin.getSelectedIndex()==0) {
									idMedSelect=-1;
									nomMedecin=null;
								}else {
									idMedSelect=ListIdMed.get(SelectMedecin.getSelectedIndex()-1);
									nomMedecin=ListNomMed.get(idMedSelect);
								}
								//AgendaC.ajoutEvenementVisiteur(EventName, DateDebutChoisie, DateFinChoisie, User.id_utilisateur, HeurDebut, HeurFin, idMedSelect);
								if(User.role==1) {
									
								}
								AgendaC.updateEvent(Integer.parseInt(idOfEvent), EventName, DateDebutChoisie, DateFinChoisie, User.id_utilisateur, HeurDebut, HeurFin,idMedSelect);
								ChangeMonth(month,year,User.id_utilisateur);
								
								popup.dispatchEvent(new WindowEvent(popup, WindowEvent.WINDOW_CLOSING));
								
							}else {
								System.out.println("erreur lors du formulaire d'entré d'évènement");
							}
						}
					});
				}else {					
					panelPopup.add(validation);
				}
				
				boutton.addActionListener(eventClick -> {
					String DateDebutChoisie;
					String DateFinChoisie;
					String EventName=NomEventJTF.getText();
					String HeurDebut=(String) HorlogeSelectDebut.getSelectedItem();
					String HeurFin=(String) HorlogeSelectFin.getSelectedItem();
					if(EventName.length()<2) {
						EventName=null;
					}
					try {
						Date selectedDateDebut = (Date) datePickerDebut.getModel().getValue();
						DateFormat formatDateDebut = new SimpleDateFormat("yyyy/MM/dd");
						DateDebutChoisie = formatDateDebut.format(selectedDateDebut);

						Date selectedDateFin = (Date) datePickerFin.getModel().getValue();
						DateFormat formatDateFin = new SimpleDateFormat("yyyy/MM/dd");
						DateFinChoisie = formatDateFin.format(selectedDateFin);
						if(selectedDateDebut.compareTo(selectedDateFin)>0) {
							DateDebutChoisie=null;
							DateFinChoisie=null;
							System.out.println("Date de début supérieur à la date de fin de l'évènement");
						}

					} catch (Exception e2) {
						System.out.println(e2);
						System.out.println("une date n'a pas été sélectionnée");
						System.out.println("problème à la ligne : "+ Integer.toString(getLineNumber()));
						DateDebutChoisie = null;
						DateFinChoisie = null;
					}
					if(DateDebutChoisie!=null && DateFinChoisie!=null) {
						if(DateDebutChoisie.equals(DateFinChoisie)) {
							if(!verifHeur(HeurDebut,HeurFin)) {
								HeurDebut=null;
								HeurFin=null;
								System.out.println("problème de logique : heure de début après l'heure de fin pour la meme date.");
							}
						}
						if(EventName!=null && HeurDebut!=null && HeurFin!=null) {
							//System.out.println("à prioris tout est bon");
							/*System.out.println(HeurDebut);
							System.out.println(HeurFin);
							System.out.println(DateDebutChoisie);
							System.out.println(DateFinChoisie);
							System.out.println(EventName);*/
							Integer idMedSelect;
							String nomMedecin;
							if(SelectMedecin.getSelectedIndex()==0) {
								idMedSelect=-1;
								nomMedecin=null;
							}else {
								idMedSelect=ListIdMed.get(SelectMedecin.getSelectedIndex()-1);
								nomMedecin=ListNomMed.get(idMedSelect);
							}
							//System.out.println("id du medecin : "+idMedSelect);
							//System.out.println(nomMedecin);
							if(User.role==1) {
								AgendaC.ajoutEvenementVisiteur(EventName, DateDebutChoisie, DateFinChoisie, User.id_utilisateur, HeurDebut, HeurFin, idMedSelect,User.id_utilisateur);
					        } else {
					        	int IdUserSelect=Integer.parseInt(idArrayVisiteurPopup[VisiteurSelectPopup.getSelectedIndex()]);
					        	AgendaC.ajoutEvenementVisiteur(EventName, DateDebutChoisie, DateFinChoisie, IdUserSelect, HeurDebut, HeurFin, idMedSelect,User.id_utilisateur);
					        }
							
							ChangeMonth(month,year,User.id_utilisateur);
							
							popup.dispatchEvent(new WindowEvent(popup, WindowEvent.WINDOW_CLOSING));
							
						}else {
							System.out.println("erreur lors du formulaire d'entré d'évènement");
						}
					}
					
		        });
				
				
				
				popup.add(panelPopup);
				

				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		return Form;
	}
	
	
	public MouseListener eventDuJourPopup(final String Jour,final int unMois,final int uneAnnee,final int idUser) {
		
		MouseListener popupOnClick = new MouseAdapter() {
			@SuppressWarnings("rawtypes")
			@Override
	        public void mousePressed(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					int widthPopup=900;
					int HeightPopup=500;
					
					
					
					Popup popup = new Popup("Évènements du "+Jour+" "+months[unMois]+" "+uneAnnee, widthPopup, HeightPopup);
					JPanel panelPopup= new JPanel();
					panelPopup.setPreferredSize(new Dimension(widthPopup, HeightPopup));
					List<List> EventsDuJour=AgendaC.consultationEvenementJour(idUser,Jour,unMois+1,uneAnnee,true);
					for(int i=0;i<EventsDuJour.size();i++) {
						JPanel ligne=new JPanel();
						JButton suppressionButton= new JButton("Supprimer");
						JButton modificationButton= new JButton("Modifier");
						ligne.setPreferredSize(new Dimension(widthPopup, 50));
						ligne.setBackground(Color.white);
						
						String idEvent=(String) EventsDuJour.get(i).get(5);
						suppressionButton.addActionListener(eventClick->{
							AgendaC.suppressionEvent(Integer.parseInt(idEvent));
							ligne.setVisible(false);
							ChangeMonth(unMois, uneAnnee,idUser);
						});
						modificationButton.addMouseListener(FormulairePopup(0,unMois,uneAnnee,Integer.parseInt(idEvent)));
						JLabel nomEvent=new JLabel((String) EventsDuJour.get(i).get(0));
						ligne.add(nomEvent);
						JLabel date;
						String dateDebut=USDateToFrDate((String) EventsDuJour.get(i).get(1));
						String dateFin=USDateToFrDate((String) EventsDuJour.get(i).get(2));
						
						String heureDebut=String.valueOf(((String) EventsDuJour.get(i).get(3)).charAt(0))+String.valueOf(((String) EventsDuJour.get(i).get(3)).charAt(1))+"h";
						String heureFin=String.valueOf(((String) EventsDuJour.get(i).get(4)).charAt(0))+String.valueOf(((String) EventsDuJour.get(i).get(4)).charAt(1))+"h";
						
						int IdMed=Integer.parseInt((String) EventsDuJour.get(i).get(6));
						int idUtilisateurAjouteur=Integer.parseInt((String) EventsDuJour.get(i).get(7));
						int roleUtilisateurAjouteur=AgendaC.roleAjouteur(idUtilisateurAjouteur);
						List<List> infoMed=null;
						String NomMed="";
						if(IdMed!=0) {							
							infoMed=AgendaC.consultationThisMedecin(Integer.toString(IdMed));
							if(infoMed.size()>0) {								
								NomMed="Visite : "+(String) infoMed.get(0).get(0);
							}
						}
						if(dateDebut.equals(dateFin)) {
							date=new JLabel("le "+USDateToFrDate((String) EventsDuJour.get(i).get(1)));
							ligne.add(date);
							JLabel heure;
							if(heureDebut.equals(heureFin)) {
								heure=new JLabel("à "+heureDebut);
							}else {
								heure=new JLabel("de "+heureDebut+" à "+heureFin);
							}
							ligne.add(heure);
							
							
						}else {		    			
							date=new JLabel("du "+USDateToFrDate((String) EventsDuJour.get(i).get(1))+" à "+heureDebut+" jusqu'au "+USDateToFrDate((String) EventsDuJour.get(i).get(2))+" à "+heureFin +" "+NomMed);
							ligne.add(date);
						}
						/*System.out.println(idUtilisateurAjouteur);
						System.out.println(User.id_utilisateur);*/
						if(roleUtilisateurAjouteur==1 || idUtilisateurAjouteur==User.id_utilisateur || idUtilisateurAjouteur==0) {							
							ligne.add(modificationButton);
							ligne.add(suppressionButton);
						}
						panelPopup.add(ligne);
					}
					popup.add(panelPopup);
					Agenda.Rightclick=false;
				} else {
					Agenda.Rightclick=true;
				}
	        }
		};
		if(!Agenda.Rightclick) {			
			return popupOnClick;
		}else {
			return FormulairePopup(0,unMois, uneAnnee,0);
		}
	}
		  

	public Date getFirstDateOfCurrentMonth(int Year,int mois) {
		//Calendar cal = Calendar.getInstance();
		Calendar cal = new GregorianCalendar(Year,mois,1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	} 

	public String USDateToFrDate(String USDate) {
		SimpleDateFormat FrFormat=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat USFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date TmpUsDate=null;
		try {
			TmpUsDate = USFormat.parse(USDate);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("erreur à la méthode USDateToFrDate -> Agenda.java");
			System.out.println("problème à la ligne : "+ Integer.toString(getLineNumber()));
		}
		
		return FrFormat.format(TmpUsDate);
	}
	
	public boolean verifHeur(String HeurDebut,String HeurFin) {
		String heureDebutWithoutPoint = Character.toString(HeurDebut.charAt(0))+Character.toString(HeurDebut.charAt(1))+Character.toString(HeurDebut.charAt(3))+Character.toString(HeurDebut.charAt(4));
		//System.out.println(heureDebutWithoutPoint);
		String heureFinWithoutPoint = Character.toString(HeurFin.charAt(0))+Character.toString(HeurFin.charAt(1))+Character.toString(HeurFin.charAt(3))+Character.toString(HeurFin.charAt(4));
		//System.out.println(heureFinWithoutPoint);
		int heurDebutInt=Integer.parseInt(heureDebutWithoutPoint);
		int heurFinInt=Integer.parseInt(heureFinWithoutPoint);
		if(heurDebutInt<heurFinInt || heurDebutInt==heurFinInt) {			
			return true;
		}else {
			return false;
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
	
	public static int getLineNumber() {
		/** Get the current line number.
		 * @return int - Current line number.
		 * Aide pour le debug
		 */
	    return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}
	
	private JLabel[] ajoutemoiLabel(JLabel[] MonArray, JLabel NouveauPanel) {
		/*
		 * 
		 * Fonction qui sert � rajouter des �l�ments dans un tableau. (ici nous ajoutons des JPanels) 
		 * 
		 */
		int newSize = MonArray.length + 1;
		JLabel[] tempArray = new JLabel[ newSize ];
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