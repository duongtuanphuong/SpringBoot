package com.example.many.service.impl;

import java.util.List;

import com.example.many.entity.Product;
import com.example.many.repository.ProductRepository;
import com.example.many.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        // TODO Auto-generated method stub
       return productRepository.save(product);
        
    }

    @Override
    public List<Product> getAllProducts() {
        // TODO Auto-generated method stub
        return productRepository.findAll();
    }
    
}
