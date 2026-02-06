package net.breezeware.food.entity;

import java.time.Instant;

public class FoodItem {
     private int id;
     private String name;
     private String description;
     private String category;
     private int quantity;
     private Instant createdOn;
     private Instant updatedOn;
     private int price;


     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getDescription() {
          return description;
     }

     public void setDescription(String description) {
          this.description = description;
     }

     public String getCategory() {
          return category;
     }

     public void setCategory(String category) {
          this.category = category;
     }

     public int getQuantity() {
          return quantity;
     }

     public void setQuantity(int quantity) {
          this.quantity = quantity;
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

     public int getPrice() {
          return price;
     }

     public void setPrice(int price) {
          this.price = price;
     }
}
