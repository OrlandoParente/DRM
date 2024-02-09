package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.model.Question;

public class EndFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	public final static String RESTART = "RESTART";
	

	//
	private ArrayList<Question> questions;
	private int score;
	
	private Container c;
	
	private JPanel panelUp;
	private JPanel panelShowQuestions;
	
	private JLabel labelScore;
	private JButton btnRestart;
	
	
	public EndFrame( ArrayList<Question> questions, int score ) {
		
		//
		this.questions = questions;
		this.score = score;
		
		setSize(800, 500);
		setLocation(500, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c = getContentPane();
		c.setLayout( new BorderLayout() );
		
		// PANEL UP --------------------------------
		
		panelUp = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		
		labelScore = new JLabel( "<html><h1>SCORE: " + this.score + "/ " + this.questions.size() + "</h1></html>" );
		
		btnRestart = new JButton("RESTART");
		btnRestart.addActionListener(this);
		btnRestart.setActionCommand(RESTART);

		panelUp.add( labelScore );
		panelUp.add( btnRestart );
		// -----------------------------------------
		
		// PANEL SHOW QUESTIONS ---------------------
		panelShowQuestions = new JPanel( new BorderLayout() );
		 addQuestionsToPanelShowQuestions( panelShowQuestions , 0 );
		// -----------------------------------------
		
		// Inserimento componenti nel main conteiner c
		c.add( panelUp , BorderLayout.NORTH );
		c.add( new JScrollPane(panelShowQuestions), BorderLayout.CENTER );
		//
		setVisible(true);
		
	}

	private void addQuestionsToPanelShowQuestions( JPanel panelPrevQuestion, int indexQuestion ) {
		
		JPanel panelNewQuestion = new JPanel( new BorderLayout() );
		
		// popolo panelNewQuestion ###################################################
		
		// PRINT QUESTION ######
		JLabel labelQuestion = new JLabel( getQuestions().get(indexQuestion).getQuestionText() );
		// panelNewQuestion.add(labelQuestion, BorderLayout.NORTH); // messo alla fine

		// PRINT ANSWERS ######
				
		String [] answers = getQuestions().get(indexQuestion).getAnswers();
		int [] selectedAns = getQuestions().get(indexQuestion).getSelectedAnswers();
		int [] rightAns = getQuestions().get(indexQuestion).getRightAnswers();
				
				
		JPanel panelAns = new JPanel( );
		panelAns.setLayout( new BoxLayout(panelAns, BoxLayout.Y_AXIS) );
		// panelAns.setMaximumSize( new Dimension( (int) panelAns.getMaximumSize().width, 40 ));
		
		//
		for( int i = 0; i < answers.length; i ++ ) {
			
			// ---- checkbox --
			JCheckBox cb = new JCheckBox( answers[i], false );
			
			if( selectedAns[i] == Question.SELECTED_ANSWER ) {
				cb.setSelected(true);
				cb.setForeground(Color.RED);
			}
			
			for( int j=0; j<rightAns.length; j++) {
				
				// Coloro di verde la risposta correntta
				if( i == rightAns[j] ) 					
					cb.setForeground(Color.decode("#228b22"));	
				
			}
			
		
			// ----------------
			// cb.setEnabled(false);
			// Non permette di spuntare o togliere la spunta al check box
			cb.addActionListener( e -> {
				if( ((JCheckBox) e.getSource()).isSelected() ) 
					((JCheckBox) e.getSource()).setSelected(false);
				else
					((JCheckBox) e.getSource()).setSelected(true);
			});
			
			panelAns.add(cb);
			
			// panelNewQuestion.add(panelAns, BorderLayout.CENTER); // messo alla fine
		}
		
		panelAns.add( new JLabel("<html><font color=\"blue\">#################################################################################################</font></html>") );
		
		panelNewQuestion.add(labelQuestion, BorderLayout.NORTH);
		panelNewQuestion.add(panelAns, BorderLayout.CENTER);
		// ###########################################################################
		
		panelPrevQuestion.add(panelNewQuestion, BorderLayout.SOUTH);
		
		indexQuestion ++;
		
		// System.out.println("==========================================>" + indexQuestion);
		
		// Richiamo ricorsivo 
		if( indexQuestion < getQuestions().size() )	addQuestionsToPanelShowQuestions( panelNewQuestion, indexQuestion );
		
		// +++++++++++++++++++++++++++++++++++++++++++++
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
			
			case RESTART:
				
				new StartFrame();
				dispose();
				
				break;
			
			}
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	
	
	
	
	
}
