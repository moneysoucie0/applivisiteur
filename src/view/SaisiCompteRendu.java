package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import controller.compteRenduControleur;
import model.User;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SaisiCompteRendu extends JPanel {
	private static final long serialVersionUID = 1L;

	public SaisiCompteRendu() {
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

		TitrePrincipale titre_page = new TitrePrincipale("Saisie Compte Rendu");

		JLabel Titre = new JLabel("Compte Rendu");
		JLabel Date = new JLabel("Date de la visite");
		JLabel Motif = new JLabel("Motif de la visite");
		JLabel Comment = new JLabel("Rapport :");
		JLabel Medecin = new JLabel("Choix du Médecin");


		Jpanel myPanel = new JPanel()
		JLabel Medicament = new JLabel("Insérez le nom du médicament");
		JLabel Echantillons = new JLabel("Nombre d'échantillons laissés au pratitien");



		
		

		AlertSuccess compteRenduBon = new AlertSuccess("le compte rendu est bien enregistré");
		AlertError compteRenduFalse = new AlertError("le compte rendu a eux une erreur d'enregistrement");

		JFormattedTextField nbrEchantillonsField = new JFormattedTextField();
		JFormattedTextField MotifPersonnalise = new JFormattedTextField();
		MotifPersonnalise.setColumns(15);

		// selectMedicament
		List<List> ListMed = compteRenduControleur.selectMedecin();
		List<List> ListMedoc = compteRenduControleur.selectMedicament();
		List<String> ListNomMed = new ArrayList<String>();
		List<Integer> ListIdMed = new ArrayList<Integer>();
		List<String> ListNomMedoc = new ArrayList<String>();
		List<Integer> ListIdMedoc = new ArrayList<Integer>();

		for (int i = 0; i < ListMed.size(); i++) {

			int idTemp = Integer.valueOf((String) ListMed.get(i).get(0));
			ListIdMed.add(idTemp);

			ListNomMed.add((String) ListMed.get(i).get(1));

		}


        
      for (int i = 0; i < ListMedoc.size(); i++) {
        	
        	int idTemp=Integer.valueOf((String) ListMedoc.get(i).get(0));
        	ListIdMedoc.add(idTemp);
			ListNomMedoc.add((String) ListMedoc.get(i).get(1));

		}
		String nomMed[] = ListNomMed.toArray(new String[0]);
		String[] nomMedoc = ListNomMedoc.toArray(new String[0]);

		Integer[] idMed = ListIdMed.toArray(new Integer[0]);
		Integer[] idMedoc = ListIdMedoc.toArray(new Integer[0]);
		JComboBox<?> BoxMedocChoice = new JComboBox<Object>(nomMedoc);
		String[] MotifItems = { "Visite annuelle", "Visite pour nouveau produit", "Motif personnalisé" };
		JComboBox<?> BoxMedChoice = new JComboBox<Object>(nomMed);
		JComboBox<?> BoxMotifChoice = new JComboBox<Object>(MotifItems);

		JButton Valider = new JButton("Valider");

		Font font = new Font("Arial", Font.BOLD, 20);
		Titre.setFont(font);

		JPanel espacement[] = { new JPanel(new FlowLayout(FlowLayout.LEFT)) };

		JPanel[] panelTemp = { new JPanel(new FlowLayout(FlowLayout.LEFT)) };

		for (int i = 1; i < 10; i++) {
			panelTemp = ajoutemoi(panelTemp, new JPanel(new FlowLayout(FlowLayout.LEFT)));
			espacement = ajoutemoi(espacement, new JPanel(new FlowLayout(FlowLayout.LEFT)));
		}

        final JPanel[] panel=panelTemp;
        
       	String PlaceHolder =" Insérez vos commentaires ici... \n\n (10 caractères minimum)";
       	JTextArea inputArea = new JTextArea(PlaceHolder,5,25);
       	inputArea.setText(PlaceHolder);
        inputArea.setBorder(BorderFactory.createLineBorder(Color.black));
        inputArea.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
          
            	
				if (inputArea.getText().equals(PlaceHolder)){
					inputArea.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (inputArea.getText() != PlaceHolder && inputArea.getText().length() >= 10) {
					inputArea.setText(inputArea.getText());
				} else {
					inputArea.setText(PlaceHolder);
				}
			}
		});
   
        this.setVisible(true); //Ceci apr�s l'initialisation des input pour �viter des bugs d'affichage � cause de setColumns
        						
        for (int i =1; i<9; i++) {
        	espacement[i-1].setPreferredSize(new Dimension(50,40));
            espacement[i-1].setOpaque(false);
            panel[i].add(espacement[i-1]);
        }
        
        int widhtPanel= 1000;
        int heightPanel= 500;
        panel[9].setPreferredSize(new Dimension(widhtPanel, heightPanel));
        
        
        espacement[9].setPreferredSize(new Dimension(50,40));
    	espacement[9].setOpaque(false);
    	
    	panel[0].add(espacement[9]);
    	panel[0].add(Medicament);
    	panel[0].add(BoxMedocChoice);
       	//nomMedoc[0].setColumns(15);
        panel[1].add(Titre);
        
 
        panel[2].add(Medecin);
        panel[3].add(Date);
        panel[3].add(datePicker);
        panel[4].add(Motif);
        panel[6].add(Comment);
        // Le nombre d'�chantillons est paticulier car on ne doit qu'accepter que les int
        panel[5].add(Echantillons);
        panel[5].add(nbrEchantillonsField);
        nbrEchantillonsField.setColumns(3);
        
       panel[2].add(BoxMedChoice);
        
        panel[4].add(BoxMotifChoice);
        panel[4].add(MotifPersonnalise);
        MotifPersonnalise.setEditable(false);
        panel[4].add(MotifPersonnalise);
        BoxMotifChoice.addActionListener(e -> {	
			if(BoxMotifChoice.getSelectedIndex()==2) {

				MotifPersonnalise.setEditable(true);
			} else {
				MotifPersonnalise.setEditable(false);
			}

		});

		for (int i = 0; i < 9; i++) {
			panel[i].setPreferredSize(new Dimension(widhtPanel, heightPanel / 11));
			if (i == 5) {
				panel[i].setPreferredSize(new Dimension(widhtPanel, heightPanel / 100 * 8));
				panel[9].add(panel[0]);
			}
			panel[i].setOpaque(false);
			if (i != 0) {
				panel[9].add(panel[i]);
			}
		}

		panel[7].setPreferredSize(new Dimension(widhtPanel, heightPanel / 5));
		panel[7].add(espacement[6]);
		panel[7].add(inputArea);

		espacement[7].setPreferredSize(new Dimension(200, 0));
		panel[8].add(espacement[7]);
		panel[8].add(Valider);
		compteRenduBon.setVisible(false);
		compteRenduFalse.setVisible(false);

		Valider.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		    });
        
        for (int i = 0; i < 9; i++) {
        	panel[i].setPreferredSize(new Dimension(widhtPanel,heightPanel/11));
        	if(i == 5) {
        		panel[i].setPreferredSize(new Dimension(widhtPanel,heightPanel/100*8));
        		panel[9].add(panel[0]);
        	}
        	panel[i].setOpaque(false);
        	if(i!=0) {
            panel[9].add(panel[i]);
        	}
        }
        
       panel[7].setPreferredSize(new Dimension(widhtPanel, heightPanel/5));
        panel[7].add(espacement[6]);
        panel[7].add(inputArea);
        
        espacement[7].setPreferredSize(new Dimension(200, 0));
        panel[8].add(espacement[7]);
        panel[8].add(Valider);
        compteRenduBon.setVisible(false);
        compteRenduFalse.setVisible(false);
        
        Valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int nbrEchantillons;
				String AreaText;
				String DateChoisie;
				
				int Medoc = idMedoc[(int) BoxMedocChoice.getSelectedIndex()];
				int Medecin = idMed[(int) BoxMedChoice.getSelectedIndex()];
				int MotifIndex = (int) BoxMotifChoice.getSelectedIndex();
				String Motif;
				if (MotifIndex == 2) {
					Motif = MotifPersonnalise.getText();
				} else {
					Motif = (String) BoxMotifChoice.getSelectedItem();
				}


				try {
					Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
					DateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
					DateChoisie = formatDate.format(selectedDate);

				} catch (Exception e2) {
					System.out.println("La date n'a pas été sélectionnée");
					compteRenduFalse.setVisible(true);
					DateChoisie = null;
				}
				
				try {
					nbrEchantillons = Integer.parseInt(nbrEchantillonsField.getText());

				} catch (Exception erreurNombre) {
					System.out.println("Nombre echantillons manquant");
					compteRenduFalse.setVisible(true);
					nbrEchantillons = 0;
				}
				try {
					AreaText = inputArea.getText();
					if (inputArea.getText().equals(PlaceHolder) || inputArea.getText().length() < 10) {
						AreaText = null;
						System.out
								.println("Soit le text est égal au placeholder, soit les commentaires font moins de 10"
										+ "caractères, entrez de vrais commentaires");
						compteRenduFalse.setVisible(true);
					} else {
						AreaText = inputArea.getText();

					}

				} catch (Exception Area) {
					System.out.println("La zone de texte est vide");
					compteRenduFalse.setVisible(true);
					AreaText = null;
				}
				

				if (DateChoisie != null && nbrEchantillons != 0 && AreaText != null) {
					System.out.println("C'est tout bon chef !");
					System.out.println("Vous choisie le médecin: " + Medecin + ", le motif: " + Motif + "\n"
							+ "Le Médicament inscrit est: " + Medoc + " la date de la visite été le: "
							+ DateChoisie + ", vous avez laissé au pratitien: " + nbrEchantillons
							+ " échantillon(s) et votre commentaire sur la visite est:\n'" + AreaText + "'.");
					if (controller.compteRenduControleur.ajoutCompteRendu(Medecin, Motif, AreaText, DateChoisie,
							nbrEchantillons, Medoc)) {

						compteRenduBon.setVisible(true);
						compteRenduFalse.setVisible(false);
						inputArea.setText("");
						;
						//nomMedoc.setValue("");
						nbrEchantillonsField.setValue("");

					} else {

						compteRenduFalse.setVisible(true);
						compteRenduBon.setVisible(false);
					}
				} else {
					System.out.println("C'est la merde ! Courrez !");
				}

			}
		});

		panel[9].setOpaque(true);
		panel[9].setBackground(Color.white);

		this.add(titre_page);
		this.add(panel[9]);

		this.add(compteRenduBon);
		this.add(compteRenduFalse);
		BoxLayout b = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(b);

		nbrEchantillonsField.addKeyListener(new KeyAdapter() { // quand la personne �crit
			public void keyTyped(KeyEvent e) {

				char lettre = e.getKeyChar(); // r�cup�re le caract�re
				if ((lettre < '0' || lettre > '9') && lettre != '\b') { // Si le caract�re est autre chose qu'un nombre
					e.consume(); // le supprime
					System.out.println("lettre invalide");

				}
      		}
      	});
      	nbrEchantillonsField.setDocument(new JTextFieldLimit(2));

	}

	private JPanel[] ajoutemoi(JPanel[] MonArray, JPanel NouveauPanel) {
		/*
		 * 
		 * Fonction qui sert � rajouter des �l�ments dans un tableau. (ici nous ajoutons
		 * des JPanels)
		 * 
		 */
		int newSize = MonArray.length + 1;
		JPanel[] tempArray = new JPanel[newSize];
		// Nous cr��ons un array temporaire avec la taille de l'array actuelle

		for (int i = 0; i < MonArray.length; i++) {
			tempArray[i] = MonArray[i];
			// Pour chaque �l�ment dans mon array, on ajoute l'�l�ment dans la nouvelle
			// array temporaire
		}
		tempArray[newSize - 1] = NouveauPanel; // Ici on ajoute le nouveau Jpanel dans l'array
		return tempArray; // on retourne notre nouvelle array
	}

	class JTextFieldLimit extends PlainDocument {
		/*
		 * Classe permettant de limiter le nombre de caract�re dans un JtextField
		 *
		 */
		private static final long serialVersionUID = 1L;
		private int limit;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		JTextFieldLimit(int limit, boolean upper) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null) {
				return;
			}
			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}
}