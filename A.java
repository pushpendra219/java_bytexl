import java.util.*;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers (type 'done' to finish):");
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("done")) break;
            try {
                Integer num = Integer.parseInt(input);
                numbers.add(num);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter an integer.");
            }
        }

        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }

        System.out.println("Sum of integers: " + sum);
    }
}
