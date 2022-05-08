package com.Project3;
import java.io.*;
import java.util.*;

public class WordDecoder {
	
	//Reads text file and decodes message based on symbols (+/-)
	public void decode(String fileName, BinaryTree binaryTree) {
	
	     try (Scanner s = new Scanner(new File(fileName))) {
	    	 
	    	 while (s.hasNextLine()) {
				 String string2 = new String(s.nextLine());
						        	
				 String[] stringArray = string2.split(" ");
						        	
				 for (int i = 0; i < stringArray.length; i++) {
					 System.out.print(binaryTree.searchReturn(stringArray[i]));
					 System.out.print(" ");
				 }
				 System.out.println();
	         }
	     } catch (FileNotFoundException e) {
	 		e.printStackTrace();
	 	 }
	}
}



