package net.breezeware.food.dao;

import net.breezeware.food.entity.FoodItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodMenuDao {

    private final String DB_URL = "jdbc:sqlite:cafeteria.db";



    public boolean insertFoodItem(FoodItem item) {
        String sql = "INSERT INTO Food_Item (name, price, menu_id, category) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getId()); // menu_id
            stmt.setString(4, item.getCategory());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateFoodItem(FoodItem item) {
        String sql = "UPDATE Food_Item SET name = ?, price = ?, category = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setString(3, item.getCategory());
            stmt.setInt(4, item.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteFoodItem(int id) {
        String sql = "DELETE FROM Food_Item WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<FoodItem> fetchAllFoodItems() {
        List<FoodItem> items = new ArrayList<>();
        String sql = "SELECT * FROM Food_Item";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                items.add(new FoodItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }
}
