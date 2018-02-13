import java.util.*;
import java.io.*;

public class Alphabets{
	public static String alphabet;
	public static int frequency;
	public static double percentage;
	
	public Alphabets(String a, int f, double p){
		alphabet = a;
		frequency = f; //method to get frequency;
		percentage = p;// method to get pom;
	}
	
	
	
	   public static ArrayList<Character> listOfChars (ArrayList<Character> list){
			ArrayList<Character> list2 = new ArrayList<Character>();
		    for (int z = 0; z <list.size(); z++){
			if (String.valueOf(list.get(z)).matches("[A-Z]+")) {
				list2.add(list.get(z));
			}
		} 
		return list2;
		}
	 public static int  occurenceOfChar (ArrayList<Character> list2, String X){
		   int counting = 0;
	    for (int i=0; i<list2.size(); i++){
		   if (String.valueOf(list2.get(i)).matches(X)) {
			    counting = counting + 1;
		   }
		
	   }
	   return counting;
	   }
	   
	     // method to calculate percentage
	   public static double percentageOfOccurence(ArrayList<Character> list2, int counting){
        double percentage = ((double)counting / list2.size()) *100;
		
		return percentage;
	   }
	   
	   // calculate mode
	   public static int mode(ArrayList<Character> markList){ 
		char mode = markList.get(0);
		int maxCount = 0;
		for (int i = 0; i < markList.size(); i++){
			char value = markList.get(i);
			int count = 1;
			for(int j = 0; j < markList.size(); j++){
				if (markList.get(j) == value) count++;
				if (count > maxCount){
					mode = value;
					maxCount = count;
				}
			}
        }
		return(maxCount - 1);
		
	}
	public static double percentageOfMode (int total, int number){
		double percent = ((double) number / total) *100;
		
		return percent;
	}
	
	public static void aesteriks(double p1, double p2){
		double x = (p1/p2)* 50;
		if( x != 50 ){
		for (int n = 0; n < (int) x ; n++) {
			System.out.print("*" + " ");
			if (n % 10 == 9)
				System.out.print(" | ");
	}
		}
	    else{
			for (int n = 0; n < 50; n++) {
			System.out.print("*" + " ");
			if (n % 10 == 9)
				System.out.print(" | ");
			
		}
		}
		
	}
	public String toString(){
	 return this.alphabet + " [ " + this.frequency + " ] " + String.format("%.2f",this.percentage) +  "%  " ;
	}
}