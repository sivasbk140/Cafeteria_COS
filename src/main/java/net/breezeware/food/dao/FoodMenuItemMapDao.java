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

    public List<FoodMenuItemMap> getMappingsByMenuId(int menuId) {
        List<FoodMenuItemMap> mappings = new ArrayList<>();
        String sql = "SELECT id, menu_id, food_item_id, is_available, created_on, updated_on " +
                "FROM Food_Menu_Items_Map WHERE menu_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FoodMenuItemMap map = new FoodMenuItemMap(
                        rs.getInt("id"),
                        rs.getInt("menu_id"),
                        rs.getInt("food_item_id"),
                        rs.getInt("is_available") == 1,
                        rs.getString("created_on"),
                        rs.getString("updated_on")
                );
                mappings.add(map);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch mappings for menu ID " + menuId);
            e.printStackTrace();
        }

        return mappings;
    }

    //  Get All Food Item IDs for a Menu
    public List<Integer> getFoodItemIdsByMenuId(int menuId) {
        List<Integer> itemIds = new ArrayList<>();
        String sql = "SELECT food_item_id FROM Food_Menu_Items_Map WHERE menu_id = ? AND is_available = 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                itemIds.add(rs.getInt("food_item_id"));
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch food item IDs for menu.");
            e.printStackTrace();
        }

        return itemIds;
    }


    public boolean toggleAvailability(int menuId, int foodItemId, boolean isAvailable) {
        String sql = "UPDATE Food_Menu_Items_Map SET is_available = ?, updated_on = datetime('now') " +
                "WHERE menu_id = ? AND food_item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, isAvailable ? 1 : 0);
            pstmt.setInt(2, menuId);
            pstmt.setInt(3, foodItemId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to toggle availability.");
            e.printStackTrace();
        }

        return false;
    }
}