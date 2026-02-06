package net.breezeware.food.dao;

import net.breezeware.food.entity.MenuDay;
import net.breezeware.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AvailabilityMapDao {

    public void setAvailability(int menuId, MenuDay day) throws SQLException {
        String sql = """
            INSERT INTO Availability_Map (menu_id, menu_day, created_on)
            VALUES (?, ?, TIMESTAMP)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menuId);
            ps.setString(2, day.name());
            ps.executeUpdate();
        }
    }
}
