package com.example.httpclientdemo.exception.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class UserResponseErrorHandler implements ResponseErrorHandler {
    Logger log = LoggerFactory.getLogger(UserResponseErrorHandler.class);
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus status = (HttpStatus) response.getStatusCode();
        return status.is4xxClientError() || status.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String responseAsString = response.getBody().toString();
        log.error("ResponseBody: {}", responseAsString);

        throw new IOException(responseAsString);
    }
}
