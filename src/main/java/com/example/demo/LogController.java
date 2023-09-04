package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogController {
    @GetMapping("/log")
    public void log()
    {
        log.info("Entered Log");
        log.trace("Trace");
        log.debug("debug");
        log.error("No Error");
        log.warn("No Warnings");
    }
}
