package com.authenticationemail.demo.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class UriUtils {
    static final Logger log = LoggerFactory.getLogger(UriUtils.class);
    @Autowired
    private HttpServletRequest request;
    public String getBaseUri(){
        try{
            return ServletUriComponentsBuilder.fromRequestUri(request)
                    .replacePath(null)
                    .build()
                    .toUriString();
        }
        catch (Exception e){
            log.error(e.toString());
            throw new RuntimeException();
        }

    }
}
