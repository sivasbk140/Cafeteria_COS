package net.breezeware.food.service;

import net.breezeware.food.dao.*;
import net.breezeware.food.dto.FoodItemDto;
import net.breezeware.food.dto.MenuViewDto;
import net.breezeware.food.enumeration.MenuDay;

import java.util.List;
import java.util.Scanner;

public class AdminFoodService extends FoodService {

    private final FoodItemDao foodItemDao;
    private final FoodMenuDao foodMenuDao;
    private final FoodMenuItemMapDao foodMenuItemMapDao;
    private final AvailabilityMapDao availabilityMapDao;
    private final CustomerMenuDao customerMenuDao;
    private final Scanner scanner;

    public AdminFoodService() {
        this.foodItemDao = new FoodItemDao();
        this.foodMenuDao = new FoodMenuDao();
        this.foodMenuItemMapDao = new FoodMenuItemMapDao();
        this.availabilityMapDao = new AvailabilityMapDao();
        this.customerMenuDao = new CustomerMenuDao();
        this.scanner = new Scanner(System.in);
    }


    public void viewAllFoodItems() {
        List<FoodItemDto> items = foodItemDao.getAllFoodItems();

        if (items.isEmpty()) {
            System.out.println("No food items found.");
            return;
        }

        System.out.println("\n=== ALL FOOD ITEMS ===");
        System.out.println("────────────────────────────────────────────────────────────────────────────────");
        System.out.printf("%-4s | %-20s | %-8s | %-15s | %-5s | %s%n",
                "ID", "Name", "Price", "Category", "Qty", "Description");
        System.out.println("────────────────────────────────────────────────────────────────────────────────");

        for (FoodItemDto item : items) {
            System.out.printf("%-4d | %-20s | %-8s | %-15s | %-5d | %s%n",
                    item.getId(),
                    item.getName(),
                    formatPrice(item.getPrice()),
                    item.getCategory(),
                    item.getQuantity(),
                    item.getDescription());
        }
        System.out.println("────────────────────────────────────────────────────────────────────────────────");
    }

    //  View Menu By Day
    public void viewMenuByDay() {
        System.out.print("\nEnter day (MONDAY/TUESDAY/.../ALLDAY): ");
        String dayInput = scanner.nextLine().trim();

        MenuDay day = MenuDay.fromString(dayInput);
        if (day == null) {
            System.out.println("Invalid day. Please try again.");
            return;
        }

        List<MenuViewDto> menuItems = customerMenuDao.getMenuByDay(day);
        displayMenu(menuItems);
    }

    //  Add Food Item
    public void addFoodItem() {
        System.out.println("\n=== ADD FOOD ITEM ===");

        System.out.print("Name        : ");
        String name = scanner.nextLine().trim();

        System.out.print("Price       : ");
        double price = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Quantity    : ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Category    : ");
        String category = scanner.nextLine().trim();

        System.out.print("Description : ");
        String description = scanner.nextLine().trim();

        int id = foodItemDao.addFoodItem(name, price, quantity, category, description);

        if (id > 0) {
            System.out.println("\n Food item '" + name + "' added successfully! (ID: " + id + ")");
        } else {
            System.out.println("\n Failed to add food item.");
        }
    }

