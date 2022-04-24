//NAME: CLAYTON BAKER
//DUE DATE: 29OCT2021
//CLASS: COP 5416
//TITLE: PROJECT #1
//DESCRIPTION: THIS PROJECT TAKES IN DATES AND VALUES FROM A TEXT DOCUMENT AND DISPLAYS THESE DATES AND VALUES USING ARRAYS.  
//THE VALUES ARE THEN USED TO CALCULATE THE MEANS AND STANDARD DEVIATIONS BASED ON THE DATES.
//LASTLY, THE DIFFERENCES BETWEEN MEANS IS CALCULATED USING PAIR-WISE T-TESTS.

package com.Project;
import java.io.*;
import java.util.*;


public class TwoDArrayAnalysis {

	//VARIABLES
    final int ROWS = 24;
    final int COLS = 5;
    final String[] titleRow = new String[COLS];
    double[][] grades = new double[ROWS][COLS];
    double[] means = new double[COLS];
    double[] stanDev = new double[COLS];
    final String[][] tTestResults = {{" ", " ", " ", " ", " "}, {" ", " ", " ", " ", " "}, {" ", " ", " ", " ", " "}, {" ", " ", " ", " ", " "}};
    //INITIALIZED AN EMPTY ARRAY FOR tTestResults IN ORDER TO STOP NULL FROM PRINTING TO SCREEN
	double tTest = 0;
	double sP = 0;
	int degOfFreedom = 46;
	double denomOfTtest = 0.28867512459481288; //HAD TO HARD CODE THE VALUE OR ELSE JAVA WOULDN'T CALCULATE ACCURATELY (1/12)^1/2
	String YorN = "";
	
	public TwoDArrayAnalysis() throws FileNotFoundException {
	   
		System.out.println("Means and Standard Deviations of Scores");
		System.out.println();
		
		//READS DATA FROM TXT FILE
		File dailyScores = new File("dailyScores.txt");
	
		Scanner s = new Scanner(dailyScores);
	
		//PRINTS FIRST TITLE ARRAY WITH DATES
		System.out.print("Student  ");
		for (int i = 0; i < COLS; i++) 
		{
			titleRow[i] = s.nextLine();
			System.out.print(" " + titleRow[i] + "	 ");
		}
		
		System.out.println();
		
		//CREATES ARRAY FOR GRADE DATA
	    for (int i = 0; i < COLS; i++) 
	    {		
	        for (int j = 0; j < ROWS; j++) 
	        {	
	            grades[j][i] = s.nextDouble();
	        }
	    }
	
		s.close();
		
		//PRINTS ARRAY FOR GRADE DATA
		for (int i = 0; i < ROWS; i++) 
		{
			System.out.print(i + 1 + "	 ");
			for (int j = 0; j < COLS; j++)
			{	
				System.out.printf("   " + grades[i][j] + "	  ");
			}
		System.out.println();
		}
		
		System.out.println("************************************************************************************");
	    System.out.println();

	    //PRINTS MEAN DATA
		System.out.print("Mean  ");
		for (int i = 0; i < COLS; i++) 
		{
			means[i] = Math.round(computeMeanOfColumn(i) * 100.0)/100.0; 
			System.out.print("	    " + means[i] + "");
		}
		
		System.out.println();
		
		//PRINTS STANDARD DEVIATION DATA
		System.out.print("SD");
		for (int i = 0; i < COLS; i++) 
		{
			stanDev[i] = Math.round(computeStanDev(i) * 100.0)/100.0;
			System.out.print("	     " + stanDev[i] + "");
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Significant Differences in Mean Scores ");
		System.out.println();
		
		//PRINTS SECOND TITLE ARRAY WITH DATES
		System.out.print("	   ");
		for (int i = 1; i < COLS; i++) 
		{
			System.out.print("   " + titleRow[i] + "   ");
		}
		
		System.out.println();
		
		//COMPUTE T-TESTS AND PRINTS ARRAY
		for(int i = 0; i < COLS - 1; i++) 
		{
			for (int j = i + 1; j < COLS; j++) 
			{ 
			double sdSquared1 = Math.pow(stanDev[i], 2);
			double sdSquared2 = Math.pow(stanDev[j], 2);
			double means1 = means[i];
			double means2 = means[j];
			  
			sP = Math.sqrt(( (((23)*sdSquared1) + ((23)*sdSquared2)) / 
				  				(degOfFreedom) ));
		  
			tTest = (means1 - means2) / (sP * denomOfTtest);
		  
			if (tTest > 2.25) {
				YorN = "Y";
			}
			else if (tTest < 2.25) {
				YorN = "N";
			} else {
				YorN = " ";
			}
			tTestResults[i][j] = YorN;
			}
		}
			  
		//PRINTS T-TESTS RESULTS ARRAY
		for(int i = 0; i < COLS - 1; i++) 
		{
			System.out.print("6/12/" + (2021 - i) + "   ");
			for (int j = 1; j < COLS; j++) 
			{
				System.out.print("	" + tTestResults[i][j] + "	");
			}
		System.out.println();
		}
		System.out.println();
	}
	
	//CALCULATES THE MEANS FROM GRADES DATA
	public double computeMeanOfColumn(int column) { 
		int total = 0; 
		for(int i = 0; i < ROWS; i++) 
		{ 
			total += grades[i][column]; 
		} 
		return (double)total/ROWS; 
	}
	
	//CALCULATES STANDARD DEVIATION FROM GRADES DATA
	public double computeStanDev(int column) { 
		double total = 0; 
		for (int i = 0; i < ROWS; i++) 
		{ 
			total += Math.pow(grades[i][column] - means[column], 2); 
		}
		return (double)Math.sqrt(total/ROWS); 
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		new TwoDArrayAnalysis();
	}
}


