package com.authenticationemail.demo.exception;

@SuppressWarnings("unused")
public class ApplicationRuntimeException extends RuntimeException{
    public ApplicationRuntimeException(String message){
        super(message);
    }
}
