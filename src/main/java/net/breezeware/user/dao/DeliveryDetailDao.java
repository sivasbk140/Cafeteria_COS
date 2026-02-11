package net.breezeware.user.dao;

import net.breezeware.user.entity.DeliveryDetail;
import net.breezeware.util.DBConnection;

import java.sql.*;

public class DeliveryDetailDao {

    public int create(DeliveryDetail detail) throws SQLException {

        String sql = """
            INSERT INTO Delivery_Details
            (email, phone_number, location, user_id, created_on)
            VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, detail.getEmail());
            ps.setString(2, detail.getPhoneNumber());
            ps.setString(3, detail.getLocation());
            ps.setInt(4, detail.getUserId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
}