    //  Update Food Item
    public void updateFoodItem() {
        System.out.println("\n=== UPDATE FOOD ITEM ===");

        System.out.print("Enter Food Item ID to update: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        FoodItemDto existing = foodItemDao.getFoodItemById(id);
        if (existing == null) {
            System.out.println(" Food item not found.");
            return;
        }

        System.out.println("\nCurrent Details → Name: " + existing.getName() +
                " | Price: " + formatPrice(existing.getPrice()) +
                " | Qty: " + existing.getQuantity() +
                " | Category: " + existing.getCategory());

        System.out.print("\nEnter new Name (press Enter to keep '" + existing.getName() + "'): ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = existing.getName();

        System.out.print("Enter new Price (press Enter to keep '" + existing.getPrice() + "'): ");
        String priceInput = scanner.nextLine().trim();
        double price = priceInput.isEmpty() ? existing.getPrice() : Double.parseDouble(priceInput);

        System.out.print("Enter new Quantity (press Enter to keep '" + existing.getQuantity() + "'): ");
        String qtyInput = scanner.nextLine().trim();
        int quantity = qtyInput.isEmpty() ? existing.getQuantity() : Integer.parseInt(qtyInput);

        System.out.print("Enter new Category (press Enter to keep '" + existing.getCategory() + "'): ");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) category = existing.getCategory();

        System.out.print("Enter new Description (press Enter to keep current): ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) description = existing.getDescription();

        boolean success = foodItemDao.updateFoodItem(id, name, price, quantity, category, description);

        if (success) {
            System.out.println("\n Food item updated successfully!");
        } else {
            System.out.println("\n Failed to update food item.");
        }
    }

    //  Delete Food Item
    public void deleteFoodItem() {
        System.out.println("\n=== DELETE FOOD ITEM ===");

        System.out.print("Enter Food Item ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        FoodItemDto item = foodItemDao.getFoodItemById(id);
        if (item == null) {
            System.out.println(" Food item not found.");
            return;
        }

        System.out.println("\n This will delete '" + item.getName() +
                "' and remove it from all menus.");
        System.out.print("Confirm delete? (yes/no): ");
        String confirm = scanner.nextLine().trim();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Delete cancelled.");
            return;
        }

        boolean success = foodItemDao.deleteFoodItem(id);

        if (success) {
            System.out.println("\n Food item '" + item.getName() + "' deleted successfully.");
        } else {
            System.out.println("\n Failed to delete food item.");
        }
    }

    //  Manage Menus
    public void manageMenus() {
        while (true) {
            System.out.println("\n=== MANAGE MENUS ===");
            System.out.println("1. Create New Menu");
            System.out.println("2. Assign Food Item to Menu");
            System.out.println("3. Remove Food Item from Menu");
            System.out.println("4. Assign Menu to Day");
            System.out.println("5. Delete Menu");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1 -> createMenu();
                case 2 -> assignFoodItemToMenu();
                case 3 -> removeFoodItemFromMenu();
                case 4 -> assignMenuToDay();
                case 5 -> deleteMenu();
                case 6 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void createMenu() {
        System.out.print("\nCategory (BREAKFAST/LUNCH/DINNER/CHINESE/WESTERN/SOUTH_INDIAN/NORTH_INDIAN): ");
        String category = scanner.nextLine().trim().toUpperCase();

        int menuId = foodMenuDao.createMenu(category);

        if (menuId > 0) {
            System.out.println(" Menu created! (Menu ID: " + menuId + ")");
        } else {
            System.out.println(" Failed to create menu.");
        }
    }

    private void assignFoodItemToMenu() {
        System.out.print("\nEnter Menu ID: ");
        int menuId = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter Food Item ID: ");
        int foodItemId = Integer.parseInt(scanner.nextLine().trim());

        boolean success = foodMenuItemMapDao.assignFoodItemToMenu(menuId, foodItemId);

        if (success) {
            System.out.println(" Food item assigned to menu successfully!");
        } else {
            System.out.println(" Failed to assign food item.");
        }
    }

    private void removeFoodItemFromMenu() {
        System.out.print("\nEnter Menu ID: ");
        int menuId = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter Food Item ID: ");
        int foodItemId = Integer.parseInt(scanner.nextLine().trim());

        boolean success = foodMenuItemMapDao.removeFoodItemFromMenu(menuId, foodItemId);

        if (success) {
            System.out.println(" Food item removed from menu successfully!");
        } else {
            System.out.println(" Failed to remove food item.");
        }
    }

    private void assignMenuToDay() {
        System.out.print("\nEnter Menu ID: ");
        int menuId = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter Day (MONDAY/TUESDAY/.../ALLDAY): ");
        String dayInput = scanner.nextLine().trim();

        MenuDay day = MenuDay.fromString(dayInput);
        if (day == null) {
            System.out.println("Invalid day.");
            return;
        }

        boolean success = availabilityMapDao.assignMenuToDay(menuId, day);

        if (success) {
            System.out.println(" Menu assigned to " + day + " successfully!");
        } else {
            System.out.println(" Failed to assign menu to day.");
        }
    }

    private void deleteMenu() {
        System.out.print("\nEnter Menu ID to delete: ");
        int menuId = Integer.parseInt(scanner.nextLine().trim());

        System.out.print(" This will remove all day assignments for this menu. Confirm? (yes/no): ");
        String confirm = scanner.nextLine().trim();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Delete cancelled.");
            return;
        }

        boolean success = foodMenuDao.deleteMenu(menuId);

        if (success) {
            System.out.println(" Menu deleted successfully!");
        } else {
            System.out.println(" Failed to delete menu.");
        }
    }
}