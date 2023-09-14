package com.authenticationemail.demo.controllers;

import com.authenticationemail.demo.models.ResponseDTO;
import com.authenticationemail.demo.models.UserDTO;
import com.authenticationemail.demo.services.UserService;
import com.authenticationemail.demo.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
public class VerificationController {
    @Autowired
    private UserService userService;
    @Autowired
    private VerificationTokenService tokenService;
    @GetMapping("/email")
    public ResponseEntity<ResponseDTO<Object>> sendVerificationEmail()
    {
        UserDetails userDetails = userService.getLoggedInUser();
        UserDTO user = userService.findByUsername(userDetails.getUsername());

        tokenService.sendVerificationToken(user);

        ResponseDTO<Object> res = ResponseDTO.builder()
                .status(true)
                .message("success.verification.email.sent")
                .build();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/token/{token}")
    public ResponseEntity<ResponseDTO<Object>> verifyToken(@PathVariable String token)
    {
        tokenService.verifyToken(token);

        ResponseDTO<Object> res = ResponseDTO.builder()
                .status(true)
                .message("success.email.verified")
                .build();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
