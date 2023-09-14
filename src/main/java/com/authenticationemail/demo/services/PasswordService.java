package com.authenticationemail.demo.services;

import com.authenticationemail.demo.models.ResetPasswordDTO;

public interface PasswordService {
    void sendResetPasswordToken(String username);
    void resetPassword(ResetPasswordDTO resetPasswordDTO);
}
