package com.example.demo.services;

import com.example.demo.models.User;

import java.util.List;

public interface UserService {
    User findById(int id);
    List<User> findAll();
    void deleteById(int id);
    void deleteAllById(Iterable<Integer> ids);
    User insert(User user);
    List<User> insertMultiple(Iterable<User> users);
    User update(User user);
}
