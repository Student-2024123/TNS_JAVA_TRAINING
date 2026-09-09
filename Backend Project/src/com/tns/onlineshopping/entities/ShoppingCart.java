package com.tns.onlineshopping.entities;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> items;

    public ShoppingCart() {
        items = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    @Override
    public String toString() {
        if (items.isEmpty())
            return "Shopping cart is empty.";
        StringBuilder result = new StringBuilder("Shopping Cart:\n");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            result.append(" ").append(entry.getKey().getName()).append(": ")
                    .append(entry.getValue()).append("\n");
        }
        return result.toString();
    }
}
