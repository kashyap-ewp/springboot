package com.example.city.services.impl;

import com.example.city.models.City;
import com.example.city.repositories.CityRepository;
import com.example.city.services.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CityServiceImpl implements CityService {
    private static final Logger log = LogManager.getLogger(CityServiceImpl.class);
    @Autowired
    CityRepository cityRepository;

    @Override
    public City findById(int id) {
        try{
            Optional<City> city = cityRepository.findById(id);
            if(city.isPresent()) return city.get();
        }
        catch (Exception e){
            log.error(e.toString());
        }
        return new City();
    }

    @Override
    public List<City> findAll() {
        try{
            List<City> cities = new ArrayList<>();
            cityRepository.findAll().forEach(b -> cities.add(b));
            return cities;
        }
        catch (Exception e){
            log.error(e.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean deleteById(int id) {
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

    @Override
    public boolean deleteAllById(Iterable<Integer> ids) {
        try{
            cityRepository.deleteAllById(ids);
            return true;
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
            return false;
        }
    }

    @Override
    public City insert(City city) {
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
    public List<City> insertMultiple(Iterable<City> cities) {
        try
        {
            return cityRepository.saveAll(cities);
        }
        catch (DataIntegrityViolationException dive)
        {
            log.info(dive.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public City update(City city) {
        try
        {
            if(cityRepository.existsById(city.getId())) return cityRepository.save(city);
        }
        catch (DataIntegrityViolationException dive)
        {
            log.info(dive.toString());
        }
        return new City();
    }
}
