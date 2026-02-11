package net.breezeware.order.dao;

import net.breezeware.order.entity.OrderItem;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemdao {

    public void create(OrderItem item) throws SQLException {

        String sql = """
            INSERT INTO Order_Items
            (order_id, food_menu_item_id, quantity, price, created_on)
            VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getFoodMenuItemId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPrice());
            ps.executeUpdate();
        }
    }

    public List<OrderItem> findByOrderId(int orderId)
            throws SQLException {

        String sql = """
            SELECT * FROM Order_Items WHERE order_id = ?
        """;

        List<OrderItem> items = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setFoodMenuItemId(
                        rs.getInt("food_menu_item_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                items.add(item);
            }
        }
        return items;
    }
}
