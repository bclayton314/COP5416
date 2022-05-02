//NAME: CLAYTON BAKER
//CLASS: COP 5416
//PR0JECT 2: ADD / SUBTRACT LARGE INTEGERS WITH STACKS
//DUE DATE: 12NOV2021
//DESCRIPTION: This is a Java program that will add / subtract arbitrarily large numbers using stacks.
//First, two (large or small) integers will be placed into two stacks.  Using pop() and push(),
//these two numbers will be added or subtracted and placed into a third stack.  The third stack
//will then be displayed on the console.
//(Apologies this program doesn't adhere to good OOP principles, I ran out of time)

package com.Project2;
import java.io.*;
import java.util.*;


public class addsAndSubtracts {
	
	//VARIABLES
	Stack stack1 = new Stack();
	Stack stack2 = new Stack();
	Stack resultStack = new Stack();
	
	//ADDS / SUBTRACTS FROM DATA FILE AND DISPLAYS TO CONSOLE
	public addsAndSubtracts() throws FileNotFoundException {
		System.out.println("Adding / Subtracting Large Integers with Stacks");
		System.out.println();
		
		//READS DATA FROM TXT FILE
		File numberValues = new File("addsAndSubtracts.txt");
		
		Scanner s = new Scanner(numberValues);
		
		//CREATES STRING ARRAY
		while (s.hasNextLine()) {
			String str1 = s.nextLine();
			String str2 = str1.replaceAll("\\s+", "");		
			
			//SPLITS UP OPERANDS, SIGNS, & THE OPERATOR
			String[] mathExprArray = new String[str2.length()];
			mathExprArray = str2.split("(?<=[-+*/])|(?=[-+*/])");

			//BOOLEANS TO CHECK FOR DIFFERENT SIGNS
			boolean sign1 = mathExprArray[0].equals("-") && !(mathExprArray[3].equals("-"));
			boolean sign2 = mathExprArray[2].equals("-") && !(mathExprArray[3].equals("-"));
			boolean sign3 = mathExprArray[0].equals("-") && mathExprArray[3].equals("-");
			
			//LOCAL VARIABLES TO SPLIT UP THE MATH EXPRESSIONS
			char[] firstSign = null;
			char[] secondSign = null;
			char[] firstOperand = null;
			char[] operator = null;
			char[] secondOperand = null;
			
			//PLACES SIGNS, OPERANDS, AND OPERATORS INTO APPROPRIATE CHAR ARRAY
			if (sign3) { 	//IF BOTH SIGNS ARE NEGATIVE: [-, 1, +, -, 2]
				firstSign = mathExprArray[0].toCharArray();
				firstOperand = mathExprArray[1].toCharArray();
				operator = mathExprArray[2].toCharArray();
				secondSign = mathExprArray[3].toCharArray();
				secondOperand = mathExprArray[4].toCharArray();
				System.out.format("%4.100s", mathExprArray[0] + mathExprArray[1] + "\n" + 
				mathExprArray[2] + mathExprArray[3] + mathExprArray[4]);
			} else if (sign1) {		//IF 1ST SIGN IS NEGATIVE: [-, 1, +, 2]
				firstSign = mathExprArray[0].toCharArray();
				firstOperand = mathExprArray[1].toCharArray();
				operator = mathExprArray[2].toCharArray();
				secondOperand = mathExprArray[3].toCharArray();
				System.out.format("%4.100s", mathExprArray[0] + mathExprArray[1] + "\n" + 
				mathExprArray[2] + mathExprArray[3]);
			} else if (sign2) { 	//IF 2ND SIGN IS NEGATIVE: [1, +, -, 2]
				firstOperand = mathExprArray[0].toCharArray();
				operator = mathExprArray[1].toCharArray();
				secondSign = mathExprArray[2].toCharArray();
				secondOperand = mathExprArray[3].toCharArray();
				System.out.format("%4.100s", mathExprArray[0] + "\n" + mathExprArray[1] + 
				mathExprArray[2] + mathExprArray[3]);
			} else {			//IF NEITHER SIGN IS NEGATIVE: [1, +, 2]
				firstOperand = mathExprArray[0].toCharArray();
				operator = mathExprArray[1].toCharArray();
				secondOperand = mathExprArray[2].toCharArray();
				System.out.format("%4.100s", mathExprArray[0] + "\n" + mathExprArray[1] + 
				mathExprArray[2]);
			}
			
			//HAVE TO CAST OPERANDS TO INT AND PUT INTO STACK
			for(int i = 0; i < firstOperand.length; i++) {
				stack1.push(Character.getNumericValue(firstOperand[i]));
			}
			for(int i = 0; i < secondOperand.length; i++) {
				stack2.push(Character.getNumericValue(secondOperand[i]));
			}
			
			//CONDITIONAL LOGIC FOR ADDITION STACKS
			if (firstSign == null && secondSign == null && operator[0] == '+') {
				resultStack = addStacks(stack1, stack2);
				display(resultStack);
			} else if (firstSign == null && secondSign != null && operator[0] == '-') {
				resultStack = addStacks(stack1, stack2);
				display(resultStack);
			} else if (firstSign != null && secondSign != null && operator[0] == '+') {
				resultStack = addStacks(stack1, stack2);
				System.out.print('-');
				display(resultStack);
			} else if (firstSign != null && secondSign == null && operator[0] == '-') {
				resultStack = addStacks(stack1, stack2);
				System.out.print('-');
				display(resultStack);
			}
			
			//IF SECOND OPERAND IS BIGGER THAN FIRST OPERAND
			//SWITCH PLACES TO MAKE SUBTRACTION EASIER
			int compareValue = compareBigNumbers(firstOperand, secondOperand);
			if (compareValue == -1) {
				Stack stackSwapVar = stack2; 
				stack2 = stack1; 
				stack1 = stackSwapVar;
			}
			
			//CONDITIONAL LOGIC FOR SUBTRACTION STACKS
			if(firstSign == null && secondSign != null && operator[0] == '+') {
				resultStack = subtractStacks(stack1, stack2);
				if(compareValue == -1) {
					System.out.print('-');
				}
				display(resultStack);
			} else if (firstSign != null && secondSign == null && operator[0] == '+') {
				resultStack = subtractStacks(stack1, stack2);
				if(compareValue == 1) {
					System.out.print('-');
				}
				display(resultStack);
			} else if (firstSign != null && secondSign != null && operator[0] == '-') {
				resultStack = subtractStacks(stack1, stack2);
				if(compareValue == 1) {
					System.out.print('-');
				}
				display(resultStack);			
			} else if (firstSign == null && secondSign == null && operator[0] == '-') {
				resultStack = subtractStacks(stack1, stack2);
				if(compareValue == -1) {
					System.out.print('-');
				}
				display(resultStack);			
			}
		}
		s.close();
	}

