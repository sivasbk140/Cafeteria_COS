package net.breezeware.order.dao;

import net.breezeware.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDeliveryMapDao {

    public void create(int orderId, int deliveryId)
            throws SQLException {

        String sql = """
            INSERT INTO Order_Delivery_Map
            (order_id, delivery_id, created_on)
            VALUES (?, ?, CURRENT_TIMESTAMP)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, deliveryId);
            ps.executeUpdate();
        }
    }
}
