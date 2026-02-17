package net.breezeware.order.dao;

import net.breezeware.order.entity.OrderItem;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemdao {

    private List<OrderItem> items;

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


    public static List<OrderItem> findByOrderId(int orderId) throws SQLException {

        String sql = "SELECT "+
        "fmm.menu_id AS menu_id, " +
                "fi.name AS food_item_name, " +
        " fi.id AS food_item_id,"+
                "oi.quantity AS quantity,"+
        "(oi.quantity * fi.price) AS total_price" +
        "FROM Order_Items oi" +
        "JOIN Food_Menu_Items_Map fmm " +
        "ON oi.food_menu_item_id = fmm.id " +
       " JOIN Food_Item fi " +
       "ON fmm.food_item_id = fi.id" +
       "JOIN Food_Menu fm" +
       "ON fmm.menu_id = fm.id " +
       "WHERE oi.order_id = ?;" ;


        List<OrderItem> items = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderItem item = new OrderItem();
                    item.setId(rs.getInt("id"));
                    item.setOrderId(rs.getInt("order_id"));
                    item.setFoodMenuItemId(rs.getInt("food_menu_item_id"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));
                    items.add(item);
                }
            }
        }
        return items;
    }

}
