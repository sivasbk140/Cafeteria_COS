package net.breezeware.food.dao;

import net.breezeware.food.entity.FoodMenuItemMap;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodMenuItemMapDao {

    //  Assign Food Item to Menu
    public boolean assignFoodItemToMenu(int menuId, int foodItemId) {
        String sql = "INSERT INTO Food_Menu_Items_Map (menu_id, food_item_id, is_available, created_on, updated_on) " +
                "VALUES (?, ?, 1, datetime('now'), datetime('now'))";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);
            pstmt.setInt(2, foodItemId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to assign food item to menu.");
            e.printStackTrace();
        }

        return false;
    }

    //  Remove Food Item from Menu
    public boolean removeFoodItemFromMenu(int menuId, int foodItemId) {
        String sql = "DELETE FROM Food_Menu_Items_Map WHERE menu_id = ? AND food_item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);
            pstmt.setInt(2, foodItemId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to remove food item from menu.");
            e.printStackTrace();
        }

        return false;
    }


}