import java.util.Scanner;

public class PersonalProfile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Name: ");
        // TODO: read name
        String name = scanner.nextLine();

        System.out.print("Age: ");
        // TODO: read age as String (simple table demo — no parse required)
        String age = scanner.nextLine();

        System.out.print("City: ");
        // TODO: read city
        String city = scanner.nextLine();

        System.out.print("Hobby: ");
        // TODO: read hobby
        String hobby = scanner.nextLine();

        System.out.println();
        // TODO: print a two-column table with printf width specifiers
        //   header: Field | Value  using %-12s and %-20s
        //   separator line
        //   four rows: Name, Age, City, Hobby
        System.out.printf("%-12s | %-20s%n", "Field", "Value");
        System.out.println("-------------------------------");
        System.out.printf("%-12s | %-20s%n", "Name", name);
        System.out.printf("%-12s | %-20s%n", "Age", age);
        System.out.printf("%-12s | %-20s%n", "City", city);
        System.out.printf("%-12s | %-20s%n", "Hobby", hobby);

        scanner.close();
    }
}
