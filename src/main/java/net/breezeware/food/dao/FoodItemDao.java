package net.breezeware.food.dao;

import net.breezeware.food.entity.FoodItem;
import net.breezeware.util.DBConnection;

import java.sql.*;

public class FoodItemDao {

    public int create(FoodItem item) {

        String sql =
                "INSERT INTO Food_Item (name, price, category, created_on) " +
                        "VALUES (?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getCategory());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create food item", e);
        }
    }


    public void delete(int foodItemId) {

        String sql = "DELETE FROM Food_Item WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, foodItemId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete food item", e);
        }
    }
}
