package net.breezeware.food.service;

import net.breezeware.food.dao.CustomerMenuDao;
import net.breezeware.food.entity.FoodItem;

import java.util.List;

public class CustomerFoodService {

    private final CustomerMenuDao dao = new CustomerMenuDao();


    public List<FoodItem> viewMenu(int menuId) {
        return dao.fetchMenu(menuId);
    }


    public List<FoodItem> viewMenus() {
        return dao.fetchAllMenus();
    }
}
