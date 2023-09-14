package com.authenticationemail.demo.models;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("unused")
@Data
@NoArgsConstructor
public class RoleDTO {
    @Id
    private int id;
    private String name;

}
