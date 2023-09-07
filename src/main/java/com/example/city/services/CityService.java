package com.example.city.services;

import com.example.city.models.City;

import java.util.List;

public interface CityService {
    City findById(int id);
    List<City> findAll();
    boolean deleteById(int id);
    boolean deleteAllById(Iterable<Integer> ids);
    City insert(City city);
    List<City> insertMultiple(Iterable<City> cities);
    City update(City city);
}
