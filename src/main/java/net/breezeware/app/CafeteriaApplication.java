package net.breezeware.app;

import net.breezeware.food.entity.FoodItem;
import net.breezeware.food.service.AdminFoodService;
import net.breezeware.food.service.CustomerFoodService;
import net.breezeware.food.service.StaffFoodService;
import net.breezeware.user.entity.Role;
import net.breezeware.user.entity.User;
import net.breezeware.user.service.UserService;

import java.util.Scanner;

public class CafeteriaApplication {

    private static final Scanner sc = new Scanner(System.in);
    private static final UserService userService = new UserService();

    public static void main(String[] args) throws Exception {

        System.out.println("Select Role:");
        System.out.println("1. ADMIN");
        System.out.println("2. STAFF");
        System.out.println("3. CUSTOMER");

        int roleChoice = sc.nextInt();
        sc.nextLine();

        Role role = switch (roleChoice) {
            case 1 -> Role.ADMIN;
            case 2 -> Role.STAFF;
            default -> Role.CUSTOMER;
        };

        System.out.println("1. Login");
        System.out.println("2. Register");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 2) {
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            userService.register(name, email, password, role);
            System.out.println("Please login to continue.\n");
        }

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = userService.login(email, password);

        if (user == null) {
            System.out.println("Invalid email or password");
            return;
        }

        if (user.getRole() != role) {
            System.out.println("Role mismatch. Access denied.");
            return;
        }

        switch (role) {
            case ADMIN -> adminMenu();
            case STAFF -> staffMenu();
            case CUSTOMER -> customerMenu();
        }
    }

    // ================= ADMIN MENU =================
    private static void adminMenu() {

        AdminFoodService service = new AdminFoodService();

        while (true) {
            System.out.println("""
            === ADMIN MENU ===
            1. View All Food Items
            2. View Food Items By Menu
            3. Add Food Item
            4. Update Food Item
            5. Delete Food Item
            6. Exit
            """);

            int c = sc.nextInt();

            if (c == 6) return;

            if (c == 1) {
                service.viewAllFoodItems();
            }

            if (c == 2) {
                System.out.print("Menu ID: ");
                int menuId = sc.nextInt();
                service.viewFoodItemsByMenu(menuId);
            }

            if (c == 3) {
                sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Price: ");
                double price = sc.nextDouble();
                sc.nextLine();

                System.out.print("Category: ");
                String category = sc.nextLine();

                System.out.print("Description: ");
                String description = sc.nextLine();

                System.out.print("Quantity: ");
                int quantity = sc.nextInt();

                System.out.print("Menu ID: ");
                int menuId = sc.nextInt();

                service.addFoodItem(
                        new FoodItem(0, name, price, category, description, quantity),
                        menuId
                );
            }

            if (c == 4) {
                sc.nextLine();

                System.out.print("Food ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("New Name: ");
                String name = sc.nextLine();

                System.out.print("New Price: ");
                double price = sc.nextDouble();
                sc.nextLine();

                System.out.print("New Category: ");
                String category = sc.nextLine();

                System.out.print("New Description: ");
                String description = sc.nextLine();

                System.out.print("New Quantity: ");
                int quantity = sc.nextInt();

                service.updateFoodItem(
                        new FoodItem(id, name, price, category, description, quantity)
                );
            }

            if (c == 5) {
                System.out.print("Food ID: ");
                service.deleteFoodItem(sc.nextInt());
            }
        }
    }


    // ================= STAFF MENU =================
    private static void staffMenu() {

        StaffFoodService service = new StaffFoodService();

        while (true) {
            System.out.println("""
                === STAFF MENU ===
                1. View All
                2. View By Menu
                3. Exit
                """);

            int c = sc.nextInt();

            if (c == 3) return;

            if (c == 1) {
                service.viewAllMenuItems();
            }

            if (c == 2) {
                System.out.print("Menu ID: ");
                int menuId = sc.nextInt();
                service.viewMenuItems(menuId);
            }
        }
    }

    // ================= CUSTOMER MENU =================
    private static void customerMenu() {

        CustomerFoodService service = new CustomerFoodService();

        while (true) {
            System.out.println("""
                === CUSTOMER MENU ===
                1. View All
                2. View By Menu
                3. Exit
                """);

            int c = sc.nextInt();

            if (c == 3) return;

            if (c == 1) {
                service.viewAllFoodItems()
                        .forEach(System.out::println);
            }

            if (c == 2) {
                System.out.print("Menu ID: ");
                int menuId = sc.nextInt();
                service.viewFoodItemsByMenu(menuId)
                        .forEach(System.out::println);
            }
        }
    }
}
