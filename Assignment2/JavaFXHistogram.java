
import java.util.*;
import java.io.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class JavaFXHistogram extends Application  {
	// initiate an ArrayList of characters
    ArrayList<Character> list2 = new ArrayList<Character>(); 
	// create an Array of alphabets 
	String [] alpha = {"A", "B", "C", "D", "E", "F","G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}; 
    // initiate pane
	Pane pane = new Pane();
	
	//initiate textfield 
    TextField textField = new TextField();
	
    VBox box = new VBox();
    
	@Override
    public void start(Stage primaryStage) throws IOException	{
        // create a label    
        Label lblFilename = new Label("Filename:", textField);
        lblFilename.setContentDisplay(ContentDisplay.RIGHT);
		
        textField.setPrefColumnCount(20);
		
		//create button for veiwing histogram
        Button btView = new Button("View");
         
		// add label and button to the something
        HBox hBox = new HBox(lblFilename, btView);
        
		
        // add to scene
        Scene scene = new Scene(box);
		//set Scene
        primaryStage.setScene(scene);
		
		CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis); //inititate bar chart      
	    
		bc.setTitle("Histogram"); //set title to Histogram
		yAxis.setLabel("Frequency"); //set yAxis title to frequency 
		xAxis.setLabel("Alphabets"); // set x axis title to alphabets
		bc.setCategoryGap(0); //remove gap between letters
		bc.setBarGap(0); //remove gap between bars
		box.getChildren().addAll(pane, hBox); //add to Vbox
        
		//when button is clicked, handle the event
		btView.setOnAction(e-> {
		ArrayList<Character> list = new ArrayList<Character>(); //inititate a list
		
        
        try{
			FileReader in = new FileReader(textField.getText()); 
			
			int c;
			while((c = in.read()) != -1){  //read file character by character
			 
				list.add((char)c);				//type cast it to char and add to list
				
				
			} 
		}
		catch (FileNotFoundException fnfe){
			System.out.println("Error Reading The File");
		}
		catch (IOException ioe ){
			System.out.println("Error");
		}
		
		
            //get a list of only upper case letters		
			for (int z = 0; z <list.size(); z++){
			if (String.valueOf(list.get(z)).matches("[A-Z]+")) {
				list2.add(list.get(z));
			}
			}
		 
		    // add data to the histogram
			XYChart.Series series1 =  new XYChart.Series();
			for(int a = 0; a< alpha.length; a++){
			  
			  series1.getData().add(new XYChart.Data(alpha[a], occurenceOfChar(list2, alpha[a]) ));
		}
		Scene scene2 = new Scene (bc); 
		bc.getData().add(series1);
		primaryStage.setScene(scene2); //set scene
			
		});
		
		
           
        primaryStage.show(); 
    }
	
	//method to get occurance of characters
	 public static int  occurenceOfChar (ArrayList<Character> list2, String X){
		   int counting = 0;
	    for (int i=0; i<list2.size(); i++){
		   if (String.valueOf(list2.get(i)).matches(X)) {
			    counting = counting + 1;
		   }
		
	   }
	   return counting;
	   }
}


  

   