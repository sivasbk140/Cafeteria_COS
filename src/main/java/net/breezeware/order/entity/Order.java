package net.breezeware.order.entity;

public class Order {

    private int id;
    private int userId;
    private OrderStatus status;
    private String createdOn;
    private String updatedOn;

    public Order() {
    }

    public Order(int id, int userId, OrderStatus status,
                 String createdOn, String updatedOn) {
        this.id        = id;
        this.userId    = userId;
        this.status    = status;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }



    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }



    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                '}';
    }
}