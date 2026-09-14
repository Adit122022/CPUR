/**
 * 9. Write a program to sort a list of students by marks (descending). If marks are equal, sort by name.
 *     If marks are equal, sort by name (ascending — alphabetical).
 *
 * Uses a multi-key Comparator:
 *   Comparator.comparingInt(Student::getMarks).reversed()
 *             .thenComparing(Student::getName)
 */
import java.util.*;

public class Q9_SortStudents {

    static class Student {
        String name;
        int marks;

        Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return name + " " + marks;
        }
    }

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
            new Student("Priya", 88),
            new Student("Aditya", 95),
            new Student("Zara", 88),
            new Student("Rohan", 72),
            new Student("Meera", 95),
            new Student("Karan", 80),
            new Student("Amit", 72),
            new Student("Sneha", 88)
        );

        students.sort( Comparator.comparingInt((Student s) -> s.marks).reversed().thenComparing(s -> s.name) );

        for (Student s : students) {
            System.out.println(s);
        }
    }
}