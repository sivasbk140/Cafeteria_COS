package net.breezeware.order.entity;

import java.time.Instant;

public class Order {

    private int id;
    private int userId;
    private OrderStatus status;
    private Instant createdOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }
    @Override
    public String toString() {
        return "FoodMenu{" +
                "id=" + id +
                ", status"+ status +
                ", userId='" + userId + '\'' +
                '}';
    }
}
