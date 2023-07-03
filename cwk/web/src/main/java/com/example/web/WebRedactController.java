package com.example.web;

import java.util.logging.Logger;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class WebRedactController {

    protected WebRedactService redactService;
    protected WebPdfService pdfService;

    protected Logger logger = Logger.getLogger(WebRedactController.class.getName());

    public WebRedactController(WebRedactService redactService, WebPdfService pdfService) {
        this.redactService = redactService;
        this.pdfService = pdfService;
    }

    @RequestMapping("/redact")
    public String redactText(@RequestParam String input, Model model) {
        String res = redactService.redact(input);
        logger.info("Reducted: " + res);
        model.addAttribute("input", input);
        model.addAttribute("res", res);

        // Build the redirect URL with URL-encoded query parameters
        String redirectUrl = UriComponentsBuilder.fromPath("/pdf")
                .queryParam("input", input)
                .queryParam("res", res)
                .toUriString();

        return "redirect:" + redirectUrl;
    }

    @GetMapping(value = "/pdf")
    public ResponseEntity<byte[]> generatePdfGet(@RequestParam(required = true) String input,
            @RequestParam(required = true) String res) {
        // Handle GET request for /pdf endpoint
        return pdfService.generatePdf(input, res);
    }

    @PostMapping(value = "/pdf")
    public ResponseEntity<byte[]> generatePdfPost(@RequestParam(required = true) String input,
            @RequestParam(required = true) String res) {
        // Handle POST request for /pdf endpoint
        return pdfService.generatePdf(input, res);
    }

}
