import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Task1 extends Application{
	  /* DECLARING ALL VARIABLES*/ 
	   /** ALL LABELS */
	    Label searchRecord = new Label("Search Student Marks");
        Label studentID1 = new Label("STUDENT_ID: ");
		Label insertRecord = new Label("Insert New Record");
        Label studentID = new Label("STUDENT_ID: ");
		Label name = new Label("NAME: ");
		Label subject = new Label("SUBJECT: ");
		Label session = new Label("SESSION: ");
        Label assignment1 = new Label("ASSIGNMENT 1: ");
		Label assignment2 = new Label("ASSIGNMENT 2");
		Label assignment3 = new Label("ASSIGNMENT 3: ");
		Label exam = new Label("FINAL EXAM: ");
		Label lblStatus = new Label();
		Label subject1 = new Label("SUBJECT: ");
		
		/** ALL TEXT FIELDS */
		TextField txtSearch = new TextField ();
	    TextField studentIDText = new TextField ();
		TextField nameText = new TextField ();;
        TextField subjectText = new TextField();
        TextField sessionText = new TextField();
        TextField assignment1Text = new TextField();
        TextField assignment2Text = new TextField();
        TextField assignment3Text = new TextField();
		TextField examText = new TextField();
        TextField subjectText1 = new TextField();
		
		/** ALL BUTTONS */
		Button btnInsert = new Button("Insert Record");
		Button update = new Button("Update");
		Button searchbt = new Button("SEARCH");
		Button btClear = new Button("Clear");
        Button btCreate1 = new Button("Create Student Table");
        Button btCreate2 = new Button("Create Mark Table");
        Button btInsert = new Button("Insert Student's Record");
        Button btDisplay = new Button("Display Students Record");
        Button btInsertStudent = new Button ("Insert New Student ");
        Button btInsertS = new Button ("Insert");
        Button btView = new Button("Check");
		Button btSearch = new Button("Search Student Record");
        
		
		/* ALL OTHER VARIABLES */
		ObservableList<ObservableList> data;
        TableView tableview;
        
        Statement stmt;
        ResultSet rs1;
        String q1;
        String query;
        
        
		
		
		
  @Override 
  
  /** MAIN STAGE THAT DISPLAYS THE MAIN MENU */
  
  public void start (Stage primaryStage){
	  VBox vBox = new VBox (10);
	  
	  vBox.getChildren().addAll(btCreate1, btCreate2, btInsertStudent, btInsert, btSearch, btDisplay, lblStatus);
	  vBox.setAlignment(Pos.CENTER);
	  BorderPane pane = new BorderPane();
	  pane.setCenter(vBox);
	  
	  
	  
	
	   // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Java Task 1"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
	    initializeDB(); //initialize database connection
	    
		/* on pressing these 2 buttons create tables if they dont exist */
		btCreate1.setOnAction(e -> createStudentTable());
	    btCreate2.setOnAction(e -> createMarkTable());
	    
		
		/** On pressing this button, new window will open which asks user to insert student name and ID */
		btInsertStudent.setOnAction(new EventHandler<ActionEvent>(){
	       @Override public void handle(ActionEvent e) {
        
		    Stage stage4 = new Stage();
            //Fill stage with content
		
		    VBox vBox = new VBox (5);
	  
	        vBox.getChildren().addAll(studentID, studentIDText, name, nameText, btInsertS, lblStatus);
	  
	        BorderPane pane = new BorderPane();
	        pane.setCenter(vBox);
	  
	  
	        // Create a scene and place it in the stage
            Scene scene = new Scene(pane, 600, 600);
            stage4.setTitle("Insert Student's Record"); // Set the stage title
            stage4.setScene(scene); // Place the scene in the stage
    
	
	        btInsertS.setOnAction( v -> insertStudentName()); // this button allows the name and ID to be inserted into database
	        btClear.setOnAction(v -> clear()); //button to clear all fields
            stage4.show();
            }
        });	
	
	    /**On Pressing this button, new window will open which allows user to insert the marks of an existing student */
        btInsert.setOnAction(new EventHandler<ActionEvent>(){
		
		    @Override public void handle(ActionEvent e) {
        
		    Stage stage = new Stage();
            //Fill stage with content
		
		    VBox vBox = new VBox (5);
	  
	        vBox.getChildren().addAll(insertRecord, studentID, studentIDText, btView, name, nameText, subject, subjectText, session, sessionText, assignment1, assignment1Text,assignment2, assignment2Text, assignment3, assignment3Text, exam, examText, btnInsert, btClear, lblStatus);
	
	        BorderPane pane = new BorderPane();
	        pane.setCenter(vBox);
	  
	  
	       // Create a scene and place it in the stage
           Scene scene = new Scene(pane, 600, 600);
           stage.setTitle("Insert Student's Record"); // Set the stage title
           stage.setScene(scene); // Place the scene in the stage
    
	
	       btnInsert.setOnAction( v -> insert()); // button to insert marks and other details in the database
	       btView.setOnAction(v -> view()); //button to view if student exists
	       btClear.setOnAction(v -> clear()); //button to clear all fields
           stage.show();
           }
        });

		/** On pressing this button new window will open which allows user to Search a students record by their name and the subject, also 
		    allows user to update any marks of that subject */
        btSearch.setOnAction(new EventHandler<ActionEvent>(){
	        @Override public void handle(ActionEvent e) {
        
		    Stage stage2 = new Stage();
            //Fill stage with content
		    VBox vBox = new VBox (5);
	  
	        vBox.getChildren().addAll(searchRecord, name, nameText, subject1, subjectText1, searchbt, studentID, studentIDText, subject, subjectText, session, sessionText, assignment1, assignment1Text,assignment2, assignment2Text, assignment3, assignment3Text, exam, examText, update, btClear, lblStatus);
	
	        BorderPane pane = new BorderPane();
	        pane.setCenter(vBox);
	  
	        // Create a scene and place it in the stage
            Scene scene = new Scene(pane, 700, 600);
            stage2.setTitle("Search Students Record"); // Set the stage title
            stage2.setScene(scene); // Place the scene in the stage
            stage2.show(); // Display the stage
	
	
	        searchbt.setOnAction(v -> search()); //button to search record
	        update.setOnAction(v -> update()); //button to update record
	        btClear.setOnAction(v -> clear());
	        }
        });
         
		 /** Pressing this button will open a new window which displays all the records of all students in database ordered by Student ID */
         btDisplay.setOnAction(new EventHandler<ActionEvent>(){  
            @Override public void handle(ActionEvent e) {
        
		    Stage stage3 = new Stage();
            //Fill stage with content
		    tableview = new TableView();
            buildData();

            //Main Scene
            Scene scene = new Scene(tableview);        
            stage3.setTitle("Records of Students in Database");
            stage3.setScene(scene);
            stage3.show();
	
	
	
	        }
        });
}
  
    /**  METHOD TO ESTABLISH DATABASE CONNECTION   */
    private void initializeDB() {
    try {
      // Connect to the local  database
         Connection conn = DriverManager.getConnection
         ("jdbc:mysql://localhost/task1?useSSL=false", "root", "root");
         System.out.println("Database connected\n");

      

         // Create a statement
          stmt = conn.createStatement();
        }
        catch (Exception ex) {
         System.out.print("Error");
        }
    }
  
  
    /** METHOD TO CREATE STUDENT TABLE  */
    private void createStudentTable() {
        try {
			
            String createSt = "CREATE TABLE IF NOT EXISTS `STUDENT` ("+
                              "`STUDENT_ID` INT NOT NULL AUTO_INCREMENT," +
                              "`NAME` VARCHAR(45) NULL," +
                               "PRIMARY KEY (`STUDENT_ID`))"; 
                                                            
            
            
            //Creation statement
            stmt.executeUpdate(createSt);
            
            
            
            
            
            
        } catch (Exception ex) {
            System.out.print("Error");
        }
    }
  
    /**  METHOD TO CREATE MARKS TABLE  */
    private void createMarkTable() {
        try {
            String createStmt = "CREATE TABLE IF NOT EXISTS `MARKS` ("+
           "`STUDENT_ID` INT NOT NULL AUTO_INCREMENT, " +
           "`SUBJECT` VARCHAR(45) NOT NULL, " +
           "`SESSION` VARCHAR(45) NOT NULL, " +
           "`ASSIGNMENT1` INT NULL, " +
           "`ASSIGNMENT2` INT NULL, " +
           "`ASSIGNMENT3` INT NULL, " +
           "`EXAM` INT NULL, " +
		   "`FINAL` DOUBLE NULL, " +
           "PRIMARY KEY (`STUDENT_ID`, `SUBJECT`, `SESSION`), " +
            
            "FOREIGN KEY (`STUDENT_ID`) " +
            "REFERENCES task1.STUDENT(`STUDENT_ID`))";
            //Creation statement
		
            stmt.executeUpdate(createStmt);
            
            
            
            
            
        } 
		    catch (Exception ex) {
            ex.printStackTrace();
            }

	} 
	
	/** METHOD THAT WHEN CALLED INSERTS STUDENTS MARKS INTO DATABASE  */
	private void insert(){
	    int id, score1, score2, score3, finalScore;
        String name, subject, session;
        
        try {
            //Get each of the inputs
            id = Integer.parseInt(this.studentIDText.getText());
			
            subject = this.subjectText.getText();
			session = this.sessionText.getText();
            score1 = Integer.parseInt(this.assignment1Text.getText());
            score2 = Integer.parseInt(this.assignment2Text.getText());
            score3 = Integer.parseInt(this.assignment3Text.getText());
            finalScore = Integer.parseInt(this.examText.getText());
            double g1 = (score1 * 0.1) + (score2 * 0.2) + (score3 * 0.2) + (finalScore * 0.5);      
		
		
            try{
				
				stmt.executeUpdate("INSERT INTO marks VALUES (" +
                         "'"+id+"','"+subject+"','"+session+"','"+score1+"','"+score2+"','"+score3+"','"+finalScore+"','" + g1+"'" +
                         ")");
		        } 
				  
                   catch (SQLException saee) {
                     lblStatus.setText("Student Record Already Exists");
                   }
            }    
			    catch (NumberFormatException ex) {
                System.out.println("Invalid entries! Please try again.");
                }
				lblStatus.setText("Record Inserted");
		
    }
  
  
    /** METHOD THAT WHEN CALLED DISPLAYS THE RECORD OF STUDENT BY PROMPTING USER TO ENTER STUDENT NAME AND SUBJECT */
    private void search(){
    
	   // Build a SQL SELECT statement
          getName();
    try{
		   if (rs1.next()){
           
           query = "SELECT * FROM marks WHERE STUDENT_ID = "+ "'" + rs1.getString(1) + "'"  + "AND SUBJECT = " + "'" + subjectText1.getText().trim() + "'";
		}
	 
        try {
		
	     //rs1 = stmt.executeQuery(q1);
		  
         // Execute query
         ResultSet rs = stmt.executeQuery(query);
         loadToTextField(rs);
        }
        catch(SQLException ex) {
         lblStatus.setText("Select failed: " + ex);
        }
	}
	catch(SQLException ee){
		 ee.printStackTrace();
		 System.out.println("Error");
	 }
    }
  
  /** METHOD CALLED WITHIN SEARCH WHICH GETS THE ID OF STUDENT BY NAME FROM STUDENT TABLE */
  private void getName(){
	  q1 = "Select STUDENT_ID from student where NAME = " + "'" + nameText.getText().trim() + "'";
	  try{
		  
		  rs1 = stmt.executeQuery(q1);
		  
	  }
	  catch(SQLException ex) {
      lblStatus.setText("Select failed: " + ex);
    }
  }
  

  /**Load the record into text fields, WHEN CALLED WITHIN SEARCH*/
  private void loadToTextField(ResultSet rs) throws SQLException {
    if (rs.next()) {
      studentIDText.setText(rs.getString(1));
      
      subjectText.setText(rs.getString(2));
      sessionText.setText(rs.getString(3));
      assignment1Text.setText(rs.getString(4));
	  assignment2Text.setText(rs.getString(5));
	  assignment3Text.setText(rs.getString(6));
      examText.setText(rs.getString(7));
      
      lblStatus.setText("Record found");
    }
    else
      lblStatus.setText("Record not found");
  }
       
	/** METHOD THAT ALLOWS USER TO UPDATE STUDENTS RECORD  */   
	private void update(){
		   int score1 = Integer.parseInt(this.assignment1Text.getText());
            int score2 = Integer.parseInt(this.assignment2Text.getText());
            int score3 = Integer.parseInt(this.assignment3Text.getText());
            int finalScore = Integer.parseInt(this.examText.getText());
		   double g1 = (score1 * 0.1) + (score2 * 0.2) + (score3 * 0.2) + (finalScore * 0.5); 
	    String stmt1= "UPDATE marks " + 
				"SET ASSIGNMENT1 = " + assignment1Text.getText().trim() + "," +
				"ASSIGNMENT2 = " +assignment2Text.getText().trim()+ "," +
				"ASSIGNMENT3 = " +assignment3Text.getText().trim()+ "," +
				"EXAM = " +examText.getText().trim()+ "," +
                "FINAL = " +g1+ "  " +				
                "WHERE STUDENT_ID = '" + studentIDText.getText() + "'"  + "AND SUBJECT = " + "'" + subjectText1.getText().trim() + "'";
		
        try{
				
				stmt.executeUpdate(stmt1);
		    } 
             catch (SQLException saee) {
				 saee.printStackTrace();
                System.out.println("Update FAiled");
            }
			lblStatus.setText("Record Updated");
    }
    
	
	/** METHOD CALLED WITHIN DISPLAY THAT CREATES A TABLE VIEW FOR THE DATABASE RECORD TO BE SHOWN  */
	public void buildData(){
			  
          data = FXCollections.observableArrayList();
          try{
            
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from marks";
            //ResultSet
            ResultSet rs = stmt.executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                tableview.getColumns().addAll(col); 
                
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
      }
	  
	/** METHOD TO CLEAR ALL THE TEXTFIELDS */  
	private void clear() {
       txtSearch.setText(null);
       studentIDText.setText(null);
       nameText.setText(null);
       subjectText.setText(null);
       sessionText.setText(null);
       assignment1Text.setText(null);
       assignment2Text.setText(null);
       assignment3Text.setText(null);
	   examText.setText(null);
	   subjectText1.setText(null);
    }
	
	/** METHOD THAT WHEN CALLED INSERTS STUDENT NAME AND ID IN STUDENT TABLE */
    private void insertStudentName(){
	    int id;
        String name;
        
        try {
            //Get each of the inputs
            id = Integer.parseInt(this.studentIDText.getText());
			name = this.nameText.getText();
                 
		
		
            try{
				
				stmt.executeUpdate("INSERT INTO student VALUES (" +
                         "'"+id+"','"+name+"'" +
                         ")");
						 lblStatus.setText("Student Inserted");
		    } 
             catch (SQLException saee) {
                lblStatus.setText("Student Record Already Exists");
            }
        }         catch (NumberFormatException ex) {
              System.out.println("Invalid entries! Please try again.");
             }
		  
    }
	
	/** METHOD TO VIEW ID THE STUDENT EXISTS IN THE DATABASE */
    private void view(){
	  String query = "SELECT * FROM student WHERE STUDENT_ID = "
      + "'" + studentIDText.getText().trim() + "'";

         try {
          // Execute query
          ResultSet rs = stmt.executeQuery(query);
          load(rs);
        }
         catch(SQLException ex) {
           lblStatus.setText("Select failed: " + ex);
        }
    }
  
    
	/** METHOD TO GET STUDENTS NAME AND ID WHEN SEARCHING FOR STUDENTS RECORD  */
    private void load(ResultSet rs) throws SQLException {
          if (rs.next()) {
             studentIDText.setText(rs.getString(1));
             nameText.setText(rs.getString(2));
      
      
      lblStatus.setText("Record found");
            }
        else
         lblStatus.setText("Record not found");
    }
}