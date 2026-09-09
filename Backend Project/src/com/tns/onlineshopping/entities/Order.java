package com.tns.onlineshopping.entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private List<ProductQuantityPair> products;
    private String status;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.status = "Pending";
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ProductQuantityPair> getProducts() {
        return products;
    }

    public void setProducts(List<ProductQuantityPair> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addProduct(Product product, int quantity) {
        for (ProductQuantityPair pair : products) {
            if (pair.getProduct().getProductId() == product.getProductId()) {
                pair.setQuantity(pair.getQuantity() + quantity);
                return;
            }
        }
        products.add(new ProductQuantityPair(product, quantity));
    }

    public void addProduct(ProductQuantityPair productQuantityPair) {
        products.add(productQuantityPair);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Order ID: ").append(orderId)
                .append(", Customer: ").append(customer.getUsername())
                .append(", Status: ").append(status);
        for (ProductQuantityPair pair : products)
            result.append("\n ").append(pair);
        return result.toString();
    }
}
