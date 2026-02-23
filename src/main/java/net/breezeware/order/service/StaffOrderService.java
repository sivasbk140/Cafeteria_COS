package net.breezeware.order.service;

import net.breezeware.order.dao.OrderDao;
import net.breezeware.order.dao.OrderItemDao;
import net.breezeware.order.dto.OrderSummaryDTO;
import net.breezeware.order.entity.Order;
import net.breezeware.order.entity.OrderStatus;
import net.breezeware.user.dao.DeliveryDetailDao;
import net.breezeware.user.dao.UserDao;
import net.breezeware.user.entity.DeliveryDetail;
import net.breezeware.user.entity.User;

import java.util.List;
import java.util.Scanner;

public class StaffOrderService {

    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final UserDao userDao;
    private final DeliveryDetailDao deliveryDetailDao;
    private final Scanner scanner;

    public StaffOrderService() {
        this.orderDao = new OrderDao();
        this.orderItemDao = new OrderItemDao();
        this.userDao = new UserDao();
        this.deliveryDetailDao = new DeliveryDetailDao();
        this.scanner = new Scanner(System.in);
    }

    //  View Active Orders
    public void viewActiveOrders() {
        List<OrderSummaryDTO> orders = orderDao.getActiveOrders();

        if (orders.isEmpty()) {
            System.out.println("\n No active orders.");
            return;
        }

        System.out.println("\n=== ACTIVE ORDERS ===");
        System.out.println("────────────────────────────────────────────────────────────────────");
        System.out.printf("%-10s | %-20s | %-12s | %s%n", "Order ID", "Status", "Total", "Date");
        System.out.println("────────────────────────────────────────────────────────────────────");

        for (OrderSummaryDTO order : orders) {
            System.out.printf("%-10d | %-20s | ₹%-11.2f | %s%n",
                    order.getOrderId(),
                    order.getStatus(),
                    order.getTotalPrice(),
                    order.getCreatedOn());
        }
        System.out.println("────────────────────────────────────────────────────────────────────");
    }

    // View Cancelled Orders
    public void viewCancelledOrders() {
        List<Order> orders = orderDao.getOrdersByStatus(OrderStatus.ORDER_CANCELLED);

        if (orders.isEmpty()) {
            System.out.println("\n No cancelled orders.");
            return;
        }

        System.out.println("\n=== CANCELLED ORDERS ===");
        System.out.println("─────────────────────────────────────────────");
        System.out.printf("%-10s | %-15s | %s%n", "Order ID", "User ID", "Date");
        System.out.println("─────────────────────────────────────────────");

        for (Order order : orders) {
            System.out.printf("%-10d | %-15d | %s%n",
                    order.getId(),
                    order.getUserId(),
                    order.getCreatedOn());
        }
        System.out.println("─────────────────────────────────────────────");
    }

    // View Completed Orders
    public void viewCompletedOrders() {
        List<Order> orders = orderDao.getOrdersByStatus(OrderStatus.ORDER_DELIVERED);

        if (orders.isEmpty()) {
            System.out.println("\n No completed orders.");
            return;
        }

        System.out.println("\n=== COMPLETED ORDERS ===");
        System.out.println("─────────────────────────────────────────────");
        System.out.printf("%-10s | %-15s | %s%n", "Order ID", "User ID", "Date");
        System.out.println("─────────────────────────────────────────────");

        for (Order order : orders) {
            System.out.printf("%-10d | %-15d | %s%n",
                    order.getId(),
                    order.getUserId(),
                    order.getCreatedOn());
        }
        System.out.println("─────────────────────────────────────────────");
    }

