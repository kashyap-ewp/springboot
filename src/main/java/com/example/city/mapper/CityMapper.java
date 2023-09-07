package com.example.city.mapper;

import com.example.city.models.City;
import com.example.city.models.CityDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CityMapper {
    CityDTO toCityDTO(City city);

    List<City> toCities(List<CityDTO> cityDTOs);
    List<CityDTO> toCityDTOs(List<City> cities);

    City toCity(CityDTO cityDTO);
}
