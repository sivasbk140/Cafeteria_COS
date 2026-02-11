package net.breezeware.food.dao;

import net.breezeware.food.entity.FoodItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerMenuDao {

    private final String DB_URL = "jdbc:sqlite:C:/Users/preet/Cafeteria_COS/cafeteria.db";



    public List<FoodItem> fetchMenu(int menuId) {
        List<FoodItem> items = new ArrayList<>();
        String sql = "SELECT * FROM Food_Item WHERE menu_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, menuId);
            ResultSet rs = stmt.executeQuery();

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


    public List<FoodItem> fetchAllMenus() {
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
