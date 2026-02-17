package net.breezeware.food.entity;

public class FoodMenuItemMap {

 private int id;
 private int menuId;
 private int foodItemId;
 private boolean isAvailable;
 private String createdOn;
 private String updatedOn;

 public FoodMenuItemMap() {
 }

 public FoodMenuItemMap(int id, int menuId, int foodItemId,
                        boolean isAvailable,
                        String createdOn, String updatedOn) {
  this.id          = id;
  this.menuId      = menuId;
  this.foodItemId  = foodItemId;
  this.isAvailable = isAvailable;
  this.createdOn   = createdOn;
  this.updatedOn   = updatedOn;
 }

 // ─── Getters ─────────────────────────────────────────────────

 public int getId() {
  return id;
 }

 public int getMenuId() {
  return menuId;
 }

 public int getFoodItemId() {
  return foodItemId;
 }

 public boolean isAvailable() {
  return isAvailable;
 }

 public String getCreatedOn() {
  return createdOn;
 }

 public String getUpdatedOn() {
  return updatedOn;
 }

 // ─── Setters ─────────────────────────────────────────────────

 public void setId(int id) {
  this.id = id;
 }

 public void setMenuId(int menuId) {
  this.menuId = menuId;
 }

 public void setFoodItemId(int foodItemId) {
  this.foodItemId = foodItemId;
 }

 public void setAvailable(boolean available) {
  this.isAvailable = available;
 }

 public void setCreatedOn(String createdOn) {
  this.createdOn = createdOn;
 }

 public void setUpdatedOn(String updatedOn) {
  this.updatedOn = updatedOn;
 }

 // ─── toString ────────────────────────────────────────────────

 @Override
 public String toString() {
  return "FoodMenuItemMap{" +
          "id=" + id +
          ", menuId=" + menuId +
          ", foodItemId=" + foodItemId +
          ", isAvailable=" + isAvailable +
          '}';
 }
}