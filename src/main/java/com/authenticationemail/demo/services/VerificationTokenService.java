package com.authenticationemail.demo.services;

import com.authenticationemail.demo.models.UserDTO;

public interface VerificationTokenService {
    void sendVerificationToken(UserDTO user);
    void verifyToken(String token);
}
