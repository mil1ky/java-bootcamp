import java.util.Scanner;   // Scanner lives in java.util — must import it

public class PersonalDetails
{
    public static void main(String[] args) {
        // TODO: create a Scanner reading from System.in
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        // TODO: read the whole line as a String
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        // TODO: read an int with nextInt()
        int age = scanner.nextInt();//next line seperated by different data types
        // TODO: consume the leftover newline after nextInt() (critical!)
        scanner.nextLine();

        System.out.print("Enter your city: ");
        // TODO: read the city with nextLine()
        String city = scanner.nextLine();

        // TODO: print a greeting with printf — %s for strings, %d for age, %n for newline
       System.out.printf("Hello, %s! You are %d years old and live in %s.%n",
               name, age, city);

        scanner.close();
    }
}
