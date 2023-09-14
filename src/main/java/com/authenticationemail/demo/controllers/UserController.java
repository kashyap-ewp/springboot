package com.authenticationemail.demo.controllers;

import com.authenticationemail.demo.models.ResetPasswordDTO;
import com.authenticationemail.demo.models.ResponseDTO;
import com.authenticationemail.demo.models.UserDTO;
import com.authenticationemail.demo.services.PasswordService;
import com.authenticationemail.demo.services.UserService;
import com.authenticationemail.demo.services.VerificationTokenService;
import com.authenticationemail.demo.services.auth.SecurityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    VerificationTokenService verificationTokenService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private PasswordService passwordService;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseDTO<Object>> signUp(@RequestBody @Valid UserDTO userDTO){
        UserDTO userDTOSaved = userService.save(userDTO);

        securityService.autoLogin(userDTOSaved.getUsername(),userDTO.getPassword());

        verificationTokenService.sendVerificationToken(userDTOSaved);

        ResponseDTO<Object> res = ResponseDTO.builder()
                .status(true)
                .data(userDTOSaved)
                .message("success.verification.email.sent")
                .build();

         return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/resetPassword/{username}")
    public ResponseEntity<ResponseDTO<Object>> sendResetPasswordToken(@PathVariable String username){
        passwordService.sendResetPasswordToken(username);

        ResponseDTO<Object> res = ResponseDTO.builder()
                .status(true)
                .message("success.verification.email.sent")
                .build();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<ResponseDTO<Object>> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
        passwordService.resetPassword(resetPasswordDTO);

        ResponseDTO<Object> res = ResponseDTO.builder()
                .status(true)
                .message("success.password.changed")
                .build();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
