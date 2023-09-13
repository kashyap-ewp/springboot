package com.example.httpclientdemo.models;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class UserDTO {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String homeTown;
    private String gender;
    private String about;
    private String hobbies;
    private List<AddressDTO> address;
}
