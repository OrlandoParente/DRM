package com.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import com.model.Question;
import com.utils.JsonKeys;
import com.utils.Utils;

public class CreateQuestions{

	public final static int USE_ALL_QUESTIONS = -1; // in generale funziona per qualsiasi numero negativo
			
	private ArrayList<Question> questions;
	
	private String questionText;
	private String [] answers;
	private int [] rightAnswers;
	
	private int numOfQuestions;
	private int startIndex;
	
	public ArrayList<Question> createQuestions( int numOfQuestions ){
		return this.createQuestions(0, numOfQuestions, false);
	}
	
	public ArrayList<Question> createQuestions( int startIndex, int numOfQuestions, boolean randomSeqOfQuestions ){
		
		//
		questions = new ArrayList<Question>();
		
		// Recupero le domande da un file json
		JSONArray ja = Utils.fileToJSONArray("questions/selected.json");
		
		// Se si vogliono avere tutte le domande oppure 
		// il numero di domande richieste supera quelle disponibili
		// // allora il numOfQuestions corrisponde alla dimensione dell'array di domande
		// // ALTRIMENTI al num di domande effettivamente richiesto
		if( numOfQuestions < 0 || numOfQuestions > ja.length() )
			this.numOfQuestions = ja.length();
		else 
			this.numOfQuestions = numOfQuestions;
		
		// Se l'index iniziale genera errore, lo inizializza a 0
		if( startIndex < 0 || startIndex > ja.length() )
			this.startIndex = 0;
		else
			this.startIndex = startIndex;
		
		// Se startIndex + numOfQuestion supera le domande disponibili, "taglia" il numero di domande
		if( this.startIndex + this.numOfQuestions > ja.length() ) {
			this.numOfQuestions = ja.length() - this.startIndex;
		}
		
		System.out.println("CreateQuestions -> createQuestions -> ja.lenght -> " + ja.length() + " numOfQuestions --> " + numOfQuestions  );
		
		int i = 0;
		
		for (Object jo : ja) {
			
			// Salta i primi "startIndex" elementi
			if( this.startIndex > 0 ) {
				// System.out.println("factory.CreateQuestions -> startIndex -> " + startIndex );
				this.startIndex --;
				continue;
			}
			
			JSONObject jObj = (JSONObject) jo;
	
			questionText = jObj.getString( JsonKeys.QUESTION );
			answers = Utils.jsonArrayToStringArray( jObj.getJSONArray( JsonKeys.ANSWERS ) );
			rightAnswers = Utils.jsonArrayToIntArray( jObj.getJSONArray( JsonKeys.RIGHT_ANSWERS ) );
		
			// System.out.println("CreateQuestions -> createQuestions -> jObj.getJSONArray( JsonKeys.RIGHT_ANSWERS ) -> " + jObj.getJSONArray( JsonKeys.RIGHT_ANSWERS ) );
			// System.out.println("CreateQuestions -> createQuestions -> rightAnswers -> " + Utils.intArrToString(rightAnswers) );

			questions.add( new Question( questionText, answers, rightAnswers ) );
			
			if( i == this.numOfQuestions ) break;
			i ++;
			
		}
		
		// SE richiesto, mischia il numero delle domande
		if( randomSeqOfQuestions )
			Collections.shuffle(questions, new Random( ));
		
		return questions;
	}
}
