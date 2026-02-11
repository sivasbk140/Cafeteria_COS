package net.breezeware.app;

import net.breezeware.food.service.CustomerFoodService;
import net.breezeware.food.entity.FoodItem;

import java.util.List;
import java.util.Scanner;

public class CafeteriaApplication {

    private static final CustomerFoodService service = new CustomerFoodService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        System.out.println("=== Welcome to the Cafeteria Application ===");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. View all menu items");
            System.out.println("2. View a specific menu by ID");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllMenus();
                    break;
                case 2:
                    viewSpecificMenu();
                    break;
                case 3:
                    System.out.println("Thank you for using Cafeteria Application!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void viewAllMenus() {
        List<FoodItem> items = service.viewMenus();
        if (items.isEmpty()) {
            System.out.println("No menu items available.");
            return;
        }

        System.out.println("\n--- All Menu Items ---");
        for (FoodItem item : items) {
            System.out.println("ID: " + item.getId() +
                    ", Name: " + item.getName() +
                    ", Price: $" + item.getPrice() +
                    ", Category: " + item.getCategory()+
                    ", Description: " + item.getDescription()+
                    ", Quantity: " + item.getQuantity());
        }
    }

    private static void viewSpecificMenu() {
        System.out.print("Enter Menu ID: ");
        int menuId = scanner.nextInt();
        scanner.nextLine();

        List<FoodItem> items = service.viewMenu(menuId);
        if (items.isEmpty()) {
            System.out.println("No items found for Menu ID: " + menuId);
            return;
        }

        System.out.println("\n--- Menu ID " + menuId + " Items ---");
        for (FoodItem item : items) {
            System.out.println("ID: " + item.getId() +
                    ", Name: " + item.getName() +
                    ", Price: $" + item.getPrice() +
                    ", Category: " + item.getCategory() +
                    "Description: " + item.getDescription() +
                    ", Quantity: " + item.getQuantity());
        }
    }
}
