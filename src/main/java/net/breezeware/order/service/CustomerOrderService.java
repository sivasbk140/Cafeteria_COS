package net.breezeware.order.service;

import net.breezeware.food.dao.FoodItemDao;
import net.breezeware.food.dto.FoodItemDTO;
import net.breezeware.order.dao.OrderDao;
import net.breezeware.order.dao.OrderItemDao;
import net.breezeware.order.dto.CartItemDTO;
import net.breezeware.order.dto.OrderSummaryDTO;
import net.breezeware.order.entity.Order;
import net.breezeware.order.entity.OrderItem;
import net.breezeware.order.entity.OrderStatus;
import net.breezeware.user.dao.DeliveryDetailDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerOrderService {

    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final FoodItemDao foodItemDao;
    private final DeliveryDetailDao deliveryDetailDao;
    private final Scanner scanner;
    private final List<CartItemDTO> cart;
    private final int currentUserId;

    public CustomerOrderService(int userId) {
        this.orderDao = new OrderDao();
        this.orderItemDao = new OrderItemDao();
        this.foodItemDao = new FoodItemDao();
        this.deliveryDetailDao = new DeliveryDetailDao();
        this.scanner = new Scanner(System.in);
        this.cart = new ArrayList<>();
        this.currentUserId = userId;
    }

    // ─── Start Shopping (Add Items to Cart)
    public void startShopping() {
        System.out.println("\n=== START SHOPPING ===");
        System.out.println("(Tip: View the menu first to see available items and prices)");

        while (true) {
            System.out.print("\nEnter food name to add (or 'done' to finish): ");
            String foodName = scanner.nextLine().trim();

            if (foodName.equalsIgnoreCase("done")) {
                break;
            }

            // Find food item by name
            FoodItemDTO foodItem = foodItemDao.getFoodItemByName(foodName);

            if (foodItem == null) {
                System.out.println(" Food item '" + foodName + "' not found. Please check spelling.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity <= 0) {
                    System.out.println(" Quantity must be positive.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println(" Invalid quantity.");
                continue;
            }

            // Check stock availability
            if (!foodItemDao.hasEnoughStock(foodItem.getId(), quantity)) {
                System.out.println(" Not enough stock for " + foodItem.getName());
                System.out.println("   Available: " + foodItem.getQuantity());
                System.out.println("   Requested: " + quantity);
                continue;
            }

            // Check if item already in cart
            CartItemDTO existingItem = findInCart(foodItem.getId());
            if (existingItem != null) {
                // Update quantity
                int newQuantity = existingItem.getQuantity() + quantity;
                if (!foodItemDao.hasEnoughStock(foodItem.getId(), newQuantity)) {
                    System.out.println(" Not enough stock. You already have " + existingItem.getQuantity() + " in cart.");
                    continue;
                }
                existingItem.setQuantity(newQuantity);
                System.out.printf(" Updated: %dx %s = ₹%.2f%n",
                        newQuantity, existingItem.getFoodItemName(), existingItem.getTotalPrice());
            } else {
                // Add new item to cart
                CartItemDTO cartItem = new CartItemDTO(
                        foodItem.getId(),
                        foodItem.getName(),
                        foodItem.getPrice(),
                        quantity
                );
                cart.add(cartItem);
                System.out.printf(" Added: %dx %s = ₹%.2f%n",
                        quantity, cartItem.getFoodItemName(), cartItem.getTotalPrice());
            }

            System.out.printf("Current cart total: ₹%.2f%n", getCartTotal());

            System.out.print("\nAdd more items? (yes/no): ");
            String more = scanner.nextLine().trim();
            if (!more.equalsIgnoreCase("yes")) {
                break;
            }
        }

        if (!cart.isEmpty()) {
            viewCart();
            cartMenu();
        } else {
            System.out.println("\nYour cart is empty.");
        }
    }

    // ─── View Cart
    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("\n Your cart is empty.");
            return;
        }

        System.out.println("\n=== YOUR CART ===");
        System.out.println("────────────────────────────────────────────────────────");
        System.out.printf("%-5s | %-20s | %-8s | %-10s%n", "S.No", "Food Name", "Quantity", "Price");
        System.out.println("────────────────────────────────────────────────────────");

        int sno = 1;
        for (CartItemDTO item : cart) {
            System.out.printf("%-5d | %-20s | %-8d | ₹%-9.2f%n",
                    sno++, item.getFoodItemName(), item.getQuantity(), item.getTotalPrice());
        }

        System.out.println("────────────────────────────────────────────────────────");
        System.out.printf("%47s ₹%.2f%n", "TOTAL: ", getCartTotal());
        System.out.println("────────────────────────────────────────────────────────");
    }

    // ─── Cart Menu
    private void cartMenu() {
        while (true) {
            System.out.println("\n1. Proceed to Checkout");
            System.out.println("2. Edit Cart");
            System.out.println("3. Clear Cart");
            System.out.println("4. Cancel");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    placeOrder();
                    return;
                }
                case 2 -> editCart();
                case 3 -> clearCart();
                case 4 -> {
                    System.out.println("Cart preserved. You can checkout later.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ─── Edit Cart ───────────────────────────────────────────────
    private void editCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        viewCart();
        System.out.print("\nEnter S.No to edit (or 0 to go back): ");

        int sno;
        try {
            sno = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (sno == 0) return;
        if (sno < 1 || sno > cart.size()) {
            System.out.println("Invalid S.No.");
            return;
        }

        CartItemDTO item = cart.get(sno - 1);

        System.out.println("\n1. Change Quantity");
        System.out.println("2. Remove Item");
        System.out.print("Enter choice: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        switch (choice) {
            case 1 -> {
                System.out.print("Enter new quantity: ");
                try {
                    int newQty = Integer.parseInt(scanner.nextLine().trim());
                    if (newQty <= 0) {
                        System.out.println("Quantity must be positive.");
                        return;
                    }
                    if (foodItemDao.hasEnoughStock(item.getFoodItemId(), newQty)) {
                        item.setQuantity(newQty);
                        System.out.println("✔ Quantity updated.");
                        viewCart();
                    } else {
                        FoodItemDTO foodItem = foodItemDao.getFoodItemById(item.getFoodItemId());
                        System.out.println(" Not enough stock. Available: " + foodItem.getQuantity());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity.");
                }
            }
            case 2 -> {
                cart.remove(sno - 1);
                System.out.println(" Item removed from cart.");
                if (!cart.isEmpty()) {
                    viewCart();
                } else {
                    System.out.println("Your cart is now empty.");
                }
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    // ─── Clear Cart ──────────────────────────────────────────────
    private void clearCart() {
        System.out.print("Clear all items from cart? (yes/no): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("yes")) {
            cart.clear();
            System.out.println(" Cart cleared.");
        }
    }

    // ─── Place Order
    private void placeOrder() {
        if (cart.isEmpty()) {
            System.out.println(" Cart is empty. Cannot place order.");
            return;
        }

        // Final stock check before placing order
        for (CartItemDTO item : cart) {
            if (!foodItemDao.hasEnoughStock(item.getFoodItemId(), item.getQuantity())) {
                FoodItemDTO foodItem = foodItemDao.getFoodItemById(item.getFoodItemId());
                System.out.println(" Stock changed! " + item.getFoodItemName() + " only has " + foodItem.getQuantity() + " left.");
                System.out.println("Please update your cart.");
                return;
            }
        }

        System.out.println("\n=== DELIVERY DETAILS ===");
        System.out.print("Email        : ");
        String email = scanner.nextLine().trim();

        System.out.print("Phone Number : ");
        String phone = scanner.nextLine().trim();

        System.out.print("Location     : ");
        String location = scanner.nextLine().trim();

        System.out.printf("\n=== ORDER SUMMARY ===%n");
        viewCart();
        System.out.println("\nDelivery Details:");
        System.out.println("  Email    : " + email);
        System.out.println("  Phone    : " + phone);
        System.out.println("  Location : " + location);

        System.out.print("\nConfirm order? (yes/no): ");
        String confirm = scanner.nextLine().trim();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Order cancelled.");
            return;
        }

        // Create order
        int orderId = orderDao.createOrder(currentUserId, OrderStatus.PLACED_ORDER);

        if (orderId == -1) {
            System.out.println(" Failed to create order.");
            return;
        }

        // Add order items
        for (CartItemDTO item : cart) {
            orderItemDao.addOrderItem(orderId, item.getFoodItemId(), item.getPrice(), item.getQuantity());
            // Reduce stock
            foodItemDao.reduceStock(item.getFoodItemId(), item.getQuantity());
        }

        // Save delivery details
        deliveryDetailDao.addDeliveryDetail(currentUserId, email, phone, location);

        System.out.println("\n Order placed successfully! (Order ID: " + orderId + ")");
        System.out.println(" Confirmation sent to " + email);

        // Clear cart
        cart.clear();
    }

    //  View My Orders
    public void viewMyOrders() {
        List<OrderSummaryDTO> orders = orderDao.getOrderSummariesByUserId(currentUserId);

        if (orders.isEmpty()) {
            System.out.println("\n No orders found.");
            return;
        }

        System.out.println("\n=== MY ORDERS ===");
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.printf("%-10s | %-20s | %-12s | %s%n", "Order ID", "Status", "Total", "Date");
        System.out.println("──────────────────────────────────────────────────────────────");

        for (OrderSummaryDTO order : orders) {
            System.out.printf("%-10d | %-20s | ₹%-11.2f | %s%n",
                    order.getOrderId(),
                    order.getStatus(),
                    order.getTotalPrice(),
                    order.getCreatedOn());
        }
        System.out.println("──────────────────────────────────────────────────────────────");
    }

    // View Order Details
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
        if (order == null || order.getUserId() != currentUserId) {
            System.out.println(" Order not found.");
            return;
        }

        List<OrderItemDao.OrderItemWithName> items = orderItemDao.getItemsWithNamesByOrderId(orderId);
        double total = orderDao.getTotalPrice(orderId);

        System.out.println("\n=== ORDER #" + orderId + " ===");
        System.out.println("Status: " + order.getStatus());
        System.out.println("Date  : " + order.getCreatedOn());
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

    //  Cancel Order
    public void cancelOrder() {
        System.out.print("\nEnter Order ID to cancel: ");
        int orderId;
        try {
            orderId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Order ID.");
            return;
        }

        Order order = orderDao.getOrderById(orderId);
        if (order == null || order.getUserId() != currentUserId) {
            System.out.println(" Order not found.");
            return;
        }

        if (!order.getStatus().isCancellable()) {
            System.out.println(" Cannot cancel order.");
            System.out.println("   Reason: Order status is " + order.getStatus());
            System.out.println("   Only orders with status PLACED_ORDER can be cancelled.");
            return;
        }

        System.out.print("⚠ Cancel order #" + orderId + "? (yes/no): ");
        String confirm = scanner.nextLine().trim();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Cancellation aborted.");
            return;
        }

        // Get order items to restore stock
        List<OrderItem> items = orderItemDao.getItemsByOrderId(orderId);

        // Update status
        orderDao.updateOrderStatus(orderId, OrderStatus.ORDER_CANCELLED);

        // Restore stock
        for (OrderItem item : items) {
            foodItemDao.restoreStock(item.getFoodItemId(), item.getQuantity());
        }

        System.out.println("✔ Order #" + orderId + " cancelled successfully!");
        System.out.println("✔ Stock restored.");
    }



    private CartItemDTO findInCart(int foodItemId) {
        for (CartItemDTO item : cart) {
            if (item.getFoodItemId() == foodItemId) {
                return item;
            }
        }
        return null;
    }

    private double getCartTotal() {
        double total = 0;
        for (CartItemDTO item : cart) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public boolean hasItemsInCart() {
        return !cart.isEmpty();
    }
}