    //  View Order Details
    public void viewOrderDetails() {
        System.out.print("\nEnter Order ID: ");
        int orderId;
        try {
            orderId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Order ID.");
            return;
        }

        Order order = orderDao.getOrderById(orderId);
        if (order == null) {
            System.out.println(" Order not found.");
            return;
        }

        User user = userDao.getUserById(order.getUserId());
        List<OrderItemDao.OrderItemWithName> items = orderItemDao.getItemsWithNamesByOrderId(orderId);
        double total = orderDao.getTotalPrice(orderId);
        List<DeliveryDetail> deliveryDetails = deliveryDetailDao.getDeliveryDetailsByUserId(order.getUserId());

        System.out.println("\n=== ORDER #" + orderId + " DETAILS ===");
        System.out.println("Customer : " + (user != null ? user.getName() : "Unknown"));
        System.out.println("Status   : " + order.getStatus());
        System.out.println("Date     : " + order.getCreatedOn());

        if (!deliveryDetails.isEmpty()) {
            DeliveryDetail latest = deliveryDetails.get(deliveryDetails.size() - 1);
            System.out.println("\nDelivery Info:");
            System.out.println("  Email    : " + latest.getEmail());
            System.out.println("  Phone    : " + latest.getPhoneNumber());
            System.out.println("  Location : " + latest.getLocation());
        }

        System.out.println("\nItems:");
        System.out.println("────────────────────────────────────────────────────────");
        System.out.printf("%-5s | %-20s | %-8s | %-10s%n", "S.No", "Food Name", "Quantity", "Price");
        System.out.println("────────────────────────────────────────────────────────");

        int sno = 1;
        for (OrderItemDao.OrderItemWithName item : items) {
            System.out.printf("%-5d | %-20s | %-8d | ₹%-9.2f%n",
                    sno++, item.getFoodName(), item.getQuantity(), item.getTotalPrice());
        }

        System.out.println("────────────────────────────────────────────────────────");
        System.out.printf("%47s ₹%.2f%n", "TOTAL: ", total);
        System.out.println("────────────────────────────────────────────────────────");
    }

    //  Update Order Status
    public void updateOrderStatus() {
        System.out.print("\nEnter Order ID: ");
        int orderId;
        try {
            orderId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Order ID.");
            return;
        }

        Order order = orderDao.getOrderById(orderId);
        if (order == null) {
            System.out.println(" Order not found.");
            return;
        }

        System.out.println("\nCurrent Status: " + order.getStatus());
        System.out.println("\nUpdate status to:");

        OrderStatus currentStatus = order.getStatus();

        if (currentStatus == OrderStatus.PLACED_ORDER) {
            System.out.println("1. WAITING_FOR_DELIVERY (Mark as prepared)");
        } else if (currentStatus == OrderStatus.WAITING_FOR_DELIVERY) {
            System.out.println("1. PENDING_DELIVERY (Given to delivery staff)");
        } else if (currentStatus == OrderStatus.PENDING_DELIVERY) {
            System.out.println("1. ORDER_DELIVERED (Mark as delivered)");
        } else {
            System.out.println(" Cannot update status from " + currentStatus);
            return;
        }

        System.out.println("2. Cancel");
        System.out.print("Enter choice: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        if (choice == 2) {
            System.out.println("Update cancelled.");
            return;
        }

        if (choice != 1) {
            System.out.println("Invalid choice.");
            return;
        }

        OrderStatus newStatus = null;
        if (currentStatus == OrderStatus.PLACED_ORDER) {
            newStatus = OrderStatus.WAITING_FOR_DELIVERY;
        } else if (currentStatus == OrderStatus.WAITING_FOR_DELIVERY) {
            newStatus = OrderStatus.PENDING_DELIVERY;
        } else if (currentStatus == OrderStatus.PENDING_DELIVERY) {
            newStatus = OrderStatus.ORDER_DELIVERED;
        }

        if (newStatus != null) {
            boolean success = orderDao.updateOrderStatus(orderId, newStatus);
            if (success) {
                System.out.println(" Order status updated to: " + newStatus);
                if (newStatus == OrderStatus.WAITING_FOR_DELIVERY) {
                    System.out.println(" Delivery staff has been notified (mock)");
                }
            } else {
                System.out.println(" Failed to update order status.");
            }
        }
    }
}
