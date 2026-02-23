package net.breezeware.order.dto;

import net.breezeware.order.entity.OrderStatus;

public class OrderSummaryDTO {

    private int orderId;
    private int userId;
    private OrderStatus status;
    private double totalPrice;
    private String createdOn;

    public OrderSummaryDTO() {
    }

    public OrderSummaryDTO(int orderId, int userId, OrderStatus status,
                           double totalPrice, String createdOn) {
        this.orderId    = orderId;
        this.userId     = userId;
        this.status     = status;
        this.totalPrice = totalPrice;
        this.createdOn  = createdOn;
    }



    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCreatedOn() {
        return createdOn;
    }



    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }



    @Override
    public String toString() {
        return "OrderSummaryDTO{" +
                "orderId=" + orderId +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}