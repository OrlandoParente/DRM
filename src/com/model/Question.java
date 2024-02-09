package com.model;

public class Question {
	
	
	public final static int NOT_SELECTED_ANSWER = -1;
	public final static int SELECTED_ANSWER = 1;
	
	private String questionText;
	private String [] answers;
	private int [] rightAnswers;
	
	private int numOfAnswers;
	private int numOfCorrectAnswers;
	
	private int [] selectedAnswers;
	
	
	// Costruttore con i valori di default
	public Question() {
		
		this.questionText = "";
		this.answers = new String[] {"1","2","3","4"};
		this.rightAnswers = new int[] {0};	// Di default la corretta è la prima
		this.numOfAnswers = 4;
		this.numOfCorrectAnswers = 1;
		this.initSelectedAnswer();
		
	}

	public Question(String questionText, int[] rightAnswers ) {
		
		this();
		this.questionText = questionText;
		this.rightAnswers = rightAnswers;
	}
	
	public Question(String[] answers, int[] rightAnswers) {
		
		this();
		this.answers = answers;
		this.rightAnswers = rightAnswers;
		this.numOfAnswers = rightAnswers.length;
		this.numOfCorrectAnswers = rightAnswers.length;
		
		this.initSelectedAnswer();
	}

	public Question(String questionText, String[] answers, int[] rightAnswers) {
		
		this();
		this.questionText = questionText;
		this.answers = answers;
		this.rightAnswers = rightAnswers;
		this.numOfAnswers = answers.length;
		this.numOfCorrectAnswers = rightAnswers.length;
		this.initSelectedAnswer();
		
		// System.out.println("Question -> Custructor -> rightAnswers -> " + Utils.intArrToString(rightAnswers) );
	}
	

	
	//
	private void initSelectedAnswer() {
		
		selectedAnswers = new int[ this.getNumOfAnswers() ];
		
		for( int i=0; i < this.getNumOfAnswers(); i ++ )
			selectedAnswers[i] = Question.NOT_SELECTED_ANSWER;
		
	}

	// Ritorna come risposta corretta (true), se sono state selezionate tutte e sole le risposte in rightAnswer
	public boolean checkAnswer() {
		
		int [] selectedAns = this.getSelectedAnswers();
		
		int [] tmp = new int[ selectedAns.length ];
		
		// copio il selectedAns in un nuovo array per non modificare l'originale
		for( int i = 0; i < selectedAns.length; i ++ )
			tmp[i] = selectedAns[i];
		
		// CONTROLLO che tutte le risposte giuste sono state selezionate
		for( int i = 0; i < this.getRightAnswers().length; i ++ ) {
			
			// System.out.println( "Question -> checkAnswer -> i : " + i + "; rightAns : " + rightAnswers[i] + ": isSelected [ YES = 1; NO = -1 ] --> " + selectedAnswers[ rightAnswers[i] ] );
			
			if( selectedAnswers[ rightAnswers[i] ] == Question.NOT_SELECTED_ANSWER ) return false;
			// una volta controllate, le tolgo per il controllo successino
			else tmp[ rightAnswers[i] ] = -1; 
		}
		
		// CONTROLLO che NON sono state selezionate risposte sbagliate
		for( int i = 0; i < tmp.length; i ++ ) {
			if( tmp[i] != Question.NOT_SELECTED_ANSWER ) return false;
		}
		
		return true;
	}
	
	// GETTERS AND SETTERS 
	
	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public int[] getRightAnswers() {
		return rightAnswers;
	}

	public void setRightAnswers(int[] rightAnswers) {
		this.rightAnswers = rightAnswers;
	}

	public int getNumOfAnswers() {
		return numOfAnswers;
	}

	public void setNumOfAnswers(int numOfAnswers) {
		this.numOfAnswers = numOfAnswers;
	}

	public int getNumOfCorrectAnswers() {
		return numOfCorrectAnswers;
	}

	public void setNumOfCorrectAnswers(int numOfCorrectAnswers) {
		this.numOfCorrectAnswers = numOfCorrectAnswers;
	}

	public int [] getSelectedAnswers() {
		return selectedAnswers;
	}

	public void setSelectedAnswer( int selectAnsw ) {
		
		if( selectAnsw < this.getNumOfAnswers() && selectAnsw >= 0 )
			this.selectedAnswers[ selectAnsw ] = Question.SELECTED_ANSWER;

	}
	
	public void setUnselectedAnswer( int unselectAnsw ) {
		
		if( unselectAnsw < this.getNumOfAnswers() && unselectAnsw >= 0 )
			this.selectedAnswers[ unselectAnsw ] = Question.NOT_SELECTED_ANSWER;

	}
	
	
	
	
}
