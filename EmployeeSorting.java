import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Salary: " + salary;
    }
}

public class EmployeeSorting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < n; i++) {
            System.out.println("\nEmployee " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();

            int age = 0;
            while (true) {
                System.out.print("Age: ");
                String ageInput = sc.nextLine();
                try {
                    age = Integer.parseInt(ageInput);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, enter an integer for age.");
                }
            }

            double salary = 0;
            while (true) {
                System.out.print("Salary: ");
                String salaryInput = sc.nextLine();
                try {
                    salary = Double.parseDouble(salaryInput);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, enter a number for salary.");
                }
            }

            employees.add(new Employee(name, age, salary));
        }

        while (true) {
            System.out.println("\n--- Sort Employees By ---");
            System.out.println("1. Name (Alphabetically)");
            System.out.println("2. Age (Ascending)");
            System.out.println("3. Salary (Descending)");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choiceInput = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Enter a number 1-4.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    employees.sort((e1, e2) -> e1.name.compareToIgnoreCase(e2.name));
                    System.out.println("\nSorted by Name:");
                    employees.forEach(System.out::println);
                }
                case 2 -> {
                    employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
                    System.out.println("\nSorted by Age:");
                    employees.forEach(System.out::println);
                }
                case 3 -> {
                    employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
                    System.out.println("\nSorted by Salary (Descending):");
                    employees.forEach(System.out::println);
                }
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
