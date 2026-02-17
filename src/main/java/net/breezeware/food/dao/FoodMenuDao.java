package net.breezeware.food.dao;

import net.breezeware.food.entity.FoodMenu;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodMenuDao {

    // ─── Get All Menus ───────────────────────────────────────────
    public List<FoodMenu> getAllMenus() {
        List<FoodMenu> menus = new ArrayList<>();
        String sql = "SELECT id, category, created_on, updated_on FROM Food_Menu";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                FoodMenu menu = new FoodMenu(
                        rs.getInt("id"),
                        rs.getString("category"),
                        rs.getString("created_on"),
                        rs.getString("updated_on")
                );
                menus.add(menu);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch menus.");
            e.printStackTrace();
        }

        return menus;
    }

    // ─── Get Menu By ID ──────────────────────────────────────────
    public FoodMenu getMenuById(int id) {
        String sql = "SELECT id, category, created_on, updated_on FROM Food_Menu WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new FoodMenu(
                        rs.getInt("id"),
                        rs.getString("category"),
                        rs.getString("created_on"),
                        rs.getString("updated_on")
                );
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch menu with ID " + id);
            e.printStackTrace();
        }

        return null;
    }

    // ─── Create New Menu ─────────────────────────────────────────
    public int createMenu(String category) {
        String sql = "INSERT INTO Food_Menu (category, created_on, updated_on) " +
                "VALUES (?, datetime('now'), datetime('now'))";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, category);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to create menu.");
            e.printStackTrace();
        }

        return -1;
    }

    // ─── Delete Menu ─────────────────────────────────────────────
    public boolean deleteMenu(int menuId) {
        // First delete from Availability_Map and Food_Menu_Items_Map
        String deleteAvailabilitySql = "DELETE FROM Availability_Map WHERE menu_id = ?";
        String deleteItemMapSql = "DELETE FROM Food_Menu_Items_Map WHERE menu_id = ?";
        String deleteMenuSql = "DELETE FROM Food_Menu WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {

            // Delete availability mappings
            try (PreparedStatement pstmt = conn.prepareStatement(deleteAvailabilitySql)) {
                pstmt.setInt(1, menuId);
                pstmt.executeUpdate();
            }

            // Delete item mappings
            try (PreparedStatement pstmt = conn.prepareStatement(deleteItemMapSql)) {
                pstmt.setInt(1, menuId);
                pstmt.executeUpdate();
            }

            // Delete menu
            try (PreparedStatement pstmt = conn.prepareStatement(deleteMenuSql)) {
                pstmt.setInt(1, menuId);
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to delete menu.");
            e.printStackTrace();
        }

        return false;
    }

    // ─── Get Menus By Category ───────────────────────────────────
    public List<FoodMenu> getMenusByCategory(String category) {
        List<FoodMenu> menus = new ArrayList<>();
        String sql = "SELECT id, category, created_on, updated_on FROM Food_Menu WHERE category = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FoodMenu menu = new FoodMenu(
                        rs.getInt("id"),
                        rs.getString("category"),
                        rs.getString("created_on"),
                        rs.getString("updated_on")
                );
                menus.add(menu);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch menus by category.");
            e.printStackTrace();
        }

        return menus;
    }
}