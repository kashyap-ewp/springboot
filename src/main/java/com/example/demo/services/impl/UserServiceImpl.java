package com.example.demo.services.impl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository UserRepository;

    @Override
    public User findById(int id) {
        Optional<User> city = UserRepository.findById(id);
        if(city.isPresent()) return city.get();
        else throw new UserNotFoundException();
    }

    @Override
    public List<User> findAll() {
        List<User> products = new ArrayList<>();
        UserRepository.findAll().forEach(b -> products.add(b));
        if(!products.isEmpty())return products;
        throw new UserNotFoundException();
    }

    @Override
    public void deleteById(int id) {
        try{
            UserRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
            throw new UserNotFoundException();
        }
    }

    @Override
    public void deleteAllById(Iterable<Integer> ids) {
        try{
            UserRepository.deleteAllById(ids);
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
            throw new UserNotFoundException();
        }
    }

    @Override
    public User insert(User product) {
        return UserRepository.save(product);
    }

    @Override
    public List<User> insertMultiple(Iterable<User> products) {
        return UserRepository.saveAll(products);
    }

    @Override
    public User update(User product) {
        if(UserRepository.existsById(product.getId())) return UserRepository.save(product);
        else throw new UserNotFoundException();
    }
}
