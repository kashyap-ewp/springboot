package com.oauth2.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class OAuth2Controller {
    @GetMapping("/")
    public OAuth2User user(@AuthenticationPrincipal OAuth2User principal) {
        return principal;
    }
    @GetMapping("/oauth2/authorization/linkedin")
    public String linkedin(@RequestParam String code){
        return code;
    }
}