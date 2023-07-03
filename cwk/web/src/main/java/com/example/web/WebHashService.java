package com.example.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebHashService {
    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl;
    protected Logger logger = Logger.getLogger(WebHashService.class.getName());

    public WebHashService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public String hash(String input) {
        return restTemplate.getForObject("http://localhost:3344/hash?input={input}", String.class, input);
    }
}
