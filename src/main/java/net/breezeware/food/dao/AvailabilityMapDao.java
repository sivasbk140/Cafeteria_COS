package net.breezeware.food.dao;

import net.breezeware.food.entity.AvailabilityMap;
import net.breezeware.food.entity.MenuDay;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


    public boolean removeMenuFromDay(int menuId, MenuDay day) {
        String sql = "DELETE FROM Availability_Map WHERE menu_id = ? AND menu_day = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, menuId);
            pstmt.setString(2, day.name());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to remove menu from day.");
            e.printStackTrace();
        }

        return false;
    }


    public List<Integer> getMenuIdsByDay(MenuDay day) {
        List<Integer> menuIds = new ArrayList<>();
        String sql = "SELECT menu_id FROM Availability_Map WHERE menu_day = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, day.name());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                menuIds.add(rs.getInt("menu_id"));
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch menu IDs for day " + day);
            e.printStackTrace();
        }

        return menuIds;
    }


    public List<AvailabilityMap> getAllAvailabilityMappings() {
        List<AvailabilityMap> mappings = new ArrayList<>();
        String sql = "SELECT id, menu_id, menu_day, created_on, updated_on FROM Availability_Map";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                MenuDay day = MenuDay.fromString(rs.getString("menu_day"));
                if (day != null) {
                    AvailabilityMap map = new AvailabilityMap(
                            rs.getInt("id"),
                            rs.getInt("menu_id"),
                            day,
                            rs.getString("created_on"),
                            rs.getString("updated_on")
                    );
                    mappings.add(map);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch availability mappings.");
            e.printStackTrace();
        }

        return mappings;
    }


    public List<AvailabilityMap> getAvailabilityByDay(MenuDay day) {
        List<AvailabilityMap> mappings = new ArrayList<>();
        String sql = "SELECT id, menu_id, menu_day, created_on, updated_on FROM Availability_Map WHERE menu_day = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, day.name());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                AvailabilityMap map = new AvailabilityMap(
                        rs.getInt("id"),
                        rs.getInt("menu_id"),
                        day,
                        rs.getString("created_on"),
                        rs.getString("updated_on")
                );
                mappings.add(map);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch availability for day " + day);
            e.printStackTrace();
        }

        return mappings;
    }
}