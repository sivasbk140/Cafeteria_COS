package net.breezeware.order.entity;

import java.time.Instant;

public class OrderDeliveryMap {
   private int id;
   private int orderId;
   private int deliveryId;
   private   Instant createdOn ;
   private   Instant updatedOn ;

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

   public Instant getCreatedOn() {
      return createdOn;
   }

   public void setCreatedOn(Instant createdOn) {
      this.createdOn = createdOn;
   }

   public Instant getUpdatedOn() {
      return updatedOn;
   }

   public void setUpdatedOn(Instant updatedOn) {
      this.updatedOn = updatedOn;
   }
}
