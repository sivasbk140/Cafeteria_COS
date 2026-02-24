package net.breezeware.order.service;

import net.breezeware.order.dao.OrderDao;

import net.breezeware.order.entity.Order;
import net.breezeware.order.enumeration.OrderStatus;


import java.util.Scanner;




public class DeliveryStaffFoodService {

    private final OrderDao orderDao;
    private final Scanner scanner;

    public DeliveryStaffFoodService() {
        this.orderDao = new OrderDao();
        this.scanner = new Scanner(System.in);
    }
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
            System.out.println("Order not found.");
            return;
        }

        OrderStatus currentStatus = order.getStatus();
        System.out.println("\nCurrent Status: " + currentStatus);

        OrderStatus newStatus;

        if (currentStatus == OrderStatus.WAITING_FOR_DELIVERY) {
            System.out.println("1. Mark as PENDING_DELIVERY (Picked up)");
            newStatus = OrderStatus.PENDING_DELIVERY;
        }
        else if (currentStatus == OrderStatus.PENDING_DELIVERY) {
            System.out.println("1. Mark as ORDER_DELIVERED");
            newStatus = OrderStatus.ORDER_DELIVERED;
        }
        else {
            System.out.println("Delivery staff cannot update this order status.");
            return;
        }

        System.out.print("Enter choice: ");
        int choice;

        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        if (choice != 1) {
            System.out.println("Invalid choice.");
            return;
        }

        boolean success = orderDao.updateOrderStatus(orderId, newStatus);
        if (success) {
            System.out.println("Order status updated to: " + newStatus);
        } else {
            System.out.println("Failed to update order status.");
        }
    }
}
