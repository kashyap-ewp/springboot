package com.authenticationemail.demo.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class VerificationTokenDTO {
    @Id
    private Long id;
    private String token;
    private String status;
    private LocalDateTime expiredDateTime;
    private LocalDateTime issuedDateTime;
    private LocalDateTime confirmedDateTime;
    private UserDTO user;
}
