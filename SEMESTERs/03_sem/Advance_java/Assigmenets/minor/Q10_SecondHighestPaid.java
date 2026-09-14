import java.util.*;
import java.util.stream.*;

/**
 * Q10: Read employee data from a "database" (simulated with a List), store it in a List,
 *      and use Streams + Comparator to find the 2nd highest paid employee.
 *
 * In a real application you would replace the `loadEmployeesFromDB()` method with
 * a JDBC / JPA call.  Here we simulate the DB with an in-memory list.
 *
 * Stream pipeline:
 *   employees
 *     .stream()
 *     .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
 *     .distinct(by salary)     ← skip(1) handles equal-salary edge case
 *     .skip(1)                 ← skip the highest paid
 *     .findFirst()             ← take the next one
 */
public class Q10_SecondHighestPaid {

    // ---------- Employee model ----------
    static class Employee {
        private final int    id;
        private final String name;
        private final String department;
        private final double salary;

        Employee(int id, String name, String department, double salary) {
            this.id         = id;
            this.name       = name;
            this.department = department;
            this.salary     = salary;
        }

        public int    getId()         { return id;         }
        public String getName()       { return name;       }
        public String getDepartment() { return department; }
        public double getSalary()     { return salary;     }

        @Override
        public String toString() {
            return String.format("ID:%-3d %-12s %-12s $%,.2f",
                    id, name, department, salary);
        }
    }

    // ---------- Simulated DB fetch ----------
    /**
     * Simulates reading employee records from a database.
     * Replace this method body with real JDBC / JPA / Hibernate code in production.
     */
    static List<Employee> loadEmployeesFromDB() {
        // Simulate DB rows
        return Arrays.asList(
            new Employee(1,  "Aditya",   "Engineering", 95_000.00),
            new Employee(2,  "Priya",    "Marketing",   72_000.00),
            new Employee(3,  "Rohan",    "Engineering", 1_20_000.00),
            new Employee(4,  "Meera",    "HR",          68_000.00),
            new Employee(5,  "Karan",    "Engineering", 1_10_000.00),
            new Employee(6,  "Sneha",    "Finance",     85_000.00),
            new Employee(7,  "Zara",     "Marketing",   78_000.00),
            new Employee(8,  "Amit",     "Finance",     1_10_000.00),  // tied salary
            new Employee(9,  "Vikram",   "Engineering", 1_00_000.00),
            new Employee(10, "Divya",    "HR",          62_000.00)
        );
    }

    // ---------- Core logic using Streams ----------

    /**
     * Returns the employee with the 2nd highest DISTINCT salary.
     * (Employees earning the same highest salary are treated as tied for 1st.)
     */
    static Optional<Employee> secondHighestPaid(List<Employee> employees) {
        return employees.stream()
            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
            // Group by distinct salary: skip all employees sharing the top salary
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    // Find the highest salary value
                    double topSalary = list.stream()
                            .mapToDouble(Employee::getSalary)
                            .max()
                            .orElse(Double.MIN_VALUE);

                    // Among remaining employees (salary < top), find the highest
                    return list.stream()
                            .filter(e -> e.getSalary() < topSalary)
                            .max(Comparator.comparingDouble(Employee::getSalary));
                }
            ));
    }

    public static void main(String[] args) {
        // Step 1: Load from DB
        List<Employee> employees = loadEmployeesFromDB();

        // Step 2: Display all employees (sorted by salary desc)
        System.out.println("=== Employee Database ===");
        System.out.printf("  %-5s %-12s %-12s %s%n", "ID", "Name", "Dept", "Salary");
        System.out.println("  " + "-".repeat(45));
        employees.stream()
                 .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                 .forEach(e -> System.out.println("  " + e));

        // Step 3: Find 2nd highest paid
        Optional<Employee> result = secondHighestPaid(employees);

        System.out.println("\n=== Result ===");
        if (result.isPresent()) {
            System.out.println("2nd Highest Paid Employee:");
            System.out.println("  " + result.get());
        } else {
            System.out.println("Not enough distinct salary levels.");
        }

        // Bonus: show the top salary(ies) too
        double topSalary = employees.stream()
                .mapToDouble(Employee::getSalary).max().orElse(0);
        System.out.println("\nHighest salary: $" + String.format("%,.2f", topSalary));
        employees.stream()
                 .filter(e -> e.getSalary() == topSalary)
                 .forEach(e -> System.out.println("  → " + e));
    }
}
