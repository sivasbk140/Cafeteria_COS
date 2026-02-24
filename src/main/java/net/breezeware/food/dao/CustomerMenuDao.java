package net.breezeware.food.dao;

import net.breezeware.food.dto.MenuViewDto;
import net.breezeware.food.enumeration.MenuDay;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerMenuDao {


    public List<MenuViewDto> getFullWeekMenu() {
        List<MenuViewDto> menuViews = new ArrayList<>();

        String sql = "SELECT a.menu_day, m.category, f.name, f.price, f.description " +
                "FROM Availability_Map a " +
                "JOIN Food_Menu m ON a.menu_id = m.id " +
                "JOIN Food_Menu_Items_Map fmim ON m.id = fmim.menu_id " +
                "JOIN Food_Item f ON fmim.food_item_id = f.id " +
                "WHERE fmim.is_available = 1 " +
                "ORDER BY " +
                "  CASE a.menu_day " +
                "    WHEN 'MONDAY' THEN 1 " +
                "    WHEN 'TUESDAY' THEN 2 " +
                "    WHEN 'WEDNESDAY' THEN 3 " +
                "    WHEN 'THURSDAY' THEN 4 " +
                "    WHEN 'FRIDAY' THEN 5 " +
                "    WHEN 'SATURDAY' THEN 6 " +
                "    WHEN 'SUNDAY' THEN 7 " +
                "WHEN 'ALLDAY' THEN 8"+
                "  END, " +
                "  CASE m.category " +
                "    WHEN 'BREAKFAST' THEN 1 " +
                "    WHEN 'LUNCH' THEN 2 " +
                "    WHEN 'DINNER' THEN 3 " +
                "  END";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                MenuDay day = MenuDay.fromString(rs.getString("menu_day"));
                if (day != null) {
                    MenuViewDto dto = new MenuViewDto(
                            day,
                            rs.getString("category"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("description")
                    );
                    menuViews.add(dto);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch full week menu.");
            e.printStackTrace();
        }

        return menuViews;
    }


    public List<MenuViewDto> getMenuByDay(MenuDay day) {
        List<MenuViewDto> menuViews = new ArrayList<>();

        String sql = "SELECT a.menu_day, m.category, f.name, f.price, f.description " +
                "FROM Availability_Map a " +
                "JOIN Food_Menu m ON a.menu_id = m.id " +
                "JOIN Food_Menu_Items_Map fmim ON m.id = fmim.menu_id " +
                "JOIN Food_Item f ON fmim.food_item_id = f.id " +
                "WHERE a.menu_day = ? AND fmim.is_available = 1 " +
                "ORDER BY " +
                "  CASE m.category " +
                "    WHEN 'BREAKFAST' THEN 1 " +
                "    WHEN 'LUNCH' THEN 2 " +
                "    WHEN 'DINNER' THEN 3 " +
                "WHEN 'CHINESE' THEN 4"+
                "  END";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, day.name());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                MenuViewDto dto = new MenuViewDto(
                        day,
                        rs.getString("category"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description")
                );
                menuViews.add(dto);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch menu for day " + day);
            e.printStackTrace();
        }

        return menuViews;
    }
}