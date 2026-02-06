package net.breezeware.food.entity;

import java.time.Instant;

public class FoodMenuItemMap {
 private int id;
 private int menu_id;
 private int item_id;
 private Instant updatedOn;
 private  Instant createdOn;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public int getMenu_id() {
  return menu_id;
 }

 public void setMenu_id(int menu_id) {
  this.menu_id = menu_id;
 }

 public int getItem_id() {
  return item_id;
 }

 public void setItem_id(int item_id) {
  this.item_id = item_id;
 }

 public Instant getUpdatedOn() {
  return updatedOn;
 }

 public void setUpdatedOn(Instant updatedOn) {
  this.updatedOn = updatedOn;
 }

 public Instant getCreatedOn() {
  return createdOn;
 }

 public void setCreatedOn(Instant createdOn) {
  this.createdOn = createdOn;
 }
}
