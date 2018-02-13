//class Will use the methods of Alphabets to show histogram 

import java.util.*;
import java.io.*;

public class TestAlphabets{
	public static void main(String[] args) throws IOException{
		ArrayList<Character> list = new ArrayList<Character>();
		FileReader in = null; //initiate FileReader
        
        try{
			in = new FileReader(args[0]); //open to be read from command line 
			
			int c;
			while((c = in.read()) != -1){  //read file character by character
			 
				list.add((char)c);				//type cast it to char and add to list
				
			} 
		}
		
		finally{
			if(in != null){
				in.close();
			}
			
		}

		
		// an array of alphabet
		String [] alpha = {"A", "B", "C", "D", "E", "F","G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		
		//array list of characters that will store only upper case letters
		ArrayList<Character> list2 = Alphabets.listOfChars(list);
		
		System.out.println("Total Characters = " + list2.size());
		
		//get frequency of the most occureing letter
		int modee = Alphabets.mode(list2);
		
		// get the percentage of mode
		double pom = Alphabets.percentageOfMode(list2.size(), modee);
		
		//create new object histogram
		for(int a = 0; a< alpha.length; a++){
		Alphabets histogram = new Alphabets(alpha[a], Alphabets.occurenceOfChar(list2, alpha[a]), Alphabets.percentageOfOccurence(list2, Alphabets.occurenceOfChar(list2, alpha[a]) ));
		
		
		System.out.println(" ");
		// display histogram
		System.out.print(histogram); 
        //display aesteriks	   
	    Alphabets.aesteriks(Alphabets.percentageOfOccurence(list2, Alphabets.occurenceOfChar(list2, alpha[a])), pom);
		System.out.println(" ");
	
	}

		
		
	}
}