import java.util.Scanner;

public class LoopsDemo {
    public static void main(String[] args) {
        // TODO: for loop — print multiplication table for 5 (5 x 1 = 5 … 5 x 5 = 25)
        System.out.println("Multiplication table for 5:");
         for (int i = 1; i <= 5 ; i++)
         {
             int product = 5 * i;
             System.out.println("5 x " + i + " = " + product);
         }

        // TODO: while loop — countdown from 3 to 1 (remember to change count each pass)
        int count = 3;
         while (count > 0 )
         {
             System.out.printf("\nCountdown: " + count);
             count--;
         }

        // TODO: do-while — prompt until the user types something other than "menu"
        Scanner scanner = new Scanner(System.in);
        String choice;
       do {
           System.out.print("\nType 'menu' to see it again, anything else to quit: ");
           choice = scanner.nextLine();
           if (choice.equals("menu")) {
               System.out.println("1) Add  2) Withdraw  3) Exit");
           }
       } while (choice.equals("menu"));

        scanner.close();
    }
}