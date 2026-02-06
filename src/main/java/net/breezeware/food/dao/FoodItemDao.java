package net.breezeware.food.dao;

import net.breezeware.food.entity.FoodItem;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodItemDao {

    public void create(FoodItem item) throws SQLException {
        String sql = """
            INSERT INTO Food_Item (name, price, quantity, category, created_on)
            VALUES (?, ?, ?, ?, TIMESTAMP)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getQuantity());
            ps.setString(4, item.getCategory());
            ps.executeUpdate();
        }
    }

    public List<FoodItem> findAll() throws SQLException {
        List<FoodItem> list = new ArrayList<>();
        String sql = "SELECT * FROM Food_Item";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                FoodItem item = new FoodItem();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getInt("price"));
                item.setQuantity(rs.getInt("quantity"));
                item.setCategory(rs.getString("category"));
                list.add(item);
            }
        }
        return list;
    }

    public void update(FoodItem item) throws SQLException {
        String sql = """
            UPDATE Food_Item
            SET name=?, price=?, quantity=?, category=?, updated_on=TIMESTAMP
            WHERE id=?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getQuantity());
            ps.setString(4, item.getCategory());
            ps.setInt(5, item.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement("DELETE FROM Food_Item WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
