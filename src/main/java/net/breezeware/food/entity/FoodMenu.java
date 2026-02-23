package net.breezeware.food.entity;

public class FoodMenu {

    private int id;
    private String category;
    private String createdOn;
    private String updatedOn;

    public FoodMenu() {
    }

    public FoodMenu(int id, String category,
                    String createdOn, String updatedOn) {
        this.id        = id;
        this.category  = category;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }



    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }



    @Override
    public String toString() {
        return "FoodMenu{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}