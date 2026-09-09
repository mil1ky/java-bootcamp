import java.util.List;
import java.util.stream.Collectors;

public class RaiseDemo {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeData.sample();

        // TODO: stream pipeline — map each salary to salary * 1.10, collect to List<Double>
        List<Double> proposedSalaries = employees.stream()
                // TODO: .map(employee -> employee.salary() * 1.10)
                .map(employee -> employee.salary() * 1.10)
                // TODO: .toList()
                .collect(Collectors.toList())
                ;

        System.out.println("Proposed salaries:");
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            double proposed = proposedSalaries.get(i);
            System.out.printf("%s: %.2f -> %.2f%n",
                    employee.name(), employee.salary(), proposed);
        }

        System.out.printf("Alice original salary: %.2f%n",
                employees.get(0).salary());
    }
}