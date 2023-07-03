package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
public class WebServer {

    public static final String PDF_SERVICE_URL = "http://pdf-server";
    public static final String REDACT_SERVICE_URL = "http://redact-server";
    @Autowired
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "web-server");
        applicationContext = SpringApplication.run(WebServer.class, args);

        displayAllBeans();
    }

    public static void displayAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : allBeanNames) {
            System.out.println(beanName);
        }
        System.out.println(applicationContext.containsBeanDefinition("RedactServer"));
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebRedactService redactService() {
        return new WebRedactService(REDACT_SERVICE_URL);
    }

    @Bean
    public WebPdfService pdfService() {
        return new WebPdfService(PDF_SERVICE_URL);
    }

    @Bean
    public WebRedactController redactController() {
        return new WebRedactController(redactService(), pdfService());
    }

    @Bean
    public HomeController homeController() {
        return new HomeController();
    }

}
