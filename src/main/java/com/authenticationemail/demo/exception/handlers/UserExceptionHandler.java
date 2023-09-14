package com.authenticationemail.demo.exception.handlers;

import com.authenticationemail.demo.exception.ApplicationRuntimeException;
import com.authenticationemail.demo.models.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@SuppressWarnings("unused")
@ControllerAdvice
public class UserExceptionHandler {
    final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = ApplicationRuntimeException.class)
    public ResponseEntity<ResponseDTO<Object>> exception(ApplicationRuntimeException e){
        log.error(e.toString());

        String message = messageSource.getMessage(e.getMessage(),null, Locale.getDefault());

        ResponseDTO<Object> res = ResponseDTO.builder()
                .status(false)
                .message(message)
                .build();

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
