package net.breezeware.order.entity;

import net.breezeware.food.entity.MenuDay;

public enum OrderStatus {
     PLACED_ORDER,
             ORDER_DELIVERED,
             ORDER_CANCELLED,
             PENDING_DELIVERY,
             WAITING_FOR_DELIVERY;


    public static MenuDay fromString(String status) {
        try {
            return MenuDay.valueOf(status.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Invalid day value in DB → '" + status + "'");
            return null;
        }
    }
}
