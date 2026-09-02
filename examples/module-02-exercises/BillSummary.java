import java.util.Scanner;

public class BillSummary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Product name: ");
        // TODO: read name
        String name = scanner.nextLine();

        System.out.print("Quantity: ");
        // TODO: read qty (nextLine + Integer.parseInt)
        int qty = Integer.parseInt(scanner.nextLine());

        System.out.print("Unit price: ");
        // TODO: read price (nextLine + Double.parseDouble)
        double price = Double.parseDouble((scanner.nextLine()));

        // TODO: compute total, 10% discount, and final amount
        double total = qty * price;           // qty * price
        double discount = .10 * total;        // 10% of total
        double finalAmount = total - discount;     // total - discount

        System.out.println("--- Bill Summary ---");
        // TODO: print Product, Quantity, Unit price, Total, Discount (10%), Final amount
        // hints: %.2f for money; use 10%% in the format string to print a literal %
        System.out.printf("Product: %s%nQuantity: %d%nUnit price: $%.2f%nTotal: $%.2f%nDiscount (10%%): $%.2f%nFinal amount: $%.2f%n"
                , name, qty, price, total, discount, finalAmount);

        scanner.close();
    }
}