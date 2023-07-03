package com.example.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class WebPdfService {

    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl;
    protected Logger logger = Logger.getLogger(WebPdfService.class.getName());

    public WebPdfService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public ResponseEntity<byte[]> generatePdf(String input, String res) {
        ResponseEntity<byte[]> response = restTemplate.getForEntity("http://localhost:3333/pdf?input={input}&res={res}",
                byte[].class, input, res);
        return response;
    }

    public ResponseEntity<byte[]> generatePdfs(String input, String res) {
        ResponseEntity<byte[]> response = restTemplate.getForEntity("http://localhost:3333/pdf?input={input}&res={res}",
                byte[].class, input, res);
        return response;
    }

}
