package com.example.many.service;

import java.util.List;

import com.example.many.entity.Product;

public interface ProductService {
    public Product saveProduct(Product product);

    public List<Product> getAllProducts();
}
