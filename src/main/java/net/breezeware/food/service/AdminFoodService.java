package net.breezeware.food.service;

import net.breezeware.food.dao.CustomerMenuDao;
import net.breezeware.food.dao.FoodItemDao;
import net.breezeware.food.dao.FoodMenuItemMapDao;
import net.breezeware.food.entity.FoodItem;

import java.util.List;

public class AdminFoodService {

    private final FoodItemDao foodItemDao = new FoodItemDao();
    private final FoodMenuItemMapDao mapDao = new FoodMenuItemMapDao();
    private final CustomerMenuDao menuDao = new CustomerMenuDao();

    // ---------- ADD ----------
    public void addFoodItem(FoodItem item, int menuId) {
        int foodItemId = foodItemDao.create(item);
        mapDao.create(menuId, foodItemId);
        System.out.println("Food item added and mapped to menu");
    }

    // ---------- UPDATE ----------
    public void updateFoodItem(FoodItem item) {
        foodItemDao.update(item);
        System.out.println("Food item updated");
    }

    // ---------- DELETE ----------
    public void deleteFoodItem(int foodItemId) {
        foodItemDao.delete(foodItemId);
        System.out.println("Food item deleted");
    }

    // ---------- VIEW ALL ----------
    public void viewAllFoodItems() {
        List<FoodItem> items = menuDao.fetchAllMenus();
        items.forEach(System.out::println);
    }

    // ---------- VIEW BY MENU ----------
    public void viewFoodItemsByMenu(int menuId) {
        List<FoodItem> items = menuDao.fetchMenu(menuId);

        if (items.isEmpty()) {
            System.out.println("No items found for Menu ID: " + menuId);
            return;
        }

        items.forEach(System.out::println);
    }
}
