import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Marks: " + marks;
    }
}

public class StudentFilterSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = 0;
        while (true) {
            String input = sc.nextLine();
            try {
                n = Integer.parseInt(input);
                if (n > 0) break;
                else System.out.println("Enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, enter an integer.");
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();

            double marks = 0;
            while (true) {
                System.out.print("Marks (0-100): ");
                String markInput = sc.nextLine();
                try {
                    marks = Double.parseDouble(markInput);
                    if (marks >= 0 && marks <= 100) break;
                    else System.out.println("Enter marks between 0 and 100.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, enter a number.");
                }
            }

            students.add(new Student(name, marks));
        }

        List<String> filteredSortedNames = students.stream()
                .filter(s -> s.marks > 75)
                .sorted(Comparator.comparingDouble(s -> s.marks))
                .map(s -> s.name)
                .collect(Collectors.toList());

        System.out.println("\nStudents scoring above 75% (sorted by marks):");
        if (filteredSortedNames.isEmpty()) {
            System.out.println("No students scored above 75%.");
        } else {
            filteredSortedNames.forEach(System.out::println);
        }
    }
}
