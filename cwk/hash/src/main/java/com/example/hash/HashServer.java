package com.example.hash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HashServer {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "hash-server");
        SpringApplication.run(HashServer.class, args);
    }
}
