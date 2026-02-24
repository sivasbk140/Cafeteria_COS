package net.breezeware.order.dto;

public class CartItemDto {

    private int foodItemId;
    private String foodItemName;
    private double price;
    private int quantity;


    public CartItemDto(int foodItemId, String foodItemName, double price, int quantity) {
        this.foodItemId   = foodItemId;
        this.foodItemName = foodItemName;
        this.price        = price;
        this.quantity     = quantity;
    }

    // ─── Getters ─────────────────────────────────────────────────

    public int getFoodItemId() {
        return foodItemId;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // ─── Setters ─────────────────────────────────────────────────

    public void setFoodItemId(int foodItemId) {
        this.foodItemId = foodItemId;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Helper Methods

    // Calculate total price for this cart item
    public double getTotalPrice() {
        return price * quantity;
    }



    @Override
    public String toString() {
        return "CartItemDTO{" +
                "foodItemId=" + foodItemId +
                ", foodItemName='" + foodItemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}