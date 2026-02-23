package net.breezeware.order.dao;

import net.breezeware.order.entity.OrderItem;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {

    //  Add Order Item
    public int addOrderItem(int orderId, int foodItemId, double price, int quantity) {
        String sql = "INSERT INTO Order_Items (order_id, food_item_id, price, quantity, created_on, updated_on) " +
                "VALUES (?, ?, ?, ?, datetime('now'), datetime('now'))";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, orderId);
            pstmt.setInt(2, foodItemId);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, quantity);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to add order item.");
            e.printStackTrace();
        }

        return -1;
    }

    // Get Order Items By Order ID
    public List<OrderItem> getItemsByOrderId(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT id, order_id, food_item_id, price, quantity, created_on, updated_on " +
                "FROM Order_Items WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("food_item_id"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("created_on"),
                        rs.getString("updated_on")
                );
                items.add(item);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch order items.");
            e.printStackTrace();
        }

        return items;
    }

    //  Get Order Items With Food Names (for display)
    public List<OrderItemWithName> getItemsWithNamesByOrderId(int orderId) {
        List<OrderItemWithName> items = new ArrayList<>();

        String sql = "SELECT oi.id, oi.order_id, oi.food_item_id, oi.price, oi.quantity, " +
                "       f.name AS food_name " +
                "FROM Order_Items oi " +
                "JOIN Food_Item f ON oi.food_item_id = f.id " +
                "WHERE oi.order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderItemWithName item = new OrderItemWithName(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("food_item_id"),
                        rs.getString("food_name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                items.add(item);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch order items with names.");
            e.printStackTrace();
        }

        return items;
    }

    // Update Order Item Quantity
    public boolean updateItemQuantity(int itemId, int newQuantity) {
        String sql = "UPDATE Order_Items SET quantity = ?, updated_on = datetime('now') WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, itemId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to update order item quantity.");
            e.printStackTrace();
        }

        return false;
    }

    //  Delete Order Item
    public boolean deleteOrderItem(int itemId) {
        String sql = "DELETE FROM Order_Items WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, itemId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to delete order item.");
            e.printStackTrace();
        }

        return false;
    }

    //  Delete All Items For Order
    public boolean deleteAllItemsForOrder(int orderId) {
        String sql = "DELETE FROM Order_Items WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to delete order items.");
            e.printStackTrace();
        }

        return false;
    }

    //  Helper Class for Items with Food Name
    public static class OrderItemWithName {
        private int id;
        private int orderId;
        private int foodItemId;
        private String foodName;
        private double price;
        private int quantity;

        public OrderItemWithName(int id, int orderId, int foodItemId,
                                 String foodName, double price, int quantity) {
            this.id         = id;
            this.orderId    = orderId;
            this.foodItemId = foodItemId;
            this.foodName   = foodName;
            this.price      = price;
            this.quantity   = quantity;
        }

        public int getId() { return id; }
        public int getOrderId() { return orderId; }
        public int getFoodItemId() { return foodItemId; }
        public String getFoodName() { return foodName; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }
        public double getTotalPrice() { return price * quantity; }
    }
}