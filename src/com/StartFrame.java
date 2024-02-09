package com;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.factory.CreateQuestions;

public class StartFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	
	// AC = Action Command
	public final static String AC_ALL = "ALL";
	public final static String AC_START = "START";
	//
	private int numOfQuestions;
	private int startIndex;
	
	
	private JLabel labelNumOfQuestions; 
	private JLabel labelStartIndex;
	private JTextField tfNumOfQuestions;
	private JTextField tfStartIndex;
	private JButton btnStart;
	
	private JPanel panelMainMenu;
	private JPanel panelNumOfQuestions;
	
	private JPanel panelBottomMenu;
	private JPanel panelSortedOrRandomQuestions;
	
	private ButtonGroup btnGroupSortedOrRandom;
	private JRadioButton radioSortedQuestions;
	private JRadioButton radioRandomQuestions;
	
	private JCheckBox cbAll;
	
	private Container c;
	
	public StartFrame() {
		
		this.setTitle("");
		this.setSize(700, 400);
		this.setLocation(700, 300);
		
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		
		// PANEL MAIN MENU ######################################
		panelMainMenu = new JPanel();
		
		panelMainMenu.setLayout( new BoxLayout(panelMainMenu, BoxLayout.Y_AXIS ) );
		
		// PANEL NUM OF QUESTIONS -------------------------------
		
		panelNumOfQuestions = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		
		panelNumOfQuestions.setMaximumSize( new Dimension( (int)panelNumOfQuestions.getMaximumSize().getWidth(), 40));
		
		labelStartIndex = new JLabel("Start from : ");
		tfStartIndex = new JTextField(4);
		tfStartIndex.setText("1");
		
		labelNumOfQuestions = new JLabel("Num. of questions : ");
		tfNumOfQuestions = new JTextField(4);
		tfNumOfQuestions.setText("20");
		
		cbAll = new JCheckBox("All");
		cbAll.addActionListener(this);
		cbAll.setActionCommand(AC_ALL);
		
		panelNumOfQuestions.add( labelStartIndex );
		panelNumOfQuestions.add( tfStartIndex );
		panelNumOfQuestions.add( labelNumOfQuestions );
		panelNumOfQuestions.add( tfNumOfQuestions );
		panelNumOfQuestions.add( cbAll );
		
		// -------------------------------------------------------

		// -------------------------------------------------------
		
		panelSortedOrRandomQuestions = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		
		panelSortedOrRandomQuestions.setMaximumSize( new Dimension( (int)panelSortedOrRandomQuestions.getMaximumSize().getWidth(), 40));

		radioRandomQuestions = new JRadioButton("Random sequenze of questions");
		radioRandomQuestions.setSelected(true);
		radioSortedQuestions = new JRadioButton("Sorted sequenze of questions");
		
		btnGroupSortedOrRandom = new ButtonGroup();
		
		btnGroupSortedOrRandom.add(radioRandomQuestions);
		btnGroupSortedOrRandom.add(radioSortedQuestions);
		
		panelSortedOrRandomQuestions.add( radioRandomQuestions );
		panelSortedOrRandomQuestions.add( radioSortedQuestions );
		
		
		// -------------------------------------------------------
		
		
		// -------------------------------------------------------
		// è possibile aggiungere altri panels al menu principale del frame in futuro
		// per esempio per dare un tempo massimo
		// ALTRO_PANEL = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		
		// ALTRO_PANEL.setMaximumSize( new Dimension( (int)ALTRO_PANEL.getMaximumSize().getWidth(), 40));

		// -------------------------------------------------------
		
		panelMainMenu.add( panelNumOfQuestions );	
		panelMainMenu.add( panelSortedOrRandomQuestions );
		
		// #######################################################
		
		// PANEL BOTTOM MENU -------------------------------------
		
		panelBottomMenu = new JPanel( new FlowLayout() );
		
		btnStart = new JButton("START");
		
		btnStart.addActionListener(this);
		
		
		panelBottomMenu.add( btnStart );
		
		// -------------------------------------------------------
		
		
		c.add( panelMainMenu, BorderLayout.CENTER );
		c.add( panelBottomMenu, BorderLayout.SOUTH );
		
		
		
		this.setVisible(true);
		
	}
	
	// GETTERS and SETTERS
	public JCheckBox getCbAll() {
		return this.cbAll;
	}
	
	public JTextField getTfNumOfQuestions() {
		return this.tfNumOfQuestions;
	}

	public JTextField getTfStartIndex() {
		return this.tfStartIndex;
	}

	
	public JRadioButton getRadioRandomQuestions() {
		return this.radioRandomQuestions;
	}


	
	public int getNumOfQuestions() {
		return numOfQuestions;
	}

	public void setNumOfQuestions(int numOfQuestions) {
		this.numOfQuestions = numOfQuestions;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		
		boolean randomSeqOfQuestions = false;
		
		switch (evt.getActionCommand()) {

		case AC_ALL:
			
			if( getCbAll().isSelected() ) {
				
				setNumOfQuestions( CreateQuestions.USE_ALL_QUESTIONS );
				this.getTfNumOfQuestions().setEditable(false);
				this.getTfStartIndex().setEditable(false);
			
			} else {
			
				this.getTfNumOfQuestions().setEditable(true);
				this.getTfStartIndex().setEditable(true);
			
			}
			
			
			break;
			
		case AC_START:
			
			if( getRadioRandomQuestions().isSelected() ) {
				randomSeqOfQuestions = true;
			}
			
			try {
				
				setNumOfQuestions( Integer.parseInt( tfNumOfQuestions.getText() ) );
				setStartIndex( Integer.parseInt( tfStartIndex.getText() ) );
				
			} catch (NumberFormatException e) {
								
				setNumOfQuestions( -10 /*ERRORE*/ );
				e.printStackTrace();
			}
			
			// Se il textFiels NumOfQuestions  non è editabile, vuol dire che è stato selezionato di usare tutti le domande
			if ( ! getTfNumOfQuestions().isEditable() ) {
				new MainFrame(CreateQuestions.USE_ALL_QUESTIONS, randomSeqOfQuestions);
				dispose();
			} else if ( getNumOfQuestions() > 0 && getStartIndex() > 0 ) { 
				// NumOfQuestions - 1 perchè inizia a contare da 0, e ci si ritrova con una domanda in più altrimenti
				new MainFrame( getStartIndex() - 1,  getNumOfQuestions() - 1, randomSeqOfQuestions );
				dispose();

			} else {
				// Messaggio di Errore
				JOptionPane.showConfirmDialog( c , "Insert a number greater than 0" ,
						"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE );
			
			}

			
			
			break;
		}

		
		
	}
		
	
}
