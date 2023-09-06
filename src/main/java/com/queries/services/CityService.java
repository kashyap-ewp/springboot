package com.queries.services;

import com.queries.models.City;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityService {
    City findById(int id);
    City findByName(String name);
    List<City> findAllByName(String like);
}
