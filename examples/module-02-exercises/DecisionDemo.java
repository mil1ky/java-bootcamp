import com.sun.source.tree.SwitchTree;

import java.lang.invoke.SwitchPoint;
import java.util.Scanner;

public class DecisionDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Score (0-100): "); // Takes in user input in java
        int score = Integer.parseInt(scanner.nextLine());//Saves user input  as int score

        // TODO: if / else if / else — print Grade: A/B/C/F
        //   A: score >= 90
        //   B: score >= 80
        //   C: score >= 70
        //   F: otherwise
        //   (branch order matters — first true wins)
        if (score >= 90)
        {
            System.out.printf("Grade: A ");

        }
        else if (score >= 80 && score < 90)
        {
            System.out.printf("Grade: B ");
        }
        else if (score >= 70 && score < 80)
        {
            System.out.printf("Grade: c ");
        }
        else
        {
            System.out.printf("Grade: F ");
        }

        System.out.print("\nDay number (1-7): "); //For new line embed in quotes
        int day = Integer.parseInt(scanner.nextLine());

        // TODO: switch on day — print Monday..Sunday for 1..7
        //   use arrow form: case 1 -> System.out.println("Monday");
        //   default -> System.out.println("Not a valid day");
        switch (day)
        {
            case 1:
            System.out.printf("Monady");
            break;

            case 2:
                System.out.printf("Tuesday");
                break;

            case 3:
                System.out.printf("Wednesday");
                break;

            case 4:
                System.out.printf("Thursday");
                break;

            case 5:
                System.out.printf("Friday");
                break;

            case 6:
                System.out.printf("Saturday");
                break;

            case 7:
                System.out.printf("Sunday");
                break;

            default:
                System.out.printf("Not a valid day! ");
        }

        scanner.close();
    }
}