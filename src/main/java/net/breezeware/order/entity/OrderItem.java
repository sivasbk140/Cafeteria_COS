package net.breezeware.order.entity;

public class OrderItem {

    private int id;
    private int orderId;
    private int foodItemId;
    private double price;
    private int quantity;
    private String createdOn;
    private String updatedOn;

    public OrderItem() {
    }

    public OrderItem(int id, int orderId, int foodItemId,
                     double price, int quantity,
                     String createdOn, String updatedOn) {
        this.id        = id;
        this.orderId   = orderId;
        this.foodItemId = foodItemId;
        this.price     = price;
        this.quantity  = quantity;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    // ─── Getters ─────────────────────────────────────────────────

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getFoodItemId() {
        return foodItemId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    // ─── Setters ─────────────────────────────────────────────────

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setFoodItemId(int foodItemId) {
        this.foodItemId = foodItemId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }



    // Calculate total price for this item
    public double getTotalPrice() {
        return price * quantity;
    }



    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", foodItemId=" + foodItemId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}