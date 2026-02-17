package net.breezeware.food.service;

import net.breezeware.food.dao.CustomerMenuDao;
import net.breezeware.food.dto.MenuViewDTO;

import java.util.List;

public class StaffFoodService extends FoodService {

    private final CustomerMenuDao customerMenuDao;

    public StaffFoodService() {
        this.customerMenuDao = new CustomerMenuDao();
    }

    // ─── View Full Weekly Menu ───────────────────────────────────
    public void viewFullWeekMenu() {
        System.out.println("\n=== FULL WEEKLY MENU ===");
        List<MenuViewDTO> menuItems = customerMenuDao.getFullWeekMenu();
        displayMenu(menuItems);
    }
}