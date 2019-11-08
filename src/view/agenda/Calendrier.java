package view.agenda;
import java.awt.BorderLayout;

import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Vector;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalComboBoxButton;
import java.util.*;

 
public class Calendrier extends JPanel {
	
	/**
         * 
         */
	private static final long serialVersionUID = 6411499808530678723L;
 
	  /** The buttons to be displayed */
	  protected JButton labs[][];

 
	  private Date date=new Date();

	  String[] months = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
		      "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre" };
	  
	  public int dom[] = { 31, 28, 31, 30, /* jan feb mar apr */
			  31, 30, 31, 31, /* may jun jul aug */
			  30, 31, 30, 31 /* sep oct nov dec */
			  };
	  /** The month choice */
	  private JComboBox<String> monthChoice;
	 
	 
 
	  /** The year choice */
	  private JComboBox<String> yearChoice;
	  
 
	  public Calendrier(){
		  super();
		 
		    buildGUI();
		    recompute();
		  }
 
	protected void recompute() {
		// TODO Auto-generated method stub
		// Blank out all
		for (int i = 0; i < 6; i++)
		      for (int j = 0; j < 7; j++) 
		         labs[i][j].setText("");
 
		 int daysInMonth = dom[date.getMonth()];
		 if (isLeap(date.getYear()) && date.getMonth() == 1)
		      daysInMonth++;
		//case vide avant le premier jour
		    for (int i = 0; i < date.firstDayOfTheMonth(); i++) {
		    	labs[0][i].setText("");
				labs[0][i].setSize(getMaximumSize());
				this.setVisible(true);
				labs[0][i].setOpaque(true);
				Font p = new Font("open-sans", Font.PLAIN, 18);
				labs[0][i].setFont(p);
				labs[0][i].setBackground(new Color(229,236,246));
			  
		    }
 
		 // Case des dates jour du mois.
		    for (int i = 1; i <= daysInMonth; i++) {
		    	
				
		      JButton b = labs[(date.firstDayOfTheMonth() + i - 2) / 7][(date.firstDayOfTheMonth() + i - 2) % 7];
		      b.setText(Integer.toString(i));
			  Font p = new Font("open-sans", Font.PLAIN, 18);
			  b.setFont(p);
			  b.setBackground(Color.white);
			  b.setForeground(new Color (0,63,128));

			  b.setSize(getMaximumSize());



		    }
 
 
		    repaint();
	}
 
 
	private boolean isLeap(int year) {
		// TODO Auto-generated method stub
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
		      return true;
		return false;
	}
 
	private void buildGUI() {
		
		getAccessibleContext().setAccessibleDescription(
		        "Calendar not accessible yet. Sorry!");
		
		    setBorder(BorderFactory.createEtchedBorder());
 
		    setLayout(new BorderLayout());
 
		    JPanel tp = new JPanel();

		  //barre ou il ya les deux listes déroulantes
			BoxLayout b= new BoxLayout(tp, BoxLayout.Y_AXIS);
			tp.setBounds(500,50,500,500/16*9);
			
			tp.setBackground(new Color (229,236,246));
			Font p = new Font("open-sans", Font.PLAIN, 24);
			tp.setFont(p);
			tp.setSize(getMaximumSize());
			this.setVisible(true);
			tp.setOpaque(true);
		    tp.add(monthChoice = new JComboBox<String>());
		      monthChoice.setBackground(Color.white);
		      monthChoice.setForeground(new Color (0,63,128));
		      Font ptx = new Font("open-sans", Font.PLAIN, 18);
		      monthChoice.setFont(ptx);
		    for (int i = 0; i < months.length; i++)

		      monthChoice.addItem(months[i]);
		    monthChoice.setSelectedItem(months[date.getMonth()]);
		    monthChoice.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
			      

		        int i = monthChoice.getSelectedIndex();

		        if (i >= 0) {
		          date.setMonth(i);
		          recompute();
		        }
		      }
		    });
		    monthChoice.getAccessibleContext().setAccessibleName("Months");
		    monthChoice.getAccessibleContext().setAccessibleDescription(
		        "Choose a month of the year");
		    tp.add(yearChoice = new JComboBox<String>());
		    yearChoice.setBackground(Color.white);
		      yearChoice.setForeground(new Color (0,63,128));
		      Font font = new Font("open-sans", Font.PLAIN, 18);
		      yearChoice.setFont(font);
		    yearChoice.setEditable(true);
		    for (int i = date.getYear() - 5; i < date.getYear() + 5; i++)
		      yearChoice.addItem(Integer.toString(i));
		    yearChoice.setSelectedItem(Integer.toString(date.getYear()));
		    yearChoice.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        int i = yearChoice.getSelectedIndex();
		        if (i >= 0) {
		          date.setYear(Integer.parseInt(yearChoice.getSelectedItem().toString()));
		          recompute();
		        }
		      }
		    });
		    add(BorderLayout.CENTER, tp);

 
		    JPanel bp = new JPanel();
			bp.setBackground(new Color (0,63,128));
		
		  
			Font pt = new Font("open-sans", Font.PLAIN, 24);
			bp.setFont(pt);
			bp.setSize(getMaximumSize());
			bp.setVisible(true);
		    bp.setLayout(new GridLayout(7, 7));
			bp.setSize(1000,1000);

		    labs = new JButton[6][7]; // first row is days
		    JButton lundi = new JButton("Lundi");
		    JButton mardi = new JButton("Mardi"); 
		    JButton mercredi = new JButton("Mercredi");
		    JButton jeudi = new JButton("Jeudi");
		    JButton vendredi = new JButton("Vendredi");
		    JButton samedi = new JButton("Samedi");
		    JButton dimanche = new JButton("Dimanche");
		    bp.add(lundi);
		    bp.add(mardi);
		    bp.add(mercredi);
		    bp.add(jeudi);
		    bp.add(vendredi);
		    bp.add(samedi);
		    bp.add(dimanche);
		    
		    lundi.setBackground(new Color (0,63,128));
		    mardi.setBackground(new Color (0,63,128));
		    mercredi.setBackground(new Color (0,63,128));
		    jeudi.setBackground(new Color (0,63,128));
		    vendredi.setBackground(new Color (0,63,128));
		    samedi.setBackground(new Color (0,63,128));
		    dimanche.setBackground(new Color (0,63,128));
		    
		    lundi.setForeground(Color.white);
		    mardi.setForeground(Color.white);
		    mercredi.setForeground(Color.white);
		    jeudi.setForeground(Color.white);
		    vendredi.setForeground(Color.white);
		    samedi.setForeground(Color.white);
		    dimanche.setForeground(Color.white);
		    
		    Font fontM = new Font("open-sans", Font.BOLD, 18);
			lundi.setFont(fontM);
			mardi.setFont(fontM);
			mercredi.setFont(fontM);
			jeudi.setFont(fontM);
			vendredi.setFont(fontM);
			samedi.setFont(fontM);
			dimanche.setFont(fontM);

		    
		    
		    //taille du calendrier
		    bp.setPreferredSize(new Dimension(1700, 900));


		    ActionListener dateSetter = new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        String num = e.getActionCommand();
		        if (!num.equals("")) {
		          // set the current day highlighted
		          setDayActive(Integer.parseInt(num));
 
		          // When this becomes a Bean, you can
		          // fire some kind of DateChanged event here.
		          // Also, build a similar daySetter for day-of-week btns.
		        }
		      }
 
		    };
 
 
 
		    // Construct all the buttons, and add them.
		    //Case vide après le mois
		    for (int i = 0; i < 6; i++)
		      for (int j = 0; j < 7; j++) {

		        bp.add(labs[i][j] = new JButton(""));
		        labs[i][j].addActionListener(dateSetter);
		        labs[i][j].setBackground(new Color(229,236,246));
		      }
 
		    add(BorderLayout.SOUTH, bp);
 
	}
 
	protected void setDayActive(int parseInt) {
		// TODO Auto-generated method stub
 
	}
 
	public static void main(String[] argv)
	  {
	    JFrame f = new JFrame("");
	    Font pt = new Font("open-sans", Font.PLAIN, 24);
		f.setFont(pt);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		

	    Container c = f.getContentPane();
		c.setBounds(500,50,500,500/16*9);
		c.setBackground(new Color (102,163,211));
		BoxLayout b= new BoxLayout(c, BoxLayout.Y_AXIS);

	    c.setLayout(new FlowLayout());
	    c.add(new Calendrier());
 
	    f.pack();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setVisible(true);
	  }
 
}