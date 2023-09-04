package com.example.city.services.impl;

import com.example.city.models.City;
import com.example.city.repositories.CityRepository;
import com.example.city.services.CityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private static final Logger log = LogManager.getLogger(CityServiceImpl.class);
    @Autowired
    CityRepository cityRepository;
    @Override
    public List<City> getCities() {
        try{
            List<City> cities = new ArrayList<>();
            cityRepository.findAll().forEach(b -> cities.add(b));
            Collections.sort(cities,Collections.reverseOrder());
            return cities;
        }
        catch (Exception e){
            log.error(e.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public City addOrUpdateCity(City city) {
        try
        {
            return cityRepository.save(city);
        }
        catch (DataIntegrityViolationException dive)
        {
            log.info(dive.toString());
            return new City();
        }
    }

    @Override
    public boolean deleteCity(int id) {
        try{
            cityRepository.deleteById(id);
            return true;
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
            return false;
        }
    }
}
