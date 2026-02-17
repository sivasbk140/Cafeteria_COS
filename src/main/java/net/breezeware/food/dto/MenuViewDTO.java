package net.breezeware.food.dto;

import net.breezeware.food.entity.MenuDay;

public class MenuViewDTO {

    private MenuDay day;
    private String menuCategory;  // BREAKFAST, LUNCH, DINNER
    private String foodItemName;
    private double price;
    private String description;
    private int quantity;

    public MenuViewDTO() {
    }

    public MenuViewDTO(MenuDay day, String menuCategory,
                       String foodItemName, double price, String description, int quantity) {
        this.day           = day;
        this.menuCategory  = menuCategory;
        this.foodItemName  = foodItemName;
        this.price         = price;
        this.description   = description;
        this.quantity =quantity;
    }

    public MenuViewDTO(MenuDay day, String menuCategory,
                       String foodItemName, double price, String description) {
        this.day           = day;
        this.menuCategory  = menuCategory;
        this.foodItemName  = foodItemName;
        this.price         = price;
        this.description   = description;
    }

    // ─── Getters ─────────────────────────────────────────────────

    public MenuDay getDay() {
        return day;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity(){ return quantity;}

    // ─── Setters ─────────────────────────────────────────────────

    public void setDay(MenuDay day) {
        this.day = day;
    }

    public void setMenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity){this.quantity=quantity;}

    // ─── toString ────────────────────────────────────────────────

    @Override
    public String toString() {
        return "MenuViewDTO{" +
                "day=" + day +
                ", menuCategory='" + menuCategory + '\'' +
                ", foodItemName='" + foodItemName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity='"+ quantity + '\'' +
                '}';
    }
}