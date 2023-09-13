package com.example.httpclientdemo.models;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddressDTO {
    @Id
    private int id;
    private int houseNo;
    private String street;
}
