import org.junit.*; 
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class TestingAlphabets {
	ArrayList<Character> sampleList = new ArrayList<>();
	
	
	
	String [] alpha = {"A", "B", "C", "D", "E", "F","G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	
	Alphabets test = new Alphabets();
	@Test
	public void Test1(){
		sampleList.add('A'); sampleList.add('B'); sampleList.add('C'); sampleList.add('D');
		sampleList.add('E'); sampleList.add('F'); sampleList.add('G'); sampleList.add('H');
		sampleList.add('I'); sampleList.add('J'); sampleList.add('K'); sampleList.add('L');
		sampleList.add('M'); sampleList.add('N'); sampleList.add('O'); sampleList.add('P');
		sampleList.add('Q'); sampleList.add('R'); sampleList.add('S'); sampleList.add('T');
		sampleList.add('U'); sampleList.add('V'); sampleList.add('W'); sampleList.add('X');
		sampleList.add('Y'); sampleList.add('Z'); sampleList.add('A'); sampleList.add('A');
		
		
		
		
        assertEquals(3, test.occurenceOfChar(sampleList, alpha[0]));	 
		assertEquals(10.7142857, test.percentageOfOccurence(sampleList, test.occurenceOfChar(sampleList, alpha[0])),  10.71f);
	}
	
	
	@Test
	public void Test2(){
		sampleList.add('A'); sampleList.add('B'); sampleList.add('C'); sampleList.add('D');
		sampleList.add('E'); sampleList.add('F'); sampleList.add('G'); sampleList.add('H');
		sampleList.add('I'); sampleList.add('J'); sampleList.add('K'); sampleList.add('L');
		sampleList.add('M'); sampleList.add('N'); sampleList.add('O'); sampleList.add('P');
		sampleList.add('Q'); sampleList.add('R'); sampleList.add('S'); sampleList.add('T');
		sampleList.add('U'); sampleList.add('V'); sampleList.add('W'); sampleList.add('X');
		sampleList.add('Y'); sampleList.add('Z'); sampleList.add('A'); sampleList.add('A');
		
		sampleList.remove(0);
		assertEquals(2, test.occurenceOfChar(sampleList, alpha[0]));	 
		assertEquals(7.4074074, test.percentageOfOccurence(sampleList, test.occurenceOfChar(sampleList, alpha[0])), 7.41f);
		
	}
}
