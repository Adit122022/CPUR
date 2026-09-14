import java.util.*;

/**
 * Q9: Sort a list of students by marks (descending).
 *     If marks are equal, sort by name (ascending — alphabetical).
 *
 * Uses a multi-key Comparator:
 *   Comparator.comparingInt(Student::getMarks).reversed()
 *             .thenComparing(Student::getName)
 */
public class Q9_SortStudents {

    // ---------- Student model ----------
    static class Student {
        private final String name;
        private final int    marks;

        Student(String name, int marks) {
            this.name  = name;
            this.marks = marks;
        }

        public String getName()  { return name;  }
        public int    getMarks() { return marks; }

        @Override
        public String toString() {
            return String.format("%-12s %d", name, marks);
        }
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Priya",   88),
            new Student("Aditya",  95),
            new Student("Zara",    88),
            new Student("Rohan",   72),
            new Student("Meera",   95),
            new Student("Karan",   80),
            new Student("Amit",    72),
            new Student("Sneha",   88)
        );

        System.out.println("Original list:");
        printTable(students);

        // Comparator: marks descending, then name ascending
        Comparator<Student> comparator =
            Comparator.comparingInt(Student::getMarks)
                      .reversed()
                      .thenComparing(Student::getName);

        students.sort(comparator);

        System.out.println("\nSorted (marks ↓, name ↑):");
        printTable(students);
    }

    private static void printTable(List<Student> list) {
        System.out.println(String.format("  %-12s %s", "Name", "Marks"));
        System.out.println("  " + "-".repeat(20));
        list.forEach(s -> System.out.println("  " + s));
    }
}
