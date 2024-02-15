import java.util.List;
import java.util.Scanner;

public class Main {
    private static ProductService productService = new ProductServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int input;
        do {
            displayMainMenu();
            input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    displayAllProducts();
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Thank you for using the CLI application.");
                    break;
                default:
                    System.out.println("Your choice is invalid!");
            }
        } while (input != 6);
    }

    private static void displayMainMenu() {
        System.out.println("------------------------");
        System.out.println("Main Menu");
        System.out.println("------------------------");
        System.out.println("1. Add Product");
        System.out.println("2. Change Product");
        System.out.println("3. Delete Product");
        System.out.println("4. See All Product");
        System.out.println("5. Search Product By");
        System.out.println("6. Exit");
    }

    private static void addProduct() {
        System.out.println("Add Product");
        System.out.print("Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Product Brand: ");
        String brand = scanner.nextLine();
        double price = scanner.nextDouble();
        Product product = new Product(name, brand, price);
        productService.addProduct(product);
        System.out.println("Product Successfully Added!");
    }


    private static void displayAllProducts() {
        System.out.println("List Product:");
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No Products are Displayed!");
            System.out.println("1. Return to Main Menu");
            System.out.println("2. Exit");
            System.out.print("Input Your Choice: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                return;
            } else if (choice == 2) {
                System.out.println("Thank you for using the CLI application.");
                System.exit(0);
            }
        } else {
            for (Product product : products) {
                System.out.println("ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Brand: " + product.getBrand());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Created At: " + product.getCreatedAt());
            }
        }
    }
}
