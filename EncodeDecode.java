//NAME: CLAYTON BAKER
//DUE DATE: 26NOV2021
//CLASS: COP5416
//TITLE: Natural Language Encoder / Decoder
//DESCRIPTION: This program encodes / decodes a list of words with plus / minus symbols.
//After inserting these words into a binary tree using the symbols, the words can be
//retrieved using the symbols as a code.


package com.Project3;


public class EncodeDecode {
	
	public static void main(String[] args) {
		
		//Instantiate objects and use methods for encode / decode
		BinaryTree b = new BinaryTree();
		
		WordEncoder w = new WordEncoder();
		w.encode("words.txt", b);
		
		WordDecoder w2 = new WordDecoder();
		w2.decode("encoded.txt", b);
	}

}


