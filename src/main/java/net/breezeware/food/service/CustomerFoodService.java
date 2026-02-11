package net.breezeware.food.service;

import net.breezeware.food.dao.CustomerMenuDao;
import net.breezeware.food.entity.FoodItem;

import java.util.List;

public class CustomerFoodService {

    private final CustomerMenuDao dao = new CustomerMenuDao();

    // View all food items (for CUSTOMER & STAFF)
    public List<FoodItem> viewAllFoodItems() {
        return dao.fetchAllMenus();
    }

    // View food items by menu (Breakfast / Dinner etc.)
    public List<FoodItem> viewFoodItemsByMenu(int menuId) {
        return dao.fetchMenu(menuId);
    }
}
