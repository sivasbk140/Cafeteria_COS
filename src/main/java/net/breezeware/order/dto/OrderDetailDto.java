package net.breezeware.order.dto;

import net.breezeware.order.enumeration.OrderStatus;

import java.util.List;

public class OrderDetailDto {

    private int orderId;
    private int userId;
    private String userName;
    private OrderStatus status;
    private List<OrderItemDetailDTO> items;
    private double totalPrice;
    private String deliveryEmail;
    private String deliveryPhone;
    private String deliveryLocation;
    private String createdOn;

    public OrderDetailDto() {
    }

    public OrderDetailDto(int orderId, int userId, String userName,
                          OrderStatus status, List<OrderItemDetailDTO> items,
                          double totalPrice, String deliveryEmail,
                          String deliveryPhone, String deliveryLocation,
                          String createdOn) {
        this.orderId          = orderId;
        this.userId           = userId;
        this.userName         = userName;
        this.status           = status;
        this.items            = items;
        this.totalPrice       = totalPrice;
        this.deliveryEmail    = deliveryEmail;
        this.deliveryPhone    = deliveryPhone;
        this.deliveryLocation = deliveryLocation;
        this.createdOn        = createdOn;
    }



    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItemDetailDTO> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getDeliveryEmail() {
        return deliveryEmail;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setItems(List<OrderItemDetailDTO> items) {
        this.items = items;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDeliveryEmail(String deliveryEmail) {
        this.deliveryEmail = deliveryEmail;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }



    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "orderId=" + orderId +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", itemCount=" + (items != null ? items.size() : 0) +
                '}';
    }

    // Nested DTO for Order Items

    public static class OrderItemDetailDTO {
        private String foodItemName;
        private int quantity;
        private double price;
        private double totalPrice;



        public String getFoodItemName() {
            return foodItemName;
        }

        public void setFoodItemName(String foodItemName) {
            this.foodItemName = foodItemName;
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

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        @Override
        public String toString() {
            return "OrderItemDetailDTO{" +
                    "foodItemName='" + foodItemName + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    ", totalPrice=" + totalPrice +
                    '}';
        }
    }
}
