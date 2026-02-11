package net.breezeware.app;

import net.breezeware.food.entity.FoodItem;
import net.breezeware.food.service.AdminFoodService;
import net.breezeware.food.service.CustomerFoodService;
import net.breezeware.user.entity.Role;

import java.util.List;
import java.util.Scanner;

public class CafeteriaApplication {

    private static final AdminFoodService adminService = new AdminFoodService();
    private static final CustomerFoodService customerService = new CustomerFoodService();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Role role = getRoleFromUser(sc);

        while (true) {
            showMenu(role);
            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> viewAllFoodItems();

                case 2 -> viewFoodItemsByMenu(sc);

                case 3 -> {
                    if (role != Role.ADMIN) {
                        System.out.println("❌ Only ADMIN can add food items");
                        break;
                    }
                    addFoodItem(sc);
                }

                case 4 -> {
                    if (role != Role.ADMIN) {
                        System.out.println("❌ Only ADMIN can delete food items");
                        break;
                    }
                    deleteFoodItem(sc);
                }

                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }
    }

    // ---------------- MENU ----------------
    private static void showMenu(Role role) {

        System.out.println("\n=== Cafeteria Application ===");
        System.out.println("1. View all food items");
        System.out.println("2. View food items by menu");

        if (role == Role.ADMIN) {
            System.out.println("3. Add food item");
            System.out.println("4. Delete food item");
        }

        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    // ---------------- VIEW ----------------
    private static void viewAllFoodItems() {
        List<FoodItem> items = customerService.viewAllFoodItems();
        items.forEach(System.out::println);
    }

    private static void viewFoodItemsByMenu(Scanner sc) {
        System.out.print("Enter Menu ID: ");
        int menuId = sc.nextInt();

        List<FoodItem> items = customerService.viewFoodItemsByMenu(menuId);

        if (items.isEmpty()) {
            System.out.println("No items found for this menu");
        } else {
            items.forEach(System.out::println);
        }
    }

    // ---------------- ADMIN ----------------
    private static void addFoodItem(Scanner sc) {

        sc.nextLine(); // clear buffer

        FoodItem item = new FoodItem();

        System.out.print("Name: ");
        item.setName(sc.nextLine());

        System.out.print("Price: ");
        item.setPrice(sc.nextDouble());

        sc.nextLine();
        System.out.print("Category: ");
        item.setCategory(sc.nextLine());

        System.out.print("Description: ");
        item.setDescription(sc.nextLine());

        System.out.print("Quantity: ");
        item.setQuantity(sc.nextInt());

        System.out.print("Menu ID: ");
        int menuId = sc.nextInt();

        adminService.addFoodItem(item, menuId);
    }

    private static void deleteFoodItem(Scanner sc) {
        System.out.print("Enter Food Item ID: ");
        int id = sc.nextInt();
        adminService.deleteFoodItem(id);
    }

    // ---------------- ROLE ----------------
    private static Role getRoleFromUser(Scanner sc) {

        System.out.println("Select Role:");
        System.out.println("1. Admin");
        System.out.println("2. Staff");
        System.out.println("3. Customer");

        int choice = sc.nextInt();

        return switch (choice) {
            case 1 -> Role.ADMIN;
            case 2 -> Role.STAFF;
            case 3 -> Role.CUSTOMER;
            default -> throw new IllegalArgumentException("Invalid role");
        };
    }
}
