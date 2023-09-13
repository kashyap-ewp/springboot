package com.example.httpclientdemo.services;

import com.example.httpclientdemo.models.Product;

import java.util.List;

public interface ProductService {
    Product findById(int id);
    List<Product> findAll();
    boolean deleteById(int id);
    boolean deleteAllById(Iterable<Integer> ids);
    Product insert(Product product);
    List<Product> insertMultiple(Iterable<Product> products);
    Product update(Product product);
}
