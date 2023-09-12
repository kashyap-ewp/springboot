package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String homeTown;
    private String gender;
    private String about;
    private String hobbies;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    private List<Address> address;
}
