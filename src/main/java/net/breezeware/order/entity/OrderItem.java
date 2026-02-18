package net.breezeware.order.entity;

import java.time.Instant;

public class OrderItem {

    private int id;
    private int orderId;
    private int foodItemId;
    private int name;
    private int quantity;
    private double price;
    private Instant createdOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodMenuItemId() {
        return foodItemId;
    }

    public void setFoodMenuItemId(int foodMenuItemId) {
        this.foodItemId = foodItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FoodMenu{" +
                "id=" + id +
                "orderId" +orderId +
                ", name" + name +
                ", quantity" + quantity +
                ", price" + price +
                ", foodItemId='" + foodItemId + '\'' +
                '}';
    }
}
