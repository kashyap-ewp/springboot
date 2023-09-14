package com.authenticationemail.demo.exception.handlers;

import com.authenticationemail.demo.models.ResponseDTO;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@SuppressWarnings("unused")
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        log.error(ex.toString());

        FieldError fieldError = ex.getBindingResult().getFieldError();

        assert fieldError != null;
        String message = messageSource.getMessage(fieldError, Locale.getDefault());

        ResponseDTO<Object> resBody = ResponseDTO.builder()
                .status(false)
                .message(message)
                .build();

        return ResponseEntity.badRequest().body(resBody);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(Exception ex) {
        log.error(ex.getMessage(), ex);

        String message = messageSource.getMessage(ex.getMessage(),null, Locale.getDefault());

        ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                .status(false)
                .message(message).build();

        return ResponseEntity.badRequest().body(responseDTO);
    }
}
