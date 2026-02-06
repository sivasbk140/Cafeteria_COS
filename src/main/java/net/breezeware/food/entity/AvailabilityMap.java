package net.breezeware.food.entity;

import java.time.Instant;

public class AvailabilityMap {
 private int id;
 private int menu_Id;
 private  MenuDay menuday;

 private Instant updatedOn;
 private Instant createdOn;


 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public int getMenu_Id() {
  return menu_Id;
 }

 public void setMenu_Id(int menu_Id) {
  this.menu_Id = menu_Id;
 }

 public MenuDay getMenuday() {
  return menuday;
 }

 public void setMenuday(MenuDay menuday) {
  this.menuday = menuday;
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
