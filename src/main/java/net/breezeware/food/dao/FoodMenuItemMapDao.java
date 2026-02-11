package net.breezeware.food.dao;

import net.breezeware.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FoodMenuItemMapDao {

    private static final String INSERT_SQL =
            "INSERT INTO Food_Menu_Items_Map (menu_id, food_item_id, is_available, created_on) " +
                    "VALUES (?, ?, 1, CURRENT_TIMESTAMP)";


    public void create(int menuId, int foodItemId) throws SQLException {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {

            ps.setInt(1, menuId);
            ps.setInt(2, foodItemId);

            ps.executeUpdate();
        }
    }
}
