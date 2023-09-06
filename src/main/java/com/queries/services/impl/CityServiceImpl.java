package com.queries.services.impl;

import com.queries.models.City;
import com.queries.repositories.CityRepository;
import com.queries.services.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private static final Logger log = LogManager.getLogger(CityServiceImpl.class);
    @Autowired
    CityRepository cityRepository;

    @Override
    public City findById(int id) {
        try{
            City city = cityRepository.findByIdC(id);
            if(city!= null) return city;
        }
        catch (Exception e){
            log.error(e.toString());
        }
        return new City();
    }

    @Override
    public City findByName(String name) {
        try{
            City city = cityRepository.findByNameC(name);
            if(city!= null) return city;
        }
        catch (Exception e){
            log.error(e.toString());
        }
        return new City();
    }

    @Override
    public List<City> findAllByName(String like) {
        try{
            List<City> cities = new ArrayList<>();
            cityRepository.findAllByNameC(like).forEach(b -> cities.add(b));
            return cities;
        }
        catch (Exception e){
            log.error(e.toString());
            return new ArrayList<>();
        }
    }
}
