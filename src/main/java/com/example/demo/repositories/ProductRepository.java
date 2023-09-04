package com.example.demo.repositories;

import com.example.demo.models.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepository {
    public Optional<Product> findById(Long id) {
        return Optional.of(new Product());
    }
}