	//HELPER METHOD FOR ADDITION
    public Stack addStacks(Stack ST1, Stack ST2) {

		int sum = 0;
		int remainder = 0;
		Stack resultStackAdd = new Stack();
		
		while (!ST1.isEmpty() && !ST2.isEmpty()) {
			sum = (remainder + ST1.peek() + ST2.peek());

			resultStackAdd.push(sum % 10);

			remainder = sum / 10;

			ST1.pop();
			ST2.pop();
		}

		//CASE IF N1 ISN'T EMPTY
		while (!ST1.isEmpty()) {
			sum = (remainder + ST1.peek());
			resultStackAdd.push(sum % 10);
			remainder = sum / 10;
			ST1.pop();
		}

		//CASE IF N2 ISN'T EMPTY
		while (!ST2.isEmpty()) {
			sum = (remainder + ST2.peek());
			resultStackAdd.push(sum % 10);
			remainder = sum / 10;
			ST2.pop();
		}

		//CASE IF THERE'S STILL A REMAINDER
		while (remainder > 0) {
			resultStackAdd.push(remainder);
			remainder /= 10;
		}

		//REVERSES STACK TO PLACE SIGNIFICANT DIGIT ON BOTTOM
		while (!resultStackAdd.isEmpty()) {
			ST1.push(resultStackAdd.peek());
			resultStackAdd.pop();
		}
		resultStackAdd = ST1;
		System.out.println();
		return resultStackAdd;
	}
	
    //HELPER METHOD FOR SUBTRACTION
    public Stack subtractStacks(Stack ST1, Stack ST2) {
    	
    	boolean borrow = false;
    	Stack resultStackSub = new Stack();
    	
    	//CHECKING IF STACKS ARE EMPTY
    	while (ST1.isEmpty() == false && ST2.isEmpty() == false) {
    		int subInteger1 = ST1.pop();
    		int subInteger2 = ST2.pop();
    		if (borrow) {
    			subInteger1 -= 1;
    			borrow = false;
    		}
    	    //CHECKING WHICH OPERAND IS LARGER
    	    if (subInteger1 < subInteger2) {
    	    	subInteger1 += 10;
    	    	resultStackSub.push(subInteger1 - subInteger2);
    	        borrow = true;
    	    }
    	    else {
    	    	resultStackSub.push(subInteger1 - subInteger2);
    	    }
    	} 

    	Stack leftStack = new Stack();
    	if(ST1.isEmpty()) {
    		leftStack = ST2;
    	} else {
    		leftStack = ST1;
    	}
    	  
    	while (leftStack.isEmpty() == false) {
    		int subInteger = leftStack.pop();
    		if (borrow) {
    			subInteger -= 1; 
    			borrow = false;
    		}
    		resultStackSub.push(subInteger);
    	}
		System.out.println();
    	return reverse(resultStackSub);
    }
	
    //HELPER METHOD TO DISPLAY RESULT STACK
    public void display(Stack results) {
    	
        String str1 = "";
        
        while (!results.isEmpty()) {
        	str1 = String.valueOf(results.peek()) + str1;
            results.pop();
        }
        str1 = removeZero(str1);
        System.out.format("%4.100s", str1 + "\n");
        System.out.println();
    }
    
    //HELPER METHOD TO REMOVE LEADING ZEROES IN STACK
    public String removeZero(String str) {

    	int i = 0;
    	StringBuffer sb = new StringBuffer(str);
    	
    	while (i < str.length() && str.charAt(i) == '0') {
    		i++;
    		sb.replace(0, i, "");
    	}
    	return sb.toString();
    }
    
    //METHOD TO REVERSE STACK IN ORDER TO DISPLAY ANSWER CORRECTLY
    public Stack reverse(Stack inStack) {
    	
    	Stack outStack = new Stack();
    	  
    	while (inStack.isEmpty() == false) {
    		outStack.push(inStack.pop());
    	}
    	return outStack;
    }
    
    //HELPER METHOD TO COMPARE LARGE INTEGERS WITH USING BIGINT
	public static int compareBigNumbers(char[] ch1, char[] ch2) {
		
		int answerNum = 0;
		
		if(ch1.length > ch2.length) {
			answerNum = 1;
		} else if (ch1.length < ch2.length) {
			answerNum = -1;
		} else if (ch1.length == ch2.length){
			for (int i = 0; i < ch1.length; i++) {
				if ((int)ch1[i] > (int)ch2[i]) {
					return 1;
				} else if ((int)ch1[i] < (int)ch2[i]) {
					return -1;
				} else {
					return 0;
				}
			}
		}
		return answerNum;
	}
    
	public static void main(String[] args) throws FileNotFoundException {
		new addsAndSubtracts();
	}

}

