import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

// Student class implementing Serializable interface
class Student implements Serializable {
    // serialVersionUID for version control compatibility
    private static final long serialVersionUID = 1L;
    
    private int studentID;
    private String name;
    private String grade;
    
    // Constructor
    public Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }
    
    // Getters
    public int getStudentID() {
        return studentID;
    }
    
    public String getName() {
        return name;
    }
    
    public String getGrade() {
        return grade;
    }
    
    // toString method for displaying student data
    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}

public class B {
    private static final String FILE_NAME = "student.ser";
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Java Object Serialization with User Input ===");
        System.out.println();
        
        Student student = getStudentFromInput();
        
        if (student != null) {
            System.out.println("\n=== Processing Student Data ===");
            
            serializeStudent(student);
            System.out.println();
            
            Student deserializedStudent = deserializeStudent();
            System.out.println();
            
            if (deserializedStudent != null) {
                displayResults(student, deserializedStudent);
                
                cleanupFile();
            } else {
                System.out.println("✗ Serialization/Deserialization failed!");
            }
        }
        
        scanner.close();
    }
    
    public static Student getStudentFromInput() {
        System.out.println("Enter Student Details:");
        System.out.println("-".repeat(25));
        
        int studentID = getValidStudentID();
        
        String name = getValidName();
        
        String grade = getValidGrade();
        
        Student student = new Student(studentID, name, grade);
        System.out.println("\n✓ Student object created successfully!");
        System.out.println("Student Details: " + student);
        
        return student;
    }
    
    private static int getValidStudentID() {
        int studentID = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print("Enter Student ID (positive integer): ");
                studentID = scanner.nextInt();
                scanner.nextLine(); 
                
                if (studentID > 0) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Student ID must be a positive integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.nextLine();
            }
        }
        return studentID;
    }
    
    private static String getValidName() {
        String name = "";
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print("Enter Student Name: ");
            name = scanner.nextLine().trim();
            
            if (!name.isEmpty() && name.length() >= 2 && name.matches("[a-zA-Z\\s]+")) {
                validInput = true;
            } else {
                System.out.println("Invalid input! Name must contain only letters and spaces (minimum 2 characters).");
            }
        }
        return name;
    }
    
    private static String getValidGrade() {
        String grade = "";
        boolean validInput = false;
        String[] validGrades = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"};
        
        while (!validInput) {
            System.out.print("Enter Student Grade (A+, A, A-, B+, B, B-, C+, C, C-, D+, D, F): ");
            grade = scanner.nextLine().trim().toUpperCase();
            
            for (String validGrade : validGrades) {
                if (grade.equals(validGrade)) {
                    validInput = true;
                    break;
                }
            }
            
            if (!validInput) {
                System.out.println("Invalid input! Please enter a valid grade from the list above.");
            }
        }
        return grade;
    }
    public static void serializeStudent(Student student) {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            
            out.writeObject(student);
            System.out.println("✓ Student object serialized successfully to " + FILE_NAME);
            
            File file = new File(FILE_NAME);
            System.out.println("  File size: " + file.length() + " bytes");
            
        } catch (IOException e) {
            System.err.println("✗ Error during serialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static Student deserializeStudent() {
        Student student = null;
        
        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            
            student = (Student) in.readObject();
            System.out.println("✓ Student object deserialized successfully from " + FILE_NAME);
            
        } catch (FileNotFoundException e) {
            System.err.println("✗ Serialized file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("✗ IO error during deserialization: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("✗ Student class not found during deserialization: " + e.getMessage());
            e.printStackTrace();
        }
        
        return student;
    }
        private static void displayResults(Student original, Student deserialized) {
        System.out.println("=== Verification of Serialization ===");
        System.out.println("Original Student: " + original);
        System.out.println("Deserialized Student: " + deserialized);
        System.out.println();
        
        System.out.println("=== Field-by-Field Verification ===");
        System.out.println("Student ID - Original: " + original.getStudentID() + 
                         ", Deserialized: " + deserialized.getStudentID());
        System.out.println("Name - Original: " + original.getName() + 
                         ", Deserialized: " + deserialized.getName());
        System.out.println("Grade - Original: " + original.getGrade() + 
                         ", Deserialized: " + deserialized.getGrade());
        System.out.println();
        
        System.out.println("✓ Serialization and Deserialization completed successfully!");
    }
    
    private static void cleanupFile() {
        File file = new File(FILE_NAME);
        if (file.exists() && file.delete()) {
            System.out.println("✓ Serialized file cleaned up successfully.");
        }
    }
}
