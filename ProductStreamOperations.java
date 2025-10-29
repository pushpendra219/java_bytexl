import java.util.*;
import java.util.stream.*;
import java.util.Map.Entry;

class Product {
    String name;
    double price;
    String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Price: " + price + ", Category: " + category;
    }
}

public class ProductStreamOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        System.out.print("Enter number of products: ");
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

        // Input product details
        for (int i = 0; i < n; i++) {
            System.out.println("\nProduct " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();

            double price = 0;
            while (true) {
                System.out.print("Price: ");
                String priceInput = sc.nextLine();
                try {
                    price = Double.parseDouble(priceInput);
                    if (price >= 0) break;
                    else System.out.println("Enter a non-negative price.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, enter a number.");
                }
            }

            System.out.print("Category: ");
            String category = sc.nextLine();

            products.add(new Product(name, price, category));
        }

        // 1. Group products by category
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));

        System.out.println("\n--- Products Grouped by Category ---");
        grouped.forEach((cat, list) -> {
            System.out.println("Category: " + cat);
            list.forEach(System.out::println);
        });

        // 2. Most expensive product in each category
        Map<String, Optional<Product>> mostExpensive = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
                ));

        System.out.println("\n--- Most Expensive Product in Each Category ---");
        mostExpensive.forEach((cat, prodOpt) -> {
            prodOpt.ifPresent(prod -> System.out.println("Category: " + cat + " -> " + prod));
        });

        // 3. Average price of all products
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage price of all products: " + avgPrice);
    }
}
