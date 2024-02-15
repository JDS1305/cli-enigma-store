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
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    displayAllProducts();
                    break;
                case 5:
                    searchProduct();
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
        System.out.print("Input Product Name: ");
        String name = scanner.nextLine();
        while (!ProductValidator.isProductNameValid(name)) {
            System.out.println("Invalid input! Product name must be between 3 and 50 characters.");
            System.out.print("Input Product Name: ");
            name = scanner.nextLine();
        }

        System.out.print("Input Product Brand: ");
        String brand = scanner.nextLine();
        while (!ProductValidator.isProductBrandValid(brand)) {
            System.out.println("Invalid input! Product brand must be between 3 and 30 characters.");
            System.out.print("Input Product Brand: ");
            brand = scanner.nextLine();
        }

        System.out.print("Input Product Price: ");
        String priceStr = scanner.nextLine();
        while (!ProductValidator.isProductPriceValid(priceStr)) {
            System.out.println("Invalid input! Product price must be a positive number.");
            System.out.print("Enter Product Price: ");
            priceStr = scanner.nextLine();
        }
        double price = Double.parseDouble(priceStr);

        Product product = new Product(name, brand, price);
        productService.addProduct(product);
        System.out.println("Product Successfully Added!");
    }

    private static void updateProduct() {
        System.out.println("Update Product");
        System.out.println("Enter Product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product productToUpdate = productService.getProductById(id);
        if (productToUpdate == null) {
            System.out.println("Product with ID " + id + "not found.");
            return;
        }

        System.out.println("Current Name: " + productToUpdate.getName());
        System.out.println("Enter New Product Name: ");
        String newProductName = scanner.nextLine();
        if (!newProductName.isEmpty() && !ProductValidator.isProductNameValid(newProductName)) {
            System.out.println("Invalid input! Product name must be between 3 and 50 characters.");
            return;
        }

        System.out.println("Current Brand: " + productToUpdate.getName());
        System.out.println("Enter New Brand Name: ");
        String newBrandName = scanner.nextLine();
        if (!newBrandName.isEmpty() && !ProductValidator.isProductBrandValid(newBrandName)) {
            System.out.println("Invalid input! Brand name must be between 3 and 30 characters.");
            return;
        }

        System.out.println("Current Price: " + productToUpdate.getPrice());
        System.out.print("Enter New Price: ");
        String newPriceStr = scanner.nextLine();
        double newPrice;
        if (!newPriceStr.isEmpty()) {
            if (!ProductValidator.isProductPriceValid(newPriceStr)) {
                System.out.println("Invalid input! Product price must be a positive number.");
                return;
            }
            newPrice = Double.parseDouble(newPriceStr);
        } else {
            newPrice = productToUpdate.getPrice(); // Keep current price
        }

        Product updatedProduct = new Product(
                newProductName.isEmpty() ? productToUpdate.getName() : newProductName,
                newBrandName.isEmpty() ? productToUpdate.getBrand() : newBrandName,
                newPrice);

        productService.updateProduct(id, updatedProduct);
        System.out.println("Product Successfully Updated!");
    }

    public static void deleteProduct() {
        System.out.println("Delete Product");
        System.out.println("Enter Product ID: ");
        String inputId = scanner.nextLine().trim();

        if (inputId.isEmpty()) {
            System.out.println("No product ID entered.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(inputId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid product ID entered. Please enter a valid integer ID.");
            return;
        }

        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            System.out.println("Product with ID " + id + " deleted successfully.");
        } else {
        }
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
                System.out.println("------------------------");
            }
        }
    }

    private static void searchProduct() {
        System.out.println("Search Product");
        System.out.println("Enter Keyword: ");
        String keyword = scanner.nextLine();

        List<Product> searchResult = productService.searchProducts(keyword);
        if (searchResult.isEmpty()) {
            System.out.println("No products found matching the keyword '" + keyword + "'.");
            System.out.println("1. Enter a new keyword");
            System.out.println("2. Return to Main Menu");
            System.out.println("3. Exit");
            System.out.print("Input Your Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    searchProduct();
                    break;
                case 2:
                    return;
                case 3:
                    System.out.println("Thank you for using the CLI application.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter 1, 2 or 3.");
            }
        } else {
            System.out.println("Search Result:");
            for (Product product : searchResult) {
                System.out.println("ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Brand: " + product.getBrand());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Created At: " + product.getCreatedAt());
                System.out.println("------------------------");
            }
        }
    }
}