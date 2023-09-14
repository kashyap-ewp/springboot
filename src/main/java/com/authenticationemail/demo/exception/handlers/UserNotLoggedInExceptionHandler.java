package com.authenticationemail.demo.exception.handlers;

import com.authenticationemail.demo.exception.UserNotLoggedInException;
import com.authenticationemail.demo.models.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@SuppressWarnings("unused")
@ControllerAdvice
public class UserNotLoggedInExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String LOGIN_ENDPOINT = "/login";
    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(value = UserNotLoggedInException.class)
    public ResponseEntity<ResponseDTO<Object>> exception(UserNotLoggedInException e) {
        log.error(e.toString());
        String uri =  request.getServerName() + LOGIN_ENDPOINT;

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(uri)).build();
    }
}
