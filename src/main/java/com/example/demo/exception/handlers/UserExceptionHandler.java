package com.example.demo.exception.handlers;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.models.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ResponseDTO> exception(UserNotFoundException e){
        ResponseDTO res = ResponseDTO.builder()
                .status(false)
                .message("User not found")
                .build();
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
