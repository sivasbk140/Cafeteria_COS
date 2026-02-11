package net.breezeware.food.service;

import net.breezeware.food.dao.FoodItemDao;
import net.breezeware.food.dao.FoodMenuItemMapDao;
import net.breezeware.food.entity.FoodItem;

public class AdminFoodService {

    private final FoodItemDao foodItemDao = new FoodItemDao();
    private final FoodMenuItemMapDao mapDao = new FoodMenuItemMapDao();

    // Add food item + map to menu
    public void addFoodItem(FoodItem item, int menuId) {
        int foodItemId = foodItemDao.create(item);
        mapDao.create(menuId, foodItemId);
        System.out.println("Food item added and mapped to menu");
    }

    // Delete food item
    public void deleteFoodItem(int foodItemId) {
        foodItemDao.delete(foodItemId);
        System.out.println("Food item deleted");
    }

    // Update food item
    public void updateFoodItem(FoodItem item) {
        foodItemDao.update(item);
        System.out.println("Food item updated");
    }
}
