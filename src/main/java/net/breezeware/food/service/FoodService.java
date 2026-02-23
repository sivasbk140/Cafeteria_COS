package net.breezeware.food.service;

import net.breezeware.food.dao.CustomerMenuDao;
import net.breezeware.food.dto.MenuViewDTO;
import net.breezeware.food.entity.MenuDay;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class FoodService {

    private final CustomerMenuDao customerMenuDao;

    public FoodService() {
        this.customerMenuDao = new CustomerMenuDao();
    }

    // Display Menu (shared logic)
    public void displayMenu(List<MenuViewDTO> menuItems) {
        if (menuItems.isEmpty()) {
            System.out.println("No menu items available.");
            return;
        }

        // Group by day and category
        Map<MenuDay, Map<String, List<MenuViewDTO>>> grouped = new LinkedHashMap<>();

        for (MenuViewDTO item : menuItems) {
            grouped.putIfAbsent(item.getDay(), new LinkedHashMap<>());
            grouped.get(item.getDay()).putIfAbsent(item.getMenuCategory(), new java.util.ArrayList<>());
            grouped.get(item.getDay()).get(item.getMenuCategory()).add(item);
        }

        // Display
        for (Map.Entry<MenuDay, Map<String, List<MenuViewDTO>>> dayEntry : grouped.entrySet()) {
            System.out.println("\n=== " + dayEntry.getKey() + " ===");

            for (Map.Entry<String, List<MenuViewDTO>> categoryEntry : dayEntry.getValue().entrySet()) {
                System.out.println("-- " + categoryEntry.getKey() + " --");

                for (MenuViewDTO item : categoryEntry.getValue()) {
                    System.out.printf("• %-20s | ₹%-7.2f | %s%n",
                            item.getFoodItemName(),
                            item.getPrice(),
                            item.getDescription());
                }
            }
        }
    }

    //  Format Price
    protected String formatPrice(double price) {
        return String.format("₹%.2f", price);
    }
}