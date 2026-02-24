package net.breezeware.user.dao;

import net.breezeware.user.entity.DeliveryDetail;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDetailDao {

    // Add Delivery Detail
    public int addDeliveryDetail(int userId, String email, String phoneNumber, String location) {
        String sql = "INSERT INTO Delivery_Details (user_id, email, phone_number, location, created_on, updated_on) " +
                "VALUES (?, ?, ?, ?, datetime('now'), datetime('now'))";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, email);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, location);

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

    // Get Delivery Details By User ID
    public List<DeliveryDetail> getDeliveryDetailsByUserId(int userId) {
        List<DeliveryDetail> details = new ArrayList<>();
        String sql = "SELECT id, user_id, email, phone_number, location, created_on, updated_on FROM Delivery_Details WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DeliveryDetail detail = new DeliveryDetail(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("location"),
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






}
