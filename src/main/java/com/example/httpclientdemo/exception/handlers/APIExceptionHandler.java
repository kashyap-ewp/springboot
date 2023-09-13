package com.example.httpclientdemo.exception.handlers;

import com.example.httpclientdemo.models.ResponseDTO;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    Logger log = LoggerFactory.getLogger(APIExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        log.error(ex.toString());

        FieldError fieldError = ex.getBindingResult().getFieldError();

        String message = fieldError.getField() + " : " + fieldError.getDefaultMessage();
        ResponseDTO resBody = ResponseDTO.builder()
                .status(false)
                .message(message)
                .build();

        return ResponseEntity.badRequest().body(resBody);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);

        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(false)
                .message(ex.getMessage()).build();

        return ResponseEntity.badRequest().body(responseDTO);
    }
}
