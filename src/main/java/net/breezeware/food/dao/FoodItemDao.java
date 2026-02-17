package net.breezeware.food.dao;

import net.breezeware.food.dto.FoodItemDTO;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodItemDao {

    // ─── Get All Food Items ──────────────────────────────────────
    public List<FoodItemDTO> getAllFoodItems() {
        List<FoodItemDTO> items = new ArrayList<>();
        String sql = "SELECT id, name, price, quantity, category, description FROM Food_Item";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                FoodItemDTO dto = new FoodItemDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("category"),
                        rs.getString("description")
                );
                items.add(dto);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch food items.");
            e.printStackTrace();
        }

        return items;
    }

    // ─── Get Food Item By ID ─────────────────────────────────────
    public FoodItemDTO getFoodItemById(int id) {
        String sql = "SELECT id, name, price, quantity, category, description FROM Food_Item WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new FoodItemDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("category"),
                        rs.getString("description")
                );
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch food item with ID " + id);
            e.printStackTrace();
        }

        return null;
    }

    // ─── Add Food Item ───────────────────────────────────────────
    public int addFoodItem(String name, double price, int quantity,
                           String category, String description) {
        String sql = "INSERT INTO Food_Item (name, price, quantity, category, description, created_on, updated_on) " +
                "VALUES (?, ?, ?, ?, ?, datetime('now'), datetime('now'))";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, category);
            pstmt.setString(5, description);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // return generated ID
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to add food item.");
            e.printStackTrace();
        }

        return -1; // failure
    }

    // ─── Update Food Item ────────────────────────────────────────
    public boolean updateFoodItem(int id, String name, double price, int quantity,
                                  String category, String description) {
        String sql = "UPDATE Food_Item SET name = ?, price = ?, quantity = ?, " +
                "category = ?, description = ?, updated_on = datetime('now') WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, category);
            pstmt.setString(5, description);
            pstmt.setInt(6, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to update food item.");
            e.printStackTrace();
        }

        return false;
    }

    // ─── Delete Food Item ────────────────────────────────────────
    public boolean deleteFoodItem(int id) {
        // First delete from Food_Menu_Items_Map (foreign key dependency)
        String deleteMappingSql = "DELETE FROM Food_Menu_Items_Map WHERE food_item_id = ?";
        String deleteItemSql = "DELETE FROM Food_Item WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {

            // Delete mappings first
            try (PreparedStatement pstmt = conn.prepareStatement(deleteMappingSql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }

            // Then delete the item
            try (PreparedStatement pstmt = conn.prepareStatement(deleteItemSql)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to delete food item.");
            e.printStackTrace();
        }

        return false;
    }

    // ─── Get Food Items By Category ──────────────────────────────
    public List<FoodItemDTO> getFoodItemsByCategory(String category) {
        List<FoodItemDTO> items = new ArrayList<>();
        String sql = "SELECT id, name, price, quantity, category, description FROM Food_Item WHERE category = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FoodItemDTO dto = new FoodItemDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("category"),
                        rs.getString("description")
                );
                items.add(dto);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch items by category.");
            e.printStackTrace();
        }

        return items;
    }
}