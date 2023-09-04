package com.example.city.services;

import com.example.city.models.City;

import java.util.List;

public interface CityService {
    List<City> getCities();

    City addOrUpdateCity(City city);

    boolean deleteCity(int id);
}
