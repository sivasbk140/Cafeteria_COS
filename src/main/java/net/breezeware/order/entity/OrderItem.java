package net.breezeware.order.entity;

import java.time.Instant;

public class OrderItem {
    private int orderId;
    private int food_menu_item_id;
    private int id;

    private int quantity;
    private double price;

    private Instant created_at;
    private Instant updated_at;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFood_menu_item_id() {
        return food_menu_item_id;
    }

    public void setFood_menu_item_id(int food_menu_item_id) {
        this.food_menu_item_id = food_menu_item_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
    }
}
