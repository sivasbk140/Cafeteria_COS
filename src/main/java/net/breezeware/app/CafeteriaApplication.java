package net.breezeware.app;

import net.breezeware.food.service.AdminFoodService;
import net.breezeware.food.service.CustomerFoodService;
import net.breezeware.food.service.StaffFoodService;
import net.breezeware.user.dto.UserDTO;
import net.breezeware.user.entity.Role;
import net.breezeware.user.service.UserService;
import net.breezeware.util.DBConnection;

import java.util.Scanner;

public class CafeteriaApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("      CAFETERIA SYSTEM");
        System.out.println("=========================================");

        Role selectedRole = selectRole();
        if (selectedRole == null) {
            System.out.println("Invalid role selection. Exiting...");
            return;
        }

        UserDTO loggedInUser = userService.loginOrRegister(selectedRole);

        if (loggedInUser == null) {
            System.out.println("Login/Registration failed. Exiting...");
            return;
        }

        // role routing
        switch (loggedInUser.getRole()) {
            case ADMIN    -> adminMenu();
            case STAFF    -> staffMenu(loggedInUser);
            case CUSTOMER -> customerMenu(loggedInUser);
        }


        DBConnection.closeConnection();
        scanner.close();
    }


    private static Role selectRole() {
        System.out.println("\nSelect Role:");
        System.out.println("1. ADMIN");
        System.out.println("2. STAFF");
        System.out.println("3. CUSTOMER");
        System.out.print("Enter choice: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return null;
        }

        return switch (choice) {
            case 1 -> Role.ADMIN;
            case 2 -> Role.STAFF;
            case 3 -> Role.CUSTOMER;
            default -> null;
        };
    }


    private static void adminMenu() {
        AdminFoodService adminService = new AdminFoodService();

        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. View All Food Items");
            System.out.println("2. View Food Items By Menu");
            System.out.println("3. Add Food Item");
            System.out.println("4. Update Food Item");
            System.out.println("5. Delete Food Item");
            System.out.println("6. Manage Menus");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {
                case 1 -> adminService.viewAllFoodItems();
                case 2 -> adminService.viewMenuByDay();
                case 3 -> adminService.addFoodItem();
                case 4 -> adminService.updateFoodItem();
                case 5 -> adminService.deleteFoodItem();
                case 6 -> adminService.manageMenus();
                case 7 -> {
                    System.out.println("\nLogging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }


    private static void staffMenu(UserDTO user) {
        StaffFoodService staffService = new StaffFoodService();
        staffService.showStaffMenu();
        System.out.println("\nLogging out..."); 
    }


    private static void customerMenu(UserDTO user) {
        CustomerFoodService customerService = new CustomerFoodService(user.getId());
        customerService.showCustomerMenu();
        System.out.println("\nLogging out...");
    }
}
