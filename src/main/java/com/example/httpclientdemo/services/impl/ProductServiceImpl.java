package com.example.httpclientdemo.services.impl;

import com.example.httpclientdemo.models.Product;
import com.example.httpclientdemo.repositories.ProductRepository;
import com.example.httpclientdemo.services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LogManager.getLogger(ProductServiceImpl.class);
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findById(int id) {
        try{
            Optional<Product> city = productRepository.findById(id);
            if(city.isPresent()) return city.get();
        }
        catch (Exception e){
            log.error(e.toString());
        }
        return new Product();
    }

    @Override
    public List<Product> findAll() {
        try{
            List<Product> products = new ArrayList<>();
            productRepository.findAll().forEach(b -> products.add(b));
            return products;
        }
        catch (Exception e){
            log.error(e.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean deleteById(int id) {
        try{
            productRepository.deleteById(id);
            return true;
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
            return false;
        }
    }

    @Override
    public boolean deleteAllById(Iterable<Integer> ids) {
        try{
            productRepository.deleteAllById(ids);
            return true;
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
            return false;
        }
    }

    @Override
    public Product insert(Product product) {
        try
        {
            return productRepository.save(product);
        }
        catch (DataIntegrityViolationException dive)
        {
            log.info(dive.toString());
            return new Product();
        }
    }

    @Override
    public List<Product> insertMultiple(Iterable<Product> products) {
        try
        {
            return productRepository.saveAll(products);
        }
        catch (DataIntegrityViolationException dive)
        {
            log.info(dive.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public Product update(Product product) {
        try
        {
            if(productRepository.existsById(product.getId())) return productRepository.save(product);
        }
        catch (DataIntegrityViolationException dive)
        {
            log.info(dive.toString());
        }
        return new Product();
    }
}
