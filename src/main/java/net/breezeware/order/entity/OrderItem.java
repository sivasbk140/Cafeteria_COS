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

}
