import java.sql.*;
import java.util.Scanner;

public class Main {
  public static void main(String args[]){
    Scanner scanner = new Scanner(System.in);

    System.out.print("Ready\n>> ");
    while (scanner.hasNext()){
      String input = scanner.nextLine();
      if (input.equals("exit")){

        break;
      }
      if (input.equals("test")){
        System.out.println("Testing complete");
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
