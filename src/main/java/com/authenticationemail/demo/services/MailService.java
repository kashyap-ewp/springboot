package com.authenticationemail.demo.services;

import com.authenticationemail.demo.models.VerificationTokenDTO;

public interface MailService {
    void sendEmailVerificationMail(VerificationTokenDTO tokenDTO);
    void sendPasswordVerificationMail(VerificationTokenDTO resetPasswordTokenDTO);
}
