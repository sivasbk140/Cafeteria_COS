package net.breezeware.food.entity;

public class FoodMenu {

    private int id;
    private String name;

    public FoodMenu() {
    }

    public FoodMenu(String name) {
        this.name = name;
    }

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
}
