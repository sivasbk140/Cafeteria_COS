package net.breezeware.order.service;

import net.breezeware.order.dao.*;
import net.breezeware.order.entity.*;
import net.breezeware.user.dao.DeliveryDetailDao;
import net.breezeware.user.entity.DeliveryDetail;

import java.sql.SQLException;
import java.util.List;

public class OrderService {

    private final OrderDao orderDao = new OrderDao();
    private final OrderItemdao orderItemDao = new OrderItemdao();
    private final DeliveryDetailDao deliveryDetailDao =
            new DeliveryDetailDao();
    private final OrderDeliveryMapDao orderDeliveryMapDao =
            new OrderDeliveryMapDao();

    public int createOrder(int userId) throws SQLException {

        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.PLACED_ORDER);

        return orderDao.create(order);
    }

    public void addItemToOrder(int orderId,
                               int foodMenuItemId,
                               int quantity,
                               double price) throws SQLException {

        OrderItem item = new OrderItem();
        item.setOrderId(orderId);
        item.setFoodMenuItemId(foodMenuItemId);
        item.setQuantity(quantity);
        item.setPrice(price);

        orderItemDao.create(item);
    }

    public List<OrderItem> viewOrderItems(int orderId)
            throws SQLException {

        return orderItemDao.findByOrderId(orderId);
    }

    public void placeOrder(int orderId,
                           DeliveryDetail detail)
            throws SQLException {

        int deliveryId = deliveryDetailDao.create(detail);
        orderDeliveryMapDao.create(orderId, deliveryId);

        orderDao.updateStatus(orderId, OrderStatus.PLACED_ORDER);
    }

    public void cancelOrder(int orderId)
            throws SQLException {

        orderDao.updateStatus(orderId,
                OrderStatus.ORDER_CANCELLED);
    }
}
