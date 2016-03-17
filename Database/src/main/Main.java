package main;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {
  public static void main(String args[]){

    try	{
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/andrris_trening","andrris_db1", "cdji2005");
			System.out.println("connection established");
			startApp(con);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
  }
    
  public static void startApp(Connection con) {
	Scanner scanner = new Scanner(System.in);
    
    String help = "add \t- add new workout\n"+
	          "show \t- show workouts/exercises\n" +
	          "search \t- find exercise\n" +
	          "exit \t- to quit this program\n";
    print(help);

    boolean running = true;
    
    while (running){
      String input = scanner.nextLine();
      switch (input) {
	      case "exit":
	    	  print("See you!");
	    	  running = false;
	          break;
	      case "add":
	    	// Start add new workout
	    	  addWorkout(con, scanner);
	    	  break;
	      case "help":
	    	//list all commands
	          print(help);
	          break;
	      case "search":
	    	  print("Enter your search for traning");
//	    	  search()
	    	  break;
	      default:
	    	  print("Not a valid command, type help to see commands");
	    	  break;
      }
    }
  }
  private static void addWorkout(Connection con, Scanner scanner){
	  
	  boolean addExercise = true;
	  while (addExercise) {
		  print("Which type of exercise would you add?\n" +
				  "type strength or cardio");
		  
	  }
	  
  }
  private static void showWorkout(){

  }
  private static void search(){

  }

  private static void print(Object o){
    System.out.println(o);
  }
}
