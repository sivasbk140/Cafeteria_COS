package net.breezeware.food.service;

import net.breezeware.food.dao.CustomerMenuDao;
import net.breezeware.food.dto.MenuViewDTO;
import net.breezeware.food.entity.MenuDay;

import java.util.List;
import java.util.Scanner;

public class CustomerFoodService extends FoodService {

    private final CustomerMenuDao customerMenuDao;
    private final Scanner scanner;

    public CustomerFoodService() {
        this.customerMenuDao = new CustomerMenuDao();
        this.scanner = new Scanner(System.in);
    }

    // ─── View Menu Options ───────────────────────────────────────
    public void viewMenuOptions() {
        System.out.println("\nDo you want to view:");
        System.out.println("1. Full Menu");
        System.out.println("2. Menu for a Specific Day");
        System.out.print("Enter choice: ");

        int choice = Integer.parseInt(scanner.nextLine().trim());

        switch (choice) {
            case 1 -> viewFullMenu();
            case 2 -> viewSpecificDay();
            default -> System.out.println("Invalid choice.");
        }
    }

    // ─── View Full Menu ──────────────────────────────────────────
    private void viewFullMenu() {
        System.out.print("\n");
        List<MenuViewDTO> menuItems = customerMenuDao.getFullWeekMenu();
        System.out.print("\n");

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
