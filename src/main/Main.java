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
        System.out.println("List of commands:\n\tlist:\tlists all exercises\n\tlog:\tshows the training log\n\tcreate exercise:\tadd a new exercise to db\n\tdiff:\tShows the difference between chosen training and best training\n\tnew training:\tregister a new training session");
      }
      if (input.equals("create exercise")){
        System.out.print("Enter exercise Name:\n>> ");
        String exerciseName = scanner.nextLine();
        System.out.println("Exercise Name: " + exerciseName);
      }
      if (input.equals("diff")){

      }
      if (input.equals("new training")){

      }

      System.out.print(">> ");
    }
  }
}
