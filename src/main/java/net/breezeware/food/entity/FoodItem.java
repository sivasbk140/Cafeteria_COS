package net.breezeware.food.entity;

public class FoodItem {
     private int id;
     private String name;
     private double price;
     private String category; // NEW


     public FoodItem(int id, String name, double price, String category) {
          this.id = id;
          this.name = name;
          this.price = price;
          this.category = category;
     }


     public FoodItem() { }


     public int getId() { return id; }
     public String getName() { return name; }
     public double getPrice() { return price; }
     public String getCategory() { return category; }


     public void setId(int id) { this.id = id; }
     public void setName(String name) { this.name = name; }
     public void setPrice(double price) { this.price = price; }
     public void setCategory(String category) { this.category = category; }

     @Override
     public String toString() {
          return "FoodItem{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", price=" + price +
                  ", category='" + category + '\'' +
                  '}';
     }
}
