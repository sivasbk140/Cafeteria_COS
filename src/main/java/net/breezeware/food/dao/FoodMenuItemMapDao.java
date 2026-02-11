package net.breezeware.food.dao;

import net.breezeware.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FoodMenuItemMapDao {

    private static final String INSERT_SQL =
            "INSERT INTO Food_Menu_Items_Map " +
                    "(menu_id, food_item_id, is_available, created_on) " +
                    "VALUES (?, ?, 1, CURRENT_TIMESTAMP)";

    private static final String DELETE_BY_ITEM =
            "DELETE FROM Food_Menu_Items_Map WHERE food_item_id = ?";

    public void create(int menuId, int foodItemId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {

            ps.setInt(1, menuId);
            ps.setInt(2, foodItemId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to map food item to menu", e);
        }
    }

    public void deleteByFoodItem(int foodItemId) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_BY_ITEM)) {

            ps.setInt(1, foodItemId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete menu mapping", e);
        }
    }
}
