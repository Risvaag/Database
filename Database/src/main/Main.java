package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Main {
  public static void main(String args[]){

    try	{
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/andrris_trening","andrris_db1", "cdji2005");
			System.out.println("Connection Established\n");
			startApp(con);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
  }
  
  
  private static Date handleTimestamp(String dateString){
	 SimpleDateFormat format = new SimpleDateFormat("dd:MM:yyyy");
	 try {
		java.util.Date parsed = format.parse(dateString);
		return new Date(parsed.getTime());
	} catch (ParseException e) {
		e.printStackTrace();
	}
	 System.out.println("null value!");
	 return null;
  }
  

  private static void printExercises(ResultSet res){
	  try {
		  int i = 0;
		  System.out.println("ID\t:\tName");
		while(res.next()){
			i++;
			  System.out.println(res.getString("id")+"\t:\t" + res.getString("navn"));
		  }
	} catch (SQLException e) {
		e.printStackTrace();
	}
  }

  private static int selectExercise(Scanner scanner){
	  System.out.println("\nSelect the ID of the exercise you wish to add: ");
	  try{
		  int value = scanner.nextInt();
		  scanner.nextLine();
		  return value;
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  return -1;
  }

  private static void addExercises(ResultSet results, Scanner scanner, List<Integer> exercises){
	  printExercises(results);
		int id = selectExercise(scanner);
		if(id == -1){
			//fuck this
		}else{
			exercises.add(id);
		}
  }
  
  public static void addResult(Connection con, Scanner scanner){
	  
  }

  public static void startApp(Connection con) {
	Scanner scanner = new Scanner(System.in);

    String help = "\n1. add workout\t- add new workout\n"+
    		"2. add exercise\t- add new workout\n"+
	          "3. help\t\t- show all commands\n" +
	          "4. search\t- find exercises\n" +
	          "5. exit\t\t- quit this program\n"+
            "6. log \t\t- write a new log entry\n"+
            "7. show log \t- Show the log";
    System.out.println(help);

    boolean running = true;

    while (running){
      System.out.print(">> ");
      String input = scanner.nextLine();
      switch (input) {
	      case "exit":
	    	  System.out.println("See you!");
	    	  running = false;
	          break;
	      case "add exercise":
	    	// Start add new workout
	    	  System.out.println("Enter the ID of the workout you want to edit");
	    	  addExercise(con, scanner,scanner.nextInt());
	    	  break;
	      case "add workout":
	    	  //TODO add workouts
	    	  addWorkout(con, scanner);
	    	  break;
	      case "help":
	    	//list all commands
	          System.out.println(help);
	          break;
	      case "search":
	    	  System.out.println("Enter your search: ");
	    	  String seek = scanner.nextLine();
	    	  search(con, seek, scanner);
	    	  break;
        case "log":
          System.out.println("New log. Press enter to send.");
          String text = scanner.nextLine();
          newLogEntry(text);
          break;
        case "show log":
          String list = getLogEntries();
          System.out.println("List of all log elements."+ list);
          break;
	      default:
	    	  System.out.println("Not a valid command, type help to see available commands");
	    	  break;
      }
    }
  }
  private static void newLogEntry(String string){
    
  }
  private static String getLogEntries(){
    String entries = "A lot of entries";
    return entries;
  }
  
  
  private static void addWorkout(Connection con, Scanner scanner)
  {
	  String note = "'No note added'";
	  System.out.println("What is the desired duration of the workout? ");
	  System.out.println(">> ");
	  float duration = scanner.nextFloat();
	  scanner.nextLine();
	  System.out.println("What date is the workout on? [dd:MM:yyyy] \n>> ");
	  String day = scanner.nextLine();
	  //System.out.println("What time is the workout on? [hh:mm] \n>> ");
	  //String time = scanner.nextLine();
	  Date date = handleTimestamp(day);//+" "+time+":"+"00.000");
	  System.out.println("add a note? [y/n] ");
	  String response = scanner.nextLine().toLowerCase();
	  if(response.equals("y") || response.equals("yes")){
		  System.out.println("Enter note (only one line): ");
		  System.out.println(">> ");
		  note = "'"+scanner.nextLine()+"'";
	  }
	  System.out.println(date);
	  try{
		  Statement s = con.createStatement();
		  System.out.println();
		  s.executeUpdate("INSERT INTO Trening (dato, varighet, notat) VALUES ('"+date+"',"+duration+","+ note +")");
	  }catch(SQLException e){
		  e.printStackTrace();
	  }
	//new Date()  
  }
  
  
  private static void addExercise(Connection con, Scanner scanner, int workout){
	  scanner.nextLine();
	  //TODO get next training ID
	  
	  List<Integer> exercises = new ArrayList<>();
	  while (true) {
		  //TODO make this not shitty
		  System.out.println("Add another exercise? [y/n]");
		  String response = scanner.nextLine().toLowerCase();

		  if(response.equals("y")|| response.equals("yes")){
        System.out.println("Which type of exercise would you add?\n" +
					  "1.\tCardio\n2.\tStrength\n3.\tEndurance");
			  boolean done = false;
			  while(done == false){
				  switch(scanner.nextLine().toLowerCase()){
			  		case "1":
			  			done = true;
			  			ResultSet results = Querries.getOving("cardio", con);
			  			addExercises(results, scanner, exercises);
			  			break;
			  		case "2":
			  			done=true;
			  			ResultSet results2 = Querries.getOving("strength", con);
			  			addExercises(results2, scanner, exercises);
			  			//StrengthExercise(con, scanner);
			  			break;
			  		case "3":
			  			done = true;
			  			ResultSet results3 = Querries.getOving("endurance", con);
			  			addExercises(results3, scanner, exercises);
			  			//EnduranceExercise(con, scanner);
			  			break;
			  		default:
			  			System.out.println("please select strength, endurance or cardio");
			  			break;
			  	}
			  }
		  }
		  else if(response.equals("n") || response.equals("no")){
			  for(int i = 0; i < exercises.size(); i++){
				  try{
					  Statement s = con.createStatement();
					  s.executeUpdate("INSERT INTO TreningsOvelse (trening_id, ovelse_id) VALUES("+ workout+", "+ exercises.get(i) +")");
				  }catch(SQLException e)
				  {
					  e.printStackTrace();
				  }
				  System.out.println(exercises.get(i));
			  }
			  break;
		  }
	  }
  }

  private static void StrengthExercise(Connection con, Scanner scanner) {
	  System.out.println("Name of exercise: ");
	  String name = scanner.nextLine();

	  System.out.println("Total reps: ");
	  int reps = scanner.nextInt();
	  scanner.nextLine();

	  System.out.println("Total sets: ");
	  int sets = scanner.nextInt();
	  scanner.nextLine();

	  System.out.println("Total weight: ");
	  int weight = scanner.nextInt();
	  scanner.nextLine();

  }

  private static void CardioExercise(Connection con, Scanner scanner) {
	  System.out.println("Name of exercise: ");
	  String name = scanner.nextLine();

	  System.out.println("Total reps: ");
	  int reps = scanner.nextInt();
	  scanner.nextLine();

	  System.out.println("Total sets: ");
	  int sets = scanner.nextInt();
	  scanner.nextLine();

	  System.out.println("Total weight: ");
	  int weight = scanner.nextInt();
	  scanner.nextLine();
  }

  private static void EnduranceExercise(Connection con, Scanner scanner) {
	  System.out.println("Name of exercise: ");
	  String name = scanner.nextLine();

	  System.out.println("Total length: ");
	  int length = scanner.nextInt();
	  scanner.nextLine();

	  System.out.println("Total time: ");
	  int time = scanner.nextInt();
	  scanner.nextLine();
  }

  private static void search(Connection con, String seek, Scanner scanner){
    System.out.println("Searching...");
    System.out.println("Sorry, can't find anything.");
  }

}
