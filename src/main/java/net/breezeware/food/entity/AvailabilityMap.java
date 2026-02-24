package net.breezeware.food.entity;

import net.breezeware.food.enumeration.MenuDay;

public class AvailabilityMap {

 private int id;
 private int menuId;
 private MenuDay menuDay;
 private String createdOn;
 private String updatedOn;

 public AvailabilityMap() {
 }

 public AvailabilityMap(int id, int menuId, MenuDay menuDay,
                        String createdOn, String updatedOn) {
  this.id        = id;
  this.menuId    = menuId;
  this.menuDay   = menuDay;
  this.createdOn = createdOn;
  this.updatedOn = updatedOn;
 }



 public int getId() {
  return id;
 }

 public int getMenuId() {
  return menuId;
 }

 public MenuDay getMenuDay() {
  return menuDay;
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

 public void setMenuId(int menuId) {
  this.menuId = menuId;
 }

 public void setMenuDay(MenuDay menuDay) {
  this.menuDay = menuDay;
 }

 public void setCreatedOn(String createdOn) {
  this.createdOn = createdOn;
 }

 public void setUpdatedOn(String updatedOn) {
  this.updatedOn = updatedOn;
 }



 @Override
 public String toString() {
  return "AvailabilityMap{" +
          "id=" + id +
          ", menuId=" + menuId +
          ", menuDay=" + menuDay +
          '}';
 }
}