package com.example.redact;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class RedactController {
    @RequestMapping("/redact") // applied at a controller or its methods to indicate how to access it/them
                               // through HTTP requests
    public String redactText(@RequestParam String input) {

        String redactedText = input;

        // Redact names
        Pattern namePattern = Pattern.compile("\\b([A-Z][a-zA-Z]*)\\b");
        Matcher nameMatcher = namePattern.matcher(redactedText);
        redactedText = nameMatcher.replaceAll("xxx");

        // Redact email addresses
        Pattern emailPattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");
        Matcher emailMatcher = emailPattern.matcher(redactedText);
        redactedText = emailMatcher.replaceAll("xxx");

        // Redact street addresses
        Pattern addressPattern = Pattern.compile("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
        Matcher addressMatcher = addressPattern.matcher(redactedText);
        redactedText = addressMatcher.replaceAll("xxx");

        return redactedText;
    }
}
