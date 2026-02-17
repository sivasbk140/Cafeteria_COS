package net.breezeware.food.dto;

public class FoodItemDTO {

    private int id;
    private String name;
    private double price;
    private int quantity;
    private String category;
    private String description;

    public FoodItemDTO() {
    }

    public FoodItemDTO(int id, String name, double price, int quantity,
                       String category, String description) {
        this.id          = id;
        this.name        = name;
        this.price       = price;
        this.quantity    = quantity;
        this.category    = category;
        this.description = description;
    }

    // ─── Getters ─────────────────────────────────────────────────

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    // ─── Setters ─────────────────────────────────────────────────

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // ─── toString ────────────────────────────────────────────────

    @Override
    public String toString() {
        return "FoodItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}