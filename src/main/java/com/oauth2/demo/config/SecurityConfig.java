package com.oauth2.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.DefaultMapOAuth2AccessTokenResponseConverter;
import org.springframework.security.oauth2.core.endpoint.DefaultOAuth2AccessTokenResponseMapConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.oauth2Login(oauth2 ->
                oauth2.tokenEndpoint(token ->
                        token.accessTokenResponseClient(linkedinTokenResponseClient())))
                .build();
    }

    private static DefaultAuthorizationCodeTokenResponseClient linkedinTokenResponseClient() {
        var defaultMapConverter = new DefaultMapOAuth2AccessTokenResponseConverter();
        Converter<Map<String, Object>, OAuth2AccessTokenResponse> linkedinMapConverter = tokenResponse -> {
            var withTokenType = new HashMap<>(tokenResponse);
            withTokenType.put(OAuth2ParameterNames.TOKEN_TYPE, OAuth2AccessToken.TokenType.BEARER.getValue());
            return defaultMapConverter.convert(withTokenType);
        };

        var httpConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
        httpConverter.setAccessTokenResponseConverter(linkedinMapConverter);

        var restOperations = new RestTemplate(List.of(new FormHttpMessageConverter(), httpConverter));
        restOperations.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        var client = new DefaultAuthorizationCodeTokenResponseClient();
        client.setRestOperations(restOperations);
        return client;
    }
}
