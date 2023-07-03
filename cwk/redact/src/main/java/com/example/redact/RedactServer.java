package com.example.redact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RedactServer {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "redact-server");
        SpringApplication.run(RedactServer.class, args);
    }
}
