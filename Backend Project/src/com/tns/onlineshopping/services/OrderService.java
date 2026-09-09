package com.tns.onlineshopping.services;

import com.tns.onlineshopping.entities.Order;
import com.tns.onlineshopping.entities.ProductQuantityPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderService {
    private List<Order> orderList = new ArrayList<>();
    private String lastMessage = "";
    private static final Set<String> VALID_STATUSES = Set.of("Pending", "Completed", "Delivered", "Cancelled");

    public void placeOrder(Order order) {
        orderList.add(order);
    }

    public Order getOrder(int orderId) {
        return orderList.stream().filter(o -> o.getOrderId() == orderId).findFirst().orElse(null);
    }

    public List<Order> getOrders() {
        return orderList;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public boolean updateOrderStatus(int orderId, String status) {
        Order order = getOrder(orderId);
        if (order == null)
            return fail("Invalid order ID.");
        if (!VALID_STATUSES.contains(status))
            return fail("Invalid status. Use Pending, Completed, Delivered, or Cancelled.");
        String current = order.getStatus();
        if (current.equals(status))
            return fail("Order already has status " + status + ".");
        if (current.equals("Cancelled") || current.equals("Delivered"))
            return fail("A " + current + " order cannot be changed.");
        if (status.equals("Completed")) {
            if (!current.equals("Pending"))
                return fail("Only Pending orders can be completed.");
            for (ProductQuantityPair pair : order.getProducts()) {
                if (pair.getProduct().getStockQuantity() < pair.getQuantity())
                    return fail("Insufficient stock for product: " + pair.getProduct().getName());
            }
            for (ProductQuantityPair pair : order.getProducts())
                pair.getProduct().setStockQuantity(pair.getProduct().getStockQuantity() - pair.getQuantity());
        } else if (status.equals("Delivered")) {
            if (!current.equals("Completed"))
                return fail("Only Completed orders can be delivered.");
        } else if (status.equals("Cancelled")) {
            if (current.equals("Completed")) {
                for (ProductQuantityPair pair : order.getProducts())
                    pair.getProduct().setStockQuantity(pair.getProduct().getStockQuantity() + pair.getQuantity());
            }
        } else {
            return fail("Status transition is not allowed.");
        }
        order.setStatus(status);
        lastMessage = "Order status updated successfully!";
        return true;
    }

    private boolean fail(String message) {
        lastMessage = message;
        return false;
    }
}
