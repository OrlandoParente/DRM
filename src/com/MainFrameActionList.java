package com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.model.Question;

public class MainFrameActionList implements ActionListener{

	public final static String BACK = "BACK";
	public final static String NEXT = "NEXT";
	public final static String END = "END";
	public final static String RESTART = "RESTART";
	
	private MainFrame mainFrame;
	
	public MainFrameActionList( MainFrame mainFrame ) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
		
		case NEXT:
			
			mainFrame.incrementCurrentQuestionIndex();
			
			break;
			
		case BACK:
			
			mainFrame.decrementCurrentQuestionIndex();
			
			break;
			
		case END:
			
			int score = 0;
			int i = 1;
			
			for( Question q : mainFrame.getQuestions() ) {
				if( q.checkAnswer() ) {
					score ++;
					System.out.println( i + " : CORRETTA");
				} else {
					System.out.println( i + " : SBAGLIATA");
				}
				
				i ++;
			}
			
			System.out.println("PUNTEGGIO : " + score + "/" + mainFrame.getQuestions().size() );
			
			new EndFrame( mainFrame.getQuestions(), score);
			mainFrame.dispose();
			
			break;
			
		case RESTART:
			
			new StartFrame();
			mainFrame.dispose();
			
			break;
		
		}
		
		
	}

}
