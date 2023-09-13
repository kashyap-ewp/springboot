package com.example.httpclientdemo.services;

import reactor.core.publisher.Mono;

public interface UserWebService {
    String findAllRT();
    Mono<String> findAllWC();
}
