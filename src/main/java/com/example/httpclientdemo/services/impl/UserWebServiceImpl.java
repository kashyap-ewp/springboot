package com.example.httpclientdemo.services.impl;

import com.example.httpclientdemo.services.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class UserWebServiceImpl implements UserWebService {
    private static final String USERS_BASE_URL = "http://producer:8082/api";
    private static final String USERS_FIND_ALL_ENDPOINT = "/verify/token";
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String findAllRT() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(USERS_BASE_URL + USERS_FIND_ALL_ENDPOINT,
                HttpMethod.GET,entity,String.class).getBody();
    }

    @Override
    public Mono<String> findAllWC() {
        WebClient webClient = WebClient.create(USERS_BASE_URL);

        return webClient.get()
                .uri(u -> u.path(USERS_FIND_ALL_ENDPOINT)
                        .build()).exchangeToMono(response ->
                        response.bodyToMono(String.class));
    }
}
