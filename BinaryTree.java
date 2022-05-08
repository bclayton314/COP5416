package com.Project3;


public class BinaryTree {
	
	//Root of Binary Tree
	private Node root = null, current, parent;
	
	//Class with constructor and left / right child of current node and data
	private class Node {
		String data;
	    Node left;
	    Node right;
	    
	    public Node (String word) {
	    	data = word;
	    	left = null;
	    	right = null;
	    }
	}
	
	//Method to insert into binary tree
	public void insert(String word, String symbols) {
		Node newNode = new Node(word);
	    char[] symbolsArray = symbols.toCharArray();
	    char[] plus = "+".toCharArray();

	    if (root == null) {
			root = newNode;
	    } else {
			parent = null;
			current = root;
			while(current != null) {
				for(int i = 0; i < symbolsArray.length; i++) {
				    parent = current;
					if(symbolsArray[i] == plus[0]) {
					    current = current.left;
					} else {
					    current = current.right;
					}
				}
			}
			if(lastSymbolInArray(symbolsArray) == plus[0]) {
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}
	    }
	}
	
	//Public method to call searchReturn method
	public String searchReturn(String data) {
	    return searchReturn(this.root, data);
	}
	
	//Private method to search and return values based on (+/-)
	private String searchReturn(Node root, String data) {
		char[] symbolChars = data.toCharArray();
		char[] plus2 = "+".toCharArray();
		
	    for(int i = 0; i < symbolChars.length; i++) {
	        if (symbolChars[i] != plus2[0]) {
	            root = root.right;
	        } else {
	            root = root.left;
	        }
	    }
	    return root.data;
	}
	
	//Helper method to find last char in chararray
	public char lastSymbolInArray(char[] charArray) {
		char[] lastSymbol = new char[1];
		
		for(int i = 0; i < charArray.length; i++) {
			if (i == charArray.length - 1) {
				lastSymbol[0] = charArray[i];
			}
		}
		return lastSymbol[0];
	}
}
