import java.sql.*;
import java.util.Scanner;

public class Main {
  public static void main(String args[]){
    Scanner scanner = new Scanner(System.in);

    System.out.print("Ready\n>> ");
    while (scanner.hasNext()){
      String input = scanner.nextLine();
// Todo / testfunksjoner:
      // Registrere hvilke oevelser man har gjort under en bestemt treningsoekt, samt hvordan selve treningen har gaatt.
      // Holde oversikt over kjente oevelser, sette opp nye maal, og vite hvilke maal man har hatt.
      // Se progresjon for en bestemt oevelse over en periode, samt hvilke maal man har hatt.
      // Se differensen mellom et bestemt resultat og det beste resultatet i loepet av siste uke, maaned eller tre maaneder, samt forskjellen mellom det og maalet som har vaert aktivt i den siste perioden.
      // Kunne kopiere en bestemt treningsoekt over til en ny mal. Hver mal skal kunne registreres med et navn og kunne brukes til aa registrere en ny treningsoekt.
      // Se sammenhengen mellom resultater og sin egen form eller spesifikke treningsforhold.
      // Lese treningsnotater samlet i en logg.
      // Legge til, omorganisere og slette oevelser, grupper og delgrupper.

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
        System.out.println("Not yet implemented.");
      }
      if (input.equals("new training")){
        System.out.println("Not yet implemented.");
      }
      if (input.equals("log")){
        System.out.println("Not yet implemented.");
      }
      System.out.print(">> ");
    }
  }
}
