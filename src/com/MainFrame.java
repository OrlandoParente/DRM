package com;

import java.awt.BorderLayout;
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

import com.factory.CreateQuestions;
import com.model.Question;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private int numOfQuestions;
	private int startIndex;
	private ArrayList<Question> questions;
	
	private int currentQuestionIndex;
	
	private MainFrameActionList listener;
	
	//
	private Container c;
	
	//
	private JPanel panelAnswers;
	private JPanel panelBottomMenu;
	
	//
	private JButton btnNext;
	private JButton btnBack;
	private JButton btnEnd;
	private JButton btnRestart;
	
	private JLabel labelPosition;
	
	//
	private JLabel labelQuestion;
	
	public MainFrame(  int numOfQuestions , boolean randomSeqOfQuestions ) {
		this( 0, numOfQuestions , randomSeqOfQuestions);
	}
	
	public MainFrame( int startIndex, int numOfQuestions , boolean randomSeqOfQuestions) {
		
		this.startIndex = startIndex;
		this.numOfQuestions = numOfQuestions;
		this.currentQuestionIndex = 0;
		
		questions = new CreateQuestions().createQuestions(this.startIndex, this.numOfQuestions, randomSeqOfQuestions);
		
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setSize(800, 500);
		this.setLocation(500, 300);
		
		this.listener = new MainFrameActionList( this );
		
		c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		
		//
		labelQuestion = new JLabel();
		
		// PANEL ANSWERS ---------------------------
		
		panelAnswers = new JPanel();
		panelAnswers.setLayout( new BoxLayout( panelAnswers,  BoxLayout.Y_AXIS ));
		
		// -----------------------------------------
	
		// PANEL BOTTOM MENU -----------------------
		
		panelBottomMenu = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		btnNext = new JButton("NEXT");
		btnNext.addActionListener(listener);
		btnNext.setActionCommand( MainFrameActionList.NEXT);
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(listener);
		btnBack.setActionCommand( MainFrameActionList.BACK);
		
		btnEnd = new JButton("END");
		btnEnd.addActionListener(listener);
		btnEnd.setActionCommand( MainFrameActionList.END);
		
		
		btnRestart = new JButton("RESTART");
		btnRestart.addActionListener(listener);
		btnRestart.setActionCommand( MainFrameActionList.RESTART );
		
		labelPosition = new JLabel( generateCurrentQuestionPositionLabel() );
		
		panelBottomMenu.add( btnBack );
		panelBottomMenu.add( btnNext );
		panelBottomMenu.add( btnEnd );
		panelBottomMenu.add( btnRestart );
		panelBottomMenu.add( labelPosition );
		
		
		// -----------------------------------------
		
		// Adding componet to the container
		c.add( labelQuestion , BorderLayout.NORTH);
		c.add( panelAnswers, BorderLayout.CENTER );
		c.add( panelBottomMenu, BorderLayout.SOUTH );
		
		//
		showQuestion();
		
		
		//
		this.setVisible(true);
		
	}
	
	private Question getCurrentQuestion() {
		return this.getQuestions().get( this.getCurrentQuestionIndex() );
	}
	
	private String generateCurrentQuestionPositionLabel() {
		int currPos = getCurrentQuestionIndex() + 1;
		return "<html>&emsp;" + currPos + "/ " + getQuestions().size() + "</html>";
	}
	
	public void showQuestion() {
		
		
		// PRINT QUESTION ######
		this.getLabelQuestion().setText( getCurrentQuestion().getQuestionText() );

		// PRINT ANSWERS ######
		
		String [] answers = getCurrentQuestion().getAnswers();
		int [] selectedAns = getCurrentQuestion().getSelectedAnswers();
		
		this.getPanelAnswers().removeAll();
		
		//
		for( int i = 0; i < answers.length; i ++ ) {
			
			// JPanel panelAns = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
			// panelAns.setMaximumSize( new Dimension( (int) panelAns.getMaximumSize().width, 40 ));
			
			// ---- checkbox --
			JCheckBox cb = new JCheckBox( answers[i], false );
			
			cb.setActionCommand( i + "" );
		
			
			if( selectedAns[i] == Question.SELECTED_ANSWER ) cb.setSelected(true);
		
			cb.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					JCheckBox jCb = (JCheckBox) e.getSource();
					int selectedAnsw = Integer.parseInt( e.getActionCommand() );
					
					if( jCb.isSelected() )
						getCurrentQuestion().setSelectedAnswer( selectedAnsw );
					else 
						getCurrentQuestion().setUnselectedAnswer( selectedAnsw );
					
					System.out.println("MainFrame -> CheckBoxListener -> currentQuestionIndex -> " + getCurrentQuestionIndex() + " selectedAns ->" + selectedAnsw );
					
				}
			});
			// ----------------
			
			// panelAns.add(cb);
			
			this.getPanelAnswers().add(cb);
			
		
		}
		
		// MANAGE BUTTONS ##########
		if( getCurrentQuestionIndex() == 0 ) {
		
			getBtnBack().setEnabled( false );
			getBtnNext().setEnabled( true );
			getBtnEnd().setEnabled( true );
		
		} else if ( getCurrentQuestionIndex() ==  this.getQuestions().size() - 1 ) {
		
			getBtnBack().setEnabled( true );
			getBtnNext().setEnabled( false );
			getBtnEnd().setEnabled( true );			
		
		} else {
			
			getBtnBack().setEnabled( true );
			getBtnNext().setEnabled( true );
			getBtnEnd().setEnabled( true );
			
		}
		
		getLabelPosition().setText( generateCurrentQuestionPositionLabel() );
		
		
		
		//
		c.revalidate();
		c.repaint();
		
		this.getPanelAnswers().revalidate();
		this.getPanelAnswers().repaint();
	}
	
	
	
	
	public void incrementCurrentQuestionIndex() {
		this.currentQuestionIndex ++;
		this.showQuestion();
	}
	
	public void decrementCurrentQuestionIndex() {
		this.currentQuestionIndex --;
		this.showQuestion();
	}

	public int getCurrentQuestionIndex() {
		return this.currentQuestionIndex;
	}
	

	public JLabel getLabelQuestion() {
		return labelQuestion;
	}

//	public void setLabelQuestion(JLabel labelQuestion) {
//		this.labelQuestion = labelQuestion;
//	}

	public ArrayList<Question> getQuestions() {
		return this.questions;
	}


	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public JPanel getPanelAnswers() {
		return panelAnswers;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public JButton getBtnEnd() {
		return btnEnd;
	}

	public JLabel getLabelPosition() {
		return labelPosition;
	}

	public void setLabelPosition(JLabel labelPosition) {
		this.labelPosition = labelPosition;
	}	
	
}
