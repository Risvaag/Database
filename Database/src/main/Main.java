package main;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
  
  private static void printExercises(ResultSet res){
	  try {
		  int i = 0;
		while(res.next()){
			i++;
			  System.out.println(res.getString("id")+" : " + res.getString("navn"));
		  }
	} catch (SQLException e) {
		e.printStackTrace();
	}
  }
  
  private static int selectExercise(Scanner scanner){
	  System.out.println("Select the ID of the exercise you wish to add: ");
	  try{
		  return scanner.nextInt();
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  return -1;
  }
    
  public static void startApp(Connection con) {
	Scanner scanner = new Scanner(System.in);
    
    String help = "\nadd\t- add new workout\n"+
	          "help\t- show all commands\n" +
	          "search\t- find exercises\n" +
	          "exit\t- to quit this program\n";
    System.out.println(help);

    boolean running = true;
    
    while (running){
      String input = scanner.nextLine();
      switch (input) {
	      case "exit":
	    	  System.out.println("See you!");
	    	  running = false;
	          break;
	      case "add":
	    	// Start add new workout
	    	  addWorkout(con, scanner);
	    	  break;
	      case "help":
	    	//list all commands
	          System.out.println(help);
	          break;
	      case "search":
	    	  System.out.println("Enter your search: ");
	    	  search(con);
	    	  break;
	      default:
	    	  System.out.println("Not a valid command, type help to see commands");
	    	  break;
      }
    }
  }
  private static void addWorkout(Connection con, Scanner scanner){
	  
	  List<Integer> exercises = new ArrayList<>();
	  while (true) {
		  //TODO make this not shitty
		  System.out.println("Add another exercise? [y/n]");
		  String response = scanner.nextLine().toLowerCase();
		  
		  if(response.equals("y")|| response.equals("yes")){
			  System.out.println("Which type of exercise would you add?\n" +
					  "type strength, endurance or cardio"); 
			  boolean done = false;
			  while(done == false){
				  switch(scanner.nextLine().toLowerCase()){
			  		case "cardio":
			  			done = true;
			  			ResultSet results = Querries.getOving("cardio", con);
			  			printExercises(results);
			  			int id = selectExercise(scanner);
			  			if(id == -1){
			  				//fuck this
			  			}else{
			  				exercises.add(id);
			  			}
			  			break;
			  		case "strength":
			  			done=true;
			  			//StrengthExercise(con, scanner);
			  			break;
			  		case "endurance":
			  			done = true;
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
	  
	  System.out.println("Total sets: ");
	  int sets = scanner.nextInt();
	  
	  System.out.println("Total weight: ");
	  int weight = scanner.nextInt();
	  
  }
  
  private static void CardioExercise(Connection con, Scanner scanner) {
	  ResultSet results = Querries.getOving("cardio", con);
	  
	  System.out.println("Name of exercise: ");
	  String name = scanner.nextLine();
	  
	  System.out.println("Total reps: ");
	  int reps = scanner.nextInt();
	  
	  System.out.println("Total sets: ");
	  int sets = scanner.nextInt();
	  
	  System.out.println("Total weight: ");
	  int weight = scanner.nextInt();
  }
  
  private static void EnduranceExercise(Connection con, Scanner scanner) {
	  System.out.println("Name of exercise: ");
	  String name = scanner.nextLine();
	  
	  System.out.println("Total length: ");
	  int length = scanner.nextInt();
	  
	  System.out.println("Total time: ");
	  int time = scanner.nextInt();
  }
  
//  private static void showWorkout(){
//
//  }
  private static void search(Connection con){

  }

}
