package com.authenticationemail.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class UserDTO {
    @Id
    private int id;
    @Size(min = 6, message = "{validation.user.username.tooShort}")
    @Size(max = 32, message = "{validation.user.username.tooLong}")
    private String username;
    private String password;
    @NotNull(message = "{validation.user.email.null}")
    private String email;
    private Set<RoleDTO> roleDTOs;
}
