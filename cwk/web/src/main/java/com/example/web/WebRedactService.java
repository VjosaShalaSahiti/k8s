package com.example.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebRedactService {
    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl;
    protected Logger logger = Logger.getLogger(WebRedactService.class.getName());

    public WebRedactService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public String redact(String input) {
        return restTemplate.getForObject("http://localhost:2222/redact?input={input}", String.class, input);
    }

}
