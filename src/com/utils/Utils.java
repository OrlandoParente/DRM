package com.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
// https://github.com/stleary/JSON-java
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;


public class Utils {
	
	public Utils() {}
	
	/*
	 * Ritorna il contenuto di un file sottoforma di String
	 */
	public static String fileToString( String path ) {
		
		String text = "";
		
		try {
			
			BufferedReader reader = new BufferedReader( new FileReader( path ) );
			String line = reader.readLine();
			while( line != null ) {
				text += line;
				line = reader.readLine();
			}
			
			reader.close();
			
			// stampa di controllo 
			System.out.println("LETTURA LINE " + path + " --> " + text );
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
		} 
		
		
		return text; 
	}

	
	/*
	 * Ritorna il contenuto di un file in un JSON Object 
	 */
	public static JSONObject fileToJSONObject( String path ) {
		
		
		String jsonString = "";
		jsonString += Utils.fileToString( path );
		
		JSONObject jsonObj = new JSONObject( jsonString );
		
		
		return jsonObj;
	}
	
	/*
	 * Ritorna il contenuto di un file in un JSON Array 
	 */
	public static JSONArray fileToJSONArray ( String path ) {
		
		
		String jsonString = "";
		jsonString += Utils.fileToString( path );
		
		System.out.println("UTILS ---> " + jsonString );
		
		JSONArray jsonArr = new JSONArray( jsonString );
		
		
		return jsonArr;
	}
	
	/*
	 * Scrive una stringa su un file
	 */
	public static void writeOnFile( String string, String path, boolean append) {
		
		try {
			
			FileWriter fw = new FileWriter( new File( path ) ,append );
			fw.write( string );
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Ritorna un array json in un array di stringhe
	 */
	public static String [] jsonArrayToStringArray( JSONArray jsonArr ) {
	
		int n = jsonArr.length();
		
		String arr [] = new String [ n ];
		int i = 0;
		
		for (Object jo : jsonArr) {
			
			if( jo instanceof String ) {
				arr[i] = (String) jo;
			}
		
			i  ++;
		}
			
		return arr;
	}

	
	/*
	 * Ritorna un array json in un array di interi
	 */
	public static int [] jsonArrayToIntArray( JSONArray jsonArr ) {
	
		int n = jsonArr.length();
		
		int arr [] = new int [ n ];
		int i = 0;
		
		for (Object jo : jsonArr) {
						
			arr[i] = Integer.parseInt( (String) jo );
		
			i  ++;
		}
			
		return arr;
	}

	/*
	 * Ritorna un array di interi sottoforma di stringa
	 */
	public static String intArrToString ( int [] arr ) {
		
		String str = "[";
		for( int i = 0; i < arr.length; i ++ ) {
			str += arr[i];
			if( i != arr.length -1 ) str += ",";
		}
		str += "]";
		
		return str;
		
	}
	
	
}
