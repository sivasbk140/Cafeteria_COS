package net.breezeware.order.entity;

public enum OrderStatus {

    PLACED_ORDER,           // Customer just placed order
    WAITING_FOR_DELIVERY,   // Staff marked as prepared, waiting for delivery person
    PENDING_DELIVERY,       // Out for delivery
    ORDER_DELIVERED,        // Successfully delivered
    ORDER_CANCELLED;        // Cancelled by customer

    // Safely convert String from DB to enum
    public static OrderStatus fromString(String status) {
        try {
            return OrderStatus.valueOf(status.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Invalid order status in DB → '" + status + "'");
            return null;
        }
    }

    // Check if order can be cancelled (only if not yet prepared)
    public boolean isCancellable() {
        return this == PLACED_ORDER;
    }

    // Check if order is in active state (not completed/cancelled)
    public boolean isActive() {
        return this == PLACED_ORDER ||
                this == WAITING_FOR_DELIVERY ||
                this == PENDING_DELIVERY;
    }

    // Check if order is completed
    public boolean isCompleted() {
        return this == ORDER_DELIVERED;
    }

    // Check if order is cancelled
    public boolean isCancelled() {
        return this == ORDER_CANCELLED;
    }
}