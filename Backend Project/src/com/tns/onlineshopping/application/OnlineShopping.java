package com.tns.onlineshopping.application;

import com.tns.onlineshopping.entities.Admin;
import com.tns.onlineshopping.entities.Customer;
import com.tns.onlineshopping.entities.Order;
import com.tns.onlineshopping.entities.Product;
import com.tns.onlineshopping.services.AdminService;
import com.tns.onlineshopping.services.CustomerService;
import com.tns.onlineshopping.services.OrderService;
import com.tns.onlineshopping.services.ProductService;
import java.util.List;
import java.util.Scanner;

public class OnlineShopping {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductService productService = new ProductService();
    private static final CustomerService customerService = new CustomerService();
    private static final OrderService orderService = new OrderService();
    private static final AdminService adminService = new AdminService();
    private static int nextOrderId = 1;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n1. Admin Menu\n2. Customer Menu\n3. Exit");
            switch (readInt("Choose an option: ")) {
                case 1 -> adminMenu();
                case 2 -> customerMenu();
                case 3 -> {
                    running = false;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid menu choice.");
            }
        }
        scanner.close();
    }

    private static void adminMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\nAdmin Menu:\n1. Add Product\n2. Remove Product\n3. View Products"
                    + "\n4. Create Admin\n5. View Admins\n6. Update Order Status\n7. View Orders\n8. Return");
            switch (readInt("Choose an option: ")) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> viewProducts();
                case 4 -> createAdmin();
                case 5 -> viewAdmins();
                case 6 -> updateOrderStatus();
                case 7 -> viewAllOrders();
                case 8 -> {
                    inMenu = false;
                    System.out.println("Exiting Admin...");
                }
                default -> System.out.println("Invalid menu choice.");
            }
        }
    }

    private static void customerMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\nCustomer Menu:\n1. Create Customer\n2. View Customers\n3. Place Order"
                    + "\n4. View Orders\n5. View Products\n6. Return");
            switch (readInt("Choose an option: ")) {
                case 1 -> createCustomer();
                case 2 -> viewCustomers();
                case 3 -> placeOrder();
                case 4 -> viewCustomerOrders();
                case 5 -> viewProducts();
                case 6 -> {
                    inMenu = false;
                    System.out.println("Exiting Customer Menu...");
                }
                default -> System.out.println("Invalid menu choice.");
            }
        }
    }

    private static void addProduct() {
        int id = readPositiveInt("Enter Product ID: ");
        String name = readNonEmptyString("Enter Product Name: ");
        double price = readNonNegativeDouble("Enter Product Price: ");
        int stock = readPositiveInt("Enter Stock Quantity: ");
        if (productService.addProduct(new Product(id, name, price, stock)))
            System.out.println("Product added successfully!");
        else
            System.out.println("A product with that ID already exists.");
    }

    private static void removeProduct() {
        int id = readPositiveInt("Enter Product ID: ");
        System.out.println(productService.removeProduct(id) ? "Product removed successfully!" : "Invalid product ID.");
    }

    private static void createAdmin() {
        int id = readPositiveInt("Enter User ID: ");
        if (userIdExists(id)) {
            System.out.println("A user with that ID already exists.");
            return;
        }
        String username = readNonEmptyString("Enter Username: ");
        String email = readNonEmptyString("Enter Email: ");
        adminService.addAdmin(new Admin(id, username, email));
        System.out.println("Admin created successfully!");
    }

    private static void createCustomer() {
        int id = readPositiveInt("Enter User ID: ");
        if (userIdExists(id)) {
            System.out.println("A user with that ID already exists.");
            return;
        }
        String username = readNonEmptyString("Enter Username: ");
        String email = readNonEmptyString("Enter Email: ");
        String address = readNonEmptyString("Enter Address: ");
        customerService.addCustomer(new Customer(id, username, email, address));
        System.out.println("Customer created successfully!");
    }

    private static void placeOrder() {
        Customer customer = customerService.getCustomer(readPositiveInt("Enter Customer ID: "));
        if (customer == null) {
            System.out.println("Invalid customer ID.");
            return;
        }
        Order order = new Order(nextOrderId, customer);
        while (true) {
            int productId = readInt("Enter Product ID to add to order (or -1 to complete): ");
            if (productId == -1)
                break;
            if (productId <= 0) {
                System.out.println("Product ID must be positive or -1 to finish.");
                continue;
            }
            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("Invalid product ID.");
                continue;
            }
            int quantity = readPositiveInt("Enter quantity: ");
            order.addProduct(product, quantity);
            customer.getShoppingCart().addItem(product, quantity);
        }
        if (order.getProducts().isEmpty()) {
            System.out.println("Cannot place an order with no products.");
            return;
        }
        orderService.placeOrder(order);
        customer.addOrder(order);
        customer.getShoppingCart().getItems().clear();
        nextOrderId++;
        System.out.println("Order placed successfully!");
    }

    private static void updateOrderStatus() {
        int id = readPositiveInt("Enter Order ID: ");
        String status = readNonEmptyString("Enter new status (Completed/Delivered/Cancelled): ");
        orderService.updateOrderStatus(id, normalizeStatus(status));
        System.out.println(orderService.getLastMessage());
    }

    private static String normalizeStatus(String status) {
        if (status.isEmpty())
            return status;
        return status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
    }

    private static void viewProducts() {
        printList("Products:", productService.getProducts());
    }

    private static void viewAdmins() {
        printList("Admins:", adminService.getAdmins());
    }

    private static void viewCustomers() {
        printList("Customers:", customerService.getCustomers());
    }

    private static void viewAllOrders() {
        printList("Orders:", orderService.getOrders());
    }

    private static void viewCustomerOrders() {
        Customer customer = customerService.getCustomer(readPositiveInt("Enter Customer ID: "));
        if (customer == null) {
            System.out.println("Invalid customer ID.");
            return;
        }
        printList("Orders:", customer.getOrders());
    }

    private static void printList(String heading, List<?> values) {
        System.out.println(heading);
        if (values.isEmpty())
            System.out.println("No records found.");
        else
            for (Object value : values)
                System.out.println(value);
    }

    private static boolean userIdExists(int id) {
        if (customerService.getCustomer(id) != null || adminService.hasAdmin(id))
            return true;
        return false;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0)
                return value;
            System.out.println("Value must be greater than zero.");
        }
    }

    private static double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= 0)
                    return value;
                System.out.println("Value cannot be negative.");
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty())
                return value;
            System.out.println("Value cannot be empty.");
        }
    }
}
