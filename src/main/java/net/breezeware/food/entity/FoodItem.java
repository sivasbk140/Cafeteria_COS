package net.breezeware.food.entity;

public class FoodItem {
     private int id;
     private String name;
     private double price;
     private String category;
     private String description;
     private int  quantity;


     public FoodItem(int id, String name, double price, String category, String description, int quantity) {
          this.id = id;
          this.name = name;
          this.price = price;
          this.category = category;
          this. price = price;
          this.description = description;
     }


     public FoodItem() { }


     public int getId() { return id; }
     public String getName() { return name; }
     public double getPrice() { return price; }
     public String getCategory() { return category; }
     public String getDescription() { return description; }
     public int getQuantity() { return quantity; }



     public void setId(int id) { this.id = id; }
     public void setName(String name) { this.name = name; }
     public void setPrice(double price) { this.price = price; }
     public void setCategory(String category) { this.category = category; }

     public void setDescription(String description) {
          this.description = description;
     }

     public void setQuantity(int quantity) {
          this.quantity = quantity;
     }

     @Override
     public String toString() {
          return "FoodItem{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", price=" + price +
                  ", category='" + category + '\'' +
                  ", description='" + description + '\'' +
                  ", quantity=" + quantity +
                  '}';
     }
}
