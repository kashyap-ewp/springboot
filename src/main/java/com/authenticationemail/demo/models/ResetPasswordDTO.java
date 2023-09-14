package com.authenticationemail.demo.models;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResetPasswordDTO {
    @Id
    private int id;
    private String username;
    private String password;
    private String token;
}
