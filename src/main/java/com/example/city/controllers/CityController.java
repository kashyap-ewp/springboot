package com.example.city.controllers;

import com.example.city.models.City;
import com.example.city.models.ResponseDto;
import com.example.city.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CityController {
    @Autowired
    CityService cityService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> addBook(@RequestBody City city)
    {
        City c = cityService.addOrUpdateCity(city);
        ResponseDto res = new ResponseDto();

        if(c.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Data not Saved, Please Enter Unique City Name");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Data Saved");
            res.setData(c);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @GetMapping("/")
    public ResponseEntity<ResponseDto> getCities()
    {
        List<City> cities = cityService.getCities();
        ResponseDto res = new ResponseDto();

        if(cities.isEmpty())
        {
            res.setStatus(false);
            res.setMessage("No Data");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setData(cities);

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}
