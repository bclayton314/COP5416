package com.Project3;
import java.io.*;
import java.util.*;


public class WordEncoder {
	
	//Reads text file and inserts into binary tree
	public void encode(String fileName, BinaryTree binaryTree) {

		binaryTree.insert("", "");
		
		try (Scanner s = new Scanner(new File(fileName))){
	    	while (s.hasNextLine()) {
	    		String string1 = new String(s.nextLine());
	    		String[] stringArray = string1.split(" ");
	    		
	    		String firstWord = stringArray[0];
	    		String plusMinusSymbols = stringArray[1];
	    		
	    		binaryTree.insert(firstWord, plusMinusSymbols);
	        }
	    } catch (FileNotFoundException e) {
	 		e.printStackTrace();
	 	}
	}
}


