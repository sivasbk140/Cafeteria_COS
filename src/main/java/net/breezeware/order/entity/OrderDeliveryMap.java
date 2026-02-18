package net.breezeware.order.entity;

import java.time.Instant;

public class OrderDeliveryMap {

   private int id;
   private int orderId;
   private int deliveryId;
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

   public int getDeliveryId() {
      return deliveryId;
   }

   public void setDeliveryId(int deliveryId) {
      this.deliveryId = deliveryId;
   }

   @Override
   public String toString() {
      return "FoodMenu{" +
              "id=" + id +
              ", orderId" + orderId +
              ", deliveryId='" + deliveryId + '\'' +
              '}';
   }
}
