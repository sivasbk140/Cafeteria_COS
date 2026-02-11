package net.breezeware.food.service;

import net.breezeware.food.dao.CustomerMenuDao;
import net.breezeware.food.entity.FoodItem;

import java.util.List;

public class StaffFoodService {

    private final CustomerMenuDao dao = new CustomerMenuDao();

    // View items of a specific menu
    public void viewMenuItems(int menuId) {

        List<FoodItem> items = dao.fetchMenu(menuId);

        if (items.isEmpty()) {
            System.out.println(" No items found for Menu ID: " + menuId);
            return;
        }

        System.out.println("\n--- Menu Items ---");
        for (FoodItem item : items) {
            System.out.println(item);
        }
    }

    // View all available food items
    public void viewAllMenuItems() {

        List<FoodItem> items = dao.fetchAllMenus();

        if (items.isEmpty()) {
            System.out.println(" No menu items available");
            return;
        }

        System.out.println("\n--- All Menu Items ---");
        for (FoodItem item : items) {
            System.out.println(item);
        }
    }
}
