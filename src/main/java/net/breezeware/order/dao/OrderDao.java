package net.breezeware.order.dao;

import net.breezeware.order.entity.Order;
import net.breezeware.order.entity.OrderStatus;
import net.breezeware.util.DBConnection;

import java.sql.*;

public class OrderDao {

    public int create(Order order) throws SQLException {

        String sql = """
            INSERT INTO Orders (user_id, status, created_on)
            VALUES (?, ?, CURRENT_TIMESTAMP)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setString(2, order.getStatus().name());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public void updateStatus(int orderId, OrderStatus status)
            throws SQLException {

        String sql = """
            UPDATE Orders SET status = ?
            WHERE id = ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status.name());
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }
    }
}
