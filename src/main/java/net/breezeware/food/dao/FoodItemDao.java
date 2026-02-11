package net.breezeware.food.dao;

import net.breezeware.food.entity.FoodItem;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodItemDao {

    // ---------- CREATE ----------
    public int create(FoodItem item) {

        String sql = """
            INSERT INTO Food_Item
            (name, price, category, description, quantity, created_on)
            VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getCategory());
            ps.setString(4, item.getDescription());
            ps.setInt(5, item.getQuantity());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException("Create food item failed", e);
        }
    }

    // ---------- UPDATE ----------
    public boolean update(FoodItem item) {

        String sql = """
            UPDATE Food_Item
            SET name = ?, price = ?, category = ?, description = ?, quantity = ?, updated_on = CURRENT_TIMESTAMP
            WHERE id = ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getCategory());
            ps.setString(4, item.getDescription());
            ps.setInt(5, item.getQuantity());
            ps.setInt(6, item.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Update food item failed", e);
        }
    }

    // ---------- DELETE ----------
    public boolean delete(int foodItemId) {

        String sql = "DELETE FROM Food_Item WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, foodItemId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Delete food item failed", e);
        }
    }

    // ---------- FIND BY ID ----------
    public FoodItem findById(int id) {

        String sql = "SELECT * FROM Food_Item WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;

            return new FoodItem(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("description"),
                    rs.getInt("quantity")
            );

        } catch (SQLException e) {
            throw new RuntimeException("Fetch food item failed", e);
        }
    }

    // ---------- FIND ALL ----------
    public List<FoodItem> findAll() {

        List<FoodItem> items = new ArrayList<>();
        String sql = "SELECT * FROM Food_Item";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

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
            throw new RuntimeException("Fetch all food items failed", e);
        }

        return items;
    }
}
