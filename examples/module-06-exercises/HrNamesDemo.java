import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HrNamesDemo {
    public static void main(String[] args) {
        // TODO: compose filter → map → sorted → toList
        List<String> hrNames = EmployeeData.sample().stream()
                // TODO: .filter(employee -> employee.department().equals("HR"))
                .filter(employee -> employee.department().equals("HR"))
                // TODO: .map(Employee::name)
                .map(Employee::name)
                // TODO: .sorted()
                .sorted()
                // TODO: .toList()
                .collect(Collectors.toList())
                ;

        System.out.println("HR names: " + hrNames);
    }
}
