package com.authenticationemail.demo.services;

import com.authenticationemail.demo.models.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("unused")
public interface UserService {
    UserDetails getLoggedInUser();
    UserDTO save(UserDTO userDTO);
    UserDTO findByUsername(String username);
}
