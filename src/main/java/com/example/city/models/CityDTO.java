package com.example.city.models;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CityDTO {
    @Id
    private int id;
    private String name;
}
