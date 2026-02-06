package net.breezeware.food.dao;

import net.breezeware.food.entity.FoodMenu;
import net.breezeware.util.DBConnection;

import java.sql.*;



public class FoodMenuDao {

    public int create(FoodMenu menu) throws SQLException {

        String sql = """
            INSERT INTO Food_Menu (category, created_on)
            VALUES (?, CURRENT_TIMESTAMP)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, menu.getCategory());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public FoodMenu findById(int id) throws SQLException {

        String sql = "SELECT * FROM Food_Menu WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                FoodMenu menu = new FoodMenu();
                menu.setId(rs.getInt("id"));
                menu.setCategory(rs.getString("category"));
                return menu;
            }
        }
        return null;
    }

    public void update(FoodMenu menu) throws SQLException {

        String sql = """
            UPDATE Food_Menu
            SET category=?, updated_on=CURRENT_TIMESTAMP
            WHERE id=?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, menu.getCategory());
            ps.setInt(2, menu.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM Food_Menu WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}



