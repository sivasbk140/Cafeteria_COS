package net.breezeware.food.service;

import net.breezeware.food.entity.FoodItem;
import net.breezeware.food.dao.FoodMenuDao;

import java.util.List;

public class FoodService {

    private final FoodMenuDao dao = new FoodMenuDao();


    public boolean addFoodItem(FoodItem item) {
        return dao.insertFoodItem(item);
    }


    public boolean updateFoodItem(FoodItem item) {
        return dao.updateFoodItem(item);
    }


    public boolean deleteFoodItem(int id) {
        return dao.deleteFoodItem(id);
    }


    public List<FoodItem> viewAllFoodItems() {
        return dao.fetchAllFoodItems();
    }


    public FoodItem viewFoodItemById(int id) {
        List<FoodItem> items = dao.fetchAllFoodItems();
        for (FoodItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
