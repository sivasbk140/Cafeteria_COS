package net.breezeware.food.dao;

import net.breezeware.food.enumeration.MenuDay;
import net.breezeware.util.DBConnection;

import java.sql.*;

public class AvailabilityMapDao {


    public boolean assignMenuToDay(int menuId, MenuDay day) {
        String sql = "INSERT INTO Availability_Map (menu_id, menu_day, created_on, updated_on) " +
                "VALUES (?, ?, datetime('now'), datetime('now'))";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);
            pstmt.setString(2, day.name());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to assign menu to day.");
            e.printStackTrace();
        }

        return false;
    }



}