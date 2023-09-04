package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        Product product = Product.builder()
                .name("Product Name")
                .build();
        log.info("Request Received");
        return productRepository.findById(id);
    }
}

