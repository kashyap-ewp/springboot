package com.example.city.controllers;

import com.example.city.mapper.CityMapper;
import com.example.city.models.City;
import com.example.city.models.CityDTO;
import com.example.city.models.ResponseDto;
import com.example.city.services.CityService;
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
    CityMapper cityMapper;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto> insert(@RequestBody CityDTO cityDTO)
    {
        City c = cityService.insert(cityMapper.toCity(cityDTO));
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
            res.setData(cityMapper.toCityDTO(c));

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updated(@RequestBody CityDTO cityDTO)
    {
        City c = cityService.insert(cityMapper.toCity(cityDTO));
        ResponseDto res = new ResponseDto();

        if(c.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Id does not exists");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Data Saved");
            res.setData(cityMapper.toCityDTO(c));

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @PostMapping("/insertAll")
    public ResponseEntity<ResponseDto> insertAll(@RequestBody List<CityDTO> cityDTOs)
    {
        List<City> cities = cityService.insertMultiple(cityMapper.toCities(cityDTOs));
        ResponseDto res = new ResponseDto();

        if(cities.isEmpty())
        {
            res.setStatus(false);
            res.setMessage("Data not Saved, Please Enter Unique City Name");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Data Saved");
            res.setData(cityMapper.toCityDTOs(cities));

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @GetMapping("/findAll")
    public ResponseEntity<ResponseDto> findAll()
    {
        List<City> cities = cityService.findAll();
        ResponseDto res = new ResponseDto();

        if(cities.isEmpty())
        {
            res.setStatus(false);
            res.setMessage("No Data");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setData(cityMapper.toCityDTOs(cities));

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable int id)
    {
        City city = cityService.findById(id);
        ResponseDto res = new ResponseDto();

        if(city.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Id not found");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setData(cityMapper.toCityDTO(city));

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int id)
    {
        boolean isDeleted = cityService.deleteById(id);
        ResponseDto res = new ResponseDto();

        if(!isDeleted)
        {
            res.setStatus(false);
            res.setMessage("Data not deleted");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setMessage("Data deleted");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    @DeleteMapping("/deleteAllById")
    public ResponseEntity<ResponseDto> deleteAllById(@RequestBody List<Integer> ids)
    {
        boolean isDeleted = cityService.deleteAllById(ids);
        ResponseDto res = new ResponseDto();

        if(!isDeleted)
        {
            res.setStatus(false);
            res.setMessage("Data not deleted");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setMessage("Data deleted");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}
