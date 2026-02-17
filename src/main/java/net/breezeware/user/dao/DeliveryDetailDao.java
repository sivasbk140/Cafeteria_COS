package net.breezeware.user.dao;

import net.breezeware.user.entity.DeliveryDetail;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDetailDao {

    // ─── Add Delivery Detail ─────────────────────────────────────
    public int addDeliveryDetail(int userId, String address, String phone) {
        String sql = "INSERT INTO Delivery_Details (user_id, address, phone, created_on, updated_on) " +
                "VALUES (?, ?, ?, datetime('now'), datetime('now'))";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, address);
            pstmt.setString(3, phone);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to add delivery detail.");
            e.printStackTrace();
        }

        return -1;
    }

    // ─── Get Delivery Details By User ID ─────────────────────────
    public List<DeliveryDetail> getDeliveryDetailsByUserId(int userId) {
        List<DeliveryDetail> details = new ArrayList<>();
        String sql = "SELECT id, user_id, address, phone, created_on, updated_on FROM Delivery_Details WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DeliveryDetail detail = new DeliveryDetail(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("created_on"),
                        rs.getString("updated_on")
                );
                details.add(detail);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch delivery details.");
            e.printStackTrace();
        }

        return details;
    }

    // ─── Update Delivery Detail ──────────────────────────────────
    public boolean updateDeliveryDetail(int id, String address, String phone) {
        String sql = "UPDATE Delivery_Details SET address = ?, phone = ?, updated_on = datetime('now') WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, address);
            pstmt.setString(2, phone);
            pstmt.setInt(3, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to update delivery detail.");
            e.printStackTrace();
        }

        return false;
    }

    // ─── Delete Delivery Detail ──────────────────────────────────
    public boolean deleteDeliveryDetail(int id) {
        String sql = "DELETE FROM Delivery_Details WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to delete delivery detail.");
            e.printStackTrace();
        }

        return false;
    }
}