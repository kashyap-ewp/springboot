package com.example.demo;

import com.example.demo.utils.ApplicationProperties;
import com.example.demo.utils.NestedProperties;
import com.example.demo.utils.SimpleProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		System.out.println(ApplicationProperties.INSTANCE.getAppName());
	}

	@Bean
	public CommandLineRunner runner(SimpleProperties simpleProperties, NestedProperties nestedProperties) {
		return r -> {
			log.info(simpleProperties.getA());
			log.info(nestedProperties.getA().getB());
		};
	}

}
