package net.breezeware.food.service;

import net.breezeware.food.dao.CustomerMenuDao;
import net.breezeware.food.dto.MenuViewDTO;
import net.breezeware.food.entity.MenuDay;
import net.breezeware.order.service.CustomerOrderService;

import java.util.List;
import java.util.Scanner;

public class CustomerFoodService extends FoodService {

    private final CustomerMenuDao customerMenuDao;
    private final CustomerOrderService orderService;
    private final Scanner scanner;

    public CustomerFoodService(int userId) {
        this.customerMenuDao = new CustomerMenuDao();
        this.orderService = new CustomerOrderService(userId);
        this.scanner = new Scanner(System.in);
    }

    // ─── Main Customer Menu ──────────────────────────────────────
    public void showCustomerMenu() {
        while (true) {
            System.out.println("\n=== CUSTOMER MENU ===");
            System.out.println("1. View Menu & Order");
            System.out.println("2. View Cart");
            System.out.println("3. View My Orders");
            System.out.println("4. View Order Details");
            System.out.println("5. Cancel Order");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {
                case 1 -> viewMenuAndOrder();
                case 2 -> orderService.viewCart();
                case 3 -> orderService.viewMyOrders();
                case 4 -> orderService.viewOrderDetails();
                case 5 -> orderService.cancelOrder();
                case 6 -> {
                    if (orderService.hasItemsInCart()) {
                        System.out.print("⚠ You have items in cart. Continue without ordering? (yes/no): ");
                        String confirm = scanner.nextLine().trim();
                        if (confirm.equalsIgnoreCase("yes")) {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ─── View Menu and Start Shopping ────────────────────────────
    private void viewMenuAndOrder() {
        System.out.println("\nDo you want to view:");
        System.out.println("1. Full Week Menu");
        System.out.println("2. Menu for Specific Day");
        System.out.print("Enter choice: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        switch (choice) {
            case 1 -> viewFullMenu();
            case 2 -> viewSpecificDay();
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        // After viewing menu, ask if they want to order
        System.out.print("\nWould you like to add items to cart? (yes/no): ");
        String wantToOrder = scanner.nextLine().trim();

        if (wantToOrder.equalsIgnoreCase("yes")) {
            orderService.startShopping();
        }
    }

    // ─── View Full Menu ──────────────────────────────────────────
    private void viewFullMenu() {
        List<MenuViewDTO> menuItems = customerMenuDao.getFullWeekMenu();
        displayMenu(menuItems);
    }

    // ─── View Specific Day ───────────────────────────────────────
    private void viewSpecificDay() {
        System.out.print("\nEnter day (MONDAY/TUESDAY/.../SUNDAY): ");
        String dayInput = scanner.nextLine().trim();

        MenuDay day = MenuDay.fromString(dayInput);
        if (day == null) {
            System.out.println("Invalid day. Please try again.");
            return;
        }

        List<MenuViewDTO> menuItems = customerMenuDao.getMenuByDay(day);
        displayMenu(menuItems);
    }
}