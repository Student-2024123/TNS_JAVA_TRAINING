package com.tns.onlineshopping.services;

import com.tns.onlineshopping.entities.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> productList = new ArrayList<>();

    public boolean addProduct(Product product) {
        if (product.getProductId() <= 0 || product.getPrice() < 0 || product.getStockQuantity() <= 0
                || getProductById(product.getProductId()) != null)
            return false;
        productList.add(product);
        return true;
    }

    public boolean removeProduct(int productId) {
        return productList.removeIf(p -> p.getProductId() == productId);
    }

    public List<Product> getProducts() {
        return productList;
    }

    public Product getProductById(int productId) {
        return productList.stream().filter(p -> p.getProductId() == productId).findFirst().orElse(null);
    }

    public boolean updateProduct(int productId, String name, double price, int stockQuantity) {
        Product product = getProductById(productId);
        if (product == null || name == null || name.trim().isEmpty() || price < 0 || stockQuantity <= 0)
            return false;
        product.setName(name);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        return true;
    }
}
