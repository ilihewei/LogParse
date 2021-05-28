package com.example.logparsing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class LogParsingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogParsingApplication.class, args);
    }

}
