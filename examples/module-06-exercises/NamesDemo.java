import java.util.List;
import java.util.stream.Collectors;

public class NamesDemo {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeData.sample();

        // TODO: stream pipeline — map each Employee to its name, collect to List<String>
        List<String> names = employees.stream()
                // TODO: .map(Employee::name)  (or equivalent lambda)
                .map(Employee::name)

                // TODO: .toList()
                .collect(Collectors.toList())
                ;

        System.out.println("Employee names:");
        names.forEach(System.out::println);
    }
}