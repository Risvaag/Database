package main;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {
  public static void main(String args[]){

    try{
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/andrris_trening","andrris_db1", "cdji2005");
			System.out.println("connection established");
			// return con;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			// return null;
		}

    Scanner scanner = new Scanner(System.in);

    System.out.print("Ready\n>> ");
    while (scanner.hasNext()){
      String input = scanner.nextLine();
      if (input.equals("exit")){
        print("See you!");
        break;
      }
      if (input.equals("help")){
        //list all commands
        String help = "add - add new workout\n"+
        "show - show workouts/exercises\n" +
        "search - find exercise\n" +
        "exit - to quit this program";
        print(help);
      }



      System.out.print(">> ");
    }
  }
  private static void addWorkout(){

  }
  private static void showWorkout(){

  }
  private static void search(){

  }

  private static void print(Object o){
    System.out.println(o);
  }
}
