package com.Project2;


public class Stack {
	
	private StackNode top = null;
	private int size = 0;
	  
	public class StackNode {
		
		private int value;
		private StackNode next;
	    
	    public void setValue(int val) {
	    	value = val;
	    }
	    
	    public void setNext(StackNode next) {
	    	this.next = next;
	    }
	    
	    public int getValue() {
	    	return value;
	    }
	    
	    public StackNode getNext() {
	    	return next;
	    }    
	    
	}

	public boolean isEmpty() {
	    return top == null;
	}
	  
	public int pop() {
		if(size > 0) {
			size--;
		}
	    int val = top.getValue();
	    top = top.next;
	    return val;
	}

	public void push(int value) {
		StackNode tmp = new StackNode();
		tmp.setValue(value);
		tmp.setNext(top);
		top = tmp;
		size++;
	}

	public int peek() {
		int val = top.getValue();
		return val;
	}
	  
	public int size() {
		int theSize = size;
		return theSize;
	}
}
