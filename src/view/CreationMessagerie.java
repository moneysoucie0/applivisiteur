package view;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;


public class CreationMessagerie extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public CreationMessagerie() {
		List<String> conversations_list = new ArrayList<String>();
		conversations_list.add("Jean Reno");
		conversations_list.add("Michel Fugain");
		conversations_list.add("René La Taupe");
		conversations_list.add("René La Taupe");
		conversations_list.add("René La Taupe");
		conversations_list.add("René La Taupe");
		conversations_list.add("René La Taupe");

		JPanel conversations = new JPanel();
		conversations.setPreferredSize(new Dimension(300, 1000));
		conversations.setBorder(BorderFactory.createLineBorder(Color.white));
		conversations.setBackground(Color.white);

		for (int i = 0; i < conversations_list.size(); i++) {
			JPanel conversation_card = new JPanel();
			conversation_card.setPreferredSize(new Dimension(300, 150));
			conversation_card.setBackground(new Color(0, 63, 128));

			JLabel nom_card = new JLabel(conversations_list.get(i).toString());
			nom_card.setPreferredSize(new Dimension(250, 35));
			nom_card.setForeground(Color.white);

			JLabel dernier_message = new JLabel("Dernier message de la part de NOM");
			dernier_message.setPreferredSize(new Dimension(220, 75));
			dernier_message.setForeground(Color.white);

			conversation_card.add(nom_card, BorderLayout.EAST);
			conversation_card.add(dernier_message, BorderLayout.CENTER);
			conversations.add(conversation_card);
		}
		
		
/*		if (conversations_list.size() < 5) {
			int nb_placeholder = 5 - conversations_list.size();
			for (int i = 0; i < nb_placeholder; i++) {
				JPanel conversation_card = new JPanel();
				conversation_card.setPreferredSize(new Dimension(300, 150));
				conversation_card.setBackground(Color.GRAY);
				conversations.add(conversation_card);
			}
		}
		else {
			JScrollPane scroll = new JScrollPane();
			scroll.setPreferredSize(new Dimension(300,1000));
			scroll.setVisible(true);
		}*/

		JPanel conversation = new JPanel();
		conversation.setPreferredSize(new Dimension(1000, 1000));
		conversation.setBorder(BorderFactory.createLineBorder(Color.white));
		conversation.setBackground(Color.white);



		JPanel panel_input_button = new JPanel();
		
		
		JTextField input_message = new JTextField();
		input_message.setPreferredSize(new Dimension(600, 80));

		JButton send_button = new JButton("Envoyer");
		
		//Retourne une erreur 

		/*send_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});*/

		JLabel message_example = new JLabel("Premier message test", JLabel.CENTER);
		

		panel_input_button.add(input_message);
		panel_input_button.add(send_button);

		//conversations.add(conversations_list);
		conversation.add(message_example);
		conversation.add(panel_input_button, BorderLayout.PAGE_END);
		this.add(conversations, BorderLayout.WEST);	
		this.add(conversation);
	}
}
