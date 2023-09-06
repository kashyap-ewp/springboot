package com.queries.controllers;

import com.queries.models.City;
import com.queries.models.ResponseDto;
import com.queries.repositories.CityRepository;
import com.queries.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    CityService cityService;
    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getCityById(@PathVariable int id)
    {
        City c = cityService.findById(id);
        ResponseDto res = new ResponseDto();

        if(c.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Id does not exists");

        }
        else {
            res.setStatus(true);
            res.setData(c);

        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseDto> getCityByName(@PathVariable  String name)
    {
        City c = cityService.findByName(name);
        ResponseDto res = new ResponseDto();

        if(c.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Name does not exists");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setData(c);

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @GetMapping("/all/{name}")
    public ResponseEntity<ResponseDto> getAllCitiesByName(@PathVariable String name)
    {
        List<City> cities = cityService.findAllByName(name);
        ResponseDto res = new ResponseDto();

        if(cities.isEmpty())
        {
            res.setStatus(false);
            res.setMessage("No Data");

        }
        else {
            res.setStatus(true);
            res.setData(cities);

        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
