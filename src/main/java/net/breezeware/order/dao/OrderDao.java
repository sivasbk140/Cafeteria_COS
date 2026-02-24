package net.breezeware.order.dao;

import net.breezeware.order.dto.OrderSummaryDto;
import net.breezeware.order.entity.Order;
import net.breezeware.order.enumeration.OrderStatus;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    //  Create Order
    public int createOrder(int userId, OrderStatus status) {
        String sql = "INSERT INTO Order_Table (user_id, status, created_on, updated_on) " +
                "VALUES (?, ?, datetime('now'), datetime('now'))";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, status.name());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to create order.");
            e.printStackTrace();
        }

        return -1;
    }

    //  Get Order By ID
    public Order getOrderById(int orderId) {
        String sql = "SELECT id, user_id, status, created_on, updated_on FROM Order_Table WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                OrderStatus status = OrderStatus.fromString(rs.getString("status"));
                if (status != null) {
                    return new Order(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            status,
                            rs.getString("created_on"),
                            rs.getString("updated_on")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch order.");
            e.printStackTrace();
        }

        return null;
    }



    // Get Order Summaries By User (with total price)
    public List<OrderSummaryDto> getOrderSummariesByUserId(int userId) {
        List<OrderSummaryDto> summaries = new ArrayList<>();

        String sql = "SELECT o.id, o.user_id, o.status, o.created_on, " +
                "       COALESCE(SUM(oi.price * oi.quantity), 0) AS total_price " +
                "FROM Order_Table o " +
                "LEFT JOIN Order_Items oi ON o.id = oi.order_id " +
                "WHERE o.user_id = ? " +
                "GROUP BY o.id, o.user_id, o.status, o.created_on " +
                "ORDER BY o.created_on DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderStatus status = OrderStatus.fromString(rs.getString("status"));
                if (status != null) {
                    OrderSummaryDto summary = new OrderSummaryDto(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            status,
                            rs.getDouble("total_price"),
                            rs.getString("created_on")
                    );
                    summaries.add(summary);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch order summaries.");
            e.printStackTrace();
        }

        return summaries;
    }

    // Get Orders By Status
    public List<Order> getOrdersByStatus(OrderStatus status) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT id, user_id, status, created_on, updated_on FROM Order_Table WHERE status = ? ORDER BY created_on ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status.name());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        status,
                        rs.getString("created_on"),
                        rs.getString("updated_on")
                );
                orders.add(order);
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch orders by status.");
            e.printStackTrace();
        }

        return orders;
    }

    // Get Active Orders (for staff)
    public List<OrderSummaryDto> getActiveOrders() {
        List<OrderSummaryDto> summaries = new ArrayList<>();

        String sql = "SELECT o.id, o.user_id, o.status, o.created_on, " +
                "       COALESCE(SUM(oi.price * oi.quantity), 0) AS total_price " +
                "FROM Order_Table o " +
                "LEFT JOIN Order_Items oi ON o.id = oi.order_id " +
                "WHERE o.status IN ('PLACED_ORDER', 'WAITING_FOR_DELIVERY', 'PENDING_DELIVERY') " +
                "GROUP BY o.id, o.user_id, o.status, o.created_on " +
                "ORDER BY o.created_on ASC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                OrderStatus status = OrderStatus.fromString(rs.getString("status"));
                if (status != null) {
                    OrderSummaryDto summary = new OrderSummaryDto(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            status,
                            rs.getDouble("total_price"),
                            rs.getString("created_on")
                    );
                    summaries.add(summary);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch active orders.");
            e.printStackTrace();
        }

        return summaries;
    }

    //  Update Order Status
    public boolean updateOrderStatus(int orderId, OrderStatus newStatus) {
        String sql = "UPDATE Order_Table SET status = ?, updated_on = datetime('now') WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newStatus.name());
            pstmt.setInt(2, orderId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to update order status.");
            e.printStackTrace();
        }

        return false;
    }

    // Get Total Price for Order
    public double getTotalPrice(int orderId) {
        String sql = "SELECT COALESCE(SUM(price * quantity), 0) AS total FROM Order_Items WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to calculate total price.");
            e.printStackTrace();
        }

        return 0.0;
    }



}